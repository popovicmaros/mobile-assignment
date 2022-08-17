package cz.cvut.popovma1.spacex.feature.rocketlist.system

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.State
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.navigation.Screen

@Composable
fun RocketListNavScreen(
    navController: NavController,
//    viewModel: RocketListViewModel
) {
    RocketListScreen(
        onItemClick = { rocket ->
            navController.navigate(
                Screen.RocketDetailNavScreen.passArgs(
                    rocket.id,
                    rocket.rocketId,
                    rocket.rocketName
                )
            )
        },
        isRefreshing = false, // todo viewModel.isRefreshing.collectAsState().value,
        refreshData = {}, // todo viewModel::refreshRockets,
        rockets = ResponseWrapper(State.SUCCESS, RocketsSampleData.getRocketsList()) // todo viewModel.rockets.collectAsState().value

    )
}
