package cz.cvut.popovma1.spacex.ui.ui.rocketDetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.theme.*

class RocketDetail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketDetailScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RocketDetailScreen() {
    SpaceXTheme {
        val rocket = RocketsSampleData.getRocket()
        val rocketPhotos = RocketsSampleData.getRocketPhotos()
        LazyColumn (
            modifier = Modifier.padding(paddingMedium)
        ){
            item { RocketOverview(rocket) }
            item { RocketParameters(rocket) }
            item { RocketPhotos(rocketPhotos) }
        }

    }

}