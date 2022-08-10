package cz.cvut.popovma1.spacex.feature.rocketlist.system

import cz.cvut.popovma1.spacex.ui.component.topappbar.ContentWithTopBar
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.ui.theme.*
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.State
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.component.screen.LoadingScreen
import cz.cvut.popovma1.spacex.ui.component.screen.informationScreen.ErrorScreen
import cz.cvut.popovma1.spacex.ui.component.screen.informationScreen.NoDataScreen
import cz.cvut.popovma1.spacex.ui.component.snackbar.showLoadingErrorSnackbar
import kotlinx.coroutines.CoroutineScope
import quanti.com.kotlinlog.Log

@Composable
fun RocketListScreen(
    rockets: ResponseWrapper<List<Rocket>>,
    onItemClick: (Rocket) -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
) {
    // setup snackbar
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Log.d("@Composable RocketListScreen recomposed")

    ContentWithTopBar(
        topBar = { TopAppBar(
            title = { Text(stringResource(id = R.string.title_activity_rocket_list)) },
        )},
        scaffoldState = scaffoldState
    ) {
        when (rockets.state) {
            State.SUCCESS -> {
                RocketListSuccess(
                    rockets = rockets,
                    onItemClick = onItemClick,
                    isRefreshing = isRefreshing,
                    refreshData = refreshData,
                )
            }
            State.LOADING -> LoadingScreen()
            State.ERROR -> {
                ErrorScreen(
                    isRefreshing = isRefreshing,
                    refreshData = refreshData,
                )
                showLoadingErrorSnackbar(
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState,
                    onActionPerformed = refreshData
                )
            }
            State.NO_DATA -> NoDataScreen(
                isRefreshing = isRefreshing,
                refreshData = refreshData,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SpaceXTheme {
        RocketListScreen(
            rockets = ResponseWrapper(State.SUCCESS, RocketsSampleData.getRocketsList()),
//            rockets = ResponseWrapper(State.LOADING, listOf()),
//            rockets = ResponseWrapper(State.ERROR, listOf()),
//            rockets = ResponseWrapper(State.NO_DATA, listOf()),
            onItemClick = {},
            isRefreshing = false,
            refreshData = {},
        )
    }
}



