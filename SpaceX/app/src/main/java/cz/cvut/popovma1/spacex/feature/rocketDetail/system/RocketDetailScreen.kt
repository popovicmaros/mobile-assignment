package cz.cvut.popovma1.spacex.feature.rocketDetail.system

import BackButton
import CenteredTitleTopBar
import LaunchButton
import cz.cvut.popovma1.spacex.presentation.component.topAppBar.ContentWithTopBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.presentation.component.screen.LoadingScreen
import cz.cvut.popovma1.spacex.presentation.theme.*
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.State

@Composable
fun RocketDetailScreen(
    rocket: ResponseWrapper<Rocket>,
    rocketPhotos: List<Int>,
    onBackClick: () -> Unit,
    onLaunchClick: () -> Unit,
) {
    ContentWithTopBar(
        topBar = { RocketDetailTopBar(
            title = getRocketName(rocket),
            onBackClick = onBackClick,
            onLaunchClick = onLaunchClick
        ) }
    ) {
        LazyColumn (
            modifier = Modifier.padding(paddingMedium)
        ){
            when(rocket.state) {
                State.SUCCESS -> RocketDetailSuccess(rocket.data, rocketPhotos)
                State.LOADING -> item { LoadingScreen() }
                State.ERROR -> item { Text(text = "Error") }
            }
        }
    }
}

private fun LazyListScope.RocketDetailSuccess(
    rocket: Rocket,
    rocketPhotos: List<Int>
) {
    item { RocketOverview(rocket) }
    item { RocketParameters(rocket) }
    item { RocketPhotos(rocketPhotos) }
}

fun getRocketName(rocket: ResponseWrapper<Rocket>): String =
    when(rocket.state) {
        State.SUCCESS -> rocket.data.rocketName
        else -> "" // TODO pass rocket name from navig. args to have fixed topbar
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
    val rocketPhotos = RocketsSampleData.getRocketPhotos()
    SpaceXTheme {
        RocketDetailScreen(
            rocket = ResponseWrapper(State.SUCCESS, RocketsSampleData.getRocket()),
            rocketPhotos,
            onBackClick = {}
        ) {}
    }
}