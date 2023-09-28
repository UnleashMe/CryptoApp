package com.example.cryptoapp.ui.theme

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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = TextColorDark,
    onSecondary = SecondaryTextColorDark
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Background,
    surface = Surface,
    onPrimary = TextColor,
    onSecondary = SecondaryTextColor


)

@Composable
fun CryptoAppTheme(
    windowSizeClass: WindowSizeClass,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val myLightScheme = dynamicLightColorScheme(context).copy(
        background = Background,
        surface = Surface,
        onPrimary = TextColor,
        onSecondary = SecondaryTextColor
    )
    val myDarkScheme = dynamicDarkColorScheme(context).copy(
        background = BackgroundDark,
        surface = SurfaceDark,
        onPrimary = TextColorDark,
        onSecondary = SecondaryTextColorDark
    )
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) myDarkScheme else myLightScheme
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val orientation = when {
        windowSizeClass.width.size > windowSizeClass.height.size -> Orientation.Landscape
        else -> Orientation.Portrait
    }

    val sizeThatMatters = when (orientation) {
        Orientation.Portrait -> windowSizeClass.width
        else -> windowSizeClass.height
    }

    val dimensions = when (sizeThatMatters) {
        is WindowSize.Small -> smallDimensions
        is WindowSize.Compact -> compactDimensions
        is WindowSize.Medium -> mediumDimensions
        else -> largeDimensions
    }
    val typography = when (sizeThatMatters) {
        is WindowSize.Small -> typographySmall
        is WindowSize.Compact -> typographyCompact
        is WindowSize.Medium -> typographyMedium
        else -> typographyBig
    }

    ProvideAppUtils(dimensions = dimensions, orientation = orientation) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}

object AppTheme {
    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current

    val orientation: Orientation
        @Composable
        get() = LocalOrientationMode.current
}