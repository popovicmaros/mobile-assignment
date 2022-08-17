package cz.cvut.popovma1.spacex.feature.rocketlaunch.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import cz.cvut.popovma1.spacex.feature.rocketlaunch.presentation.RocketLaunchViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun RocketLaunchNavScreen(
    navController: NavController,
    viewModel: RocketLaunchViewModel = getViewModel(),
    rocketName: String
) {
    // todo rocket doesn't launch
    RocketLaunchScreen(
        rocketName = rocketName,
        isLifted = viewModel.isLifted.collectAsState().value,
        onBackClick = navController::popBackStack
    )
}
