package cz.cvut.popovma1.spacex.feature.rocketlaunch.system

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cz.cvut.popovma1.spacex.feature.rocketlaunch.presentation.RocketLaunchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketLaunchFragment : Fragment() {

    private val args: RocketLaunchFragmentArgs by navArgs()
    private val viewModel: RocketLaunchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RocketLaunchFragment", "onCreate called")
        viewModel.registerLiftSensor(orientation = resources.configuration.orientation)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
/*
        setContent {
            SpaceXTheme {
                RocketLaunchScreen(
                    rocketName = args.rocketName,
                    isLifted = viewModel.isLifted.collectAsState().value,
                    onBackClick = ::navigateBack,
                )
            }
        }
*/
    }

    private fun navigateBack() {
        Log.d("RocketLaunchFragment", "navigateBack() pressed")
        findNavController().popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unregisterLiftSensor()
        Log.d("RocketLaunchFragment", "RocketLaunchFragment onDestroy called")
    }
}
