package cz.cvut.popovma1.spacex.ui.ui.rocketList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.Rocket
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.theme.*

class RocketList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketListScreen(rockets = RocketsSampleData.getRocketsList())
        }
    }
}

@Composable
fun RocketListScreen(rockets: List<Rocket>) {
    SpaceXTheme {
        RocketListWithTitle(rockets = rockets)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    RocketListScreen(rockets = RocketsSampleData.getRocketsList())
}

