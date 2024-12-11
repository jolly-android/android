package com.example.nearandaroundme.data

import com.example.nearandaroundme.model.Place

interface PlaceRepository {
    suspend fun getNearbyPlaces(): List<Place>
}

