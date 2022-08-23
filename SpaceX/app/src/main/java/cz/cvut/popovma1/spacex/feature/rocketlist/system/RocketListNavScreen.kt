package cz.cvut.popovma1.spacex.feature.rocketlist.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.cvut.popovma1.spacex.feature.destinations.RocketDetailNavScreenDestination
import cz.cvut.popovma1.spacex.feature.rocketlist.presentation.RocketListViewModel
import org.koin.androidx.compose.getViewModel

@Destination(start = true)
@Composable
fun RocketListNavScreen(
    navigator: DestinationsNavigator,
    viewModel: RocketListViewModel = getViewModel()
) {
    RocketListScreen(
        onItemClick = { rocket ->
            navigator.navigate(
                RocketDetailNavScreenDestination(
                    id = rocket.id,
                    rocketName = rocket.rocketName
                )
            )
        },
        isRefreshing = viewModel.isRefreshing.collectAsState().value,
        refreshData = viewModel::refreshRockets,
        rockets = viewModel.rockets.collectAsState().value

    )
}
