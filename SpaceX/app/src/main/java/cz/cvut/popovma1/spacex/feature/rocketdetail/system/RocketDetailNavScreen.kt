package cz.cvut.popovma1.spacex.feature.rocketdetail.system

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.State
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.navigation.Screen

@Composable
fun RocketDetailNavScreen(
    navController: NavController,
//    todo viewModel: RocketDetailViewModel,
    id: Int,
    rocketName: String,
) {
//   todo viewModel.getRocket(id = id)

    RocketDetailScreen(
        rocket = ResponseWrapper(State.SUCCESS, RocketsSampleData.getRocket()), // viewModel.rocket.collectAsState().value,
        rocketName = rocketName,
        onBackClick = navController::popBackStack,
        onLaunchClick = {
            navController.navigate(
                route = Screen.RocketLaunchNavScreen.passArgs(rocketName)
            )
        },
        isRefreshing = false, // todo viewModel.isRefreshing.collectAsState().value,
        refreshData = {} // todo { viewModel.refreshRocket(id) }
    )
}
