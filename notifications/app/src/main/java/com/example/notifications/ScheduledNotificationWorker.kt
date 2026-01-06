package com.example.notifications

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ScheduledNotificationWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val notificationHelper = NotificationHelper(applicationContext)
        notificationHelper.showBasicNotification()
        return Result.success()
    }
}

