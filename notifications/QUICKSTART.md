# Quick Start Guide

Get the Android Notifications Demo app running in 5 minutes!

## Prerequisites

- Android Studio (Arctic Fox or later)
- Android device or emulator (API 24+)
- ⏱️ Estimated time: 5 minutes

## Quick Setup (Without Push Notifications)

If you just want to see local notifications without FCM:

1. **Open Project**
   ```bash
   # Open the project in Android Studio
   # File → Open → Select notifications folder
   ```

2. **Sync Gradle**
   - Android Studio will prompt to sync Gradle
   - Click "Sync Now"

3. **Run the App**
   - Click the green "Run" button or press `Shift + F10`
   - Select your device/emulator
   - Wait for installation

4. **Grant Permission**
   - On Android 13+, tap "Allow" when prompted for notification permission
   - If you missed it, go to Settings → Apps → Notifications → Allow notifications

5. **Test Notifications**
   - Tap any button to see different notification types!
   - Try "Basic Notification" first
   - Explore all 11 different notification styles

## Full Setup (With Push Notifications)

To enable FCM push notifications:

1. **Follow Steps 1-4 above**

2. **Setup Firebase**
   - See [FIREBASE_SETUP.md](FIREBASE_SETUP.md) for detailed instructions
   - Quick steps:
     - Create Firebase project
     - Add Android app (package: `com.example.notifications`)
     - Download `google-services.json`
     - Place in `app/` directory
     - Rebuild project

3. **Test Push Notifications**
   - Launch app
   - Copy FCM token (displayed at bottom)
   - Send test notification from Firebase Console

## What You Can Test

### Local Notifications (No setup required)
✅ Basic Notification
✅ Big Text Notification
✅ Big Picture Notification
✅ Inbox Style Notification
✅ Action Buttons Notification
✅ Progress Notification
✅ Heads-Up Notification
✅ Grouped Notifications
✅ Custom Styled Notification
✅ Media Style Notification
✅ Scheduled Notification (10 seconds delay)

### Push Notifications (Requires Firebase setup)
🔔 FCM Push Notifications

## Troubleshooting

### Build fails with "google-services.json missing"
**Solution**: Either:
- Option A: Add real `google-services.json` (see FIREBASE_SETUP.md)
- Option B: Comment out the google-services plugin temporarily:
  ```kotlin
  // In app/build.gradle.kts
  plugins {
      alias(libs.plugins.android.application)
      alias(libs.plugins.kotlin.android)
      // id("com.google.gms.google-services")  // Comment this line
  }
  ```

### Notifications not showing
- ✅ Check notification permission is granted
- ✅ Disable Do Not Disturb mode
- ✅ Enable notifications for the app in system settings

### Heads-up notification doesn't appear on top
- ℹ️ This is normal on some devices
- ℹ️ Check Do Not Disturb settings
- ℹ️ Some OEMs restrict heads-up notifications

## Next Steps

1. **Explore the code**
   - `NotificationHelper.kt` - See how each notification is created
   - `MainActivity.kt` - See how notifications are triggered
   - `MyFirebaseMessagingService.kt` - See how FCM messages are handled

2. **Customize**
   - Modify notification content
   - Change colors and icons
   - Add your own notification types

3. **Learn more**
   - Read [README.md](README.md) for complete documentation
   - Check [FIREBASE_SETUP.md](FIREBASE_SETUP.md) for FCM setup

## Support

Having issues? Check:
- README.md - Full documentation
- FIREBASE_SETUP.md - Firebase setup guide
- Android Studio Logcat for error messages

## Summary

```bash
# 1. Clone or download the project
# 2. Open in Android Studio
# 3. Sync Gradle
# 4. Run on device/emulator
# 5. Grant notification permission
# 6. Tap buttons to see notifications!
```

That's it! 🎉 You now have a working notifications demo app.

