package cz.cvut.popovma1.spacex.presentation.ui.rocketList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.presentation.theme.SpaceXTheme
import quanti.com.kotlinlog.Log

class RocketListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        setContent {
            SpaceXTheme {
                RocketListScreen(
                    rockets = RocketsSampleData.getRocketsList(),
                    onItemClick = { rocketId ->
                        val navController: NavController = findNavController() // from navigation-fragment-ktx
                        val action: NavDirections = RocketListFragmentDirections
                            .actionRocketListFragmentToRocketDetailFragment(
                                rocketId = rocketId
                            ) // from safeArgs

                        navController.navigate(action)
                        Log.d("RocketListFragment", "rocketId = $rocketId")
                    }
                )
            }
        }
    }
}