package com.example.kotlinfeatures.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotlinfeatures.model.KotlinFeatures
import com.example.kotlinfeatures.ui.screens.FeatureDetailScreen
import com.example.kotlinfeatures.ui.screens.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object FeatureDetail : Screen("feature/{featureId}") {
        fun createRoute(featureId: Int) = "feature/$featureId"
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onFeatureClick = { feature ->
                    navController.navigate(Screen.FeatureDetail.createRoute(feature.id))
                }
            )
        }
        
        composable(
            route = Screen.FeatureDetail.route,
            arguments = listOf(
                navArgument("featureId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val featureId = backStackEntry.arguments?.getInt("featureId") ?: 1
            val feature = KotlinFeatures.allFeatures.firstOrNull { it.id == featureId }
            
            feature?.let {
                FeatureDetailScreen(
                    feature = it,
                    onBackClick = { navController.navigateUp() }
                )
            }
        }
    }
}

