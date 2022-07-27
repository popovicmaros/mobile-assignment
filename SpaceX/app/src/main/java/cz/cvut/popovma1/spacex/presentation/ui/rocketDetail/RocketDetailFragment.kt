package cz.cvut.popovma1.spacex.presentation.ui.rocketDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.presentation.theme.SpaceXTheme
import quanti.com.kotlinlog.Log

class RocketDetailFragment : Fragment() {

    private val args: RocketDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
//        Log.d( "rocketId = ${args.rocketId}")
        val rocket = RocketsSampleData.getRocket(id = args.rocketId)
        val rocketPhotos = RocketsSampleData.getRocketPhotos()

        setContent {
            SpaceXTheme {
                RocketDetailScreen(
                    rocket,
                    rocketPhotos,
                    onBackClick = { navigateBack() },
                    onLaunchClick = { navigateToRocketLaunch(rocketName = rocket.rocketName) }
                )
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