package com.example.alarmmanager

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alarmmanager.ui.theme.AlarmManagerTheme

class MainActivity : ComponentActivity() {

    private lateinit var alarmScheduler: AlarmScheduler
    private var canScheduleExactAlarms by mutableStateOf(true)

    // Permission launcher for notifications (Android 13+)
    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            // Handle permission denied
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        alarmScheduler = AlarmScheduler(this)
        
        // Request notification permission on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        // Check exact alarm permission
        checkExactAlarmPermission()

        setContent {
            AlarmManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AlarmManagerDemo(
                        alarmScheduler = alarmScheduler,
                        canScheduleExactAlarms = canScheduleExactAlarms,
                        onRequestExactAlarmPermission = { requestExactAlarmPermission() }
                    )
                }
            }
        }
    }

    private fun checkExactAlarmPermission() {
        canScheduleExactAlarms = alarmScheduler.canScheduleExactAlarms()
    }

    private fun requestExactAlarmPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                data = Uri.parse("package:$packageName")
            }
            startActivity(intent)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmManagerDemo(
    alarmScheduler: AlarmScheduler,
    canScheduleExactAlarms: Boolean,
    onRequestExactAlarmPermission: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AlarmManager Demo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Permission Status
            if (!canScheduleExactAlarms && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PermissionWarningCard(onRequestExactAlarmPermission)
            }

            // Info Card
            InfoCard()

            // One-Time Alarm Section
            AlarmSection(
                title = "1. One-Time Alarm (RTC_WAKEUP)",
                description = "Fires once at a specific time. Wakes up device if sleeping.",
                buttonText = "Set Alarm (10s)",
                onSchedule = { alarmScheduler.scheduleOneTimeAlarm(10) },
                onCancel = { alarmScheduler.cancelAlarm(AlarmScheduler.ALARM_ID_ONE_TIME, "One-time") }
            )

            // Repeating Alarm Section
            AlarmSection(
                title = "2. Repeating Alarm",
                description = "Repeats at fixed intervals. Note: Inexact on Android 4.4+",
                buttonText = "Set Alarm (15s interval)",
                onSchedule = { alarmScheduler.scheduleRepeatingAlarm(15) },
                onCancel = { alarmScheduler.cancelAlarm(AlarmScheduler.ALARM_ID_REPEATING, "Repeating") }
            )

            // Inexact Repeating Alarm Section
            AlarmSection(
                title = "3. Inexact Repeating Alarm",
                description = "System batches alarms for better battery life. ~1 min interval.",
                buttonText = "Set Alarm (~1 min)",
                onSchedule = { alarmScheduler.scheduleInexactRepeatingAlarm(1) },
                onCancel = { alarmScheduler.cancelAlarm(AlarmScheduler.ALARM_ID_INEXACT, "Inexact") }
            )

            // Elapsed Realtime Alarm Section
            AlarmSection(
                title = "4. Elapsed Realtime Alarm",
                description = "Based on time since device boot (including sleep time).",
                buttonText = "Set Alarm (20s)",
                onSchedule = { alarmScheduler.scheduleElapsedRealtimeAlarm(20) },
                onCancel = { alarmScheduler.cancelAlarm(AlarmScheduler.ALARM_ID_ELAPSED, "Elapsed") }
            )

            // Idle Window Alarm Section (Android M+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                AlarmSection(
                    title = "5. Idle Window Alarm (Android M+)",
                    description = "Fires during device idle maintenance window for battery efficiency.",
                    buttonText = "Set Alarm (~2 min)",
                    onSchedule = { alarmScheduler.scheduleIdleAlarm(2) },
                    onCancel = { alarmScheduler.cancelAlarm(AlarmScheduler.ALARM_ID_IDLE, "Idle") }
                )
            }

            // Window Alarm Section (Android KitKat+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                AlarmSection(
                    title = "6. Window Alarm (Android KitKat+)",
                    description = "Fires within a time window (15-25 seconds).",
                    buttonText = "Set Window Alarm",
                    onSchedule = { alarmScheduler.scheduleWindowAlarm(15, 10) },
                    onCancel = { alarmScheduler.cancelAlarm(AlarmScheduler.ALARM_ID_WINDOW, "Window") }
                )
            }

            // Cancel All Button
            Button(
                onClick = { alarmScheduler.cancelAllAlarms() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Cancel All Alarms")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PermissionWarningCard(onRequestPermission: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "⚠️ Permission Required",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onErrorContainer
            )
            Text(
                text = "Exact alarms require permission on Android 12+",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onErrorContainer
            )
            Button(
                onClick = onRequestPermission,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Grant Permission")
            }
        }
    }
}

@Composable
fun InfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "📱 AlarmManager Features",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "This demo showcases different types of alarms:\n\n" +
                        "• RTC_WAKEUP: Real-time clock, wakes device\n" +
                        "• Repeating: Fixed interval alarms\n" +
                        "• Inexact: Battery-efficient, batched alarms\n" +
                        "• Elapsed Realtime: Since boot time\n" +
                        "• Idle Window: During device idle periods\n" +
                        "• Window: Flexible time window",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Composable
fun AlarmSection(
    title: String,
    description: String,
    buttonText: String,
    onSchedule: () -> Unit,
    onCancel: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onSchedule,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(buttonText)
                }
                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}
