package cz.cvut.popovma1.spacex.feature.rocketlaunch.system

import BackButton
import CenteredTitleTopBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.ui.component.topappbar.ContentWithTopBar
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.ui.theme.launchedRocketSize

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
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_rocket_idle),
                contentDescription = stringResource(id = R.string.rocket_launch_image_description),
                modifier = Modifier
                    .width(launchedRocketSize)
//                    .padding(bottom = 1.dp) // TODO use in launch animation ?
            )
//            Spacer(modifier = Modifier.height(0.dp)) // TODO use in launch animation ?
        }
        Text(
            text = stringResource(id = getLaunchText(false)),
            modifier = Modifier.width(launchedRocketSize)
        )
        Spacer(modifier = Modifier.height(200.dp))
        getLaunchText(isLaunched = true)
    }
}

private fun getLaunchText(isLaunched: Boolean): Int =
    if(isLaunched)
        R.string.rocket_launch_text_before_launch
    else
        R.string.rocket_launch_text_after_launch


@Preview(showBackground = true)
@Composable
fun PreviewRocketLaunchScreen() {
    SpaceXTheme {
        RocketLaunchScreen("Falcon 1", {})
    }
}