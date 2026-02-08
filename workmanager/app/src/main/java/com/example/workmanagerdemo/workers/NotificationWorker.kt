package com.example.workmanagerdemo.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanagerdemo.R
import kotlinx.coroutines.delay

/**
 * Worker that shows a notification when work is complete
 */
class NotificationWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    
    override suspend fun doWork(): Result {
        Log.d(TAG, "NotificationWorker started")
        
        return try {
            // Simulate some work
            delay(3000)
            
            // Show notification
            showNotification("Work completed!", "Your background task has finished.")
            
            Log.d(TAG, "NotificationWorker completed")
            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error in NotificationWorker", e)
            Result.failure()
        }
    }
    
    private fun showNotification(title: String, message: String) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        
        // Create notification channel for Android O+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "WorkManager Demo",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications from WorkManager Demo"
            }
            notificationManager.createNotificationChannel(channel)
        }
        
        // Build and show notification
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
        
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
    
    companion object {
        private const val TAG = "NotificationWorker"
        private const val CHANNEL_ID = "workmanager_demo_channel"
        private const val NOTIFICATION_ID = 1001
    }
}


