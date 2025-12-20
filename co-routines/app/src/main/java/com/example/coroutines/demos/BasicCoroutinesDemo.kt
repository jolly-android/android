package com.example.coroutines.demos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class BasicCoroutinesViewModel : ViewModel() {
    private val _logs = mutableStateListOf<String>()
    val logs: List<String> = _logs
    
    fun clearLogs() {
        _logs.clear()
    }
    
    private fun addLog(message: String) {
        _logs.add("${_logs.size + 1}. $message")
    }
    
    // Demo 1: Basic launch
    fun demoLaunch() {
        addLog("Starting launch demo...")
        viewModelScope.launch {
            addLog("Inside coroutine - Thread: ${Thread.currentThread().name}")
            delay(1000)
            addLog("After 1 second delay")
        }
        addLog("After launch (non-blocking)")
    }
    
    // Demo 2: Multiple coroutines
    fun demoMultipleCoroutines() {
        addLog("Starting multiple coroutines...")
        viewModelScope.launch {
            launch {
                delay(500)
                addLog("Coroutine 1 completed")
            }
            launch {
                delay(1000)
                addLog("Coroutine 2 completed")
            }
            launch {
                delay(1500)
                addLog("Coroutine 3 completed")
            }
            addLog("All coroutines launched")
        }
    }
    
    // Demo 3: async/await
    fun demoAsyncAwait() {
        addLog("Starting async/await demo...")
        viewModelScope.launch {
            val deferred1 = async {
                delay(1000)
                addLog("Async 1 computing...")
                return@async 10
            }
            
            val deferred2 = async {
                delay(1500)
                addLog("Async 2 computing...")
                return@async 20
            }
            
            addLog("Waiting for results...")
            val result1 = deferred1.await()
            val result2 = deferred2.await()
            addLog("Results: $result1 + $result2 = ${result1 + result2}")
        }
    }
    
    // Demo 4: withContext - switching dispatchers
    fun demoWithContext() {
        addLog("Starting withContext demo...")
        viewModelScope.launch {
            addLog("Main thread: ${Thread.currentThread().name}")
            
            val result = withContext(Dispatchers.IO) {
                addLog("IO thread: ${Thread.currentThread().name}")
                delay(1000)
                "Data from IO thread"
            }
            
            addLog("Back on Main thread: ${Thread.currentThread().name}")
            addLog("Result: $result")
        }
    }
    
    // Demo 5: Job cancellation
    private var cancellableJob: Job? = null
    
    fun demoCancellation() {
        addLog("Starting cancellation demo...")
        cancellableJob = viewModelScope.launch {
            repeat(5) { i ->
                if (isActive) {
                    addLog("Working... step ${i + 1}")
                    delay(1000)
                } else {
                    addLog("Job was cancelled!")
                }
            }
            addLog("Job completed normally")
        }
    }
    
    fun cancelJob() {
        cancellableJob?.cancel()
        addLog("Job cancellation requested")
    }
    
    // Demo 6: Exception handling
    fun demoExceptionHandling() {
        addLog("Starting exception handling demo...")
        
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            addLog("Caught exception: ${throwable.message}")
        }
        
        viewModelScope.launch(exceptionHandler) {
            addLog("About to throw exception...")
            delay(500)
            throw RuntimeException("Demo exception!")
        }
    }
    
    // Demo 7: Sequential vs Parallel execution
    fun demoSequentialVsParallel() {
        addLog("=== Sequential Execution ===")
        viewModelScope.launch {
            val startTime = System.currentTimeMillis()
            
            val result1 = doWork("Task 1", 1000)
            val result2 = doWork("Task 2", 1000)
            
            val sequentialTime = System.currentTimeMillis() - startTime
            addLog("Sequential total time: ${sequentialTime}ms")
            addLog("Results: $result1, $result2")
            
            addLog("\n=== Parallel Execution ===")
            val startTime2 = System.currentTimeMillis()
            
            val deferred1 = async { doWork("Task 3", 1000) }
            val deferred2 = async { doWork("Task 4", 1000) }
            
            val result3 = deferred1.await()
            val result4 = deferred2.await()
            
            val parallelTime = System.currentTimeMillis() - startTime2
            addLog("Parallel total time: ${parallelTime}ms")
            addLog("Results: $result3, $result4")
        }
    }
    
    private suspend fun doWork(name: String, delayMs: Long): String {
        addLog("$name started")
        delay(delayMs)
        addLog("$name completed")
        return "$name result"
    }
    
    // Demo 8: Timeout
    fun demoTimeout() {
        addLog("Starting timeout demo...")
        viewModelScope.launch {
            try {
                withTimeout(2000) {
                    addLog("Starting long operation...")
                    delay(3000) // This will timeout
                    addLog("This won't be printed")
                }
            } catch (e: TimeoutCancellationException) {
                addLog("Operation timed out!")
            }
        }
    }
}

@Composable
fun BasicCoroutinesDemo(viewModel: BasicCoroutinesViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Basic Coroutines Demos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Control buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoLaunch() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Launch")
                }
                Button(
                    onClick = { viewModel.demoMultipleCoroutines() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Multiple")
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoAsyncAwait() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Async/Await")
                }
                Button(
                    onClick = { viewModel.demoWithContext() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("WithContext")
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoCancellation() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Start Cancellable")
                }
                Button(
                    onClick = { viewModel.cancelJob() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Cancel")
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoExceptionHandling() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Exception")
                }
                Button(
                    onClick = { viewModel.demoTimeout() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Timeout")
                }
            }
            
            Button(
                onClick = { viewModel.demoSequentialVsParallel() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sequential vs Parallel")
            }
            
            Button(
                onClick = { viewModel.clearLogs() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Clear Logs")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Logs display
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                if (viewModel.logs.isEmpty()) {
                    Text(
                        text = "Tap a button to see coroutine demos",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    viewModel.logs.forEach { log ->
                        Text(
                            text = log,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

