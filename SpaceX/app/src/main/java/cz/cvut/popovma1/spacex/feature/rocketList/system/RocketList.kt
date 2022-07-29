package cz.cvut.popovma1.spacex.feature.rocketList.system

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.presentation.component.screen.LoadingScreen
import cz.cvut.popovma1.spacex.presentation.component.snackbar.showLoadingErrorSnackbar
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.presentation.theme.cornerRadius
import cz.cvut.popovma1.spacex.presentation.theme.paddingMedium
import cz.cvut.popovma1.spacex.presentation.theme.spacerSizeSmall
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.State
import kotlinx.coroutines.CoroutineScope

@Composable
fun RocketListWithTitle(
    title: String = stringResource(id = R.string.rocket_list_title_rockets),
    rockets: List<Rocket>,
    onItemClick: (Int, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingMedium),
    ) {
        LargeTitle(title)
        Spacer(modifier = Modifier.width(spacerSizeSmall))
        RocketList(rockets = rockets, onItemClick = onItemClick)
    }
}

@Composable
fun LargeTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h3,
    )
}

@Composable
fun RocketList(rockets: List<Rocket>, onItemClick: (Int, String) -> Unit) {
    Surface (
        shape = RoundedCornerShape(cornerRadius),
        elevation = 1.dp
    ){
        LazyColumn {
            items(rockets) { rocket ->
                RocketItem(rocket, onItemClick)
            }
        }
    }
}

@Composable
fun RocketListWithTitleScrollable(
    title: String = stringResource(id = R.string.rocket_list_title_rockets),
    rockets: ResponseWrapper<List<Rocket>>,
    onItemClick: (Int, String) -> Unit,
    scaffoldState: ScaffoldState
) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Surface (
        shape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius),
        elevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingMedium, start = paddingMedium, end = paddingMedium),
    ) {
        LazyColumn {
            item {
                LargeTitle(title)
                Spacer(modifier = Modifier.width(spacerSizeSmall))
            }
            when (rockets.state) {
                State.SUCCESS -> RocketListSuccess(rockets, onItemClick)
                State.LOADING -> item { LoadingScreen() }
                State.ERROR -> showLoadingErrorSnackbar(
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState
                )
            }
        }
    }
}

private fun LazyListScope.RocketListSuccess(
    rockets: ResponseWrapper<List<Rocket>>,
    onItemClick: (Int, String) -> Unit
) {
    if (rockets.data.isNotEmpty()) {
        items(rockets.data) { rocket ->
            RocketItem(rocket, onItemClick)
        }
    } else {
        item { NoItemsSurface() }
    }
}

@Composable
fun NoItemsSurface() {
    Surface {
        Text(text = "Success: No Items")
    }
}