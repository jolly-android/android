package com.example.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.concurrent.atomic.AtomicInteger

class NotificationHelper(private val context: Context) {

    private val notificationManager = NotificationManagerCompat.from(context)
    private val notificationIdGenerator = AtomicInteger(0)

    companion object {
        const val CHANNEL_DEFAULT = "default_channel"
        const val CHANNEL_IMPORTANT = "important_channel"
        const val CHANNEL_PROGRESS = "progress_channel"
        const val CHANNEL_MEDIA = "media_channel"
        const val GROUP_KEY = "notification_group"
    }

    init {
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Default Channel
            val defaultChannel = NotificationChannel(
                CHANNEL_DEFAULT,
                "Default Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "General notifications"
                enableVibration(true)
            }

            // Important Channel
            val importantChannel = NotificationChannel(
                CHANNEL_IMPORTANT,
                "Important Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "High priority notifications"
                enableVibration(true)
                enableLights(true)
            }

            // Progress Channel
            val progressChannel = NotificationChannel(
                CHANNEL_PROGRESS,
                "Progress Notifications",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Download and upload progress"
            }

            // Media Channel
            val mediaChannel = NotificationChannel(
                CHANNEL_MEDIA,
                "Media Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Media playback notifications"
            }

            val manager = context.getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannels(
                listOf(defaultChannel, importantChannel, progressChannel, mediaChannel)
            )
        }
    }

    private fun hasNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    // 1. Basic Simple Notification
    fun showBasicNotification() {
        if (!hasNotificationPermission()) return

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_DEFAULT)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Basic Notification")
            .setContentText("This is a simple notification with text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationIdGenerator.incrementAndGet(), notification)
    }

    // 2. Big Text Notification
    fun showBigTextNotification() {
        if (!hasNotificationPermission()) return

        val longText = "This is a long text notification that expands when you swipe down. " +
                "It can contain multiple lines of text and is perfect for displaying detailed " +
                "information like email content, messages, or articles. The text will be " +
                "truncated when collapsed and fully visible when expanded."

        val notification = NotificationCompat.Builder(context, CHANNEL_DEFAULT)
            .setSmallIcon(android.R.drawable.ic_dialog_email)
            .setContentTitle("Big Text Notification")
            .setContentText("Swipe down to see more...")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(longText)
                    .setBigContentTitle("Expanded Title")
                    .setSummaryText("Summary Text")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationIdGenerator.incrementAndGet(), notification)
    }

    // 3. Big Picture Notification
    fun showBigPictureNotification() {
        if (!hasNotificationPermission()) return

        // Create a sample bitmap (in real app, load from resources or URL)
        val bitmap = createSampleBitmap()

        val notification = NotificationCompat.Builder(context, CHANNEL_DEFAULT)
            .setSmallIcon(android.R.drawable.ic_menu_gallery)
            .setContentTitle("Big Picture Notification")
            .setContentText("Tap to view full image")
            .setLargeIcon(bitmap)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap)
                    .bigLargeIcon(null as Bitmap?) // Hide large icon when expanded
                    .setBigContentTitle("Beautiful Image")
                    .setSummaryText("Image notification example")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationIdGenerator.incrementAndGet(), notification)
    }

    // 4. Inbox Style Notification
    fun showInboxNotification() {
        if (!hasNotificationPermission()) return

        val notification = NotificationCompat.Builder(context, CHANNEL_DEFAULT)
            .setSmallIcon(android.R.drawable.ic_dialog_email)
            .setContentTitle("5 New Messages")
            .setContentText("You have new messages")
            .setStyle(
                NotificationCompat.InboxStyle()
                    .addLine("Alice: Hey, how are you?")
                    .addLine("Bob: Meeting at 3 PM")
                    .addLine("Charlie: Don't forget the documents")
                    .addLine("Diana: Lunch tomorrow?")
                    .addLine("Eve: Project update needed")
                    .setBigContentTitle("5 New Messages")
                    .setSummaryText("+2 more")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationIdGenerator.incrementAndGet(), notification)
    }

    // 5. Notification with Action Buttons
    fun showActionNotification() {
        if (!hasNotificationPermission()) return

        val likeIntent = Intent(context, NotificationReceiver::class.java).apply {
            action = "ACTION_LIKE"
        }
        val likePendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            likeIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val shareIntent = Intent(context, NotificationReceiver::class.java).apply {
            action = "ACTION_SHARE"
        }
        val sharePendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            shareIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_DEFAULT)
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .setContentTitle("Action Notification")
            .setContentText("This notification has action buttons")
            .addAction(
                android.R.drawable.ic_input_add,
                "Like",
                likePendingIntent
            )
            .addAction(
                android.R.drawable.ic_menu_share,
                "Share",
                sharePendingIntent
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationIdGenerator.incrementAndGet(), notification)
    }

    // 6. Progress Notification
    fun showProgressNotification(): Int {
        if (!hasNotificationPermission()) return -1

        val notificationId = notificationIdGenerator.incrementAndGet()
        
        val notification = NotificationCompat.Builder(context, CHANNEL_PROGRESS)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setContentTitle("Downloading")
            .setContentText("Download in progress")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setProgress(100, 0, false)
            .setOngoing(true)
            .build()

        notificationManager.notify(notificationId, notification)
        
        // Simulate progress updates
        Thread {
            for (progress in 0..100 step 10) {
                Thread.sleep(500)
                val updatedNotification = NotificationCompat.Builder(context, CHANNEL_PROGRESS)
                    .setSmallIcon(android.R.drawable.stat_sys_download)
                    .setContentTitle("Downloading")
                    .setContentText("$progress% Complete")
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setProgress(100, progress, false)
                    .setOngoing(progress < 100)
                    .build()
                
                notificationManager.notify(notificationId, updatedNotification)
            }
            
            // Show completion
            val completedNotification = NotificationCompat.Builder(context, CHANNEL_PROGRESS)
                .setSmallIcon(android.R.drawable.stat_sys_download_done)
                .setContentTitle("Download Complete")
                .setContentText("File downloaded successfully")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setProgress(0, 0, false)
                .setAutoCancel(true)
                .build()
            
            notificationManager.notify(notificationId, completedNotification)
        }.start()
        
        return notificationId
    }

    // 7. Heads-Up Notification (High Priority)
    fun showHeadsUpNotification() {
        if (!hasNotificationPermission()) return

        val notification = NotificationCompat.Builder(context, CHANNEL_IMPORTANT)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("Urgent Notification")
            .setContentText("This is a high-priority heads-up notification")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(0, 500, 200, 500))
            .build()

        notificationManager.notify(notificationIdGenerator.incrementAndGet(), notification)
    }

    // 8. Grouped Notifications
    fun showGroupedNotifications() {
        if (!hasNotificationPermission()) return

        val groupId = notificationIdGenerator.incrementAndGet()

        // Create multiple individual notifications
        for (i in 1..3) {
            val notification = NotificationCompat.Builder(context, CHANNEL_DEFAULT)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentTitle("Message $i")
                .setContentText("This is message number $i")
                .setGroup(GROUP_KEY)
                .setAutoCancel(true)
                .build()

            notificationManager.notify(groupId + i, notification)
        }

        // Create summary notification
        val summaryNotification = NotificationCompat.Builder(context, CHANNEL_DEFAULT)
            .setSmallIcon(android.R.drawable.ic_dialog_email)
            .setContentTitle("3 New Messages")
            .setContentText("You have 3 unread messages")
            .setStyle(
                NotificationCompat.InboxStyle()
                    .addLine("Message 1")
                    .addLine("Message 2")
                    .addLine("Message 3")
                    .setSummaryText("3 messages")
            )
            .setGroup(GROUP_KEY)
            .setGroupSummary(true)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(groupId, summaryNotification)
    }

    // 9. Custom Notification
    fun showCustomNotification() {
        if (!hasNotificationPermission()) return

        val notification = NotificationCompat.Builder(context, CHANNEL_DEFAULT)
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .setContentTitle("Custom Notification")
            .setContentText("This notification has custom styling")
            .setColor(0xFF6200EE.toInt())
            .setColorized(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationIdGenerator.incrementAndGet(), notification)
    }

    // 10. Media Style Notification
    fun showMediaNotification() {
        if (!hasNotificationPermission()) return

        val playIntent = Intent(context, NotificationReceiver::class.java).apply {
            action = "ACTION_PLAY"
        }
        val playPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            playIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val pauseIntent = Intent(context, NotificationReceiver::class.java).apply {
            action = "ACTION_PAUSE"
        }
        val pausePendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            pauseIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val nextIntent = Intent(context, NotificationReceiver::class.java).apply {
            action = "ACTION_NEXT"
        }
        val nextPendingIntent = PendingIntent.getBroadcast(
            context,
            2,
            nextIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_MEDIA)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentTitle("Now Playing")
            .setContentText("Song Title - Artist Name")
            .setLargeIcon(createSampleBitmap())
            .addAction(android.R.drawable.ic_media_previous, "Previous", playPendingIntent)
            .addAction(android.R.drawable.ic_media_pause, "Pause", pausePendingIntent)
            .addAction(android.R.drawable.ic_media_next, "Next", nextPendingIntent)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0, 1, 2)
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOngoing(true)
            .build()

        notificationManager.notify(notificationIdGenerator.incrementAndGet(), notification)
    }

    // Helper function to create sample bitmap
    private fun createSampleBitmap(): Bitmap {
        return Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888).apply {
            eraseColor(android.graphics.Color.parseColor("#6200EE"))
        }
    }

    fun cancelAll() {
        notificationManager.cancelAll()
    }
}

