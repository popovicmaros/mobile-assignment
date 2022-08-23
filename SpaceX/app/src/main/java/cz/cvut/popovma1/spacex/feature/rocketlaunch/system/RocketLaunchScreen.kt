package cz.cvut.popovma1.spacex.feature.rocketlaunch.system

import BackButton
import CenteredTitleTopBar
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.ui.component.topappbar.ContentWithTopBar
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.ui.theme.launchedRocketMaxHeight
import cz.cvut.popovma1.spacex.ui.theme.launchedRocketWidth
import cz.cvut.popovma1.spacex.ui.theme.rocketFlyingOffset
import cz.cvut.popovma1.spacex.ui.theme.rocketIdleOffset
import cz.cvut.popovma1.spacex.ui.theme.rocketLaunchBottomOffset
import cz.cvut.popovma1.spacex.ui.theme.rocketLaunchDuration
import cz.cvut.popovma1.spacex.ui.theme.rocketLaunchHeight
import cz.cvut.popovma1.spacex.ui.theme.rocketLaunchWidth
import quanti.com.kotlinlog.Log

@Composable
fun RocketLaunchScreen(
    rocketName: String,
    isLifted: Boolean,
    onBackClick: () -> Unit
) {
    ContentWithTopBar(
        topBar = {
            RocketLaunchTopBar(
                rocketName = rocketName,
                onBackClick = onBackClick,
            )
        }
    ) {
        Column(
            modifier = Modifier.align(Alignment.BottomCenter), // column is centered inside box
            horizontalAlignment = Alignment.CenterHorizontally // column content is centered inside column
        ) {
            RocketLaunchAnimation(isLifted)
            RocketLaunchText(isLifted)
            Log.d("compose: isLifted = $isLifted")
            Spacer(modifier = Modifier.height(rocketLaunchBottomOffset))
        }
    }
}

@Composable
private fun RocketLaunchAnimation(isLaunched: Boolean) {
    val flyOffset: Dp by animateDpAsState(
        targetValue = if (isLaunched) rocketFlyingOffset else rocketIdleOffset,
        animationSpec = tween(rocketLaunchDuration)
    )
    Image(
        painter = if (isLaunched)
            painterResource(id = R.drawable.ic_rocket_flying)
        else
            painterResource(id = R.drawable.ic_rocket_idle),
        contentDescription = stringResource(id = R.string.rocket_launch_image_description),
        modifier = Modifier
            .width(launchedRocketWidth)
            .height(launchedRocketMaxHeight)
            .offset(y = flyOffset),
        contentScale = ContentScale.FillWidth,
        alignment = Alignment.TopCenter
    )
}

@Composable
private fun RocketLaunchText(isLaunched: Boolean) {
    Text(
        modifier = Modifier.size(
            width = rocketLaunchWidth,
            height = rocketLaunchHeight
        ),
        text = if (isLaunched)
            stringResource(id = R.string.rocket_launch_text_after_launch)
        else
            stringResource(id = R.string.rocket_launch_text_before_launch),
        textAlign = TextAlign.Center
    )
}

@Composable
fun RocketLaunchTopBar(rocketName: String, onBackClick: () -> Unit) {
    CenteredTitleTopBar(title = stringResource(id = R.string.rocket_launch_title)) {
        BackButton(text = rocketName, onBackClick = onBackClick)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRocketLaunchScreen() {
    SpaceXTheme {
        RocketLaunchScreen(
            rocketName = "Falcon 1",
            isLifted = false
        ) {}
    }
}
