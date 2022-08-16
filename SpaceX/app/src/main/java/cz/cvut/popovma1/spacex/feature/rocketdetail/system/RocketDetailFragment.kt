package cz.cvut.popovma1.spacex.feature.rocketdetail.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cz.cvut.popovma1.spacex.feature.rocketdetail.presentation.RocketDetailViewModel
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import quanti.com.kotlinlog.Log

class RocketDetailFragment : Fragment() {

    private val viewModel: RocketDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        val args: RocketDetailFragmentArgs by navArgs()
        Log.d("id = ${args.id}")
        Log.d("rocketId = ${args.rocketId}")
        Log.d("rocketName = ${args.rocketName}") // passing rocketName to avoid topBar loading

        viewModel.getRocket(id = args.id) // db

        setContent {
            SpaceXTheme {
                RocketDetailScreen(
                    rocket = viewModel.rocket.collectAsState().value,
                    rocketName = args.rocketName,
                    onBackClick = ::navigateBack,
                    onLaunchClick = { navigateToRocketLaunch(rocketName = args.rocketName) },
                    isRefreshing = viewModel.isRefreshing.collectAsState().value,
                    refreshData = { viewModel.refreshRocket(id = args.id) }
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
