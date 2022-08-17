package cz.cvut.popovma1.spacex.feature.rocketlist.system

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.component.snackbar.ShowLoadingErrorSnackbar
import cz.cvut.popovma1.spacex.ui.component.stateful.Loading
import cz.cvut.popovma1.spacex.ui.component.stateful.informationStateful.Error
import cz.cvut.popovma1.spacex.ui.component.stateful.informationStateful.NoData
import cz.cvut.popovma1.spacex.ui.component.topappbar.ContentWithTopBar
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import kotlinx.coroutines.CoroutineScope
import quanti.com.kotlinlog.Log

@Composable
fun RocketListScreen(
    navController: NavHostController,
    onItemClick: (Rocket) -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    rockets: ResponseWrapper<List<Rocket>>,
) {
    // setup snackbar
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Log.d("@Composable RocketListScreen recomposed")

    ContentWithTopBar(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_activity_rocket_list)) },
            )
        },
        scaffoldState = scaffoldState
    ) {
        when (rockets.state) {
            State.SUCCESS -> {
                RocketListSuccess(
                    navController = navController,
                    rockets = rockets,
                    onItemClick = onItemClick,
                    isRefreshing = isRefreshing,
                    refreshData = refreshData,
                )
            }
            State.LOADING -> Loading()
            State.ERROR -> {
                Error(
                    isRefreshing = isRefreshing,
                    refreshData = refreshData,
                )
                ShowLoadingErrorSnackbar(
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState,
                    onActionPerformed = refreshData
                )
            }
            State.NO_DATA -> NoData(
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
//            rockets = ResponseWrapper(State.LOADING, listOf()),
//            rockets = ResponseWrapper(State.ERROR, listOf()),
//            rockets = ResponseWrapper(State.NO_DATA, listOf()),
            navController = rememberNavController(),
            onItemClick = {},
            isRefreshing = false,
            refreshData = {},
            rockets = ResponseWrapper(State.SUCCESS, RocketsSampleData.getRocketsList()),
        )
    }
}
