# BroadcastReceiver Demo Application

A comprehensive Android application demonstrating various BroadcastReceiver features and best practices using Kotlin and Jetpack Compose.

## Features Demonstrated

### 1. **Static BroadcastReceivers** (Manifest-Registered)
- **Airplane Mode Receiver**: Detects when airplane mode is toggled on/off
- **Boot Completed Receiver**: Triggers when device finishes booting
- Registered in `AndroidManifest.xml` for system-wide events

### 2. **Dynamic BroadcastReceivers** (Programmatically Registered)
- **Battery Level Receiver**: Monitors battery status, charging state, and percentage
- Demonstrates runtime registration/unregistration
- Shows proper lifecycle management

### 3. **Custom Broadcasts**
- Send and receive app-specific broadcasts
- Pass custom data using Intent extras
- Demonstrates inter-component communication within the app

### 4. **Ordered Broadcasts**
- Three receivers with different priorities (High, Medium, Low)
- Shows sequential processing of broadcasts
- Demonstrates result modification and passing data between receivers
- Higher priority receivers execute first

## Architecture

```
receivers/
├── AirplaneModeReceiver.kt       # Static - Airplane mode changes
├── BootCompletedReceiver.kt       # Static - Device boot completion
├── BatteryLevelReceiver.kt        # Dynamic - Battery monitoring
├── CustomBroadcastReceiver.kt     # Dynamic - Custom broadcasts
└── OrderedBroadcastReceiver.kt    # Dynamic - Ordered broadcast chain
```

## Key Concepts Covered

### Static vs Dynamic Registration

**Static (Manifest):**
- Always listening, even when app is closed
- Good for system events that should wake the app
- Declared in AndroidManifest.xml

**Dynamic (Programmatic):**
- Only active when registered
- Must unregister in onDestroy() to prevent memory leaks
- Better for temporary listeners

### IntentFilter
Defines which broadcasts a receiver listens for:
```kotlin
val filter = IntentFilter().apply {
    addAction(Intent.ACTION_BATTERY_CHANGED)
    addAction(Intent.ACTION_BATTERY_LOW)
}
```

### Priority in Ordered Broadcasts
```kotlin
val filter = IntentFilter(ACTION).apply {
    priority = 1000  // Higher number = higher priority
}
```

## Usage Instructions

### Testing System Broadcasts

1. **Airplane Mode**:
   - Open device settings
   - Toggle airplane mode on/off
   - Toast message will appear showing the state

2. **Boot Completed**:
   - Requires device restart
   - Receiver will trigger after boot completes

### Testing Dynamic Receivers

1. **Battery Monitoring**:
   - Tap "Register" button in Battery Monitoring section
   - Current battery info will appear in logs
   - Plug/unplug charger to see updates
   - Tap "Unregister" to stop monitoring

2. **Custom Broadcasts**:
   - Tap "Register" in Custom Broadcasts section
   - Tap "Send Custom Broadcast" to send
   - Watch the logs to see the received data
   - Each broadcast includes timestamp and counter

3. **Ordered Broadcasts**:
   - Tap "Register" in Ordered Broadcasts section
   - Tap "Send Ordered Broadcast"
   - Observe logs showing sequential processing:
     - High Priority receives first
     - Medium Priority receives second (with modified data)
     - Low Priority receives last (with final data)
   - Notice how each receiver modifies resultData

## Permissions Required

```xml
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
```

## Best Practices Implemented

1. **Proper Lifecycle Management**
   - Unregister receivers in `onDestroy()`
   - Check if receiver is already registered before registering again

2. **Android 13+ Compatibility**
   - Uses `RECEIVER_NOT_EXPORTED` flag for API 33+
   - Properly handles version-specific behavior

3. **Memory Leak Prevention**
   - Nullifies receiver references after unregistering
   - Limits log size to 50 entries

4. **User Feedback**
   - Real-time logs showing all broadcast events
   - Clear visual indication of receiver state
   - Timestamped log entries

5. **Modern Android Development**
   - Jetpack Compose UI
   - Material Design 3
   - Kotlin best practices

## UI Components

- **Section Cards**: Organized by broadcast type
- **Control Buttons**: Register/Unregister receivers
- **Action Buttons**: Send broadcasts
- **Log Display**: Real-time broadcast monitoring
- **Clear Logs**: Reset log history

## Building and Running

1. Open project in Android Studio
2. Sync Gradle files
3. Run on device or emulator (API 24+)
4. Grant necessary permissions if prompted

## Testing Tips

- Use Android Studio's Logcat to see detailed logs (filter by receiver names)
- Test battery broadcasts by plugging/unplugging USB cable
- For boot receiver, use emulator cold boot or device restart
- Custom and ordered broadcasts work immediately after registration

## API Requirements

- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 36
- **Compile SDK**: 36

## Dependencies

- Kotlin 1.9+
- Jetpack Compose
- Material Design 3
- AndroidX Core KTX
- AndroidX Lifecycle Runtime KTX

## Learning Outcomes

After exploring this demo, you'll understand:
- Different types of BroadcastReceivers
- When to use static vs dynamic registration
- How to handle system broadcasts
- Creating custom broadcasts
- Ordered broadcast chains
- Proper receiver lifecycle management
- Android security best practices

## Code Structure

### MainActivity.kt
- Manages all dynamic receivers
- Handles registration/unregistration
- Maintains broadcast log
- Provides Compose UI

### Receiver Classes
Each receiver demonstrates specific functionality with clear comments and logging.

## Troubleshooting

**Receiver not receiving broadcasts:**
- Check if receiver is registered (check logs)
- Verify IntentFilter actions match broadcast intent
- For system broadcasts, check permissions

**Memory leaks:**
- Ensure unregisterReceiver() is called in onDestroy()
- Don't register same receiver multiple times

**Ordered broadcasts not working:**
- Make sure all receivers are registered before sending
- Check priority values (higher = earlier)

## License

This is a demo application for educational purposes.

