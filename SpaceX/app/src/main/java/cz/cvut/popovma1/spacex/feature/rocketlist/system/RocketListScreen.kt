package cz.cvut.popovma1.spacex.feature.rocketlist.system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.component.snackbar.ShowLoadingErrorSnackbar
import cz.cvut.popovma1.spacex.ui.component.stateful.Loading
import cz.cvut.popovma1.spacex.ui.component.stateful.informationStateful.Error
import cz.cvut.popovma1.spacex.ui.component.stateful.informationStateful.NoData
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import kotlinx.coroutines.CoroutineScope
import quanti.com.kotlinlog.Log

@Composable
fun RocketListScreen(
    onItemClick: (Rocket) -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    rockets: ResponseWrapper<List<Rocket>>,
) {
    // setup snackbar
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Log.d("RocketListScreen recomposed")

    Scaffold(scaffoldState = scaffoldState) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(color = MaterialTheme.colors.surface)
        ) {
            when (rockets.state) {
                State.SUCCESS -> {
                    RocketList(
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
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SpaceXTheme {
        RocketListScreen(
//            rockets = ResponseWrapper(State.LOADING, listOf()),
//            rockets = ResponseWrapper(State.ERROR, listOf()),
//            rockets = ResponseWrapper(State.NO_DATA, listOf()),
            onItemClick = {},
            isRefreshing = false,
            refreshData = {},
            rockets = ResponseWrapper(
                State.SUCCESS,
                RocketsSampleData.getRocketsList().subList(0, 4)
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDark() {
    SpaceXTheme(darkTheme = true) {
        RocketListScreen(
            onItemClick = {},
            isRefreshing = false,
            refreshData = {},
//            rockets = ResponseWrapper(State.LOADING, listOf()),
//            rockets = ResponseWrapper(State.ERROR, listOf()),
//            rockets = ResponseWrapper(State.NO_DATA, listOf()),
            rockets = ResponseWrapper(
                State.SUCCESS,
                RocketsSampleData.getRocketsList().subList(0, 4)
            ),
        )
    }
}
