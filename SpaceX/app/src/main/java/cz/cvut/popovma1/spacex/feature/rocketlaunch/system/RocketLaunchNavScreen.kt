package cz.cvut.popovma1.spacex.feature.rocketlaunch.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.cvut.popovma1.spacex.feature.rocketlaunch.presentation.RocketLaunchViewModel
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun RocketLaunchNavScreen(
    navigator: DestinationsNavigator,
    viewModel: RocketLaunchViewModel = getViewModel(),
    rocketName: String
) {
    SetUpSensor(viewModel)
    RocketLaunchScreen(
        rocketName = rocketName,
        isLifted = viewModel.isLifted.collectAsState().value,
        onBackClick = navigator::popBackStack
    )
}

@Composable
private fun SetUpSensor(
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
