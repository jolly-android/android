package com.example.notifications

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
// Temporarily commented out - Uncomment when you add Firebase
// import com.google.android.gms.tasks.OnCompleteListener
// import com.google.firebase.messaging.FirebaseMessaging
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var notificationHelper: NotificationHelper
    private lateinit var tvFcmToken: TextView

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationHelper = NotificationHelper(this)
        
        // Request notification permission for Android 13+
        checkAndRequestNotificationPermission()
        
        // Get FCM token
        getFCMToken()
        
        setupClickListeners()
    }

    private fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // Permission already granted
                }
                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    // Show rationale if needed
                    Toast.makeText(
                        this,
                        "Notification permission is needed to show notifications",
                        Toast.LENGTH_LONG
                    ).show()
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
                else -> {
                    // Request permission
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    private fun getFCMToken() {
        tvFcmToken = findViewById(R.id.tvFcmToken)
        
        // Temporarily disabled - Enable when you add Firebase support
        tvFcmToken.text = "FCM disabled - Follow FIREBASE_SETUP.md to enable push notifications"
        
        /* Uncomment this when you add google-services.json:
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                tvFcmToken.text = "Failed to get FCM token"
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d("FCM", "FCM Token: $token")
            tvFcmToken.text = getString(R.string.fcm_token_prefix) + token
        })
        */
    }

    private fun setupClickListeners() {
        // Basic Notifications
        findViewById<Button>(R.id.btnBasicNotification).setOnClickListener {
            notificationHelper.showBasicNotification()
        }

        findViewById<Button>(R.id.btnBigTextNotification).setOnClickListener {
            notificationHelper.showBigTextNotification()
        }

        findViewById<Button>(R.id.btnBigPictureNotification).setOnClickListener {
            notificationHelper.showBigPictureNotification()
        }

        findViewById<Button>(R.id.btnInboxNotification).setOnClickListener {
            notificationHelper.showInboxNotification()
        }

        // Advanced Notifications
        findViewById<Button>(R.id.btnActionNotification).setOnClickListener {
            notificationHelper.showActionNotification()
        }

        findViewById<Button>(R.id.btnProgressNotification).setOnClickListener {
            notificationHelper.showProgressNotification()
        }

        findViewById<Button>(R.id.btnHeadsUpNotification).setOnClickListener {
            notificationHelper.showHeadsUpNotification()
        }

        findViewById<Button>(R.id.btnGroupedNotification).setOnClickListener {
            notificationHelper.showGroupedNotifications()
        }

        // Special Notifications
        findViewById<Button>(R.id.btnCustomNotification).setOnClickListener {
            notificationHelper.showCustomNotification()
        }

        findViewById<Button>(R.id.btnMediaNotification).setOnClickListener {
            notificationHelper.showMediaNotification()
        }

        findViewById<Button>(R.id.btnScheduledNotification).setOnClickListener {
            scheduleNotification()
        }

        // FCM Token
        findViewById<Button>(R.id.btnCopyToken).setOnClickListener {
            copyTokenToClipboard()
        }

        // Controls
        findViewById<Button>(R.id.btnCancelAll).setOnClickListener {
            notificationHelper.cancelAll()
            Toast.makeText(
                this,
                getString(R.string.all_notifications_cancelled),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun scheduleNotification() {
        val workRequest = OneTimeWorkRequestBuilder<ScheduledNotificationWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
        
        Toast.makeText(
            this,
            getString(R.string.scheduled_notification_set),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun copyTokenToClipboard() {
        val tokenText = tvFcmToken.text.toString()
        
        if (tokenText.contains("FCM disabled")) {
            Toast.makeText(
                this,
                "FCM not configured - See FIREBASE_SETUP.md",
                Toast.LENGTH_LONG
            ).show()
            return
        }
        
        val token = tokenText.replace(getString(R.string.fcm_token_prefix), "")
        
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("FCM Token", token)
        clipboard.setPrimaryClip(clip)
        
        Toast.makeText(
            this,
            getString(R.string.fcm_token_copied),
            Toast.LENGTH_SHORT
        ).show()
    }
}
