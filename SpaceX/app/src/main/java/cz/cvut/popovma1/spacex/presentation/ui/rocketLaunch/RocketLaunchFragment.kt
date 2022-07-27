package cz.cvut.popovma1.spacex.presentation.ui.rocketLaunch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.presentation.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.presentation.ui.rocketDetail.RocketDetailFragmentArgs
import quanti.com.kotlinlog.Log

class RocketLaunchFragment : Fragment() {

    private val args: RocketLaunchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        setContent {
            SpaceXTheme {
                RocketLaunchScreen(
                    rocketName = args.rocketName,
                    onBackClick = { navigateBack() }
                )
            }
        }
    }

    private fun navigateBack() {
        Log.d("navigateBack() pressed")
        findNavController().popBackStack()
    }

}