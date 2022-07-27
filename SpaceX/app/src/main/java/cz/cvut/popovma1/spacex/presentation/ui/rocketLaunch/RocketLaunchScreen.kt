package cz.cvut.popovma1.spacex.presentation.ui.rocketLaunch

import BackButton
import CenteredTitleTopBar
import LaunchButton
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.presentation.component.topAppBar.ContentWithTopBar
import cz.cvut.popovma1.spacex.presentation.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.presentation.theme.launchedRocketSize

@Composable
fun RocketLaunchScreen(rocketName: String, onBackClick: () -> Unit) {
    ContentWithTopBar(
        topBar = { RocketLaunchTopBar(
            rocketName = rocketName,
            onBackClick = onBackClick,
        ) }
    ) {
        RocketLaunchContent()
    }
}

@Composable
fun RocketLaunchTopBar(rocketName: String, onBackClick: () -> Unit) {
    CenteredTitleTopBar(title = stringResource(id = R.string.rocket_launch_title)) {
        BackButton(text = rocketName, onBackClick = onBackClick)
    }
}

@Composable
fun RocketLaunchContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Text(
            text = stringResource(id = R.string.rocket_launch_text_before_launch),
            modifier = Modifier
                .width(launchedRocketSize)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRocketLaunchScreen() {
    SpaceXTheme {
        RocketLaunchScreen("RocketName", {})
    }
}