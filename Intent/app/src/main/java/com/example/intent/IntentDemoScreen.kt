package com.example.intent

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntentDemoScreen() {
    val context = LocalContext.current
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Android Intents Demo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            
            // Communication Category
            item {
                CategoryHeader(
                    title = "Communication",
                    icon = Icons.Default.Phone
                )
            }
            items(getCommunicationIntents()) { intent ->
                IntentButton(intent = intent, context = context)
            }
            
            // Web & Content Category
            item {
                CategoryHeader(
                    title = "Web & Content",
                    icon = Icons.Default.Search
                )
            }
            items(getWebContentIntents()) { intent ->
                IntentButton(intent = intent, context = context)
            }
            
            // Media Category
            item {
                CategoryHeader(
                    title = "Media",
                    icon = Icons.Default.Favorite
                )
            }
            items(getMediaIntents()) { intent ->
                IntentButton(intent = intent, context = context)
            }
            
            // System Category
            item {
                CategoryHeader(
                    title = "System",
                    icon = Icons.Default.Settings
                )
            }
            items(getSystemIntents()) { intent ->
                IntentButton(intent = intent, context = context)
            }
            
            // Calendar & Time Category
            item {
                CategoryHeader(
                    title = "Calendar & Time",
                    icon = Icons.Default.DateRange
                )
            }
            items(getCalendarIntents()) { intent ->
                IntentButton(intent = intent, context = context)
            }
            
            // Sharing Category
            item {
                CategoryHeader(
                    title = "Sharing",
                    icon = Icons.Default.Share
                )
            }
            items(getSharingIntents()) { intent ->
                IntentButton(intent = intent, context = context)
            }
            
            // File Operations Category
            item {
                CategoryHeader(
                    title = "File Operations",
                    icon = Icons.Default.Add
                )
            }
            items(getFileIntents()) { intent ->
                IntentButton(intent = intent, context = context)
            }
            
            // Explicit Intent Category
            item {
                CategoryHeader(
                    title = "Explicit Intent",
                    icon = Icons.Default.ArrowForward
                )
            }
            items(getExplicitIntents()) { intent ->
                IntentButton(intent = intent, context = context)
            }
            
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

@Composable
fun CategoryHeader(title: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 8.dp),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    )
}

@Composable
fun IntentButton(intent: IntentItem, context: Context) {
    ElevatedButton(
        onClick = { intent.action(context) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = intent.icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = intent.title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

data class IntentItem(
    val title: String,
    val icon: ImageVector,
    val action: (Context) -> Unit
)

// Communication Intents
fun getCommunicationIntents() = listOf(
    IntentItem(
        title = "Open Dialer",
        icon = Icons.Default.Phone,
        action = { context -> IntentHelper.openDialer(context) }
    ),
    IntentItem(
        title = "Send SMS",
        icon = Icons.Default.Send,
        action = { context -> IntentHelper.sendSMS(context) }
    ),
    IntentItem(
        title = "Send Email",
        icon = Icons.Default.Email,
        action = { context -> IntentHelper.sendEmail(context) }
    )
)

// Web & Content Intents
fun getWebContentIntents() = listOf(
    IntentItem(
        title = "Open Web Page",
        icon = Icons.Default.Search,
        action = { context -> IntentHelper.openWebPage(context) }
    ),
    IntentItem(
        title = "Open Maps",
        icon = Icons.Default.LocationOn,
        action = { context -> IntentHelper.openMaps(context) }
    ),
    IntentItem(
        title = "Search on Map",
        icon = Icons.Default.Place,
        action = { context -> IntentHelper.searchOnMap(context) }
    ),
    IntentItem(
        title = "Open YouTube",
        icon = Icons.Default.Face,
        action = { context -> IntentHelper.openYouTube(context) }
    ),
    IntentItem(
        title = "Web Search",
        icon = Icons.Default.Search,
        action = { context -> IntentHelper.searchWeb(context) }
    ),
    IntentItem(
        title = "Open Play Store",
        icon = Icons.Default.Star,
        action = { context -> IntentHelper.openPlayStore(context) }
    )
)

// Media Intents
fun getMediaIntents() = listOf(
    IntentItem(
        title = "Open Camera",
        icon = Icons.Default.AccountBox,
        action = { context -> IntentHelper.openCamera(context) }
    ),
    IntentItem(
        title = "Open Gallery",
        icon = Icons.Default.Favorite,
        action = { context -> IntentHelper.openGallery(context) }
    ),
    IntentItem(
        title = "Record Video",
        icon = Icons.Default.Create,
        action = { context -> IntentHelper.recordVideo(context) }
    ),
    IntentItem(
        title = "Record Audio",
        icon = Icons.Default.AccountCircle,
        action = { context -> IntentHelper.recordAudio(context) }
    )
)

// System Intents
fun getSystemIntents() = listOf(
    IntentItem(
        title = "Open Settings",
        icon = Icons.Default.Settings,
        action = { context -> IntentHelper.openSettings(context) }
    ),
    IntentItem(
        title = "Open App Settings",
        icon = Icons.Default.Info,
        action = { context -> IntentHelper.openAppSettings(context) }
    ),
    IntentItem(
        title = "Open WiFi Settings",
        icon = Icons.Default.Settings,
        action = { context -> IntentHelper.openWifiSettings(context) }
    ),
    IntentItem(
        title = "Open Contacts",
        icon = Icons.Default.Person,
        action = { context -> IntentHelper.openContacts(context) }
    ),
    IntentItem(
        title = "Add Contact",
        icon = Icons.Default.Add,
        action = { context -> IntentHelper.addContact(context) }
    )
)

// Calendar & Time Intents
fun getCalendarIntents() = listOf(
    IntentItem(
        title = "Create Calendar Event",
        icon = Icons.Default.DateRange,
        action = { context -> IntentHelper.createCalendarEvent(context) }
    ),
    IntentItem(
        title = "Set Alarm",
        icon = Icons.Default.Notifications,
        action = { context -> IntentHelper.setAlarm(context) }
    )
)

// Sharing Intents
fun getSharingIntents() = listOf(
    IntentItem(
        title = "Share Text",
        icon = Icons.Default.Share,
        action = { context -> IntentHelper.shareText(context) }
    ),
    IntentItem(
        title = "Share Multiple",
        icon = Icons.Default.Share,
        action = { context -> IntentHelper.shareMultipleText(context) }
    )
)

// File Operations Intents
fun getFileIntents() = listOf(
    IntentItem(
        title = "Open Document",
        icon = Icons.Default.Add,
        action = { context -> IntentHelper.openDocument(context) }
    ),
    IntentItem(
        title = "Create Document",
        icon = Icons.Default.Create,
        action = { context -> IntentHelper.createDocument(context) }
    )
)

// Explicit Intent
fun getExplicitIntents() = listOf(
    IntentItem(
        title = "Launch Second Activity",
        icon = Icons.Default.ArrowForward,
        action = { context ->
            val intent = Intent(context, SecondActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", "Hello from MainActivity!")
                putExtra("EXTRA_NUMBER", 42)
            }
            context.startActivity(intent)
        }
    )
)

