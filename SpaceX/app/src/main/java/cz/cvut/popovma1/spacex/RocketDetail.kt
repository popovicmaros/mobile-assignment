package cz.cvut.popovma1.spacex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.cvut.popovma1.spacex.ui.theme.*

class RocketDetail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                }
            }
        }
    }
}

/* --------------- utils --------------- */

@Composable
fun TextWithIcon(
    iconSrc: Int,
    iconDescription: String = "icon",
    text: String
) {
    Row {
        Image(
            painter = painterResource(iconSrc),
            contentDescription = iconDescription,
            modifier = Modifier
                .size(iconSizeSmall)
        )
        Spacer(modifier = Modifier.width(spacerSize))
        Text(text = text)
    }
    Spacer(modifier = Modifier.height(spacerSize))
}

@Composable
fun TextWithTitle(title: String, content: String) {
    ColumnWithTitle(title = title) {
        Spacer(modifier = Modifier.height(spacerSize))
        Text(text = content)
    }
}

@Composable
fun ColumnWithTitle(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier) {
        Title(text = title)
        content()
    }
}

@Composable
fun Title(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6
    )
}

/* --------------- overview --------------- */

@Composable
fun Overview(rocket: Rocket) {
    TextWithTitle(
        title = stringResource(id = R.string.rocket_detail_overview),
        content = rocket.description
    )
}

/* --------------- parameters --------------- */

@Composable
fun Parameters(rocket: Rocket) {
    ColumnWithTitle(
        title = stringResource(id = R.string.rocket_detail_parameters),
    ) {
        ParametersRow(rocket = rocket)
        RocketStagesColumn(rocket = rocket)
    }
}

@Composable
fun ParametersRow(rocket: Rocket) {
        Row(Modifier.padding(paddingMedium)) {
            Parameter(
                value = rocket.heightInMeters,
                metrics = stringResource(id = R.string.rocket_detail_meters_abbr),
                description = stringResource(id = R.string.rocket_detail_height)
            )
            Spacer(Modifier.width(spacerSize))
            Parameter(
                value = rocket.diameterInMeters,
                metrics = stringResource(id = R.string.rocket_detail_meters_abbr),
                description = stringResource(id = R.string.rocket_detail_diameter)
            )
            Spacer(Modifier.width(spacerSize))
            Parameter(
                value = rocket.massInKilograms,
                metrics = stringResource(id = R.string.rocket_detail_tons_abbr),
                description = stringResource(id = R.string.rocket_detail_mass)
            )
        }
}

@Composable
fun Parameter(value: Int, metrics: String, description: String) {
    Surface(
        shape = RoundedCornerShape(cornerRadius),
        elevation = 1.dp,
        color = MaterialTheme.colors.primary
    ) {
        Column(
            Modifier.padding(paddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$value$metrics",
                style = MaterialTheme.typography.h5
            )
            Text(
                text = description,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

/* --------------- rocket stages --------------- */
@Composable
fun RocketStagesColumn(rocket: Rocket) {
    LazyColumn(
        Modifier.padding(paddingMedium),
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(rocket.stages) { i, stage ->
            RocketStage(stageNumber = i + 1, stage = stage)
            Spacer(modifier = Modifier.height(spacerSize))
        }
    }
}

@Composable
fun RocketStage(stageNumber: Int, stage: Rocket.Stage) {
    val title = "Stage #$stageNumber"
    Surface(
        shape = RoundedCornerShape(cornerRadius),
        color = MaterialTheme.colors.surface,
        elevation = 1.dp,
        modifier = Modifier.fillMaxSize()
    ) {
        ColumnWithTitle(
            title = title,
            modifier = Modifier.padding(paddingSmall)
        ) {
            Spacer(modifier = Modifier.height(spacerSize))
            Reusable(stage)
            Engine(stage)
            Fuel(stage)
            BurnTime(stage)
        }
    }
}

@Composable
fun Reusable(stage: Rocket.Stage) {
    val reusableText = stringResource(id = R.string.rocket_detail_stage_reusable)
    val notReusableText = stringResource(id = R.string.rocket_detail_stage_not_reusable)
    val text = if (stage.isReusable) reusableText else notReusableText
    TextWithIcon(
        iconSrc = R.drawable.reusable,
        iconDescription = "reusable",
        text = text
    )
}

@Composable
fun Engine(stage: Rocket.Stage) {
    val engineSingular = stringResource(id = R.string.rocket_detail_engine_singular)
    val enginePlural = stringResource(id = R.string.rocket_detail_engine_plural)
    val engineText = if (stage.enginesCnt == 1) engineSingular else enginePlural
    val text = "${stage.enginesCnt} $engineText"
    TextWithIcon(
        iconSrc = R.drawable.engine,
        iconDescription = "engine",
        text = text
    )
}

@Composable
fun Fuel(stage: Rocket.Stage) {
    val tonsText = stringResource(id = R.string.rocket_detail_tons_of_fuel)
    val text = "${stage.tonsOfFuel} $tonsText"
    TextWithIcon(
        iconSrc = R.drawable.fuel,
        iconDescription = "fuel",
        text = text
    )
}

@Composable
fun BurnTime(stage: Rocket.Stage) {
    val burnTimeText = stringResource(id = R.string.rocket_detail_seconds_burn_time)
    val text = "${stage.burnTimeInSec} $burnTimeText"
    TextWithIcon(
        iconSrc = R.drawable.burn,
        iconDescription = "burn",
        text = text
    )
}

/* --------------- photos --------------- */
@Composable
fun RocketPhotos(rocketPhotos: List<Int>) {
    Title(text = stringResource(id = R.string.rocket_detail_photos))
    LazyColumn {
        items(rocketPhotos) { src ->
            RocketPhoto(photoSrc = src)
        }
    }
}

@Composable
fun RocketPhoto(
    photoSrc: Int,
    photoDescription: String = "rocket photo",
) {
    Image(
        painter = painterResource(photoSrc),
        contentDescription = photoDescription,
        modifier = Modifier.fillMaxWidth()
    )
}
/* --------------- preview --------------- */

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SpaceXTheme {
        val rocket = RocketsSampleData.getFirstRocketData()
        val rocketPhotos = RocketsSampleData.getRocketPhotos()
        Column (
            modifier = Modifier.padding(paddingMedium)
        ){
            Overview(rocket)
            Spacer(modifier = Modifier.height(spacerSize))
            Parameters(rocket)
            Spacer(modifier = Modifier.height(spacerSize))
            RocketPhotos(rocketPhotos)
        }
    }
}