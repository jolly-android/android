package com.example.kotlinfeatures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.kotlinfeatures.navigation.AppNavigation
import com.example.kotlinfeatures.ui.theme.KotlinFeaturesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinFeaturesTheme {
                AppNavigation()
            }
        }
    }
}