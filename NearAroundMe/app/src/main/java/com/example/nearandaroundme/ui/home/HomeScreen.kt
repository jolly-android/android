package com.example.nearandaroundme.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nearandaroundme.R
import com.example.nearandaroundme.model.Place
import com.example.nearandaroundme.nearme.NearMeUiState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraUpdateFactory
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun HomeScreen(
    uiState: NearMeUiState,
    onQueryChange: (String) -> Unit,
    onPlaceFocused: (Place) -> Unit,
    onPlaceSelected: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val defaultTarget = LatLng(47.6097, -122.3331)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultTarget, 13f)
    }

    LaunchedEffect(uiState.mapTarget) {
        val coordinate = uiState.mapTarget ?: return@LaunchedEffect
        val latLng = LatLng(coordinate.latitude, coordinate.longitude)
        cameraPositionState.animate(
            update = CameraUpdateFactory.newCameraPosition(
                CameraPosition.fromLatLngZoom(latLng, 14f)
            ),
            durationMs = 600
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = stringResource(id = R.string.home_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = uiState.query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = stringResource(id = R.string.search_hint))
            },
            singleLine = true
        )
        if (uiState.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.map_section_title),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            if (uiState.filteredPlaces.isEmpty()) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    tonalElevation = 4.dp
                ) {
                    EmptyState(
                        title = stringResource(id = R.string.no_results_title),
                        body = stringResource(id = R.string.no_results_body)
                    )
                }
            } else {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(isMyLocationEnabled = false),
                    uiSettings = MapUiSettings(zoomControlsEnabled = false)
                ) {
                    uiState.filteredPlaces.forEach { place ->
                        val markerState = remember(place.id) {
                            MarkerState(
                                position = LatLng(place.latitude, place.longitude)
                            )
                        }
                        Marker(
                            state = markerState,
                            title = place.name,
                            snippet = place.address,
                            icon = BitmapDescriptorFactory.defaultMarker(
                                if (place.id == uiState.highlightedPlaceId) {
                                    BitmapDescriptorFactory.HUE_AZURE
                                } else {
                                    BitmapDescriptorFactory.HUE_RED
                                }
                            ),
                            onClick = {
                                onPlaceFocused(place)
                                true
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = buildString {
                append(stringResource(id = R.string.list_section_title))
                if (uiState.filteredPlaces.isNotEmpty()) {
                    append(" · ${uiState.filteredPlaces.size}")
                }
            },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            if (uiState.filteredPlaces.isEmpty()) {
                EmptyState(
                    title = stringResource(id = R.string.no_results_title),
                    body = stringResource(id = R.string.no_results_body)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = uiState.filteredPlaces,
                        key = { it.id }
                    ) { place ->
                        PlaceCard(
                            place = place,
                            isHighlighted = place.id == uiState.highlightedPlaceId,
                            onFocus = { onPlaceFocused(place) },
                            onOpen = { onPlaceSelected(place) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PlaceCard(
    place: Place,
    isHighlighted: Boolean,
    onFocus: () -> Unit,
    onOpen: () -> Unit
) {
    ElevatedCard(
        onClick = onOpen,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (isHighlighted) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = place.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = place.address,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(
                    onClick = onFocus,
                    label = { Text(text = place.category) }
                )
                val distanceLabel = remember(place.distanceMiles) {
                    String.format(Locale.getDefault(), "%.1f mi", place.distanceMiles)
                }
                Text(text = distanceLabel, style = MaterialTheme.typography.labelLarge)
                place.rating?.let { rating ->
                    Text(
                        text = "★ ${((rating * 10).roundToInt() / 10f)}",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyState(
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = body,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


# auto-edit 2025-12-05 12:47:03.582473

# auto-edit 2025-12-05 12:47:04.865470
