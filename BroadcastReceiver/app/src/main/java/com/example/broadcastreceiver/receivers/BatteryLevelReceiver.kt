package com.example.broadcastreceiver.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log

/**
 * Dynamic BroadcastReceiver for monitoring battery changes
 * Registered programmatically at runtime
 */
class BatteryLevelReceiver(
    private val onBatteryInfoReceived: (String) -> Unit
) : BroadcastReceiver() {
    
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_BATTERY_CHANGED -> {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val batteryPct = level * 100 / scale.toFloat()
                
                val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL
                
                val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
                val chargingSource = when (plugged) {
                    BatteryManager.BATTERY_PLUGGED_AC -> "AC"
                    BatteryManager.BATTERY_PLUGGED_USB -> "USB"
                    BatteryManager.BATTERY_PLUGGED_WIRELESS -> "Wireless"
                    else -> "Not charging"
                }
                
                val message = "Battery: ${batteryPct.toInt()}%\n" +
                        "Charging: ${if (isCharging) "Yes" else "No"}\n" +
                        "Source: $chargingSource"
                
                Log.d(TAG, message)
                onBatteryInfoReceived(message)
            }
            
            Intent.ACTION_BATTERY_LOW -> {
                val message = "⚠️ Battery Low Warning!"
                Log.d(TAG, message)
                onBatteryInfoReceived(message)
            }
            
            Intent.ACTION_BATTERY_OKAY -> {
                val message = "✓ Battery Level Okay"
                Log.d(TAG, message)
                onBatteryInfoReceived(message)
            }
        }
    }

    companion object {
        private const val TAG = "BatteryLevelReceiver"
    }
}

