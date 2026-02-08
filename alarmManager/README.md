# Android AlarmManager Demo

A comprehensive demonstration of Android's AlarmManager API showcasing various alarm types and scheduling strategies.

## Features

This demo application demonstrates the following AlarmManager features:

### 1. **One-Time Alarm (RTC_WAKEUP)**
- Fires once at a specific time
- Wakes up the device from sleep mode
- Uses `setExactAndAllowWhileIdle()` for precise timing
- Demo: Triggers after 10 seconds

### 2. **Repeating Alarm**
- Fires at fixed intervals
- Uses `setRepeating()` method
- Note: On Android 4.4+ (API 19+), these alarms are inexact
- Demo: 15-second intervals

### 3. **Inexact Repeating Alarm**
- More battery-efficient than exact repeating alarms
- System batches alarms together to minimize wake-ups
- Uses `setInexactRepeating()` method
- Demo: Approximately 1-minute intervals

### 4. **Elapsed Realtime Alarm**
- Based on time since device boot (including sleep time)
- Uses `ELAPSED_REALTIME_WAKEUP` alarm type
- Useful when you need timing relative to boot time
- Demo: Triggers after 20 seconds from boot time

### 5. **Idle Window Alarm (Android M+)**
- Fires during device idle maintenance windows
- Optimized for battery life
- Uses `setAndAllowWhileIdle()` method
- Demo: Approximately 2-minute window

### 6. **Window Alarm (Android KitKat+)**
- Fires within a flexible time window
- Allows system to optimize battery usage
- Uses `setWindow()` method
- Demo: 15-25 second window

## Permissions

The app requires the following permissions:

- `SCHEDULE_EXACT_ALARM` - For scheduling exact alarms (Android 12+)
- `USE_EXACT_ALARM` - Alternative permission for exact alarms
- `POST_NOTIFICATIONS` - For showing notifications (Android 13+)
- `VIBRATE` - For vibrating on alarm trigger
- `WAKE_LOCK` - For waking up the device
- `RECEIVE_BOOT_COMPLETED` - For rescheduling alarms after device reboot

## Components

### AlarmReceiver
- BroadcastReceiver that handles alarm broadcasts
- Creates and displays notifications when alarms trigger
- Shows alarm type, ID, and custom message

### AlarmScheduler
- Helper class for managing all alarm operations
- Provides methods for scheduling different types of alarms
- Handles cancellation of individual or all alarms
- Checks for exact alarm permissions

### MainActivity
- Compose-based UI showcasing all alarm types
- Interactive buttons to schedule and cancel each alarm type
- Permission status indicators
- Informative cards explaining each alarm type

## How to Use

1. **Grant Permissions**: On Android 12+, tap "Grant Permission" if prompted for exact alarms
2. **Schedule Alarms**: Tap any "Set Alarm" button to schedule that type of alarm
3. **Receive Notifications**: When an alarm triggers, you'll receive a notification
4. **Cancel Alarms**: Use individual "Cancel" buttons or "Cancel All Alarms" button

## Technical Details

### Alarm Types

| Type | Clock | Wakes Device | Use Case |
|------|-------|--------------|----------|
| RTC_WAKEUP | Real-time | Yes | Specific wall clock time, important |
| RTC | Real-time | No | Specific time, not critical |
| ELAPSED_REALTIME_WAKEUP | Boot time | Yes | Relative to boot, important |
| ELAPSED_REALTIME | Boot time | No | Relative to boot, not critical |

### API Level Considerations

- **Android 4.4 (KitKat, API 19)**: Introduced `setWindow()` and made repeating alarms inexact
- **Android 6.0 (Marshmallow, API 23)**: Introduced Doze mode and `setAndAllowWhileIdle()`
- **Android 12 (S, API 31)**: Requires `SCHEDULE_EXACT_ALARM` permission for exact alarms
- **Android 13 (Tiramisu, API 33)**: Requires `POST_NOTIFICATIONS` permission

### Best Practices Demonstrated

1. ✅ Using `PendingIntent.FLAG_IMMUTABLE` for security
2. ✅ Handling different Android versions with appropriate API checks
3. ✅ Creating notification channels for Android O+
4. ✅ Requesting runtime permissions when needed
5. ✅ Using appropriate alarm types for different use cases
6. ✅ Properly canceling alarms to prevent memory leaks
7. ✅ Logging for debugging purposes

## Building and Running

1. Open the project in Android Studio
2. Sync Gradle files
3. Run on a device or emulator (minimum SDK 24)
4. Grant necessary permissions when prompted

## Testing Alarms

- **Short intervals** (10-20 seconds): For quick testing
- **Notifications**: Check notification when alarm fires
- **Logs**: Monitor Logcat for "AlarmReceiver" and "AlarmScheduler" tags
- **Cancellation**: Test cancel functionality before alarms trigger

## Code Structure

```
app/src/main/java/com/example/alarmmanager/
├── AlarmReceiver.kt        # Handles alarm broadcasts
├── AlarmScheduler.kt       # Manages alarm scheduling
├── MainActivity.kt         # UI and user interaction
└── ui/theme/              # Material 3 theme components
```

## Notes

- Repeating alarms may not fire at exact intervals on modern Android versions (optimization)
- Exact alarms should be used sparingly as they consume more battery
- Consider using WorkManager for tasks that don't require exact timing
- Alarms are cancelled when the app is uninstalled
- For production apps, consider implementing alarm rescheduling after device reboot

## License

This is a demo application for educational purposes.

