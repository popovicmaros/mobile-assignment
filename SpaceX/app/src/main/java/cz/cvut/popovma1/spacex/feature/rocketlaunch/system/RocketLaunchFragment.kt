package cz.cvut.popovma1.spacex.feature.rocketlaunch.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cz.cvut.popovma1.spacex.repository.gyroscope.PhoneLiftDetection
import cz.cvut.popovma1.spacex.repository.gyroscope.PhoneLiftDetectionImpl
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import quanti.com.kotlinlog.Log

class RocketLaunchFragment : Fragment() {

    private val args: RocketLaunchFragmentArgs by navArgs()
    private lateinit var phoneLiftDetection: PhoneLiftDetection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        phoneLiftDetection = PhoneLiftDetectionImpl(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        setContent {
            SpaceXTheme {
                RocketLaunchScreen(
                    rocketName = args.rocketName,
                    onBackClick = { navigateBack() },
                    isLifted = phoneLiftDetection.isLifted.collectAsState().value,
                )
            }
        }
    }

    private fun navigateBack() {
        Log.d("navigateBack() pressed")
        findNavController().popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        phoneLiftDetection.unregisterSensor()
    }
}
