package com.example.alarmmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return

        val alarmType = intent.getStringExtra(EXTRA_ALARM_TYPE) ?: "Unknown"
        val alarmId = intent.getIntExtra(EXTRA_ALARM_ID, 0)
        val message = intent.getStringExtra(EXTRA_MESSAGE) ?: "Alarm triggered!"

        Log.d(TAG, "Alarm received - Type: $alarmType, ID: $alarmId, Message: $message")

        // Show notification
        showNotification(context, alarmType, message, alarmId)
    }

    private fun showNotification(context: Context, alarmType: String, message: String, alarmId: Int) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for alarm triggers"
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Build notification
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle("$alarmType Alarm Triggered")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(0, 500, 250, 500))
            .build()

        notificationManager.notify(alarmId, notification)
    }

    companion object {
        private const val TAG = "AlarmReceiver"
        const val CHANNEL_ID = "alarm_channel"
        const val EXTRA_ALARM_TYPE = "alarm_type"
        const val EXTRA_ALARM_ID = "alarm_id"
        const val EXTRA_MESSAGE = "alarm_message"
    }
}

