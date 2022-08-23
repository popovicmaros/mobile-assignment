package cz.cvut.popovma1.spacex.feature.rocketlist.system

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.ui.theme.firstFlightText
import cz.cvut.popovma1.spacex.ui.theme.iconSizeMedium
import cz.cvut.popovma1.spacex.ui.theme.paddingSmall
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeMedium
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeSmall

@Composable
fun RocketItem(
    rocket: Rocket,
    onItemClick: (Rocket) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable {
                onItemClick(rocket)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = paddingSmall, vertical = paddingSmall /* TODO */)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Spacer(modifier = Modifier.width(spacerSizeSmall))
            RocketIcon()
            Spacer(modifier = Modifier.width(spacerSizeMedium))
            Column {
                RocketName(rocket.rocketName)
                RocketFirstFlight(rocket.firstFlight)
            }
            Spacer(Modifier.weight(1f))
            RightArrowIcon()
        }
    }
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
        color = MaterialTheme.colors.firstFlightText
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
