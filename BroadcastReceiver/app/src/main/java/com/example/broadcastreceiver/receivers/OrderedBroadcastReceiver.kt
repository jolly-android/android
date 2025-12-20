package com.example.broadcastreceiver.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Demonstrates ordered broadcasts where receivers can process sequentially
 * and modify results or abort the broadcast chain
 */
class OrderedBroadcastReceiverHigh(
    private val onReceived: (String) -> Unit
) : BroadcastReceiver() {
    
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == ACTION_ORDERED_BROADCAST) {
            val message = "High Priority Receiver: Received first\n" +
                    "Initial data: ${intent.getStringExtra(EXTRA_DATA)}"
            
            Log.d(TAG_HIGH, message)
            onReceived(message)
            
            // Modify the result for next receiver
            resultData = "Modified by High Priority"
            setResultCode(100)
        }
    }

    companion object {
        private const val TAG_HIGH = "OrderedReceiver_High"
        const val ACTION_ORDERED_BROADCAST = "com.example.broadcastreceiver.ORDERED_BROADCAST"
        const val EXTRA_DATA = "extra_data"
    }
}

class OrderedBroadcastReceiverMedium(
    private val onReceived: (String) -> Unit
) : BroadcastReceiver() {
    
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == OrderedBroadcastReceiverHigh.ACTION_ORDERED_BROADCAST) {
            val previousData = resultData ?: "No previous data"
            val message = "Medium Priority Receiver: Received second\n" +
                    "Data from previous: $previousData\n" +
                    "Result code: $resultCode"
            
            Log.d(TAG_MEDIUM, message)
            onReceived(message)
            
            // Further modify the result
            resultData = "$previousData -> Modified by Medium Priority"
            setResultCode(200)
        }
    }

    companion object {
        private const val TAG_MEDIUM = "OrderedReceiver_Medium"
    }
}

class OrderedBroadcastReceiverLow(
    private val onReceived: (String) -> Unit
) : BroadcastReceiver() {
    
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == OrderedBroadcastReceiverHigh.ACTION_ORDERED_BROADCAST) {
            val finalData = resultData ?: "No data"
            val message = "Low Priority Receiver: Received last\n" +
                    "Final data: $finalData\n" +
                    "Final result code: $resultCode"
            
            Log.d(TAG_LOW, message)
            onReceived(message)
        }
    }

    companion object {
        private const val TAG_LOW = "OrderedReceiver_Low"
    }
}

