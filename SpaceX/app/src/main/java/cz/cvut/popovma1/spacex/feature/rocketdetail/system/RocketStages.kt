package cz.cvut.popovma1.spacex.feature.rocketdetail.system

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
import cz.cvut.popovma1.spacex.repository.model.Stage
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.component.card.CardWithTitle
import cz.cvut.popovma1.spacex.ui.component.text.TextWithIcon
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.ui.theme.paddingMedium
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeSmall

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
fun RocketStage(stageNumber: Int, stage: Stage) {
    CardWithTitle(
        title = when (stageNumber) {
            1 -> stringResource(id = R.string.rocket_detail_stage_one)
            2 -> stringResource(id = R.string.rocket_detail_stage_two)
            else -> stringResource(id = R.string.rocket_detail_stage_other)
        },
        modifier = Modifier.padding(paddingMedium)
    ) {
        ReusableItem(stage)
        EngineItem(stage)
        FuelItem(stage)
        BurnTimeItem(stage)
    }
}

@Composable
private fun ReusableItem(stage: Stage) {
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
private fun EngineItem(stage: Stage) {
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
private fun FuelItem(stage: Stage) {
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
private fun BurnTimeItem(stage: Stage) {
    TextWithIcon(
        iconSrc = R.drawable.ic_burn,
        iconDescription = stringResource(
            id = R.string.rocket_detail_burn_icon_desc
        ),
        text = pluralStringResource(
            id = R.plurals.rocket_detail_seconds_burn_time,
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
