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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowViewModel : ViewModel() {
    private val _logs = mutableStateListOf<String>()
    val logs: List<String> = _logs
    
    private val _flowValue = MutableStateFlow<String>("No value yet")
    val flowValue: StateFlow<String> = _flowValue.asStateFlow()
    
    fun clearLogs() {
        _logs.clear()
    }
    
    private fun addLog(message: String) {
        _logs.add("${_logs.size + 1}. $message")
    }
    
    // Demo 1: Simple Flow
    fun demoSimpleFlow() {
        addLog("=== Simple Flow ===")
        viewModelScope.launch {
            simpleFlow().collect { value ->
                addLog("Collected: $value")
            }
        }
    }
    
    private fun simpleFlow(): Flow<Int> = flow {
        addLog("Flow started")
        for (i in 1..5) {
            delay(500)
            addLog("Emitting $i")
            emit(i)
        }
        addLog("Flow completed")
    }
    
    // Demo 2: Flow Operators
    fun demoFlowOperators() {
        addLog("=== Flow Operators ===")
        viewModelScope.launch {
            (1..10).asFlow()
                .filter { 
                    addLog("Filter: checking $it")
                    it % 2 == 0 
                }
                .map { 
                    addLog("Map: transforming $it to ${it * it}")
                    it * it 
                }
                .take(3)
                .collect { value ->
                    addLog("Final result: $value")
                }
        }
    }
    
    // Demo 3: Flow on different dispatchers
    fun demoFlowContext() {
        addLog("=== Flow Context ===")
        viewModelScope.launch {
            flow {
                addLog("Flow on: ${Thread.currentThread().name}")
                emit(1)
            }
            .flowOn(kotlinx.coroutines.Dispatchers.IO)
            .collect { value ->
                addLog("Collected on: ${Thread.currentThread().name}")
                addLog("Value: $value")
            }
        }
    }
    
    // Demo 4: StateFlow
    fun demoStateFlow() {
        addLog("=== StateFlow Demo ===")
        viewModelScope.launch {
            repeat(5) { i ->
                delay(500)
                _flowValue.value = "Update ${i + 1}"
                addLog("StateFlow value: ${_flowValue.value}")
            }
        }
    }
    
    // Demo 5: SharedFlow
    private val _sharedFlow = MutableSharedFlow<String>()
    
    fun demoSharedFlow() {
        addLog("=== SharedFlow Demo ===")
        
        // Collector 1
        viewModelScope.launch {
            _sharedFlow.collect { value ->
                addLog("Collector 1: $value")
            }
        }
        
        // Collector 2
        viewModelScope.launch {
            _sharedFlow.collect { value ->
                addLog("Collector 2: $value")
            }
        }
        
        // Emit values
        viewModelScope.launch {
            delay(500)
            repeat(3) { i ->
                val value = "Event ${i + 1}"
                addLog("Emitting: $value")
                _sharedFlow.emit(value)
                delay(500)
            }
        }
    }
    
    // Demo 6: Flow Builder - flowOf, asFlow
    fun demoFlowBuilders() {
        addLog("=== Flow Builders ===")
        viewModelScope.launch {
            addLog("flowOf:")
            flowOf(1, 2, 3, 4, 5).collect { 
                addLog("  $it")
            }
            
            addLog("asFlow:")
            listOf("A", "B", "C").asFlow().collect {
                addLog("  $it")
            }
        }
    }
    
    // Demo 7: Flow Exception Handling
    fun demoFlowExceptions() {
        addLog("=== Flow Exceptions ===")
        viewModelScope.launch {
            flow {
                emit(1)
                emit(2)
                throw RuntimeException("Flow error!")
            }
            .catch { e ->
                addLog("Caught exception: ${e.message}")
                emit(-1) // Emit fallback value
            }
            .collect { value ->
                addLog("Collected: $value")
            }
        }
    }
    
    // Demo 8: Combining Flows
    fun demoCombineFlows() {
        addLog("=== Combining Flows ===")
        
        val flow1 = flow {
            repeat(3) {
                emit("A$it")
                delay(300)
            }
        }
        
        val flow2 = flow {
            repeat(3) {
                emit("B$it")
                delay(500)
            }
        }
        
        viewModelScope.launch {
            flow1.combine(flow2) { a, b ->
                "$a + $b"
            }.collect { combined ->
                addLog("Combined: $combined")
            }
        }
    }
    
    // Demo 9: Zip Flows
    fun demoZipFlows() {
        addLog("=== Zip Flows ===")
        
        val numbers = (1..3).asFlow().onEach { delay(300) }
        val letters = flowOf("A", "B", "C").onEach { delay(400) }
        
        viewModelScope.launch {
            numbers.zip(letters) { num, letter ->
                "$num$letter"
            }.collect { result ->
                addLog("Zipped: $result")
            }
        }
    }
    
    // Demo 10: Debounce and DistinctUntilChanged
    fun demoDebounce() {
        addLog("=== Debounce Demo ===")
        
        val searchFlow = MutableStateFlow("")
        
        viewModelScope.launch {
            searchFlow
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    addLog("Search for: '$query'")
                }
        }
        
        viewModelScope.launch {
            val queries = listOf("a", "ab", "abc", "abc", "abcd")
            queries.forEach { query ->
                searchFlow.value = query
                addLog("Typed: '$query'")
                delay(300)
            }
        }
    }
    
    // Demo 11: FlatMap operations
    fun demoFlatMap() {
        addLog("=== FlatMap Demo ===")
        viewModelScope.launch {
            (1..3).asFlow()
                .onEach { addLog("Processing $it") }
                .flatMapConcat { value ->
                    flow {
                        emit("$value-A")
                        delay(100)
                        emit("$value-B")
                    }
                }
                .collect { result ->
                    addLog("Result: $result")
                }
        }
    }
    
    // Demo 12: Buffer
    fun demoBuffer() {
        addLog("=== Buffer Demo ===")
        viewModelScope.launch {
            val startTime = System.currentTimeMillis()
            
            (1..5).asFlow()
                .onEach { 
                    delay(100) // Simulate production
                    addLog("Emitting $it")
                }
                .buffer() // Buffer emissions
                .collect { value ->
                    delay(300) // Simulate slow collection
                    addLog("Collected $value")
                }
            
            val totalTime = System.currentTimeMillis() - startTime
            addLog("Total time: ${totalTime}ms")
        }
    }
}

@Composable
fun FlowDemo(viewModel: FlowViewModel) {
    val flowValue by viewModel.flowValue.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Flow Demos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = "StateFlow Value: $flowValue",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
        
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
                    onClick = { viewModel.demoSimpleFlow() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Simple Flow", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoFlowOperators() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Operators", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoFlowContext() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Context", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoStateFlow() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("StateFlow", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoSharedFlow() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("SharedFlow", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoFlowBuilders() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Builders", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoFlowExceptions() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Exceptions", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoCombineFlows() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Combine", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoZipFlows() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Zip", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoDebounce() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Debounce", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoFlatMap() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("FlatMap", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoBuffer() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Buffer", style = MaterialTheme.typography.bodySmall)
                }
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
                        text = "Tap a button to see Flow demos",
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

