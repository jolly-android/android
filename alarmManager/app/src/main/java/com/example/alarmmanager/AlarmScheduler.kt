package com.example.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast

class AlarmScheduler(private val context: Context) {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    /**
     * Schedule a one-time alarm using RTC_WAKEUP
     * This will wake up the device to fire the alarm
     */
    fun scheduleOneTimeAlarm(delayInSeconds: Long) {
        val triggerTime = System.currentTimeMillis() + (delayInSeconds * 1000)
        val intent = createAlarmIntent(
            alarmType = "One-Time RTC_WAKEUP",
            alarmId = ALARM_ID_ONE_TIME,
            message = "One-time alarm fired after $delayInSeconds seconds!"
        )

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    intent
                )
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, intent)
            }
            showToast("One-time alarm set for $delayInSeconds seconds")
            Log.d(TAG, "One-time alarm scheduled for: $triggerTime")
        } catch (e: SecurityException) {
            showToast("Permission denied for exact alarm")
            Log.e(TAG, "SecurityException: ${e.message}")
        }
    }

    /**
     * Schedule a repeating alarm using RTC_WAKEUP
     * Note: On Android 4.4+ (API 19+), repeating alarms are inexact
     */
    fun scheduleRepeatingAlarm(intervalInSeconds: Long) {
        val triggerTime = System.currentTimeMillis() + (intervalInSeconds * 1000)
        val intent = createAlarmIntent(
            alarmType = "Repeating",
            alarmId = ALARM_ID_REPEATING,
            message = "Repeating alarm! Interval: $intervalInSeconds seconds"
        )

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            intervalInSeconds * 1000,
            intent
        )
        showToast("Repeating alarm set with $intervalInSeconds sec interval")
        Log.d(TAG, "Repeating alarm scheduled with interval: $intervalInSeconds seconds")
    }

    /**
     * Schedule an inexact repeating alarm
     * More battery efficient as system can batch alarms
     */
    fun scheduleInexactRepeatingAlarm(intervalMinutes: Long) {
        val triggerTime = System.currentTimeMillis() + (intervalMinutes * 60 * 1000)
        val intent = createAlarmIntent(
            alarmType = "Inexact Repeating",
            alarmId = ALARM_ID_INEXACT,
            message = "Inexact repeating alarm! (~$intervalMinutes min interval)"
        )

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            intervalMinutes * 60 * 1000,
            intent
        )
        showToast("Inexact repeating alarm set (~$intervalMinutes min)")
        Log.d(TAG, "Inexact repeating alarm scheduled")
    }

    /**
     * Schedule alarm using ELAPSED_REALTIME_WAKEUP
     * Time since device boot (including deep sleep)
     */
    fun scheduleElapsedRealtimeAlarm(delayInSeconds: Long) {
        val triggerTime = android.os.SystemClock.elapsedRealtime() + (delayInSeconds * 1000)
        val intent = createAlarmIntent(
            alarmType = "Elapsed Realtime",
            alarmId = ALARM_ID_ELAPSED,
            message = "Elapsed realtime alarm after $delayInSeconds seconds since boot!"
        )

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    intent
                )
            } else {
                alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, intent)
            }
            showToast("Elapsed realtime alarm set for $delayInSeconds sec")
            Log.d(TAG, "Elapsed realtime alarm scheduled")
        } catch (e: SecurityException) {
            showToast("Permission denied for exact alarm")
            Log.e(TAG, "SecurityException: ${e.message}")
        }
    }

    /**
     * Schedule an alarm in the idle maintenance window
     * System will choose optimal time to fire (battery efficient)
     */
    fun scheduleIdleAlarm(delayInMinutes: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val triggerTime = System.currentTimeMillis() + (delayInMinutes * 60 * 1000)
            val intent = createAlarmIntent(
                alarmType = "Idle Window",
                alarmId = ALARM_ID_IDLE,
                message = "Alarm fired during idle maintenance window!"
            )

            alarmManager.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerTime,
                intent
            )
            showToast("Idle window alarm set (~$delayInMinutes min)")
            Log.d(TAG, "Idle window alarm scheduled")
        } else {
            showToast("Idle alarms require Android M+")
        }
    }

    /**
     * Schedule an alarm window (between earliest and latest time)
     */
    fun scheduleWindowAlarm(startDelaySeconds: Long, windowSeconds: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val triggerAtMillis = System.currentTimeMillis() + (startDelaySeconds * 1000)
            val windowMillis = windowSeconds * 1000
            val intent = createAlarmIntent(
                alarmType = "Window",
                alarmId = ALARM_ID_WINDOW,
                message = "Window alarm fired! (${startDelaySeconds}s - ${startDelaySeconds + windowSeconds}s)"
            )

            alarmManager.setWindow(
                AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                windowMillis,
                intent
            )
            showToast("Window alarm set ($startDelaySeconds-${startDelaySeconds + windowSeconds}s)")
            Log.d(TAG, "Window alarm scheduled")
        } else {
            showToast("Window alarms require Android KitKat+")
        }
    }

    /**
     * Cancel a specific alarm by ID
     */
    fun cancelAlarm(alarmId: Int, alarmName: String) {
        val intent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context,
                alarmId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }
        alarmManager.cancel(intent)
        showToast("$alarmName alarm cancelled")
        Log.d(TAG, "$alarmName alarm cancelled (ID: $alarmId)")
    }

    /**
     * Cancel all alarms
     */
    fun cancelAllAlarms() {
        val alarmIds = listOf(
            ALARM_ID_ONE_TIME to "One-time",
            ALARM_ID_REPEATING to "Repeating",
            ALARM_ID_INEXACT to "Inexact",
            ALARM_ID_ELAPSED to "Elapsed",
            ALARM_ID_IDLE to "Idle",
            ALARM_ID_WINDOW to "Window"
        )

        alarmIds.forEach { (id, _) ->
            val intent = Intent(context, AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(
                    context,
                    id,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }
            alarmManager.cancel(intent)
        }
        showToast("All alarms cancelled")
        Log.d(TAG, "All alarms cancelled")
    }

    /**
     * Check if exact alarms are allowed (Android 12+)
     */
    fun canScheduleExactAlarms(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms()
        } else {
            true
        }
    }

    private fun createAlarmIntent(alarmType: String, alarmId: Int, message: String): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(AlarmReceiver.EXTRA_ALARM_TYPE, alarmType)
            putExtra(AlarmReceiver.EXTRA_ALARM_ID, alarmId)
            putExtra(AlarmReceiver.EXTRA_MESSAGE, message)
        }

        return PendingIntent.getBroadcast(
            context,
            alarmId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "AlarmScheduler"
        const val ALARM_ID_ONE_TIME = 1001
        const val ALARM_ID_REPEATING = 1002
        const val ALARM_ID_INEXACT = 1003
        const val ALARM_ID_ELAPSED = 1004
        const val ALARM_ID_IDLE = 1005
        const val ALARM_ID_WINDOW = 1006
    }
}

