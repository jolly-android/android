package com.example.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        
        when (intent?.action) {
            "ACTION_LIKE" -> {
                Toast.makeText(context, "Liked! ❤️", Toast.LENGTH_SHORT).show()
            }
            "ACTION_SHARE" -> {
                Toast.makeText(context, "Shared! 📤", Toast.LENGTH_SHORT).show()
            }
            "ACTION_PLAY" -> {
                Toast.makeText(context, "Playing ▶️", Toast.LENGTH_SHORT).show()
            }
            "ACTION_PAUSE" -> {
                Toast.makeText(context, "Paused ⏸️", Toast.LENGTH_SHORT).show()
            }
            "ACTION_NEXT" -> {
                Toast.makeText(context, "Next Track ⏭️", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

