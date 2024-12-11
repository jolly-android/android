package com.example.nearandaroundme.model

data class Place(
    val id: String,
    val name: String,
    val category: String,
    val description: String,
    val address: String,
    val phoneNumber: String,
    val latitude: Double,
    val longitude: Double,
    val distanceMiles: Double,
    val rating: Double? = null,
    val isOpenNow: Boolean = true
)

