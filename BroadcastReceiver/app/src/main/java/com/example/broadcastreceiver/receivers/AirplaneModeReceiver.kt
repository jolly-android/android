package com.example.broadcastreceiver.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.widget.Toast

/**
 * Static BroadcastReceiver registered in AndroidManifest.xml
 * Listens for Airplane Mode changes
 */
class AirplaneModeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isAirplaneModeOn = intent.getBooleanExtra("state", false)
            val message = "Airplane Mode: ${if (isAirplaneModeOn) "ON" else "OFF"}"
            
            Log.d(TAG, message)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val TAG = "AirplaneModeReceiver"
    }
}

