package cz.cvut.popovma1.spacex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = quantiPink500,
    secondary = blue200,
    background = backgroundDark100,
    surface = backgroundDark200
)

private val LightColorPalette = lightColors(
    primary = quantiPink200,
    secondary = blue200,
    background = background100,
    surface = background200
)

val Colors.firstFlightText: Color
    @Composable
    get() = gray500 /* for both dark & light */

@Composable
fun SpaceXTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
    ) {
        rememberSystemUiController().let {
            it.setNavigationBarColor(color = colors.background, darkIcons = !darkTheme)
            it.setStatusBarColor(color = colors.surface, darkIcons = !darkTheme)
        }
        content()
    }
}
