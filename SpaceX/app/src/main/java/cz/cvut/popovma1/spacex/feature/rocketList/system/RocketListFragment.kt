package cz.cvut.popovma1.spacex.feature.rocketList.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.feature.rocketList.presentation.RocketListViewModel
import cz.cvut.popovma1.spacex.presentation.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.repository.model.Rocket
import quanti.com.kotlinlog.Log

class RocketListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        val viewModel: RocketListViewModel by viewModels()
        setContent {
            SpaceXTheme {
                RocketListScreen(
                    rockets = viewModel.rockets.collectAsState().value,
                    onItemClick = { rocketId, rocketName -> navigateToRocketDetail(rocketId, rocketName) },
                )
            }
        }
    }

    private fun navigateToRocketDetail(rocketId: Int, rocketName: String) {
        val navController: NavController = findNavController() // from navigation-fragment-ktx
        val action: NavDirections = RocketListFragmentDirections
            .actionRocketListFragmentToRocketDetailFragment(
                rocketId = rocketId,
                rocketName = rocketName
            ) // from safeArgs

        navController.navigate(action)
        Log.d("RocketListFragment", "rocketId = $rocketId")
    }
}