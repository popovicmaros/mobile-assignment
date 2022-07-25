package cz.cvut.popovma1.spacex.ui.ui.rocketList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.Rocket
import cz.cvut.popovma1.spacex.ui.theme.iconSizeMedium
import cz.cvut.popovma1.spacex.ui.theme.paddingSmall
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeMedium

@Composable
fun RocketItem(rocket: Rocket) {
    Row(
        modifier = Modifier
            .padding(all = paddingSmall)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RocketIcon()
        Spacer(modifier = Modifier.width(spacerSizeMedium))
        Column {
            RocketName(rocket.rocketName)
            RocketFirstFlight(rocket.firstFlight)
        }
        Spacer(Modifier.weight(1f))
        RightArrowIcon()
    }
    Divider(startIndent = paddingSmall)
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

