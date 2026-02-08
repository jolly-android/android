package com.example.workmanagerdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

/**
 * Workers used to demonstrate work chaining
 */
class ChainWorkerA(context: Context, params: WorkerParameters) : Worker(context, params) {
    
    override fun doWork(): Result {
        Log.d(TAG, "ChainWorkerA started")
        
        return try {
            // Simulate work
            Thread.sleep(2000)
            
            val outputData = workDataOf(KEY_DATA to "Data from Worker A")
            
            Log.d(TAG, "ChainWorkerA completed")
            Result.success(outputData)
        } catch (e: Exception) {
            Log.e(TAG, "Error in ChainWorkerA", e)
            Result.failure()
        }
    }
    
    companion object {
        private const val TAG = "ChainWorkerA"
        const val KEY_DATA = "worker_data"
    }
}

class ChainWorkerB(context: Context, params: WorkerParameters) : Worker(context, params) {
    
    override fun doWork(): Result {
        Log.d(TAG, "ChainWorkerB started")
        
        // Get data from previous worker
        val previousData = inputData.getString(ChainWorkerA.KEY_DATA) ?: ""
        Log.d(TAG, "Received from previous worker: $previousData")
        
        return try {
            // Simulate work
            Thread.sleep(2000)
            
            val outputData = workDataOf(KEY_DATA to "$previousData -> Data from Worker B")
            
            Log.d(TAG, "ChainWorkerB completed")
            Result.success(outputData)
        } catch (e: Exception) {
            Log.e(TAG, "Error in ChainWorkerB", e)
            Result.failure()
        }
    }
    
    companion object {
        private const val TAG = "ChainWorkerB"
        const val KEY_DATA = "worker_data"
    }
}

class ChainWorkerC(context: Context, params: WorkerParameters) : Worker(context, params) {
    
    override fun doWork(): Result {
        Log.d(TAG, "ChainWorkerC started")
        
        // Get data from previous worker
        val previousData = inputData.getString(ChainWorkerB.KEY_DATA) ?: ""
        Log.d(TAG, "Received from previous worker: $previousData")
        
        return try {
            // Simulate work
            Thread.sleep(2000)
            
            val outputData = workDataOf(KEY_DATA to "$previousData -> Data from Worker C (Final)")
            
            Log.d(TAG, "ChainWorkerC completed")
            Result.success(outputData)
        } catch (e: Exception) {
            Log.e(TAG, "Error in ChainWorkerC", e)
            Result.failure()
        }
    }
    
    companion object {
        private const val TAG = "ChainWorkerC"
        const val KEY_DATA = "worker_data"
    }
}


