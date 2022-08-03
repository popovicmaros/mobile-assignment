package cz.cvut.popovma1.spacex.feature.rocketlist.system

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
import cz.cvut.popovma1.spacex.feature.rocketlist.presentation.RocketListViewModel
import cz.cvut.popovma1.spacex.repository.RocketRepositoryImpl
import cz.cvut.popovma1.spacex.repository.api.SpaceXApi
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import quanti.com.kotlinlog.Log
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class RocketListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        val spaceXApi = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(SpaceXApi::class.java)

        val rocketRepository = RocketRepositoryImpl(spaceXApi)
//        val viewModel: RocketListViewModel by viewModels()
        val viewModel = RocketListViewModel(rocketRepository)

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