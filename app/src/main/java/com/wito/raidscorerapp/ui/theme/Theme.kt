package com.wito.raidscorerapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
    background = Color.White,
    onBackground = Color.Black



    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

//Colors based on WoW
private val WoWLightColorScheme = lightColorScheme(
    primary = Color(0xFFC79C6E),
    onPrimary = Color(0xFF1C1C1C),
    surface = Color(0xFFFAE6B1),
    onSurface = Color.Black,
    background = Color(0xFF003366),
    onBackground = Color.White,
)

private val WoWDarkColorScheme = darkColorScheme(
    primary = Color(0xFFC79C6E),
    onPrimary = Color.Black,
    surface = Color(0xFF1C1C1C),
    onSurface = Color(0xFFFAE6B1),
    background = Color(0xFF000000),
    onBackground = Color(0xFFC79C6E)
)

// Font based on WoW
private val WoWTypography = Typography(
    titleLarge = Typography().titleLarge.copy(color = Color(0xFFC79C6E)),
    bodyMedium = Typography().bodyMedium.copy(color = Color.White),
)


@Composable
fun RaidScorerAppTheme(
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

    val colors = if (darkTheme) WoWDarkColorScheme else WoWLightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = WoWTypography,
        content = content
    )
}