package cz.cvut.popovma1.spacex.ui.ui.rocketLaunch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.ui.theme.launchedRocketSize

@Composable
fun RocketLaunchScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Surface(

        ) {

        }
        Text(
            text = stringResource(id = R.string.rocket_launch_text_before_launch),
            modifier = Modifier
                .width(launchedRocketSize)
                .background(Color.Magenta),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRocketLaunchScreen() {
    SpaceXTheme {
        RocketLaunchScreen()
    }
}