package com.example.nearandaroundme.nearme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nearandaroundme.data.FakePlaceRepository
import com.example.nearandaroundme.data.PlaceRepository
import com.example.nearandaroundme.model.Coordinate
import com.example.nearandaroundme.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class NearMeUiState(
    val isLoading: Boolean = true,
    val query: String = "",
    val places: List<Place> = emptyList(),
    val filteredPlaces: List<Place> = emptyList(),
    val highlightedPlaceId: String? = null,
    val mapTarget: Coordinate? = null,
    val errorMessage: String? = null
)

class NearMeViewModel(
    private val repository: PlaceRepository = FakePlaceRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(NearMeUiState())
    val uiState: StateFlow<NearMeUiState> = _uiState

    init {
        refreshPlaces()
    }

    fun refreshPlaces() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            runCatching { repository.getNearbyPlaces() }
                .onSuccess { places ->
                    _uiState.update { current ->
                        val filtered = filterPlaces(places, current.query)
                        current.copy(
                            isLoading = false,
                            places = places,
                            filteredPlaces = filtered,
                            mapTarget = current.mapTarget ?: places.firstOrNull()?.toCoordinate(),
                            highlightedPlaceId = current.highlightedPlaceId?.takeIf { id ->
                                places.any { it.id == id }
                            }
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage ?: "Unable to load nearby places."
                        )
                    }
                }
        }
    }

    fun updateQuery(query: String) {
        _uiState.update { current ->
            val filtered = filterPlaces(current.places, query)
            val newHighlight = current.highlightedPlaceId?.takeIf { id ->
                filtered.any { it.id == id }
            }

            current.copy(
                query = query,
                filteredPlaces = filtered,
                highlightedPlaceId = newHighlight,
                mapTarget = when {
                    newHighlight != null -> filtered.firstOrNull { it.id == newHighlight }?.toCoordinate()
                    filtered.isNotEmpty() -> filtered.first().toCoordinate()
                    else -> current.mapTarget
                }
            )
        }
    }

    fun focusOnPlace(placeId: String) {
        val place = _uiState.value.places.firstOrNull { it.id == placeId } ?: return
        _uiState.update {
            it.copy(
                highlightedPlaceId = placeId,
                mapTarget = place.toCoordinate()
            )
        }
    }

    private fun filterPlaces(places: List<Place>, query: String): List<Place> {
        if (query.isBlank()) return places
        val token = query.trim().lowercase()
        return places.filter { place ->
            place.name.lowercase().contains(token) ||
                place.category.lowercase().contains(token) ||
                place.address.lowercase().contains(token)
        }
    }

    private fun Place.toCoordinate(): Coordinate = Coordinate(latitude, longitude)
}


# auto-edit 2025-12-05 12:47:04.916574
