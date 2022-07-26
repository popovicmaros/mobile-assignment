package cz.cvut.popovma1.spacex.presentation.ui.rocketDetail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.Rocket
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.presentation.component.column.ColumnWithTitle
import cz.cvut.popovma1.spacex.presentation.theme.spacerSizeSmall

@Composable
fun RocketParameters(rocket: Rocket) {
    ColumnWithTitle(
        title = stringResource(id = R.string.rocket_detail_parameters),
    ) {
        RocketDimensions(rocket = rocket)
        Spacer(modifier = Modifier.height(spacerSizeSmall))
        RocketStages(rocket = rocket)
    }
    Spacer(modifier = Modifier.height(spacerSizeSmall))
}

@Preview
@Composable
fun PreviewRocketParameters() {
    RocketParameters(rocket = RocketsSampleData.getRocket())
}
