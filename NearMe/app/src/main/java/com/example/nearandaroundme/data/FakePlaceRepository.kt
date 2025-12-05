package com.example.nearandaroundme.data

import com.example.nearandaroundme.model.Place
import kotlinx.coroutines.delay
import kotlin.random.Random

class FakePlaceRepository : PlaceRepository {

    override suspend fun getNearbyPlaces(): List<Place> {
        // Pretend we hit the network to keep the UI honest.
        delay(350)
        return curatedPlaces.shuffled(Random(42))
    }

    private val curatedPlaces = listOf(
        Place(
            id = "coffee_roasters",
            name = "Pier 42 Coffee Roasters",
            category = "Coffee",
            description = "Independent roastery with single-origin espresso flights and seasonal pastries.",
            address = "1104 Madison St, Seattle, WA",
            phoneNumber = "+12065550142",
            latitude = 47.61095,
            longitude = -122.32745,
            distanceMiles = 0.3,
            rating = 4.8
        ),
        Place(
            id = "green_lake_park",
            name = "Green Lake Park",
            category = "Outdoors",
            description = "Tree-lined 2.8 mi loop trail with kayak rentals and open lawns for picnics.",
            address = "7201 E Green Lake Dr N, Seattle, WA",
            phoneNumber = "+12066844070",
            latitude = 47.67978,
            longitude = -122.32714,
            distanceMiles = 4.9,
            rating = 4.9
        ),
        Place(
            id = "noodle_house",
            name = "Lantern Noodle House",
            category = "Food",
            description = "Hand-pulled noodles, warming broths, and shareable small plates until late.",
            address = "422 9th Ave N, Seattle, WA",
            phoneNumber = "+12066559811",
            latitude = 47.62251,
            longitude = -122.33921,
            distanceMiles = 1.2,
            rating = 4.6
        ),
        Place(
            id = "makers_market",
            name = "South Lake Makers Market",
            category = "Shopping",
            description = "Weekend pop-up featuring rotating local artisans, florals, and vinyl DJs.",
            address = "901 Harrison St, Seattle, WA",
            phoneNumber = "+12064558090",
            latitude = 47.62203,
            longitude = -122.33995,
            distanceMiles = 0.8,
            rating = 4.7
        ),
        Place(
            id = "community_climb",
            name = "Community Climb Gym",
            category = "Fitness",
            description = "Bouldering-focused gym with coaching sessions and a sunny cowork nook.",
            address = "210 Yale Ave N, Seattle, WA",
            phoneNumber = "+12066557611",
            latitude = 47.62022,
            longitude = -122.32991,
            distanceMiles = 0.6,
            rating = 4.5
        )
    )
}

