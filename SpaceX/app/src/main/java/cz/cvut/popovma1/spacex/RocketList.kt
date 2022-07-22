package cz.cvut.popovma1.spacex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.cvut.popovma1.spacex.ui.theme.*

class RocketList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketListScreen(rockets = RocketsSampleData.getRocketsData())
        }
    }
}

@Composable
fun RocketCard(rocket: Rocket) {
    Row(
        modifier = Modifier
            .padding(all = paddingSmall)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RocketIcon()
        Spacer(modifier = Modifier.width(spacerSize))
        Column {
            RocketName(rocket.rocketName)
            RocketFirstFlight(rocket.firstFlight)
        }
        Spacer(Modifier.weight(1f))
        RightArrowIcon()
    }
}

@Composable
fun RocketName(rocketName: String) {
    Text(
        text = rocketName,
        style = MaterialTheme.typography.h6
    )
}

@Composable
fun RocketFirstFlight(firstFlight: String) {
    Text(
        text = stringResource(R.string.rocket_list_first_flight, firstFlight),
        style = MaterialTheme.typography.subtitle1,
//                    color = MaterialTheme.colors. TODO color
    )
}

@Composable
fun RocketIcon() {
    Image(
        painter = painterResource(R.drawable.ic_rocket),
        contentDescription = stringResource(
            id = R.string.rocket_detail_rocket_icon_desc
        ),
        modifier = Modifier
            .size(iconSizeMedium)
    )
}

@Composable
fun RightArrowIcon() {
    Image(
        painter = painterResource(R.drawable.ic_baseline_keyboard_arrow_right_24),
        contentDescription = stringResource(
            id = R.string.rocket_detail_right_arrow_icon_desc
        ),
        modifier = Modifier
            .size(iconSizeMedium)
    )
}

@Composable
fun RocketList(rockets: List<Rocket>) {
    Surface (
        shape = RoundedCornerShape(cornerRadius),
        elevation = 1.dp
    ){
        LazyColumn {
            items(rockets) { rocket ->
                RocketCard(rocket)
            }
        }
    }
}

@Composable
fun RocketListWithHeadline(rockets: List<Rocket>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingMedium),
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LargeTitle(stringResource(id = R.string.rocket_list_title_rockets))
        Spacer(modifier = Modifier.width(spacerSize))
        RocketList(rockets)
    }
}

@Composable
fun LargeTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h3,
    )
}

@Composable
fun RocketListScreen(rockets: List<Rocket>) {
    SpaceXTheme {
        Surface( //TODO remove surface?
            modifier = Modifier
                .fillMaxSize()
//                .padding(paddingMedium),
                    ,
        ) {
            RocketListWithHeadline(rockets)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    RocketListScreen(rockets = RocketsSampleData.getRocketsData())
}

