package com.example.broadcastreceiver.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

/**
 * Static receiver that listens for device boot completion
 * Requires RECEIVE_BOOT_COMPLETED permission
 */
class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val message = "Device Boot Completed!"
            Log.d(TAG, message)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            
            // Here you can start services, schedule jobs, etc.
        }
    }

    companion object {
        private const val TAG = "BootCompletedReceiver"
    }
}

