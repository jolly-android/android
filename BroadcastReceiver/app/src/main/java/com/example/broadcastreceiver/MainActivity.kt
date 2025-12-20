package com.example.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.broadcastreceiver.receivers.*
import com.example.broadcastreceiver.ui.theme.BroadcastReceiverTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    
    // Dynamic receivers
    private var batteryReceiver: BatteryLevelReceiver? = null
    private var customReceiver: CustomBroadcastReceiver? = null
    private var orderedReceiverHigh: OrderedBroadcastReceiverHigh? = null
    private var orderedReceiverMedium: OrderedBroadcastReceiverMedium? = null
    private var orderedReceiverLow: OrderedBroadcastReceiverLow? = null
    
    private val broadcastLogs = mutableStateListOf<BroadcastLog>()
    private var customBroadcastCount = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BroadcastReceiverTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BroadcastReceiverDemo(
                        modifier = Modifier.padding(innerPadding),
                        logs = broadcastLogs,
                        onRegisterBattery = { registerBatteryReceiver() },
                        onUnregisterBattery = { unregisterBatteryReceiver() },
                        onRegisterCustom = { registerCustomReceiver() },
                        onUnregisterCustom = { unregisterCustomReceiver() },
                        onSendCustomBroadcast = { sendCustomBroadcast() },
                        onRegisterOrdered = { registerOrderedReceivers() },
                        onUnregisterOrdered = { unregisterOrderedReceivers() },
                        onSendOrderedBroadcast = { sendOrderedBroadcast() },
                        onClearLogs = { broadcastLogs.clear() }
                    )
                }
            }
        }
        
        addLog("App Started", "Welcome to BroadcastReceiver Demo!")
    }
    
    private fun registerBatteryReceiver() {
        if (batteryReceiver != null) {
            addLog("Battery Receiver", "Already registered!")
            return
        }
        
        batteryReceiver = BatteryLevelReceiver { info ->
            addLog("Battery Info", info)
        }
        
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_OKAY)
        }
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(batteryReceiver, filter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(batteryReceiver, filter)
        }
        
        addLog("Battery Receiver", "✓ Registered successfully (Dynamic)")
    }
    
    private fun unregisterBatteryReceiver() {
        batteryReceiver?.let {
            unregisterReceiver(it)
            batteryReceiver = null
            addLog("Battery Receiver", "✗ Unregistered")
        } ?: addLog("Battery Receiver", "Not registered!")
    }
    
    private fun registerCustomReceiver() {
        if (customReceiver != null) {
            addLog("Custom Receiver", "Already registered!")
            return
        }
        
        customReceiver = CustomBroadcastReceiver { info ->
            addLog("Custom Broadcast", info)
        }
        
        val filter = IntentFilter(CustomBroadcastReceiver.ACTION_CUSTOM_BROADCAST)
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(customReceiver, filter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(customReceiver, filter)
        }
        
        addLog("Custom Receiver", "✓ Registered successfully (Dynamic)")
    }
    
    private fun unregisterCustomReceiver() {
        customReceiver?.let {
            unregisterReceiver(it)
            customReceiver = null
            addLog("Custom Receiver", "✗ Unregistered")
        } ?: addLog("Custom Receiver", "Not registered!")
    }
    
    private fun sendCustomBroadcast() {
        customBroadcastCount++
        val intent = Intent(CustomBroadcastReceiver.ACTION_CUSTOM_BROADCAST).apply {
            putExtra(CustomBroadcastReceiver.EXTRA_MESSAGE, "Hello from MainActivity!")
            putExtra(CustomBroadcastReceiver.EXTRA_TIMESTAMP, System.currentTimeMillis())
            putExtra(CustomBroadcastReceiver.EXTRA_COUNT, customBroadcastCount)
        }
        sendBroadcast(intent)
        addLog("Custom Broadcast", "📤 Sent custom broadcast #$customBroadcastCount")
    }
    
    private fun registerOrderedReceivers() {
        if (orderedReceiverHigh != null) {
            addLog("Ordered Receivers", "Already registered!")
            return
        }
        
        // High priority receiver
        orderedReceiverHigh = OrderedBroadcastReceiverHigh { info ->
            addLog("Ordered (High)", info)
        }
        val filterHigh = IntentFilter(OrderedBroadcastReceiverHigh.ACTION_ORDERED_BROADCAST).apply {
            priority = 1000
        }
        
        // Medium priority receiver
        orderedReceiverMedium = OrderedBroadcastReceiverMedium { info ->
            addLog("Ordered (Medium)", info)
        }
        val filterMedium = IntentFilter(OrderedBroadcastReceiverHigh.ACTION_ORDERED_BROADCAST).apply {
            priority = 500
        }
        
        // Low priority receiver
        orderedReceiverLow = OrderedBroadcastReceiverLow { info ->
            addLog("Ordered (Low)", info)
        }
        val filterLow = IntentFilter(OrderedBroadcastReceiverHigh.ACTION_ORDERED_BROADCAST).apply {
            priority = 100
        }
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(orderedReceiverHigh, filterHigh, RECEIVER_NOT_EXPORTED)
            registerReceiver(orderedReceiverMedium, filterMedium, RECEIVER_NOT_EXPORTED)
            registerReceiver(orderedReceiverLow, filterLow, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(orderedReceiverHigh, filterHigh)
            registerReceiver(orderedReceiverMedium, filterMedium)
            registerReceiver(orderedReceiverLow, filterLow)
        }
        
        addLog("Ordered Receivers", "✓ Registered 3 receivers with different priorities")
    }
    
    private fun unregisterOrderedReceivers() {
        var count = 0
        orderedReceiverHigh?.let {
            unregisterReceiver(it)
            orderedReceiverHigh = null
            count++
        }
        orderedReceiverMedium?.let {
            unregisterReceiver(it)
            orderedReceiverMedium = null
            count++
        }
        orderedReceiverLow?.let {
            unregisterReceiver(it)
            orderedReceiverLow = null
            count++
        }
        
        if (count > 0) {
            addLog("Ordered Receivers", "✗ Unregistered $count receivers")
        } else {
            addLog("Ordered Receivers", "Not registered!")
        }
    }
    
    private fun sendOrderedBroadcast() {
        val intent = Intent(OrderedBroadcastReceiverHigh.ACTION_ORDERED_BROADCAST).apply {
            putExtra(OrderedBroadcastReceiverHigh.EXTRA_DATA, "Initial payload")
        }
        
        sendOrderedBroadcast(intent, null)
        addLog("Ordered Broadcast", "📤 Sent ordered broadcast (check priority chain)")
    }
    
    private fun addLog(type: String, message: String) {
        val timestamp = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        broadcastLogs.add(0, BroadcastLog(type, message, timestamp))
        
        // Keep only last 50 logs
        if (broadcastLogs.size > 50) {
            broadcastLogs.removeLast()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // Unregister all receivers
        unregisterBatteryReceiver()
        unregisterCustomReceiver()
        unregisterOrderedReceivers()
    }
}

data class BroadcastLog(
    val type: String,
    val message: String,
    val timestamp: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BroadcastReceiverDemo(
    modifier: Modifier = Modifier,
    logs: List<BroadcastLog>,
    onRegisterBattery: () -> Unit,
    onUnregisterBattery: () -> Unit,
    onRegisterCustom: () -> Unit,
    onUnregisterCustom: () -> Unit,
    onSendCustomBroadcast: () -> Unit,
    onRegisterOrdered: () -> Unit,
    onUnregisterOrdered: () -> Unit,
    onSendOrderedBroadcast: () -> Unit,
    onClearLogs: () -> Unit
) {
    val listState = rememberLazyListState()
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "BroadcastReceiver Demo",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = "Demonstrates various BroadcastReceiver features",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Control Sections
        LazyColumn(
            modifier = Modifier.weight(1f),
            state = listState
        ) {
            // System Broadcasts Section
            item {
                SectionCard(title = "System Broadcasts") {
                    Text(
                        text = "Static receivers registered in AndroidManifest.xml",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    InfoChip("✈️ Airplane Mode - Change airplane mode to test")
                    Spacer(modifier = Modifier.height(4.dp))
                    InfoChip("🔋 Boot Completed - Requires device restart to test")
                }
            }
            
            // Battery Monitoring Section
            item {
                SectionCard(title = "Battery Monitoring (Dynamic)") {
                    Text(
                        text = "Dynamically register/unregister at runtime",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onRegisterBattery,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Register")
                        }
                        OutlinedButton(
                            onClick = onUnregisterBattery,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Unregister")
                        }
                    }
                }
            }
            
            // Custom Broadcasts Section
            item {
                SectionCard(title = "Custom Broadcasts") {
                    Text(
                        text = "Send and receive app-specific broadcasts",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onRegisterCustom,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Register")
                        }
                        OutlinedButton(
                            onClick = onUnregisterCustom,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Unregister")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = onSendCustomBroadcast,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text("Send Custom Broadcast")
                    }
                }
            }
            
            // Ordered Broadcasts Section
            item {
                SectionCard(title = "Ordered Broadcasts") {
                    Text(
                        text = "Receivers process in priority order, can modify results",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onRegisterOrdered,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Register")
                        }
                        OutlinedButton(
                            onClick = onUnregisterOrdered,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Unregister")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = onSendOrderedBroadcast,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        Text("Send Ordered Broadcast")
                    }
                }
            }
            
            // Logs Section Header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Broadcast Logs",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = onClearLogs) {
                        Text("Clear")
                    }
                }
            }
            
            // Logs
            items(logs) { log ->
                LogItem(log)
            }
            
            // Empty state
            if (logs.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No broadcasts received yet.\nTry the buttons above!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SectionCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            content()
        }
    }
}

@Composable
fun InfoChip(text: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
fun LogItem(log: BroadcastLog) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = log.type,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = log.timestamp,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = log.message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
