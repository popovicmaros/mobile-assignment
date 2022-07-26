package cz.cvut.popovma1.spacex.ui.ui.rocketList

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.Rocket
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.theme.*

@Composable
fun RocketListScreen(rockets: List<Rocket>, onItemClick: (Int) -> Unit) {
    RocketListWithTitle(rockets = rockets, onItemClick = onItemClick)
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SpaceXTheme {
        RocketListScreen(
            rockets = RocketsSampleData.getRocketsList(),
            onItemClick = {}
        )
    }
}

