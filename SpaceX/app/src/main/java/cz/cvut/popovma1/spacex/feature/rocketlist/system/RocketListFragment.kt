package cz.cvut.popovma1.spacex.feature.rocketlist.system

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import cz.cvut.popovma1.spacex.feature.rocketlist.presentation.RocketListViewModel
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketListFragment : Fragment() {

    private val viewModel: RocketListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RocketListFragment", "onCreate called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("RocketListFragment", "onDestroy called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        setContent {
            SpaceXTheme {
                RocketListScreen(
                    rockets = viewModel.rockets.collectAsState().value,
                    onItemClick = ::navigateToRocketDetail,
                    isRefreshing = viewModel.isRefreshing.collectAsState().value,
                    refreshData = viewModel::refreshRockets
                )
            }
        }
    }

    private fun navigateToRocketDetail(rocket: Rocket) {
        val navController: NavController = findNavController() // from navigation-fragment-ktx
        val action: NavDirections = RocketListFragmentDirections
            .actionRocketListFragmentToRocketDetailFragment(
                id = rocket.id,
                rocketId = rocket.rocketId,
                rocketName = rocket.rocketName
            ) // from safeArgs

        navController.navigate(action)
        Log.d("RocketListFragment", "rocketId = ${rocket.rocketId}")
    }
}
