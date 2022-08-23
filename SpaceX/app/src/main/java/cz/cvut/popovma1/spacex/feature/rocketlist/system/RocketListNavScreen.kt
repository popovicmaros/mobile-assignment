package cz.cvut.popovma1.spacex.feature.rocketlist.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import cz.cvut.popovma1.spacex.feature.rocketlist.presentation.RocketListViewModel
import cz.cvut.popovma1.spacex.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun RocketListNavScreen(
    navController: NavController,
    viewModel: RocketListViewModel = getViewModel()
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
        isRefreshing = viewModel.isRefreshing.collectAsState().value,
        refreshData = viewModel::refreshRockets,
        rockets = viewModel.rockets.collectAsState().value

    )
}
