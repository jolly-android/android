# Firebase Setup Instructions

To enable Push Notifications (FCM) in this app, follow these steps:

## Step 1: Create Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click "Add project" or select an existing project
3. Follow the setup wizard

## Step 2: Add Android App to Firebase

1. In Firebase Console, click the Android icon to add Android app
2. Enter package name: `com.example.notifications`
3. (Optional) Add app nickname and debug signing certificate SHA-1
4. Click "Register app"

## Step 3: Download Configuration File

1. Download the `google-services.json` file
2. Place it in the `app/` directory of your project
   ```
   android-external/notifications/app/google-services.json
   ```
3. **Important**: Make sure the file is named exactly `google-services.json`

## Step 4: Enable Cloud Messaging

1. In Firebase Console, go to "Project settings" (gear icon)
2. Navigate to "Cloud Messaging" tab
3. Note down your "Server key" (needed for sending test notifications)

## Step 5: Build and Run

After adding `google-services.json`, rebuild the project:

```bash
./gradlew clean build
./gradlew installDebug
```

## Testing Push Notifications

### Method 1: Firebase Console

1. Launch the app and copy the FCM token
2. In Firebase Console, go to "Cloud Messaging"
3. Click "Send your first message"
4. Enter notification details
5. Click "Send test message"
6. Paste your FCM token
7. Click "Test"

### Method 2: Using cURL

```bash
curl -X POST https://fcm.googleapis.com/fcm/send \
  -H "Authorization: key=YOUR_SERVER_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "to": "YOUR_FCM_TOKEN",
    "notification": {
      "title": "Test Notification",
      "body": "Hello from FCM!"
    },
    "data": {
      "custom_key": "custom_value"
    }
  }'
```

### Method 3: Using Postman

**URL**: `https://fcm.googleapis.com/fcm/send`

**Method**: POST

**Headers**:
```
Authorization: key=YOUR_SERVER_KEY
Content-Type: application/json
```

**Body** (raw JSON):
```json
{
  "to": "YOUR_FCM_TOKEN",
  "notification": {
    "title": "Hello from Postman!",
    "body": "This is a push notification sent via Postman"
  },
  "priority": "high"
}
```

## Troubleshooting

### App won't build
- Ensure `google-services.json` is in the correct location (`app/` directory)
- Check that package name in file matches `com.example.notifications`
- Try "Sync Project with Gradle Files" in Android Studio

### Can't get FCM token
- Check internet connectivity
- Ensure Firebase project is properly configured
- Check logcat for Firebase initialization errors

### Not receiving notifications
- Verify the app is registered with Firebase (check Firebase Console → Project settings → Your apps)
- Ensure notification permission is granted on Android 13+
- Check that your Server Key is correct
- Verify FCM token is current (tokens can expire)

## Additional Resources

- [Firebase Cloud Messaging Documentation](https://firebase.google.com/docs/cloud-messaging)
- [Android Notification Guide](https://developer.android.com/develop/ui/views/notifications)
- [FCM HTTP v1 API](https://firebase.google.com/docs/cloud-messaging/migrate-v1)

## Security Notes

⚠️ **Never commit `google-services.json` to public repositories**

The file contains sensitive API keys. It's already added to `.gitignore` to prevent accidental commits.

For production apps:
- Use environment-specific configuration files
- Implement proper server-side authentication
- Use FCM HTTP v1 API (more secure than legacy API)
- Rotate API keys periodically

