package cz.cvut.popovma1.spacex.feature.rocketlaunch.system

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cz.cvut.popovma1.spacex.feature.rocketlaunch.presentation.RocketLaunchViewModel
import cz.cvut.popovma1.spacex.repository.RocketLaunchRepositoryImpl
import cz.cvut.popovma1.spacex.repository.gyroscope.PhoneLiftDetectionImpl
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme

class RocketLaunchFragment : Fragment() {

    private val args: RocketLaunchFragmentArgs by navArgs()
    private lateinit var viewModel: RocketLaunchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RocketLaunchFragment", "onCreate called")
    }

    private fun setupViewModel() {
        Log.d("RocketLaunchFragment", "isInitialized setupViewModel()")
        if (!this::viewModel.isInitialized) {
            val phoneLiftDetection = PhoneLiftDetectionImpl(requireContext())
            val orientation = this.resources.configuration.orientation
            val rocketLaunchRepository = RocketLaunchRepositoryImpl(phoneLiftDetection)
            viewModel = RocketLaunchViewModel(rocketLaunchRepository)
            viewModel.registerLiftSensor(orientation)
//            Log.d("RocketLaunchFragment", "isInitialized = false")
            Log.d("RocketLaunchFragment", "initializing new viewModel")
        } else {
            Log.d("RocketLaunchFragment", "isInitialized = true")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        setupViewModel()

        setContent {
            SpaceXTheme {
                RocketLaunchScreen(
                    rocketName = args.rocketName,
                    isLifted = viewModel.isLifted.collectAsState().value,
                    onBackClick = ::navigateBack,
                )
            }
        }
    }

    private fun navigateBack() {
        Log.d("RocketLaunchFragment", "navigateBack() pressed")
        findNavController().popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unregisterLiftSensor()
        Log.d("RocketLaunchFragment", "onDestroy called")
    }
}
