package com.example.broadcastreceiver.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Custom BroadcastReceiver for app-specific broadcasts
 * Demonstrates custom action handling
 */
class CustomBroadcastReceiver(
    private val onCustomBroadcastReceived: (String) -> Unit
) : BroadcastReceiver() {
    
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            ACTION_CUSTOM_BROADCAST -> {
                val message = intent.getStringExtra(EXTRA_MESSAGE) ?: "No message"
                val timestamp = intent.getLongExtra(EXTRA_TIMESTAMP, 0)
                val count = intent.getIntExtra(EXTRA_COUNT, 0)
                
                val result = "Custom Broadcast Received!\n" +
                        "Message: $message\n" +
                        "Count: $count\n" +
                        "Timestamp: $timestamp"
                
                Log.d(TAG, result)
                onCustomBroadcastReceived(result)
            }
        }
    }

    companion object {
        private const val TAG = "CustomBroadcastReceiver"
        const val ACTION_CUSTOM_BROADCAST = "com.example.broadcastreceiver.CUSTOM_BROADCAST"
        const val EXTRA_MESSAGE = "extra_message"
        const val EXTRA_TIMESTAMP = "extra_timestamp"
        const val EXTRA_COUNT = "extra_count"
    }
}

