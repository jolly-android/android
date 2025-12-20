# Quick Start Guide

## What Was Created

Your Kotlin Coroutines Demo App includes:

### ✅ 4 Demo Modules

1. **BasicCoroutinesDemo.kt** - 8 fundamental coroutine examples
2. **FlowDemo.kt** - 12 Flow-related demonstrations
3. **ChannelsDemo.kt** - 12 Channel examples
4. **RealWorldDemo.kt** - 12 practical use cases

### ✅ Features

- Tab-based navigation
- Real-time logging
- Interactive buttons for each demo
- Loading state indicators
- Material Design 3 UI
- Clean MVVM architecture

## How to Run

### Step 1: Sync Gradle
```bash
# In Android Studio:
File → Sync Project with Gradle Files
```

Or via command line:
```bash
./gradlew build
```

### Step 2: Run on Device/Emulator
```bash
# Via Android Studio:
Click the green ▶️ Run button

# Or via command line:
./gradlew installDebug
```

### Step 3: Explore Demos

1. **Basic Tab** - Start here if you're new to coroutines
   - Try "Launch" to see basic coroutine
   - Try "Sequential vs Parallel" to see performance differences

2. **Flow Tab** - Learn reactive streams
   - Try "StateFlow" to see UI updates
   - Try "Debounce" to see search optimization

3. **Channels Tab** - Understand hot streams
   - Try "Basic" to see producer-consumer
   - Try "Pipeline" to see data processing chains

4. **Real World Tab** - Apply to real scenarios
   - Try "Parallel" to see concurrent API calls
   - Try "Caching" to see optimization patterns

## Project Structure

```
co-routines/
├── README.md                    # Project overview
├── COROUTINES_DEMOS.md         # Detailed documentation
├── QUICK_START.md              # This file
├── app/
│   ├── build.gradle.kts        # App dependencies (✅ Updated)
│   └── src/main/java/com/example/coroutines/
│       ├── MainActivity.kt      # Main navigation (✅ Updated)
│       └── demos/
│           ├── BasicCoroutinesDemo.kt   # ✅ Created
│           ├── FlowDemo.kt              # ✅ Created
│           ├── ChannelsDemo.kt          # ✅ Created
│           └── RealWorldDemo.kt         # ✅ Created
└── gradle/
    └── libs.versions.toml       # Version catalog (✅ Updated)
```

## Dependencies Added

```toml
[versions]
coroutines = "1.8.0"
lifecycleViewmodel = "2.10.0"

[libraries]
kotlinx-coroutines-android = "..."
kotlinx-coroutines-core = "..."
androidx-lifecycle-viewmodel-compose = "..."
```

## Demo Categories

### 1. Basic Coroutines (8)
- Launch, Multiple, Async/Await, WithContext
- Cancellation, Exceptions, Sequential vs Parallel, Timeout

### 2. Flow (12)
- Simple Flow, Operators, Context, StateFlow
- SharedFlow, Builders, Exceptions, Combine
- Zip, Debounce, FlatMap, Buffer

### 3. Channels (12)
- Basic, Buffered, Conflated, Produce
- Fan-out, Fan-in, Pipeline, Select
- Ticker, Hot Stream, Closing, Rendezvous

### 4. Real World (12)
- Sequential/Parallel APIs, Retry, Timeout
- Loading State, Search, Pagination, Caching
- Error Handling, Polling, Repository, Combine Sources

## Tips for Learning

1. **Read the logs carefully** - They show execution order and timing
2. **Try demos multiple times** - Coroutines can behave differently
3. **Check thread names** - Understand dispatcher switching
4. **Compare timing** - See performance differences
5. **Experiment with code** - Modify delays and parameters

## Common Commands

```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug

# Run app
./gradlew installDebug
adb shell am start -n com.example.coroutines/.MainActivity

# View logs
adb logcat | grep -i coroutines
```

## Troubleshooting

### Gradle Sync Issues
```bash
./gradlew clean
./gradlew build --refresh-dependencies
```

### Emulator Not Found
```bash
# List emulators
emulator -list-avds

# Start emulator
emulator -avd <avd_name>
```

### App Crashes
- Check Android version (min SDK 24)
- Check logcat for error messages
- Ensure Gradle sync completed successfully

## Next Steps

1. ✅ Run the app
2. 📱 Try all demos in each tab
3. 📖 Read COROUTINES_DEMOS.md for details
4. 🔧 Modify code to experiment
5. 🚀 Apply patterns to your own projects

## Key Takeaways

After exploring all demos, you should understand:

- ✅ How to launch and manage coroutines
- ✅ When to use Flow vs Channel
- ✅ How to handle errors and cancellation
- ✅ Best practices for Android apps
- ✅ Real-world patterns for production code

---

**Happy Learning! 🚀**

For questions or issues, refer to:
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- [Android Coroutines](https://developer.android.com/kotlin/coroutines)

