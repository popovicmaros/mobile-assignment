package cz.cvut.popovma1.spacex.presentation.ui.rocketDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.Rocket
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.presentation.theme.*

@Composable
fun RocketDetailScreen(rocket: Rocket, rocketPhotos: List<Int>) {
        LazyColumn (
            modifier = Modifier.padding(paddingMedium)
        ){
            item { RocketOverview(rocket) }
            item { RocketParameters(rocket) }
            item { RocketPhotos(rocketPhotos) }
        }
}

@Preview(showBackground = true)
@Composable
fun PreviewRocketDetailScreen() {
    val rocket = RocketsSampleData.getRocket()
    val rocketPhotos = RocketsSampleData.getRocketPhotos()
    SpaceXTheme {
        RocketDetailScreen(rocket, rocketPhotos)
    }
}