package cz.cvut.popovma1.spacex.ui.ui.rocketDetail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.Rocket
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.component.text.TextWithTitle
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeSmall

@Composable
fun RocketOverview(rocket: Rocket) {
    TextWithTitle(
        title = stringResource(id = R.string.rocket_detail_overview),
        content = rocket.description
    )
    Spacer(modifier = Modifier.height(spacerSizeSmall))
}

@Preview
@Composable
fun PreviewRocketOverview() {
    SpaceXTheme {
        RocketOverview(rocket = RocketsSampleData.getRocket())
    }
}
