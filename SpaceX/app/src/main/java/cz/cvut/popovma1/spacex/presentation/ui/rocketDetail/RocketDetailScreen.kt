package cz.cvut.popovma1.spacex.presentation.ui.rocketDetail

import BackButton
import CenteredTitleTopBar
import LaunchButton
import cz.cvut.popovma1.spacex.presentation.component.topAppBar.ContentWithTopBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.presentation.theme.*

@Composable
fun RocketDetailScreen(
    rocket: Rocket,
    rocketPhotos: List<Int>,
    onBackClick: () -> Unit,
    onLaunchClick: () -> Unit,
) {
    ContentWithTopBar(
        topBar = { RocketDetailTopBar(
            title = rocket.rocketName,
            onBackClick = onBackClick,
            onLaunchClick = onLaunchClick
        ) }
    ) {
        LazyColumn (
            modifier = Modifier.padding(paddingMedium)
        ){
            item { RocketOverview(rocket) }
            item { RocketParameters(rocket) }
            item { RocketPhotos(rocketPhotos) }
        }
    }
}

@Composable
private fun RocketDetailTopBar(
    title: String,
    onBackClick: () -> Unit,
    onLaunchClick: () -> Unit
) {
    CenteredTitleTopBar(title = title) {
        BackButton(
            text = stringResource(id = R.string.rocket_list_title_rockets),
            onBackClick = onBackClick
        )
        Spacer(modifier = Modifier.weight(1f))
        LaunchButton(onLaunchClick = onLaunchClick)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRocketDetailScreen() {
    val rocket = RocketsSampleData.getRocket()
    val rocketPhotos = RocketsSampleData.getRocketPhotos()
    SpaceXTheme {
        RocketDetailScreen(
            rocket,
            rocketPhotos,
            onBackClick = {},
            onLaunchClick = {}
        )
    }
}