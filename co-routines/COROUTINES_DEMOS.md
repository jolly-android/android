# Kotlin Coroutines Comprehensive Demo App

This Android app demonstrates various aspects of Kotlin Coroutines with interactive examples.

## Features

The app is organized into 4 main sections with multiple demos in each:

### 1. Basic Coroutines (8 Demos)

#### 1.1 Launch
- Demonstrates basic `launch` coroutine builder
- Shows non-blocking behavior
- Displays thread information

#### 1.2 Multiple Coroutines
- Launches multiple coroutines concurrently
- Shows how coroutines can run in parallel
- Demonstrates completion at different times

#### 1.3 Async/Await
- Shows how to use `async` for concurrent operations
- Demonstrates `await()` to get results
- Compares with sequential execution

#### 1.4 WithContext
- Demonstrates dispatcher switching with `withContext`
- Shows Main, IO thread differences
- Best practice for background operations

#### 1.5 Cancellation
- Shows how to cancel coroutines
- Demonstrates `isActive` check
- Interactive cancel button

#### 1.6 Exception Handling
- Uses `CoroutineExceptionHandler`
- Shows how to handle exceptions in coroutines
- Prevents app crashes

#### 1.7 Sequential vs Parallel
- Direct comparison of execution times
- Shows performance benefits of parallel execution
- Real timing measurements

#### 1.8 Timeout
- Demonstrates `withTimeout`
- Handles `TimeoutCancellationException`
- Use case for unresponsive operations

---

### 2. Flow (12 Demos)

#### 2.1 Simple Flow
- Basic flow creation with `flow { }`
- Emission with `emit()`
- Collection with `collect()`

#### 2.2 Flow Operators
- `filter()` - filtering values
- `map()` - transforming values
- `take()` - limiting emissions
- Chain multiple operators

#### 2.3 Flow Context
- Using `flowOn()` to specify dispatcher
- Thread switching demonstration
- Proper context management

#### 2.4 StateFlow
- Hot flow that always has a value
- State management use case
- UI updates with `collectAsState()`

#### 2.5 SharedFlow
- Multiple collectors receiving same values
- Event broadcasting
- Hot flow behavior

#### 2.6 Flow Builders
- `flowOf()` - from fixed values
- `asFlow()` - from collections
- Quick flow creation

#### 2.7 Flow Exceptions
- Error handling with `catch()`
- Emitting fallback values
- Graceful error recovery

#### 2.8 Combining Flows
- `combine()` operator
- Combining multiple data sources
- Latest values from each flow

#### 2.9 Zip Flows
- `zip()` operator
- Pairing values one-to-one
- Synchronized emission

#### 2.10 Debounce
- `debounce()` for search queries
- `distinctUntilChanged()` to avoid duplicates
- Real search input simulation

#### 2.11 FlatMap
- `flatMapConcat()` transformation
- Flattening nested flows
- Sequential processing

#### 2.12 Buffer
- Performance optimization with `buffer()`
- Decoupling producer and consumer
- Timing comparison

---

### 3. Channels (12 Demos)

#### 3.1 Basic Channel
- Channel creation and usage
- Producer-consumer pattern
- Closing channels

#### 3.2 Buffered Channel
- Channel with capacity
- Non-blocking sends until buffer full
- Buffer size demonstration

#### 3.3 Conflated Channel
- Keeps only latest value
- Useful for UI updates
- Drops intermediate values

#### 3.4 Produce Builder
- Convenient channel creation
- Automatic closing
- Cleaner syntax

#### 3.5 Fan-out
- Multiple consumers from one producer
- Load distribution
- Concurrent processing

#### 3.6 Fan-in
- Multiple producers to one channel
- Message aggregation
- Order demonstration

#### 3.7 Pipeline Pattern
- Chaining multiple processing stages
- Data transformation pipeline
- Modular design

#### 3.8 Select Expression
- Waiting for multiple channels
- First-wins semantics
- Racing operations

#### 3.9 Ticker Channel
- Periodic events
- Timer implementation
- Regular intervals

#### 3.10 Channel vs Flow
- Hot vs Cold streams
- When to use each
- Behavior differences

#### 3.11 Closing Channel
- Proper channel lifecycle
- Iteration after close
- Resource management

#### 3.12 Rendezvous Channel
- No buffer (capacity 0)
- Synchronous handoff
- Producer waits for consumer

---

### 4. Real World Use Cases (12 Demos)

#### 4.1 Sequential API Calls
- Making API calls one after another
- Waiting for each to complete
- Total time measurement

#### 4.2 Parallel API Calls
- Concurrent API requests
- Performance improvement
- Using `async`/`await`

#### 4.3 Retry Logic
- Automatic retry on failure
- Exponential backoff
- Configurable retry attempts

#### 4.4 Timeout Handling
- Network request timeout
- Fallback to cached data
- User experience optimization

#### 4.5 Loading State
- Managing loading indicators
- StateFlow for UI state
- Proper lifecycle handling

#### 4.6 Debounced Search
- Real-time search implementation
- Avoiding excessive API calls
- User input optimization

#### 4.7 Pagination
- Loading data page by page
- Infinite scroll pattern
- Progressive loading

#### 4.8 Caching
- In-memory cache implementation
- Cache-first strategy
- Performance optimization

#### 4.9 Multiple Calls with Errors
- Using `supervisorScope`
- Partial failure handling
- Independent operations

#### 4.10 Polling
- Regular data updates
- Checking for new content
- Background sync pattern

#### 4.11 Repository Pattern
- Clean architecture example
- Flow-based repository
- Separation of concerns

#### 4.12 Combining Data Sources
- Local + Remote data
- Multiple data sources
- Unified data stream

---

## Key Concepts Demonstrated

### Coroutine Builders
- `launch` - Fire and forget
- `async` - Returns a result
- `runBlocking` - Blocks the thread
- `produce` - Creates a channel

### Dispatchers
- `Dispatchers.Main` - UI thread
- `Dispatchers.IO` - Blocking IO operations
- `Dispatchers.Default` - CPU-intensive work

### Flow Types
- **Flow** - Cold stream, starts on collection
- **StateFlow** - Hot stream, always has value
- **SharedFlow** - Hot stream, broadcasts to multiple collectors
- **Channel** - Hot stream with buffering

### Structured Concurrency
- Parent-child relationships
- Automatic cancellation propagation
- Exception handling hierarchy

### Exception Handling
- `CoroutineExceptionHandler`
- `supervisorScope` for independent failures
- `try-catch` blocks
- Flow's `catch()` operator

### Cancellation
- Cooperative cancellation
- `isActive` check
- `ensureActive()`
- Cleanup with `finally`

### Advanced Patterns
- Retry with exponential backoff
- Timeout handling
- Debouncing
- Throttling (via channels)
- Pipeline pattern
- Fan-out/Fan-in

---

## How to Use

1. **Build and Run** the app
2. **Navigate** between tabs: Basic, Flow, Channels, Real World
3. **Tap buttons** to run individual demos
4. **Watch logs** appear in real-time showing coroutine behavior
5. **Clear logs** to start fresh

## Requirements

- Android Studio Arctic Fox or newer
- Minimum SDK: 24
- Target SDK: 36
- Kotlin 2.0.21
- Coroutines 1.8.0

## Dependencies

```kotlin
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0")
```

## Learning Path

### Beginner
1. Start with **Basic Coroutines** tab
2. Run Launch, Multiple Coroutines, and Async/Await
3. Understand the logs and timing

### Intermediate
1. Explore **Flow** tab
2. Focus on StateFlow and SharedFlow
3. Try operators like map, filter, combine

### Advanced
1. Deep dive into **Channels** tab
2. Understand fan-out, fan-in, pipelines
3. Study **Real World** patterns

## Code Structure

```
com.example.coroutines/
├── MainActivity.kt           # Main navigation
├── demos/
│   ├── BasicCoroutinesDemo.kt   # 8 basic demos
│   ├── FlowDemo.kt              # 12 flow demos
│   ├── ChannelsDemo.kt          # 12 channel demos
│   └── RealWorldDemo.kt         # 12 real-world patterns
└── ui/theme/                # Material 3 theme
```

## Best Practices Demonstrated

1. **ViewModelScope** - Automatic cancellation on ViewModel clear
2. **Structured Concurrency** - Proper parent-child relationships
3. **Exception Handling** - Graceful error recovery
4. **Dispatcher Usage** - Right dispatcher for right task
5. **Flow vs Channel** - When to use each
6. **StateFlow for UI** - Reactive UI updates
7. **Repository Pattern** - Clean architecture
8. **Caching** - Performance optimization
9. **Retry Logic** - Network resilience
10. **Loading States** - Better UX

## Common Pitfalls Avoided

- ❌ Blocking the main thread
- ❌ Not handling cancellation
- ❌ Ignoring exceptions
- ❌ Memory leaks from infinite flows
- ❌ Using wrong dispatcher
- ❌ Not closing channels
- ❌ Excessive API calls without debouncing

## Performance Tips

- Use `async` for parallel operations
- Use `buffer()` for producer-consumer patterns
- Use `conflated` channels for UI updates
- Use `debounce()` for search
- Cache data when appropriate
- Use appropriate dispatchers

## Testing Coroutines

Each demo includes timing and logging to help you understand:
- Execution order
- Thread switching
- Performance differences
- Error handling
- Cancellation behavior

## Further Learning

- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- [Android Coroutines Best Practices](https://developer.android.com/kotlin/coroutines)
- [Flow Documentation](https://kotlinlang.org/docs/flow.html)
- [Channel Documentation](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-channel/)

---

## License

This is a demo/educational project. Feel free to use and modify for learning purposes.

