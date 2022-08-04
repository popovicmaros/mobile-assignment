package cz.cvut.popovma1.spacex.feature.rocketdetail.system

import BackButton
import CenteredTitleTopBar
import LaunchButton
import cz.cvut.popovma1.spacex.ui.component.topappbar.ContentWithTopBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.component.screen.LoadingScreen
import cz.cvut.popovma1.spacex.ui.component.snackbar.showLoadingErrorSnackbar
import cz.cvut.popovma1.spacex.ui.theme.*
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.State
import cz.cvut.popovma1.spacex.ui.component.screen.informationScreen.ErrorScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun RocketDetailScreen(
    rocket: ResponseWrapper<Rocket>,
    rocketName: String,
    onBackClick: () -> Unit,
    onLaunchClick: () -> Unit,
) {
    // snackbar setup
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    ContentWithTopBar(
        topBar = { RocketDetailTopBar(
            title = rocketName,
            onBackClick = onBackClick,
            onLaunchClick = onLaunchClick,
        ) },
        scaffoldState = scaffoldState,
    ) {
            when(rocket.state) {
                State.SUCCESS -> RocketDetailSuccess(rocket.data)
                State.LOADING -> LoadingScreen()
                else -> {
                    ErrorScreen()
                    showLoadingErrorSnackbar(
                        coroutineScope,
                        scaffoldState
                        // TODO onActionPerformed -> call refresh()
                        // TODO fix snackbar showing up multiple times after screen rotation (error snackBars are being added to queue)
                    )
                }
            }
    }
}

@Composable
private fun RocketDetailSuccess(
    rocket: Rocket,
) {
    LazyColumn (modifier = Modifier.padding(paddingMedium)) {
        item { RocketOverview(rocket) }
        item { RocketParameters(rocket) }
        item { RocketImages(rocket.images) }
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
    SpaceXTheme {
        RocketDetailScreen(
//            rocket = ResponseWrapper(State.SUCCESS, rocket),
//            rocket = ResponseWrapper(State.LOADING, Rocket.NULL_ROCKET),
            rocket = ResponseWrapper(State.ERROR, Rocket.NULL_ROCKET),
            rocketName = rocket.rocketName,
            onBackClick = {},
            onLaunchClick = {}
        )
    }
}