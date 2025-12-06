package com.phoenix.pillreminder.feature_alarms.presentation.viewmodels

import com.phoenix.pillreminder.feature_alarms.domain.model.Medicine
import java.util.Calendar
import java.util.Date

data class HomeUiState(
    val selectedDate: Date = Calendar.getInstance().time,
    val medicinesForSelectedDate: List<Medicine> = emptyList(),
    val isPillboxReminderEnabled: Boolean = false,
    val showCredits: Boolean = false
)

