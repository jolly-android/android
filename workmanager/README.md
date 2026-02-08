# WorkManager Demo

A comprehensive Android demo application showcasing various features of AndroidX WorkManager for background task execution.

## Features

This demo application demonstrates the following WorkManager capabilities:

### 1. **Simple Work** 
- Basic one-time work execution
- Passing input data to workers
- Receiving output data from workers

### 2. **Progress Work**
- Real-time progress tracking during work execution
- Updating UI with progress information
- Using `CoroutineWorker` for async operations

### 3. **Constrained Work**
- Running work only when specific constraints are met
- Network connectivity requirements
- Battery level constraints

### 4. **Chained Work**
- Sequential execution of multiple workers (A → B → C)
- Data passing between chained workers
- Dependency management

### 5. **Periodic Work**
- Scheduling work to run periodically (minimum 15 minutes interval)
- Handling repeating background tasks

### 6. **Notification Work**
- Showing notifications when work completes
- Notification channel creation for Android O+
- Delayed work execution

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/workmanagerdemo/
│   │   ├── MainActivity.kt                    # Main activity with UI
│   │   ├── adapters/
│   │   │   └── WorkInfoAdapter.kt            # RecyclerView adapter for work status
│   │   └── workers/
│   │       ├── SimpleWorker.kt               # Basic worker example
│   │       ├── ProgressWorker.kt             # Progress reporting worker
│   │       ├── NotificationWorker.kt         # Notification showing worker
│   │       ├── ChainWorker.kt                # Workers for chaining demo
│   │       ├── PeriodicWorker.kt             # Periodic work example
│   │       └── ConstrainedWorker.kt          # Constrained work example
│   └── res/
│       ├── layout/
│       │   ├── activity_main.xml             # Main activity layout
│       │   └── item_work_info.xml            # Work info item layout
│       ├── drawable/                          # Vector icons
│       └── values/                            # Colors, strings, themes
```

## Requirements

- **Android Studio**: Arctic Fox or later
- **Minimum SDK**: 21 (Android 5.0)
- **Target SDK**: 34 (Android 14)
- **Kotlin**: 1.9.0
- **Gradle**: 8.1.0

## Dependencies

- **AndroidX Core KTX**: 1.12.0
- **AndroidX AppCompat**: 1.6.1
- **Material Design**: 1.11.0
- **WorkManager**: 2.9.0
- **Lifecycle Components**: 2.7.0
- **RecyclerView**: 1.3.2

## Getting Started

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd workmanager
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the project directory

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run on device/emulator**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio

## How to Use

### Running Different Work Types

1. **Simple Work**: Tap "Simple Work" to execute a basic background task
2. **Progress Work**: Tap "Progress Work" to see real-time progress updates
3. **Constrained Work**: Tap "Constrained Work" to queue work that runs only when device is connected to network and battery is not low
4. **Chained Work**: Tap "Chained Work (A → B → C)" to see sequential work execution
5. **Periodic Work**: Tap "Periodic Work" to schedule repeating work every 15 minutes
6. **Notification Work**: Tap "Notification Work" to receive a notification after 3 seconds

### Monitoring Work Status

- The "Active Work Status" section shows real-time information about running work
- Each work item displays:
  - Work name and ID
  - Current state (ENQUEUED, RUNNING, SUCCEEDED, FAILED, etc.)
  - Output data when available
  - Timestamp of last update

### Progress Tracking

- The "Work Progress" section displays progress for ProgressWorker
- Shows percentage completion and current step

### Cancelling Work

- Tap "Cancel All Work" to cancel all queued and running work

## Key Concepts Demonstrated

### WorkManager Features

1. **One-time Requests**: `OneTimeWorkRequest`
2. **Periodic Requests**: `PeriodicWorkRequest`
3. **Work Constraints**: Network, battery, storage constraints
4. **Work Chaining**: Sequential and parallel work execution
5. **Input/Output Data**: Passing data to and from workers
6. **Work States**: Observing work lifecycle states
7. **Unique Work**: Handling duplicate work requests
8. **Progress Updates**: Reporting progress during execution

### Android Best Practices

1. **Material Design 3**: Modern UI with Material components
2. **ViewBinding**: Type-safe view access
3. **Lifecycle Awareness**: LiveData for UI updates
4. **Proper Permissions**: Runtime permission requests for notifications
5. **Notification Channels**: Android O+ notification handling

## Testing Work

### Testing Constraints

To test constrained work:
- **Network constraint**: Toggle WiFi/mobile data on device
- **Battery constraint**: Adjust battery level or charging state

### Observing Logs

View detailed logs in Logcat with these tags:
- `SimpleWorker`
- `ProgressWorker`
- `NotificationWorker`
- `ChainWorkerA/B/C`
- `PeriodicWorker`
- `ConstrainedWorker`

## WorkManager Benefits

- **Guaranteed execution**: Work will run even if app is killed
- **Constraint awareness**: Defers work until conditions are met
- **Battery efficient**: Optimizes battery usage
- **Backwards compatible**: Works on API 14+
- **Easy to use**: Simple, powerful API

## Learn More

- [WorkManager Official Documentation](https://developer.android.com/topic/libraries/architecture/workmanager)
- [WorkManager Codelab](https://developer.android.com/codelabs/android-workmanager)
- [Background Processing Guide](https://developer.android.com/guide/background)

## License

This is a demo project for educational purposes.

## Contributing

Feel free to submit issues and enhancement requests!

---

**Happy Coding!** 🚀


