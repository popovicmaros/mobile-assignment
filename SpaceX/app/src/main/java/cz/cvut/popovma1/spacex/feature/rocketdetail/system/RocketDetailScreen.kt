package cz.cvut.popovma1.spacex.feature.rocketdetail.system

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.component.snackbar.ShowLoadingErrorSnackbar
import cz.cvut.popovma1.spacex.ui.component.stateful.Loading
import cz.cvut.popovma1.spacex.ui.component.stateful.informationStateful.Error
import cz.cvut.popovma1.spacex.ui.component.topappbar.ContentWithTopBar
import cz.cvut.popovma1.spacex.ui.component.topappbar.TopBarCustom
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.ui.theme.paddingMedium
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeMedium
import kotlinx.coroutines.CoroutineScope

@Composable
fun RocketDetailScreen(
    rocket: ResponseWrapper<Rocket>,
    rocketName: String,
    onBackClick: () -> Unit,
    onLaunchClick: () -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
) {
    // snackbar setup
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    ContentWithTopBar(
        topBar = {
            TopBarCustom(
                title = rocketName,
                backButtonText = stringResource(id = R.string.rocket_list_title_rockets),
                onBackButtonClick = onBackClick,
                actionButtonText = stringResource(id = R.string.rocket_launch_title),
                onActionButtonClick = onLaunchClick
            )
        },
        scaffoldState = scaffoldState,
    ) {
        when (rocket.state) {
            State.SUCCESS -> RocketDetailSuccess(
                rocket = rocket.data,
                isRefreshing = isRefreshing,
                refreshData = refreshData
            )
            State.LOADING -> Loading()
            else -> {
                Error(
                    isRefreshing = isRefreshing,
                    refreshData = refreshData
                )
                ShowLoadingErrorSnackbar(
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState,
                    onActionPerformed = refreshData
                    // TODO fix snackbar showing up multiple times after screen rotation (error snackBars are being added to queue)
                )
            }
        }
    }
}

@Composable
private fun RocketDetailSuccess(
    rocket: Rocket,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = refreshData
    ) {
        LazyColumn(contentPadding = PaddingValues(horizontal = paddingMedium)) {
            item { Spacer(modifier = Modifier.height(height = spacerSizeMedium)) }
            item { RocketOverview(rocket) }
            item { RocketParameters(rocket) }
            item { RocketImages(rocket.images) }
            item { Spacer(modifier = Modifier.height(height = spacerSizeMedium)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRocketDetailScreen() {
    val rocket = RocketsSampleData.getRocket()
    SpaceXTheme {
        RocketDetailScreen(
            rocket = ResponseWrapper(State.SUCCESS, rocket),
//            rocket = ResponseWrapper(State.LOADING, Rocket.NULL_ROCKET),
//            rocket = ResponseWrapper(State.ERROR, Rocket.NULL_ROCKET),
            rocketName = rocket.rocketName,
            onBackClick = {},
            onLaunchClick = {},
            isRefreshing = false,
            refreshData = {}
        )
    }
}
