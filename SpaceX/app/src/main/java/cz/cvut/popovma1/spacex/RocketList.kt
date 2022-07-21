package cz.cvut.popovma1.spacex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.ui.theme.*

class RocketList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketListWithHeadline(rockets = RocketsSampleData.getRocketsData())
        }
    }
}

@Composable
fun RocketCard(rocket: Rocket) {
    Row(modifier = Modifier.padding(all = paddingSmall)) {
        Image(
            painter = painterResource(R.drawable.rocket),
            contentDescription = "Rocket icon",
            modifier = Modifier
                .size(iconSizeMedium)
        )

        Spacer(modifier = Modifier.width(spacerSize))

        Column {
            Text(
                text = rocket.rocketName,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(spacerSize))

            val firstFlightText =
                stringResource(R.string.rocket_list_first_flight) + rocket.firstFlight
            Text(
                text = firstFlightText,
                style = MaterialTheme.typography.subtitle1,
//                    color = MaterialTheme.colors. TODO color
            )
        }

        Image(
            painter = painterResource(R.drawable.ic_baseline_keyboard_arrow_right_24),
            contentDescription = "Rocket icon",
            modifier = Modifier
                .size(iconSizeMedium)
        )
    }
}

@Composable
fun RocketList(rockets: List<Rocket>) {
    LazyColumn {
        items(rockets) { rocket ->
            RocketCard(rocket)
        }
    }
}
//    @Preview(showBackground = true)
@Composable
fun RocketCardPreview() {
    SpaceXTheme {
        RocketCard(rocket = RocketsSampleData.getFirstRocketData())
    }
}

//    @Preview(showBackground = true)
@Composable
fun RocketListPreview() {
    SpaceXTheme {
        RocketList(rockets = RocketsSampleData.getRocketsData())
    }
}

@Composable
fun RocketListWithHeadline(rockets: List<Rocket>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Rockets",
            style = MaterialTheme.typography.h3,
        )
        Spacer(modifier = Modifier.width(spacerSize))
        RocketList(rockets)
    }
}

@Composable
fun RocketListScreen(rockets: List<Rocket>) {
    SpaceXTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
        ) {
            RocketListWithHeadline(rockets)
        }
    }
}

@Preview(showBackground = true)
    @Composable
    fun RocketListWithHeadlinePreview() {
        SpaceXTheme {
            RocketListScreen(rockets = RocketsSampleData.getRocketsData())
        }
    }

