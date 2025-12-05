package com.example.nearandaroundme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.nearandaroundme.ui.theme.NearAndAroundMeTheme
import com.example.nearandaroundme.ui.NearAndAroundMeApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearAndAroundMeTheme {
                NearAndAroundMeApp()
            }
        }
    }
}