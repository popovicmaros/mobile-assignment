package cz.cvut.popovma1.spacex.ui.ui.rocketDetail

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.Rocket
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.component.card.SquareCardWithTwoTexts
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeSmall

@Composable
fun RocketDimensions(rocket: Rocket) {
    Row(
        Modifier
//                .padding(paddingMedium)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        DimensionCard(
            value = rocket.heightInMeters,
            metrics = stringResource(id = R.string.rocket_detail_meters_abbr),
            description = stringResource(id = R.string.rocket_detail_height)
        )
        Spacer(Modifier.width(spacerSizeSmall))
        DimensionCard(
            value = rocket.diameterInMeters,
            metrics = stringResource(id = R.string.rocket_detail_meters_abbr),
            description = stringResource(id = R.string.rocket_detail_diameter)
        )
        Spacer(Modifier.width(spacerSizeSmall))
        DimensionCard(
            value = rocket.massInKilograms,
            metrics = stringResource(id = R.string.rocket_detail_tons_abbr),
            description = stringResource(id = R.string.rocket_detail_mass)
        )
    }
}

@Composable
fun DimensionCard(value: Int, metrics: String, description: String) {
    SquareCardWithTwoTexts(
        topText = stringResource(
            id = R.string.rocket_detail_dimension,
            value,
            metrics
        ),
        bottomText = description
    )
}


@Preview
@Composable
fun PreviewRocketDimensions() {
    SpaceXTheme {
        RocketDimensions(rocket = RocketsSampleData.getRocket())
    }
}
