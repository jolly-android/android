package com.phoenix.pillreminder.feature_alarms.presentation.viewmodels

import android.content.Context
import androidx.annotation.StringRes
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.phoenix.pillreminder.R
import com.phoenix.pillreminder.feature_alarms.data.preferences.UserPreferencesRepository
import com.phoenix.pillreminder.feature_alarms.domain.model.AlarmItem
import com.phoenix.pillreminder.feature_alarms.domain.model.Medicine
import com.phoenix.pillreminder.feature_alarms.domain.repository.AlarmScheduler
import com.phoenix.pillreminder.feature_alarms.domain.repository.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

private const val PILLBOX_REMINDER_WORK_NAME = "PillboxReminder"
private const val PILLBOX_NOTIFICATION_ID = 999

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val medicineRepository: MedicineRepository,
    private val alarmScheduler: AlarmScheduler,
    private val userPreferencesRepository: UserPreferencesRepository,
    @ApplicationContext private val appContext: Context,
    private val workManager: WorkManager
) : ViewModel() {

    private val selectedDate = MutableStateFlow(Calendar.getInstance().time)

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<HomeEvent>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()

    init {
        observeHomeState()
    }

    private fun observeHomeState() {
        viewModelScope.launch {
            combine(
                medicineRepository.getAllMedicines().asFlow(),
                selectedDate,
                userPreferencesRepository.userPreferences
            ) { medicines, date, preferences ->
                val filteredMedicines = medicines.filterByDate(date)
                HomeUiState(
                    selectedDate = date,
                    medicinesForSelectedDate = filteredMedicines,
                    isPillboxReminderEnabled = preferences.isPillboxReminderEnabled,
                    showCredits = filteredMedicines.isNotEmpty()
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun onDateSelected(date: Date) {
        selectedDate.value = date
    }

    fun onDeleteAlarmConfirmed(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            cancelAlarmIfScheduled(medicine, cancelAll = false)
            cancelWorkerIfNoUpcomingAlarm(medicine.name)
            medicineRepository.deleteMedicine(medicine)
            emitToast(R.string.alarm_successfully_deleted)
        }
    }

    fun onDeleteAllConfirmed(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            cancelAlarmIfScheduled(medicine, cancelAll = true)
            cancelWorkerById(medicineRepository.getWorkerID(medicine.name))
            val alarmsToDelete = medicineRepository.getAllMedicinesWithSameName(medicine.name)
            medicineRepository.deleteAllSelectedMedicines(alarmsToDelete)
            emitToast(R.string.alarm_successfully_deleted)
        }
    }

    fun onMarkUsageClick(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            if (shouldWarnAboutUsage(medicine)) {
                _events.emit(HomeEvent.ShowUsageWarning(medicine))
            } else {
                markMedicineAsTaken(medicine)
            }
        }
    }

    fun onConfirmUsageAfterWarning(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            markMedicineAsTaken(medicine)
        }
    }

    fun onSkipDose(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            if (medicine.alarmInMillis > System.currentTimeMillis()) {
                alarmScheduler.cancelAlarm(medicine.toAlarmItem(), false)
            }
            medicine.wasSkipped = true
            medicineRepository.updateMedicine(medicine)
            cancelWorkerIfNoUpcomingAlarm(medicine.name)
            emitToast(R.string.medicine_marked_as_skipped)
        }
    }

    fun onPillboxReminderScheduled(hour: Int, minute: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            alarmScheduler.schedulePillboxReminder(hour, minute)
            userPreferencesRepository.setPillboxReminder(true)
            emitToast(R.string.pillbox_reminder_enabled)
        }
    }

    fun onDisablePillboxReminder() {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferencesRepository.setPillboxReminder(false)
            workManager.cancelUniqueWork(PILLBOX_REMINDER_WORK_NAME)
            NotificationManagerCompat.from(appContext).cancel(PILLBOX_NOTIFICATION_ID)
            emitToast(R.string.pillbox_reminder_disabled)
        }
    }

    private suspend fun markMedicineAsTaken(medicine: Medicine) {
        medicine.medicineWasTaken = true
        medicine.wasSkipped = false
        medicineRepository.updateMedicine(medicine)
        emitToast(R.string.medicine_marked_as_taken)
    }

    private suspend fun cancelAlarmIfScheduled(medicine: Medicine, cancelAll: Boolean) {
        if (cancelAll || medicine.alarmInMillis > System.currentTimeMillis()) {
            alarmScheduler.cancelAlarm(medicine.toAlarmItem(), cancelAll)
        }
    }

    private suspend fun cancelWorkerIfNoUpcomingAlarm(medicineName: String) {
        val hasNextAlarm = medicineRepository.hasNextAlarmData(medicineName, System.currentTimeMillis())
        if (!hasNextAlarm) {
            cancelWorkerById(medicineRepository.getWorkerID(medicineName))
        }
    }

    private fun cancelWorkerById(workerId: String) {
        if (workerId == "noID" || workerId.isBlank()) return
        runCatching {
            val workRequestID = UUID.fromString(workerId)
            workManager.cancelWorkById(workRequestID)
        }
    }

    private suspend fun shouldWarnAboutUsage(medicine: Medicine): Boolean {
        val usageHour = medicine.alarmInMillis
        val nextAlarmHour = medicineRepository.getNextAlarmData(medicine.name, usageHour)?.alarmInMillis
        nextAlarmHour ?: return false

        val intervalBetweenAlarms = nextAlarmHour - usageHour
        if (intervalBetweenAlarms <= 0) return false

        val now = System.currentTimeMillis()
        val closeToNextAlarm = (now - usageHour) > ((2.0 / 3.0) * intervalBetweenAlarms)
        val pastTheNextAlarmHour = now > nextAlarmHour
        return closeToNextAlarm || pastTheNextAlarmHour
    }

    private suspend fun emitToast(@StringRes messageRes: Int) {
        _events.emit(HomeEvent.ShowToast(messageRes))
    }
}

private fun List<Medicine>.filterByDate(date: Date): List<Medicine> {
    val selectedCalendar = Calendar.getInstance().apply { time = date }
    return filter { medicine ->
        val medicineCalendar = Calendar.getInstance().apply { timeInMillis = medicine.alarmInMillis }
        medicineCalendar.get(Calendar.YEAR) == selectedCalendar.get(Calendar.YEAR) &&
                medicineCalendar.get(Calendar.MONTH) == selectedCalendar.get(Calendar.MONTH) &&
                medicineCalendar.get(Calendar.DAY_OF_MONTH) == selectedCalendar.get(Calendar.DAY_OF_MONTH)
    }.sortedWith(compareBy({ it.alarmHour }, { it.alarmMinute }))
}

private fun Medicine.toAlarmItem(): AlarmItem {
    val alarmTime = Instant.ofEpochMilli(alarmInMillis).atZone(ZoneId.systemDefault()).toLocalDateTime()
    return AlarmItem(
        alarmTime,
        name,
        form,
        quantity.toString(),
        alarmHour.toString(),
        alarmMinute.toString()
    )
}