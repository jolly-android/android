package com.example.workmanagerdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

/**
 * A simple Worker that demonstrates basic WorkManager functionality
 */
class SimpleWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    
    override fun doWork(): Result {
        Log.d(TAG, "SimpleWorker started")
        
        // Get input data
        val taskName = inputData.getString(KEY_TASK_NAME) ?: "Unknown Task"
        
        return try {
            // Simulate some work
            for (i in 1..5) {
                Log.d(TAG, "Processing $taskName: Step $i/5")
                Thread.sleep(1000)
            }
            
            // Return success with output data
            val outputData = workDataOf(
                KEY_RESULT to "Task '$taskName' completed successfully!"
            )
            
            Log.d(TAG, "SimpleWorker finished successfully")
            Result.success(outputData)
        } catch (e: Exception) {
            Log.e(TAG, "Error in SimpleWorker", e)
            Result.failure()
        }
    }
    
    companion object {
        private const val TAG = "SimpleWorker"
        const val KEY_TASK_NAME = "task_name"
        const val KEY_RESULT = "result"
    }
}


