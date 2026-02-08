package com.example.workmanagerdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * A Worker that runs only when specific constraints are met
 * (e.g., device is charging, connected to WiFi, etc.)
 */
class ConstrainedWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    
    override fun doWork(): Result {
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        Log.d(TAG, "ConstrainedWorker started at: $currentTime")
        Log.d(TAG, "All constraints have been met!")
        
        return try {
            // Simulate heavy work that should only run under specific conditions
            for (i in 1..5) {
                Log.d(TAG, "Processing heavy task: Step $i/5")
                Thread.sleep(1000)
            }
            
            Log.d(TAG, "ConstrainedWorker completed successfully")
            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error in ConstrainedWorker", e)
            Result.failure()
        }
    }
    
    companion object {
        private const val TAG = "ConstrainedWorker"
    }
}


