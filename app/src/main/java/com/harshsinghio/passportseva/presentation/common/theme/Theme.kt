package com.harshsinghio.passportseva.presentation.common.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Blue600,
    onPrimary = White,
    primaryContainer = Blue100,
    onPrimaryContainer = Blue600,
    secondary = InfoBlue,
    onSecondary = White,
    secondaryContainer = Blue50,
    onSecondaryContainer = InfoBlue,
    tertiary = SuccessGreen,
    onTertiary = White,
    tertiaryContainer = Color(0xFFD1F2D6),
    onTertiaryContainer = Color(0xFF002107),
    error = ErrorRed,
    onError = White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = SlateGray50,
    onBackground = TextPrimary,
    surface = White,
    onSurface = TextPrimary,
    surfaceVariant = Color(0xFFEFF6FF),
    onSurfaceVariant = MutedText,
    outline = BorderColor,
    outlineVariant = DividerColor
)

private val DarkColorScheme = darkColorScheme(
    primary = Blue600,
    onPrimary = White,
    primaryContainer = Color(0xFF004A77),
    onPrimaryContainer = Color(0xFFCCE5FF),
    secondary = InfoBlue,
    onSecondary = White,
    secondaryContainer = Color(0xFF004A77),
    onSecondaryContainer = Color(0xFFCCE5FF),
    tertiary = SuccessGreen,
    onTertiary = White,
    tertiaryContainer = Color(0xFF005321),
    onTertiaryContainer = Color(0xFFB2F5C0),
    error = ErrorRed,
    onError = White,
    errorContainer = Color(0xFF8C0009),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF1A1C1E),
    onBackground = White,
    surface = Color(0xFF121212),
    onSurface = White,
    surfaceVariant = Color(0xFF42474E),
    onSurfaceVariant = Color(0xFFCAC4D0),
    outline = Color(0xFF8C9198),
    outlineVariant = Color(0xFF42474E)
)

@Composable
fun PassportSevaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PassportSevaTypography,
        shapes = PassportSevaShapes,
        content = content
    )
}