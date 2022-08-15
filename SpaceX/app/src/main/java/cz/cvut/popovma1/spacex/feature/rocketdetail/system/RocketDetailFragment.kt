package cz.cvut.popovma1.spacex.feature.rocketdetail.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cz.cvut.popovma1.spacex.feature.rocketdetail.presentation.RocketDetailViewModel
import cz.cvut.popovma1.spacex.repository.RocketRepositoryImpl
import cz.cvut.popovma1.spacex.repository.api.SpaceXRetrofitApi
import cz.cvut.popovma1.spacex.repository.database.RocketRoomDatabase
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import quanti.com.kotlinlog.Log

class RocketDetailFragment : Fragment() {

    private lateinit var viewModel: RocketDetailViewModel

    private fun setupViewModel() {

        if (!this::viewModel.isInitialized) {
            val spaceXApi = SpaceXRetrofitApi.spaceXApi
            val rocketDatabase = RocketRoomDatabase(requireContext()).let {
                RocketRoomDatabase.db
            }
            val rocketRepository = RocketRepositoryImpl(spaceXApi, rocketDatabase!!.rocketDao() /*tmp*/)
//        val viewModel: RocketListViewModel by viewModels()
            viewModel = RocketDetailViewModel(rocketRepository)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        setupViewModel()

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
