package cz.cvut.popovma1.spacex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.PluralsRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.cvut.popovma1.spacex.ui.theme.*

class RocketDetail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketDetailScreen()
        }
    }
}

/* --------------- utils --------------- */

@Composable
fun TextWithIcon(
    iconSrc: Int,
    iconDescription: String = stringResource(
        id = R.string.rocket_detail_default_icon_desc
    ),
    text: String
) {
    Row(
        Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(iconSrc),
            contentDescription = iconDescription,
            modifier = Modifier.size(iconSizeSmall)
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
    Spacer(modifier = Modifier.height(spacerSize))
}

/* --------------- parameters --------------- */

@Composable
fun Parameters(rocket: Rocket) {
    ColumnWithTitle(
        title = stringResource(id = R.string.rocket_detail_parameters),
    ) {
        DimensionsRow(rocket = rocket)
        RocketStagesColumn(rocket = rocket)
    }
    Spacer(modifier = Modifier.height(spacerSize))
}

@Composable
fun DimensionsRow(rocket: Rocket) {
        Row(
            Modifier
                .padding(paddingMedium)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Dimension(
                value = rocket.heightInMeters,
                metrics = stringResource(id = R.string.rocket_detail_meters_abbr),
                description = stringResource(id = R.string.rocket_detail_height)
            )
            Spacer(Modifier.width(spacerSize))
            Dimension(
                value = rocket.diameterInMeters,
                metrics = stringResource(id = R.string.rocket_detail_meters_abbr),
                description = stringResource(id = R.string.rocket_detail_diameter)
            )
            Spacer(Modifier.width(spacerSize))
            Dimension(
                value = rocket.massInKilograms,
                metrics = stringResource(id = R.string.rocket_detail_tons_abbr),
                description = stringResource(id = R.string.rocket_detail_mass)
            )
        }
}

@Composable
fun Dimension(value: Int, metrics: String, description: String) {
    Surface(
        shape = RoundedCornerShape(cornerRadius),
        elevation = 1.dp,
        color = MaterialTheme.colors.primary,
    ) {
        Column(
            Modifier.padding(paddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(
                    id = R.string.rocket_detail_dimension,
                    value,
                    metrics
                ),
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
    Column(
        Modifier.padding(paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        rocket.stages.forEachIndexed { i, stage ->
            RocketStage(stageNumber = i + 1, stage = stage)
            Spacer(modifier = Modifier.height(spacerSize))
        }
    }
}

@Composable
fun RocketStage(stageNumber: Int, stage: Rocket.Stage) {
    Surface(
        shape = RoundedCornerShape(cornerRadius),
        color = MaterialTheme.colors.surface,
        elevation = 1.dp,
        modifier = Modifier.fillMaxSize()
    ) {
        ColumnWithTitle(
            title = stringResource(
                id = R.string.rocket_detail_stage,
                stageNumber
            ),
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
        iconSrc = R.drawable.ic_reusable,
        iconDescription = stringResource(
            id = R.string.rocket_detail_reusable_icon_desc
        ),
        text = text
    )
}

@Composable
fun Engine(stage: Rocket.Stage) {
    TextWithIcon(
        iconSrc = R.drawable.ic_engine,
        iconDescription = stringResource(
            id = R.string.rocket_detail_engine_icon_desc
        ),
        text = pluralResource(
            resId = R.plurals.rocket_detail_engine,
            stage.enginesCnt,
            stage.enginesCnt
        )
    )
}

@Composable
fun Fuel(stage: Rocket.Stage) {
    TextWithIcon(
        iconSrc = R.drawable.ic_fuel,
        iconDescription = stringResource(
            id = R.string.rocket_detail_fuel_icon_desc
        ),
        text = pluralResource(
            resId = R.plurals.rocket_detail_tons_of_fuel,
            stage.tonsOfFuel,
            stage.tonsOfFuel,
        )
    )
}

@Composable
fun BurnTime(stage: Rocket.Stage) {
    TextWithIcon(
        iconSrc = R.drawable.ic_burn,
        iconDescription = stringResource(
            id = R.string.rocket_detail_burn_icon_desc
        ),
        text = pluralResource(
            resId =  R.plurals.rocket_detail_seconds_burn_time,
            stage.burnTimeInSec,
            stage.burnTimeInSec,
        )
    )
}

/* --------------- photos --------------- */
@Composable
fun RocketPhotos(rocketPhotos: List<Int>) {
    Title(text = stringResource(id = R.string.rocket_detail_photos))
    Column {
        rocketPhotos.forEach { src ->
            RocketPhoto(photoSrc = src)
        }
    }
}

@Composable
fun RocketPhoto(
    photoSrc: Int,
    photoDescription: String = "rocket photo",
) {
    Spacer(modifier = Modifier.height(paddingMedium))
    Image(
        painter = painterResource(photoSrc),
        contentDescription = photoDescription,
        modifier = Modifier
//            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius))
    )
}

/* --------------- utils --------------- */
@Composable
fun pluralResource(
    @PluralsRes resId: Int,
    quantity: Int,
    vararg formatArgs: Any? = emptyArray()
): String {
    return LocalContext.current.resources
        .getQuantityString(resId, quantity, *formatArgs)
}

/* --------------- preview --------------- */

@Preview(showBackground = true)
@Composable
fun RocketDetailScreen() {
    SpaceXTheme {
        val rocket = RocketsSampleData.getFirstRocketData()
        val rocketPhotos = RocketsSampleData.getRocketPhotos()
        LazyColumn (
            modifier = Modifier.padding(paddingMedium)
        ){
            item { Overview(rocket) }
            item { Parameters(rocket) }
            item { RocketPhotos(rocketPhotos) }
        }

    }

}