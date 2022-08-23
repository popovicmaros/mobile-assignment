package cz.cvut.popovma1.spacex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.cvut.popovma1.spacex.feature.rocketdetail.system.RocketDetailNavScreen
import cz.cvut.popovma1.spacex.feature.rocketlaunch.system.RocketLaunchNavScreen
import cz.cvut.popovma1.spacex.feature.rocketlist.system.RocketListNavScreen
import cz.cvut.popovma1.spacex.ui.navigation.Screen.Companion.ARG_ID
import cz.cvut.popovma1.spacex.ui.navigation.Screen.Companion.ARG_ROCKET_ID
import cz.cvut.popovma1.spacex.ui.navigation.Screen.Companion.ARG_ROCKET_NAME

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RocketListNavScreen.route) {
        composable(route = Screen.RocketListNavScreen.route) {
            RocketListNavScreen(
                navController = navController,
            )
        }

        composable(
            route = Screen.RocketDetailNavScreen.expectArgs(ARG_ID, ARG_ROCKET_ID, ARG_ROCKET_NAME),
            arguments = listOf(
                navArgument(ARG_ID) { type = NavType.IntType },
            )
        ) { entry ->
            RocketDetailNavScreen(
                navController = navController,
                id = entry.arguments?.getInt(ARG_ID) ?: 0,
                rocketName = entry.arguments?.getString(ARG_ROCKET_NAME) ?: "",
            )
        }

        composable(
            route = Screen.RocketLaunchNavScreen.expectArgs(ARG_ROCKET_NAME),
        ) { entry ->
            RocketLaunchNavScreen(
                navController = navController,
                rocketName = entry.arguments?.getString(ARG_ROCKET_NAME) ?: "",
            )
        }
    }
}
