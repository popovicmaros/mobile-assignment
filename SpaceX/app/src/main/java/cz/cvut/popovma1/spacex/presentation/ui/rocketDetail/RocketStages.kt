package cz.cvut.popovma1.spacex.presentation.ui.rocketDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.presentation.component.card.CardWithTitle
import cz.cvut.popovma1.spacex.presentation.component.text.TextWithIcon
import cz.cvut.popovma1.spacex.presentation.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.presentation.theme.paddingMedium
import cz.cvut.popovma1.spacex.presentation.theme.spacerSizeSmall

@Composable
fun RocketStages(rocket: Rocket) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        rocket.stages.forEachIndexed { i, stage ->
            RocketStage(stageNumber = i + 1, stage = stage)
            Spacer(modifier = Modifier.height(spacerSizeSmall))
        }
    }
}

@Composable
fun RocketStage(stageNumber: Int, stage: Rocket.Stage) {
    CardWithTitle(
        title = stringResource(
            id = R.string.rocket_detail_stage,
            stageNumber
        ),
        modifier = Modifier.padding(paddingMedium)
    ) {
        ReusableItem(stage)
        EngineItem(stage)
        FuelItem(stage)
        BurnTimeItem(stage)
    }
}

@Composable
private fun ReusableItem(stage: Rocket.Stage) {
    val reusableText = stringResource(id = R.string.rocket_detail_stage_reusable)
    val notReusableText = stringResource(id = R.string.rocket_detail_stage_not_reusable)
    val text = if (stage.isReusable) reusableText else notReusableText
    TextWithIcon(
        iconSrc = R.drawable.ic_reusable,
        iconDescription = stringResource(
            id = R.string.rocket_detail_reusable_icon_desc
        ),
        text = text
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun EngineItem(stage: Rocket.Stage) {
    TextWithIcon(
        iconSrc = R.drawable.ic_engine,
        iconDescription = stringResource(
            id = R.string.rocket_detail_engine_icon_desc
        ),
        text = pluralStringResource(
            id = R.plurals.rocket_detail_engine,
            stage.enginesCnt,
            stage.enginesCnt
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun FuelItem(stage: Rocket.Stage) {
    TextWithIcon(
        iconSrc = R.drawable.ic_fuel,
        iconDescription = stringResource(
            id = R.string.rocket_detail_fuel_icon_desc
        ),
        text = pluralStringResource(
            id = R.plurals.rocket_detail_tons_of_fuel,
            stage.tonsOfFuel,
            stage.tonsOfFuel,
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun BurnTimeItem(stage: Rocket.Stage) {
    TextWithIcon(
        iconSrc = R.drawable.ic_burn,
        iconDescription = stringResource(
            id = R.string.rocket_detail_burn_icon_desc
        ),
        text = pluralStringResource(
            id =  R.plurals.rocket_detail_seconds_burn_time,
            stage.burnTimeInSec,
            stage.burnTimeInSec,
        )
    )
}

@Preview
@Composable
fun PreviewRocketStages() {
    SpaceXTheme {
        RocketStages(rocket = RocketsSampleData.getRocket())
    }
}
