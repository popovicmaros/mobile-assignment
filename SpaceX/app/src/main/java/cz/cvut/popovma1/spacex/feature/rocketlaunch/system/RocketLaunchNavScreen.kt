package cz.cvut.popovma1.spacex.feature.rocketlaunch.system

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun RocketLaunchNavScreen(
    navController: NavController,
//    viewModel: RocketLaunchViewModel, // todo
    rocketName: String
) {

    RocketLaunchScreen(
        rocketName = rocketName,
        isLifted = false, // todo viewModel.isLifted.collectAsState().value,
        onBackClick = navController::popBackStack
    )
}
