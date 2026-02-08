package com.example.workmanagerdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * A Worker that runs periodically
 * Note: Minimum interval for periodic work is 15 minutes
 */
class PeriodicWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    
    override fun doWork(): Result {
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        Log.d(TAG, "PeriodicWorker executed at: $currentTime")
        
        return try {
            // Simulate periodic task (e.g., syncing data, checking for updates)
            Thread.sleep(2000)
            
            Log.d(TAG, "PeriodicWorker completed at: ${SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())}")
            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error in PeriodicWorker", e)
            Result.retry()
        }
    }
    
    companion object {
        private const val TAG = "PeriodicWorker"
    }
}


