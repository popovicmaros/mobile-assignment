package cz.cvut.popovma1.spacex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.cvut.popovma1.spacex.feature.rocketdetail.system.RocketDetailScreen
import cz.cvut.popovma1.spacex.feature.rocketlaunch.system.RocketLaunchScreen
import cz.cvut.popovma1.spacex.feature.rocketlist.system.RocketListScreen
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.State
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.navigation.Screen.Companion.ARG_ID
import cz.cvut.popovma1.spacex.ui.navigation.Screen.Companion.ARG_ROCKET_ID
import cz.cvut.popovma1.spacex.ui.navigation.Screen.Companion.ARG_ROCKET_NAME

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RocketListScreen.route) {
        composable(route = Screen.RocketListScreen.route) {
            RocketListScreen(
                navController = navController,
                rockets = ResponseWrapper(State.SUCCESS, RocketsSampleData.getRocketsList()),
                onItemClick = {},
                isRefreshing = false,
                refreshData = {}
            )
        }

        composable(
            route = Screen.RocketDetailScreen.expectArgs(ARG_ID, ARG_ROCKET_ID, ARG_ROCKET_NAME),
            arguments = listOf(
                navArgument(ARG_ID) { type = NavType.IntType },
            )
        ) { entry ->
            RocketDetailScreen(
                navController = navController,
                rocket = ResponseWrapper(
                    State.SUCCESS,
                    RocketsSampleData.getRocket(
                        id = entry.arguments?.getInt(ARG_ID) ?: 0
                    )
                ),
                rocketId = entry.arguments?.getString(ARG_ROCKET_ID) ?: "",
                rocketName = entry.arguments?.getString(ARG_ROCKET_NAME) ?: "",
                onBackClick = {},
                onLaunchClick = {},
                isRefreshing = false,
                refreshData = {}
            )
        }

        composable(
            route = Screen.RocketLaunchScreen.expectArgs(ARG_ROCKET_NAME),
        ) { entry ->
            RocketLaunchScreen(
                navController = navController,
                rocketName = entry.arguments?.getString(ARG_ROCKET_NAME) ?: "",
                isLifted = false,
                onBackClick = {}
            )
        }
    }
}
