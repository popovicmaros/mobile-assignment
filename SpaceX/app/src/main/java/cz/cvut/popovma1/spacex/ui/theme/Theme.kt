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
    primary = QuantiPink500,
    primaryVariant = QuantiPink900,
    secondary = Blue200,
    background = BackgroundDark100,
    surface = BackgroundDark200
)

private val LightColorPalette = lightColors(
    primary = QuantiPink200,
    primaryVariant = QuantiPink500,
    secondary = Blue200,
    background = Background100,
    surface = Background200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val Colors.firstFlightText: Color
    @Composable
    get() = Gray500

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
/*
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
*/
    }
}

/*
@Composable
fun TopBarSpaceXTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette.copy(
            primary = Blue200
        )
    } else {
        LightColorPalette.copy(
            primary = Blue200
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
    ) {
        content()
//        Box(modifier = Modifier.fillMaxSize()) {
//            content()
//        }
    }
}
*/
