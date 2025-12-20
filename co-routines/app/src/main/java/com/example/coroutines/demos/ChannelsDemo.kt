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
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select

class ChannelsViewModel : ViewModel() {
    private val _logs = mutableStateListOf<String>()
    val logs: List<String> = _logs
    
    fun clearLogs() {
        _logs.clear()
    }
    
    private fun addLog(message: String) {
        _logs.add("${_logs.size + 1}. $message")
    }
    
    // Demo 1: Basic Channel
    fun demoBasicChannel() {
        addLog("=== Basic Channel ===")
        viewModelScope.launch {
            val channel = Channel<Int>()
            
            // Producer
            launch {
                for (x in 1..5) {
                    addLog("Sending: $x")
                    channel.send(x)
                    delay(500)
                }
                channel.close()
                addLog("Channel closed by producer")
            }
            
            // Consumer
            launch {
                for (y in channel) {
                    addLog("Received: $y")
                }
                addLog("Consumer finished")
            }
        }
    }
    
    // Demo 2: Channel with buffer
    fun demoBufferedChannel() {
        addLog("=== Buffered Channel ===")
        viewModelScope.launch {
            val channel = Channel<Int>(capacity = 3)
            
            launch {
                repeat(5) { i ->
                    addLog("Sending: $i (buffer available)")
                    channel.send(i)
                    addLog("Sent: $i")
                }
                channel.close()
            }
            
            delay(2000) // Wait before consuming
            addLog("Starting to consume...")
            
            for (value in channel) {
                addLog("Received: $value")
                delay(500)
            }
        }
    }
    
    // Demo 3: Conflated Channel (keeps only latest)
    fun demoConflatedChannel() {
        addLog("=== Conflated Channel ===")
        viewModelScope.launch {
            val channel = Channel<Int>(Channel.CONFLATED)
            
            launch {
                repeat(10) { i ->
                    addLog("Sending: $i")
                    channel.send(i)
                    delay(100)
                }
                channel.close()
            }
            
            delay(1000) // Slow consumer
            
            for (value in channel) {
                addLog("Received: $value (others were dropped)")
            }
        }
    }
    
    // Demo 4: Produce builder
    fun demoProduceBuilder() {
        addLog("=== Produce Builder ===")
        viewModelScope.launch {
            val squares = produceSquares()
            
            repeat(5) {
                addLog("Received: ${squares.receive()}")
            }
            
            squares.cancel()
            addLog("Producer cancelled")
        }
    }
    
    private fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
        for (x in 1..10) {
            addLog("Producing: ${x * x}")
            send(x * x)
            delay(500)
        }
    }
    
    // Demo 5: Fan-out (multiple consumers)
    fun demoFanOut() {
        addLog("=== Fan-out: Multiple Consumers ===")
        viewModelScope.launch {
            val producer = produceNumbers()
            
            repeat(3) { id ->
                launchProcessor(id, producer)
            }
            
            delay(3000)
            producer.cancel()
            addLog("Producer cancelled")
        }
    }
    
    private fun CoroutineScope.produceNumbers() = produce {
        var x = 1
        while (true) {
            send(x++)
            delay(500)
        }
    }
    
    private fun CoroutineScope.launchProcessor(
        id: Int,
        channel: ReceiveChannel<Int>
    ) = launch {
        for (msg in channel) {
            addLog("Processor #$id received: $msg")
        }
    }
    
    // Demo 6: Fan-in (multiple producers)
    fun demoFanIn() {
        addLog("=== Fan-in: Multiple Producers ===")
        viewModelScope.launch {
            val channel = Channel<String>()
            
            launch { sendString(channel, "Producer 1", 500) }
            launch { sendString(channel, "Producer 2", 800) }
            launch { sendString(channel, "Producer 3", 300) }
            
            repeat(9) {
                addLog("Received: ${channel.receive()}")
            }
            
            coroutineContext.cancelChildren()
        }
    }
    
    private suspend fun sendString(
        channel: SendChannel<String>,
        s: String,
        time: Long
    ) {
        repeat(3) {
            delay(time)
            channel.send("$s: message $it")
        }
    }
    
    // Demo 7: Pipeline pattern
    fun demoPipeline() {
        addLog("=== Pipeline Pattern ===")
        viewModelScope.launch {
            val numbers = produceNumbersForPipeline()
            val squares = square(numbers)
            val doubled = double(squares)
            
            repeat(5) {
                addLog("Final result: ${doubled.receive()}")
            }
            
            coroutineContext.cancelChildren()
            addLog("Pipeline cancelled")
        }
    }
    
    private fun CoroutineScope.produceNumbersForPipeline() = produce {
        var x = 1
        while (true) {
            send(x++)
            delay(300)
        }
    }
    
    private fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce {
        for (x in numbers) {
            val result = x * x
            addLog("Square: $x -> $result")
            send(result)
        }
    }
    
    private fun CoroutineScope.double(numbers: ReceiveChannel<Int>) = produce {
        for (x in numbers) {
            val result = x * 2
            addLog("Double: $x -> $result")
            send(result)
        }
    }
    
    // Demo 8: Select Expression (wait for multiple channels)
    fun demoSelect() {
        addLog("=== Select Expression ===")
        viewModelScope.launch {
            val chan1 = produce {
                delay(500)
                send("From Channel 1")
            }
            
            val chan2 = produce {
                delay(300)
                send("From Channel 2")
            }
            
            select<Unit> {
                chan1.onReceive { value ->
                    addLog("Received first from chan1: $value")
                }
                chan2.onReceive { value ->
                    addLog("Received first from chan2: $value")
                }
            }
            
            coroutineContext.cancelChildren()
        }
    }
    
    // Demo 9: Ticker Channel
    fun demoTicker() {
        addLog("=== Ticker Channel ===")
        var job: Job? = null
        
        job = viewModelScope.launch {
            val tickerChannel = ticker(delayMillis = 500, initialDelayMillis = 0)
            
            repeat(5) { i ->
                tickerChannel.receive()
                addLog("Tick ${i + 1}")
            }
            
            tickerChannel.cancel()
            addLog("Ticker stopped")
        }
    }
    
    // Demo 10: Channel vs Flow
    fun demoChannelVsFlow() {
        addLog("=== Channel: Hot Stream ===")
        viewModelScope.launch {
            val channel = Channel<Int>()
            
            launch {
                repeat(5) {
                    channel.send(it)
                    addLog("Channel sent: $it")
                    delay(300)
                }
                channel.close()
            }
            
            delay(1000) // Start consuming late
            addLog("Started consuming (missed some values)")
            
            for (value in channel) {
                addLog("Channel received: $value")
            }
        }
    }
    
    // Demo 11: Closing and iteration
    fun demoClosingChannel() {
        addLog("=== Closing Channel ===")
        viewModelScope.launch {
            val channel = Channel<Int>()
            
            launch {
                for (x in 1..5) {
                    channel.send(x * x)
                    addLog("Sent: ${x * x}")
                }
                channel.close()
                addLog("Channel explicitly closed")
            }
            
            // Using for loop automatically handles closed channel
            for (y in channel) {
                addLog("Received: $y")
            }
            
            addLog("Iteration completed")
        }
    }
    
    // Demo 12: Rendezvous Channel (no buffer)
    fun demoRendezvousChannel() {
        addLog("=== Rendezvous Channel (no buffer) ===")
        viewModelScope.launch {
            val channel = Channel<Int>(Channel.RENDEZVOUS)
            
            launch {
                repeat(3) {
                    addLog("Trying to send: $it")
                    channel.send(it)
                    addLog("Send completed: $it (receiver was ready)")
                }
                channel.close()
            }
            
            launch {
                delay(1000)
                for (value in channel) {
                    addLog("Received: $value")
                    delay(500)
                }
            }
        }
    }
}

@Composable
fun ChannelsDemo(viewModel: ChannelsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Channels Demos",
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
                    onClick = { viewModel.demoBasicChannel() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Basic", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoBufferedChannel() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Buffered", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoConflatedChannel() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Conflated", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoProduceBuilder() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Produce", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoFanOut() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Fan-out", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoFanIn() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Fan-in", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoPipeline() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Pipeline", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoSelect() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Select", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoTicker() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Ticker", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoChannelVsFlow() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Hot Stream", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoClosingChannel() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Closing", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoRendezvousChannel() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Rendezvous", style = MaterialTheme.typography.bodySmall)
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
                        text = "Tap a button to see Channels demos",
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

