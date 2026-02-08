package com.example.workmanagerdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

/**
 * A Worker that reports progress during execution
 */
class ProgressWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    
    override suspend fun doWork(): Result {
        Log.d(TAG, "ProgressWorker started")
        
        val totalSteps = 10
        
        return try {
            for (i in 1..totalSteps) {
                // Update progress
                setProgress(workDataOf(
                    KEY_PROGRESS to i * 10,
                    KEY_MESSAGE to "Processing step $i of $totalSteps"
                ))
                
                Log.d(TAG, "Progress: ${i * 10}%")
                
                // Simulate work
                delay(1000)
            }
            
            Log.d(TAG, "ProgressWorker completed")
            Result.success(workDataOf(KEY_MESSAGE to "All steps completed!"))
        } catch (e: Exception) {
            Log.e(TAG, "Error in ProgressWorker", e)
            Result.failure()
        }
    }
    
    companion object {
        private const val TAG = "ProgressWorker"
        const val KEY_PROGRESS = "progress"
        const val KEY_MESSAGE = "message"
    }
}


