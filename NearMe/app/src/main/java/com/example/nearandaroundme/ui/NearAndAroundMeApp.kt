package com.example.nearandaroundme.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nearandaroundme.nearme.NearMeViewModel
import com.example.nearandaroundme.ui.detail.PlaceDetailScreen
import com.example.nearandaroundme.ui.home.HomeScreen

private const val HomeRoute = "home"
private const val DetailRoute = "detail"

@Composable
fun NearAndAroundMeApp() {
    val navController = rememberNavController()
    val nearMeViewModel: NearMeViewModel = viewModel()
    val uiState = nearMeViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable(HomeRoute) {
            HomeScreen(
                uiState = uiState.value,
                onQueryChange = nearMeViewModel::updateQuery,
                onPlaceFocused = { place ->
                    nearMeViewModel.focusOnPlace(place.id)
                },
                onPlaceSelected = { place ->
                    nearMeViewModel.focusOnPlace(place.id)
                    navController.navigate("$DetailRoute/${place.id}") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = "$DetailRoute/{placeId}",
            arguments = listOf(
                navArgument("placeId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getString("placeId")
            val place = uiState.value.places.firstOrNull { it.id == placeId }

            PlaceDetailScreen(
                place = place,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}


# auto-edit 2025-12-05 12:47:05.861554
