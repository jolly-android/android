package com.phoenix.pillreminder.feature_alarms.presentation.fragments

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.text.format.DateFormat.is24HourFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.switchmaterial.SwitchMaterial
import com.phoenix.pillreminder.R
import com.phoenix.pillreminder.databinding.FragmentHomeBinding
import com.phoenix.pillreminder.databinding.LayoutSetPillboxReminderDialogBinding
import com.phoenix.pillreminder.databinding.LayoutWarnAboutMedicineUsageHourBinding
import com.phoenix.pillreminder.feature_alarms.domain.model.Medicine
import com.phoenix.pillreminder.feature_alarms.presentation.HideFabScrollListener
import com.phoenix.pillreminder.feature_alarms.presentation.adapter.RvMedicinesListAdapter
import com.phoenix.pillreminder.feature_alarms.presentation.viewmodels.HomeEvent
import com.phoenix.pillreminder.feature_alarms.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: RvMedicinesListAdapter
    private var toast: Toast? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = findNavController()

        //Prevents the app from going back to an alarm registration
        requireActivity().onBackPressedDispatcher.addCallback(this){
            if(navController.currentDestination?.id == R.id.homeFragment){
                requireActivity().finish()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("dont_show_again", Context.MODE_PRIVATE)
        val dontShowAgain = sharedPreferences.getBoolean("dont_show_again", false)

        initRecyclerView()
        requestPermissions(dontShowAgain)
        observeUiState()
        observeViewModelEvents()

        binding.datePicker.onSelectionChanged = { date ->
            homeViewModel.onDateSelected(date)
        }


        binding.fabAddMedicine.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addMedicinesFragment)
        }


        getPillboxSwitch().setOnCheckedChangeListener { buttonView, isChecked ->
            if (!buttonView.isPressed) {
                return@setOnCheckedChangeListener
            }
            if (isChecked) {
                buttonView.isChecked = false
                showSetPillboxReminderDialog()
            } else {
                homeViewModel.onDisablePillboxReminder()
            }
        }
    }

    private fun requestPermissions(dontShowAgain: Boolean){
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
            binding.rvMedicinesList.visibility = View.VISIBLE
            binding.fabAddMedicine.visibility = View.VISIBLE
        }
        if(!Settings.canDrawOverlays(requireContext()) && !dontShowAgain){
            showOverlayAndNotificationPermissionDialog()
        }
    }

     private fun initRecyclerView(){
        binding.rvMedicinesList.layoutManager = LinearLayoutManager(activity)
        adapter = RvMedicinesListAdapter(
            showDeleteAlarmDialog = { medicine ->
                showDeleteAlarmDialog(medicine)
            },
            showDeleteAllAlarmsDialog = { medicine ->
                showDeleteAllAlarmsDialog(medicine)
            },
            markMedicineUsage = { medicine ->
                homeViewModel.onMarkUsageClick(medicine)
            },
            markMedicinesAsSkipped = { medicine ->
                homeViewModel.onSkipDose(medicine)
            }
        )
        binding.rvMedicinesList.adapter = adapter
         val hideFabScrollListener = HideFabScrollListener(binding.fabAddMedicine)
         binding.rvMedicinesList.addOnScrollListener(hideFabScrollListener)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.uiState.collect { state ->
                    adapter.submitList(state.medicinesForSelectedDate)
                    setCreditsVisibility(state.showCredits)
                    val pillboxSwitch = getPillboxSwitch()
                    if (pillboxSwitch.isChecked != state.isPillboxReminderEnabled) {
                        pillboxSwitch.isChecked = state.isPillboxReminderEnabled
                    }
                }
            }
        }
    }

    private fun observeViewModelEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.events.collect { event ->
                    when(event) {
                        is HomeEvent.ShowUsageWarning -> showWarningMedicineUsageDialog(event.medicine)
                        is HomeEvent.ShowToast -> showToast(event.messageRes)
                    }
                }
            }
        }
    }

    private fun getPillboxSwitch(): SwitchMaterial =
        binding.datePicker.findViewById(R.id.switchPillbox)

    private fun showSetPillboxReminderDialog() {
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)

        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogBinding = LayoutSetPillboxReminderDialogBinding.inflate(inflater)

        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val hourFormat = is24HourFormat(requireContext())
        dialogBinding.tpDialogPillbox.setIs24HourView(hourFormat)

        var pillboxReminderHour = dialogBinding.tpDialogPillbox.hour
        var pillboxReminderMinute = dialogBinding.tpDialogPillbox.minute

        dialogBinding.tpDialogPillbox.setOnTimeChangedListener { _, hourOfDay, minute ->
            pillboxReminderHour = hourOfDay
            pillboxReminderMinute = minute
        }

        dialogBinding.btnSaveDialogPillbox.setOnClickListener {
            homeViewModel.onPillboxReminderScheduled(pillboxReminderHour, pillboxReminderMinute)
            dialog.dismiss()
        }

        dialogBinding.btnCancelDialogPillbox.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun uncheckSwitch(){
        binding.datePicker.findViewById<SwitchMaterial>(R.id.switchPillbox).isChecked = false
    }

    private fun showOverlayAndNotificationPermissionDialog(){
        val sharedPreferences = requireContext().getSharedPreferences("overlay_permission_prefs", Context.MODE_PRIVATE)
        val dontShowAgain = sharedPreferences.getBoolean("dont_show_again", false)

        if (dontShowAgain){
            return
        }

        val dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_overlay_permission_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val requestPermission: Button = dialog.findViewById(R.id.btnGivePermissions)
        val dismissRequest: Button = dialog.findViewById(R.id.btnDismissRequest)
        val checkboxDontShowAgain: CheckBox = dialog.findViewById(R.id.cbDontShowAgain)

        requestPermission.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
        dismissRequest.setOnClickListener {
            if(checkboxDontShowAgain.isChecked){
                with(sharedPreferences.edit()){
                    putBoolean("dont_show_again", true)
                    apply()
                }
            }
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showDeleteAlarmDialog(medicine: Medicine){
        dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_delete_alarm_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMedicine: TextView = dialog.findViewById(R.id.tvMedicineAndHour)
        val btnDelete: Button = dialog.findViewById(R.id.btnDelete)
        val btnCancel: Button = dialog.findViewById(R.id.btnCancel)

        tvMedicine.text = context?.getString(R.string.tv_alarm_and_hour, medicine.name, showTvAlarm(medicine.alarmHour, medicine.alarmMinute))

        btnDelete.setOnClickListener {
            homeViewModel.onDeleteAlarmConfirmed(medicine)
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showDeleteAllAlarmsDialog(medicine: Medicine){
        dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_delete_all_alarms_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMedicine: TextView = dialog.findViewById(R.id.tvMedicineAndHour)
        val btnDelete: Button = dialog.findViewById(R.id.btnDeleteAll)
        val btnCancel: Button = dialog.findViewById(R.id.btnCancel)

        tvMedicine.text = context?.getString(R.string.tv_medicine, medicine.name)

        btnDelete.setOnClickListener {
            homeViewModel.onDeleteAllConfirmed(medicine)
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showWarningMedicineUsageDialog(medicine: Medicine){
        dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)

        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = LayoutWarnAboutMedicineUsageHourBinding.inflate(inflater)

        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnMarkMedicineUsage.setOnClickListener {
            homeViewModel.onConfirmUsageAfterWarning(medicine)
            dialog.dismiss()
        }

        binding.btnSkipDose.setOnClickListener {
            homeViewModel.onSkipDose(medicine)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showToast(@StringRes messageRes: Int){
        toast?.cancel()
        toast = Toast.makeText(context, getString(messageRes), Toast.LENGTH_LONG)
        toast?.show()
    }

    private fun showTvAlarm(alarmHour: Int, alarmMinute: Int): String{
        val context = requireContext()
        when {
            is24HourFormat(context) -> {
                return formatHour(alarmHour, alarmMinute, "HH:mm")
            }
            !is24HourFormat(context) -> {
                return formatHour(alarmHour, alarmMinute, "hh:mm a")
            }
        }
        return ""
    }

    private fun formatHour(hour: Int, minute: Int, pattern: String): String{
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(calendar.time)
    }

    private fun setCreditsVisibility(shouldShow: Boolean){
        binding.tvCredits.isVisible = shouldShow
    }

    private fun requestOverlayPermission(){
        if(!Settings.canDrawOverlays(requireContext())){
            val overlayIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply{
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                data = Uri.parse("package:${requireContext().packageName}")
            }
            requestOverlayPermissionLauncher.launch(overlayIntent)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ permissionGranted: Boolean ->
        if(permissionGranted){
            binding.fabAddMedicine.visibility = View.VISIBLE
            binding.rvMedicinesList.visibility = View.VISIBLE
        }
        requestOverlayPermission()
    }

    private val requestOverlayPermissionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){}

    override fun onPause() {
        super.onPause()

        //Dismiss the dialog to avoid window leakage
        if(::dialog.isInitialized && dialog.isShowing){
            dialog.dismiss()
        }
    }

}



