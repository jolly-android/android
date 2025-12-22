package com.example.kotlinfeatures.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = KotlinPurpleLight,
    onPrimary = Color.White,
    primaryContainer = KotlinPurpleDark,
    onPrimaryContainer = Color.White,
    secondary = KotlinOrangeLight,
    onSecondary = Color.White,
    secondaryContainer = KotlinOrangeDark,
    onSecondaryContainer = Color.White,
    tertiary = Pink80,
    onTertiary = Color.White,
    tertiaryContainer = Pink40,
    onTertiaryContainer = Color.White,
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0)
)

private val LightColorScheme = lightColorScheme(
    primary = KotlinPurple,
    onPrimary = Color.White,
    primaryContainer = KotlinPurpleLight,
    onPrimaryContainer = Color(0xFF21005D),
    secondary = KotlinOrange,
    onSecondary = Color.White,
    secondaryContainer = KotlinOrangeLight,
    onSecondaryContainer = Color(0xFF2D1600),
    tertiary = Pink40,
    onTertiary = Color.White,
    tertiaryContainer = Pink80,
    onTertiaryContainer = Color(0xFF31111D),
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F)
)

@Composable
fun KotlinFeaturesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}