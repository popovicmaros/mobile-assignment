package cz.cvut.popovma1.spacex.feature.rocketdetail.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import cz.cvut.popovma1.spacex.feature.rocketdetail.presentation.RocketDetailViewModel
import cz.cvut.popovma1.spacex.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun RocketDetailNavScreen(
    navController: NavController,
    viewModel: RocketDetailViewModel = getViewModel(),
    id: Int,
    rocketName: String,
) {
    LaunchedEffect(key1 = id) {
        viewModel.getRocket(id = id)
    }

    RocketDetailScreen(
        rocket = viewModel.rocket.collectAsState().value,
        rocketName = rocketName,
        onBackClick = navController::popBackStack,
        onLaunchClick = {
            navController.navigate(
                route = Screen.RocketLaunchNavScreen.passArgs(rocketName)
            )
        },
        isRefreshing = viewModel.isRefreshing.collectAsState().value,
        refreshData = { viewModel.refreshRocket(id) }
    )
}
