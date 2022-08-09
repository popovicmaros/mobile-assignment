package cz.cvut.popovma1.spacex.feature.rocketlist.system

import android.os.Bundle
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
import cz.cvut.popovma1.spacex.repository.RocketRepositoryImpl
import cz.cvut.popovma1.spacex.repository.api.SpaceXRetrofitApi
import cz.cvut.popovma1.spacex.repository.database.RocketRoomDatabase
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import quanti.com.kotlinlog.Log

class RocketListFragment : Fragment() {

    private lateinit var viewModel: RocketListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!this::viewModel.isInitialized) {
            val spaceXApi = SpaceXRetrofitApi.spaceXApi
            val rocketDatabase = RocketRoomDatabase(requireContext())
            val rocketRepository = RocketRepositoryImpl(spaceXApi, RocketRoomDatabase.db!! /*tmp*/)
//        val viewModel: RocketListViewModel by viewModels()
            viewModel = RocketListViewModel(rocketRepository)
        }

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
