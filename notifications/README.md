# Android Notifications Demo App

A comprehensive Android application demonstrating all types of notifications including push notifications using Firebase Cloud Messaging (FCM).

## Features

### Basic Notifications
1. **Basic Notification** - Simple notification with title and text
2. **Big Text Notification** - Expandable notification with long text content
3. **Big Picture Notification** - Notification with large image
4. **Inbox Style Notification** - List-style notification showing multiple lines

### Advanced Notifications
5. **Action Buttons Notification** - Notification with interactive action buttons (Like, Share)
6. **Progress Notification** - Shows progress bar with percentage (simulates download)
7. **Heads-Up Notification** - High-priority notification that appears at the top of screen
8. **Grouped Notifications** - Multiple notifications grouped together with summary

### Special Notifications
9. **Custom Styled Notification** - Notification with custom colors and styling
10. **Media Style Notification** - Music player-style notification with media controls
11. **Scheduled Notification** - Notification scheduled to appear after 10 seconds

### Push Notifications
12. **Firebase Cloud Messaging (FCM)** - Receive push notifications from server
    - Display FCM token
    - Copy token to clipboard
    - Handle remote messages

## Setup Instructions

### 1. Prerequisites
- Android Studio Arctic Fox or later
- Android device/emulator running Android 7.0 (API 24) or higher
- Google account for Firebase setup

### 2. Firebase Setup (For Push Notifications)

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create a new project or select existing one
3. Add an Android app to your Firebase project
   - Package name: `com.example.notifications`
4. Download `google-services.json`
5. Place it in `app/` directory of your project
6. The app is already configured with Firebase dependencies

### 3. Build and Run

```bash
# Clean and build
./gradlew clean build

# Install on connected device
./gradlew installDebug
```

### 4. Grant Notification Permission
- On Android 13+ (API 33+), the app will request notification permission on first launch
- Grant the permission to see all notifications

## Testing Notifications

### Testing Local Notifications
Simply tap any button in the app to trigger the corresponding notification type.

### Testing Push Notifications (FCM)

1. **Get FCM Token**
   - Launch the app
   - The FCM token will be displayed at the bottom
   - Tap "Copy FCM Token" to copy it

2. **Send Test Notification from Firebase Console**
   - Go to Firebase Console → Cloud Messaging
   - Click "Send your first message"
   - Enter notification title and text
   - Click "Send test message"
   - Paste your FCM token
   - Click "Test"

3. **Send Notification using cURL**

```bash
curl -X POST https://fcm.googleapis.com/fcm/send \
  -H "Authorization: key=YOUR_SERVER_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "to": "YOUR_FCM_TOKEN",
    "notification": {
      "title": "Test Push Notification",
      "body": "This is a test notification from FCM"
    }
  }'
```

Replace:
- `YOUR_SERVER_KEY`: Get from Firebase Console → Project Settings → Cloud Messaging → Server Key
- `YOUR_FCM_TOKEN`: The token displayed in the app

4. **Send Notification using Postman**
   - Method: POST
   - URL: `https://fcm.googleapis.com/fcm/send`
   - Headers:
     - `Authorization: key=YOUR_SERVER_KEY`
     - `Content-Type: application/json`
   - Body (raw JSON):
```json
{
  "to": "YOUR_FCM_TOKEN",
  "notification": {
    "title": "Hello from Postman",
    "body": "This is a push notification!"
  }
}
```

## Notification Types Explained

### Basic vs Expandable Notifications
- **Basic**: Shows title and short text
- **Expandable**: Can be expanded to show more content (Big Text, Big Picture, Inbox)

### Notification Channels
Android 8.0+ groups notifications into channels:
- **Default Channel**: General notifications
- **Important Channel**: High-priority notifications with sound and vibration
- **Progress Channel**: Low-priority notifications for ongoing tasks
- **Media Channel**: Media playback notifications

### Notification Priority
- **HIGH**: Heads-up notifications, alerts
- **DEFAULT**: Normal notifications
- **LOW**: Silent notifications, progress indicators

## Features Demonstrated

### 1. Notification Styles
- Basic notification
- BigTextStyle
- BigPictureStyle
- InboxStyle
- MediaStyle

### 2. Notification Components
- Small icon
- Large icon
- Content title and text
- Action buttons
- Progress indicator
- Expandable content

### 3. User Interactions
- Tap to open app
- Action buttons (Like, Share, Play, Pause, Next)
- Swipe to dismiss
- Expand/collapse

### 4. Advanced Features
- Notification channels (Android 8.0+)
- Runtime permission (Android 13+)
- Notification grouping
- Scheduled notifications with WorkManager
- Push notifications with FCM

## Project Structure

```
app/src/main/java/com/example/notifications/
├── MainActivity.kt                    # Main activity with UI
├── NotificationHelper.kt              # All notification creation logic
├── NotificationReceiver.kt            # Handles notification actions
├── MyFirebaseMessagingService.kt      # FCM service for push notifications
└── ScheduledNotificationWorker.kt     # WorkManager for scheduled notifications

app/src/main/res/
├── layout/
│   └── activity_main.xml              # UI layout with all buttons
├── values/
│   ├── strings.xml                    # String resources
│   ├── colors.xml                     # Color definitions
│   └── themes.xml                     # App theme
└── AndroidManifest.xml                # Permissions and components
```

## Permissions Used

```xml
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.INTERNET" />
```

## Dependencies

- AndroidX Core KTX
- AppCompat
- Material Design Components
- ConstraintLayout
- WorkManager (for scheduled notifications)
- Firebase Cloud Messaging (for push notifications)
- Firebase Analytics
- AndroidX Media (for media style notifications)

## Notes

- **Android 13+ (API 33+)**: Requires runtime permission for notifications
- **Android 8.0+ (API 26+)**: Requires notification channels
- **Heads-up notifications**: Only work with HIGH priority
- **Media notifications**: Require ongoing flag to stay persistent
- **FCM**: Requires `google-services.json` configuration file

## Troubleshooting

### Notifications not showing
1. Check notification permission is granted (Settings → Apps → Notifications → Allow)
2. Check notification channel is enabled
3. Check Do Not Disturb mode is off

### FCM not working
1. Ensure `google-services.json` is in the correct location
2. Check package name matches Firebase configuration
3. Verify internet permission is granted
4. Check Firebase project is properly configured

### Heads-up notification not appearing
1. Must use HIGH priority
2. Must set category (e.g., CATEGORY_ALARM)
3. Check phone is not in Do Not Disturb mode

## License

This is a demonstration project for educational purposes.

## Author

Created to demonstrate all Android notification types and FCM integration.

