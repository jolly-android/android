package com.phoenix.pillreminder.feature_alarms.presentation.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.phoenix.pillreminder.R
import com.phoenix.pillreminder.databinding.AdapterListMedicinesBinding
import com.phoenix.pillreminder.feature_alarms.domain.model.Medicine
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val HOUR_24_FORMAT = "HH:mm"
private const val HOUR_12_FORMAT = "hh:mm a"

class RvMedicinesListAdapter(
    private val showDeleteAlarmDialog: (Medicine) -> Unit,
    private val showDeleteAllAlarmsDialog: (Medicine) -> Unit,
    private val markMedicineUsage: (Medicine) -> Unit,
    private val markMedicinesAsSkipped: (Medicine) -> Unit
) : ListAdapter<Medicine, MedicineViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val medicinesBinding = AdapterListMedicinesBinding.inflate(layoutInflater, parent, false)

        return MedicineViewHolder(medicinesBinding)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bind(
            getItem(position),
            showDeleteAlarmDialog,
            showDeleteAllAlarmsDialog,
            markMedicineUsage,
            markMedicinesAsSkipped
        )
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Medicine>() {
        override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
            return oldItem.id == newItem.id && oldItem.alarmInMillis == newItem.alarmInMillis
        }

        override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
            return oldItem == newItem
        }
    }
}

class MedicineViewHolder(private val medicinesBinding: AdapterListMedicinesBinding): RecyclerView.ViewHolder(medicinesBinding.root){


    fun bind(
        medicine: Medicine,
        showDeleteAlarmDialog: (Medicine) -> Unit,
        showDeleteAllAlarmsDialog: (Medicine) -> Unit,
        markMedicineUsage: (Medicine) -> Unit,
        markMedicinesAsSkipped: (Medicine) -> Unit
    ){
        val context = itemView.context
        val currentTimeInMillis = System.currentTimeMillis()

        //Formats the textview to show the hour in format HH:MM
        medicinesBinding.apply{
            when {
                DateFormat.is24HourFormat(context) -> {
                    formatHour(medicine.alarmHour, medicine.alarmMinute, HOUR_24_FORMAT).let{
                        tvHour.text = it
                    }
                }

                !DateFormat.is24HourFormat(context) -> {
                    formatHour(medicine.alarmHour, medicine.alarmMinute, HOUR_12_FORMAT).let{
                        tvHour.text = it
                    }
                }
            }

            when(medicine.form){
                "pill" -> ivMedicineType.setImageResource(R.drawable.ic_pill_coloured)
                "pomade" -> ivMedicineType.setImageResource(R.drawable.ic_ointment)
                "injection" -> ivMedicineType.setImageResource(R.drawable.ic_injection)
                "drop" -> ivMedicineType.setImageResource(R.drawable.ic_dropper)
                "inhaler" -> ivMedicineType.setImageResource(R.drawable.ic_inhalator)
                "liquid" -> ivMedicineType.setImageResource(R.drawable.ic_liquid)
            }

            tvMedicineName.text = medicine.name


            when(medicine.form){
                "pill" -> tvQuantity.text = context.getString(R.string.take_pill, medicine.quantity.toInt().toString())
                "injection" -> tvQuantity.text = context.getString(R.string.take_injection, medicine.quantity.toString())
                "liquid" -> tvQuantity.text = context.getString(R.string.take_liquid, medicine.quantity.toString())
                "drop" -> tvQuantity.text = context.getString(R.string.take_drops, medicine.quantity.toInt().toString())
                "inhaler" -> tvQuantity.text = context.getString(R.string.inhale, medicine.quantity.toString())
                "pomade" -> tvQuantity.text = context.getString(R.string.apply_pomade)
            }

            when(medicine.medicineWasTaken){
                true -> {
                    tvMedicineTaken.isVisible = true

                    if(medicine.form == "pomade" || medicine.form == "inhaler"){
                        tvMedicineTaken.text = "Medicine already used."
                    } else{
                        tvMedicineTaken.text = context.getString(R.string.medicine_already_taken)
                    }

                }
                false -> {
                    tvMedicineTaken.isVisible = true

                    when (medicine.wasSkipped) {
                        true -> {
                            tvMedicineTaken.text =
                                context.getString(R.string.medicine_skipped)
                        }
                        false -> {
                            if(medicine.form == "pomade" || medicine.form == "inhaler"){
                                tvMedicineTaken.text = "Medicine not used yet."
                            } else{
                                tvMedicineTaken.text = context.getString(R.string.medicine_not_taken_yet)
                            }
                        }
                    }
                }

            }

           ivMenu.setOnClickListener {
               val popup = PopupMenu(context, ivMenu)
               popup.inflate(R.menu.options_menu)

               popup.setOnMenuItemClickListener { menuItem ->
                   when (menuItem.itemId) {
                       R.id.menu1 -> {
                           showDeleteAlarmDialog(medicine)
                           true
                       }
                       R.id.menu2 -> {
                           showDeleteAllAlarmsDialog(medicine)
                           true
                       }
                       R.id.menu3 -> {
                           markMedicinesAsSkipped(medicine)
                           true
                       }
                       else -> false
                   }
               }
               popup.show()
           }

            btnMarkUsage.visibility =
                if (currentTimeInMillis > medicine.alarmInMillis && !medicine.medicineWasTaken && !medicine.wasSkipped){
                    View.VISIBLE
                }  else View.GONE

            btnMarkUsage.setOnClickListener {
                markMedicineUsage(medicine)
            }
        }
    }

     private fun formatHour(hour: Int, minute: Int, pattern: String): String{
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(calendar.time)
    }
}