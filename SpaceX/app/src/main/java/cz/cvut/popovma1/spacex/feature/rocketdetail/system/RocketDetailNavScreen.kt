package cz.cvut.popovma1.spacex.feature.rocketdetail.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.cvut.popovma1.spacex.feature.destinations.RocketLaunchNavScreenDestination
import cz.cvut.popovma1.spacex.feature.rocketdetail.presentation.RocketDetailViewModel
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun RocketDetailNavScreen(
    navigator: DestinationsNavigator,
    viewModel: RocketDetailViewModel = getViewModel(),
    id: Int,
    rocketName: String,
) {
    LaunchedEffect(key1 = true) {
        viewModel.getRocket(id = id)
    }

    RocketDetailScreen(
        rocket = viewModel.rocket.collectAsState().value, // causes +1 recompose
        rocketName = rocketName,
        onBackClick = navigator::popBackStack,
        onLaunchClick = {
            navigator.navigate(
                RocketLaunchNavScreenDestination(rocketName = rocketName)
            )
        },
        isRefreshing = viewModel.isRefreshing.collectAsState().value, // causes +2 recomposes
        refreshData = { viewModel.refreshRocket(id) } // doesn't cause any recompose
    )
}
