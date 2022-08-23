package cz.cvut.popovma1.spacex.ui.navigation

sealed class Screen(val route: String) {
    object RocketListNavScreen : Screen("RocketListNavScreen")
    object RocketDetailNavScreen : Screen("RocketDetailNavScreen")
    object RocketLaunchNavScreen : Screen("RocketLaunchNavScreen")

    fun expectArgs(vararg args: String) = buildString {
        append(route)
        args.forEach { arg -> append("/{$arg}") }
    }

    fun passArgs(vararg args: Any) = buildString {
        append(route)
        args.forEach { arg -> append("/$arg") }
    }

    companion object {
        const val ARG_ID = "id"
        const val ARG_ROCKET_ID = "rocketId"
        const val ARG_ROCKET_NAME = "rocketName"
    }
}
