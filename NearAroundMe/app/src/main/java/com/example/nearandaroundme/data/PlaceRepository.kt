package com.example.nearandaroundme.data

import com.example.nearandaroundme.model.Place

interface PlaceRepository {
    suspend fun getNearbyPlaces(): List<Place>
}


# auto-edit 2025-12-05 12:47:04.205027
