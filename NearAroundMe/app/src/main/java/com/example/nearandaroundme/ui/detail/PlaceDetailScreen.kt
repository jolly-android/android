package com.example.nearandaroundme.ui.detail

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nearandaroundme.R
import com.example.nearandaroundme.model.Place
import java.util.Locale

@Composable
fun PlaceDetailScreen(
    place: Place?,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = place?.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        if (place == null) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "We couldn't find that place right now.")
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = place.category.uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = place.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = place.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            InfoRow(label = "Address", value = place.address)
            InfoRow(label = "Phone", value = place.phoneNumber)
            val distanceLabel = String.format(Locale.getDefault(), "%.1f mi", place.distanceMiles)
            InfoRow(
                label = "Distance",
                value = "$distanceLabel • ${if (place.isOpenNow) "Open now" else "Closed"}"
            )

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        val success = openDirections(context, place)
                        if (!success) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.directions_unavailable),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Map, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.action_directions))
                }

                OutlinedButton(
                    onClick = {
                        val success = openDialer(context, place.phoneNumber)
                        if (!success) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.call_unavailable),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Outlined.Call, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.action_call))
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

private fun openDialer(context: Context, phoneNumber: String): Boolean {
    return runCatching {
        val dialIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(dialIntent)
    }.isSuccess
}

private fun openDirections(context: Context, place: Place): Boolean {
    val geoUri = Uri.parse("google.navigation:q=${place.latitude},${place.longitude}")
    val mapsIntent = Intent(Intent.ACTION_VIEW, geoUri).apply {
        setPackage("com.google.android.apps.maps")
    }

    return try {
        context.startActivity(mapsIntent)
        true
    } catch (notFound: ActivityNotFoundException) {
        runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, geoUri))
        }.isSuccess
    }
}

