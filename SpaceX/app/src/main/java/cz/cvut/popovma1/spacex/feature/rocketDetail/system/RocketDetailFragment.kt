package cz.cvut.popovma1.spacex.feature.rocketDetail.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.feature.rocketDetail.presentation.RocketDetailViewModel
import cz.cvut.popovma1.spacex.presentation.theme.SpaceXTheme
import quanti.com.kotlinlog.Log

class RocketDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        val args: RocketDetailFragmentArgs by navArgs()
        Log.d( "rocketId = ${args.rocketId}")
//        Log.d( "rocketName = ${args.rocketName}")

        val rocketPhotos = RocketsSampleData.getRocketPhotos()

        val viewModel: RocketDetailViewModel by viewModels()
        viewModel.getRocket(id = args.rocketId)

        setContent {
            SpaceXTheme {
                RocketDetailScreen(
                    rocket = viewModel.rocket.collectAsState().value,
                    rocketPhotos,
                    onBackClick = { navigateBack() }
                ) { navigateToRocketLaunch(rocketName = "args.rocketName") } // TODO args.rocketName
            }
        }
    }

    private fun navigateToRocketLaunch(rocketName: String) {
        Log.d("navigateToRocketLaunch() pressed")
        val action: NavDirections = RocketDetailFragmentDirections
            .actionRocketDetailFragmentToRocketLaunchFragment(
                rocketName = rocketName
            )
        findNavController().navigate(action)
    }

    private fun navigateBack() {
        Log.d("navigateBack() pressed")
        findNavController().popBackStack()
    }
}