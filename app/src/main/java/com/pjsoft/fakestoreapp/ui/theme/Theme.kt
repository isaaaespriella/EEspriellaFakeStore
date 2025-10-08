package com.pjsoft.fakestoreapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

private val LightColors = lightColorScheme(
    primary = Black,           // brand/primary
    onPrimary = White,
    secondary = WarmGray,      // secondary accents (icons, dividers)
    onSecondary = Black,
    background = BackgroundLight,
    onBackground = Black,      // main text
    surface = SurfaceLight,    // cards, app bar
    onSurface = Black,
    tertiary = Beige,          // highlights (e.g., price tags)
    error = ErrorRed,
    onError = White
)

private val DarkColors = darkColorScheme(
    primary = OnDark,
    onPrimary = Black,
    secondary = WarmGray,
    onSecondary = OnDark,
    background = BackgroundDark,
    onBackground = OnDark,
    surface = SurfaceDark,
    onSurface = OnDark,
    tertiary = Beige,
    error = ErrorRed,
    onError = Black
)

@Composable
fun FakeStoreAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = RobotoTypography,
        content = content
    )

}

