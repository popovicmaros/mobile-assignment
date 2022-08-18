package cz.cvut.popovma1.spacex.feature.rocketlaunch.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import cz.cvut.popovma1.spacex.feature.rocketlaunch.presentation.RocketLaunchViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun RocketLaunchNavScreen(
    navController: NavController,
    viewModel: RocketLaunchViewModel = getViewModel(),
    rocketName: String
) {
    setupSensor(viewModel)
    RocketLaunchScreen(
        rocketName = rocketName,
        isLifted = viewModel.isLifted.collectAsState().value,
        onBackClick = navController::popBackStack
    )
}

@Composable
private fun setupSensor(
    viewModel: RocketLaunchViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    orientation: Int = LocalConfiguration.current.orientation
) {
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    viewModel.registerLiftSensor(orientation)
                }
                Lifecycle.Event.ON_DESTROY -> {
                    viewModel.unregisterLiftSensor()
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
