package cz.cvut.popovma1.spacex.feature.rocketlist.system

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.ui.theme.cornerRadius
import cz.cvut.popovma1.spacex.ui.theme.paddingMedium
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeSmall

@Composable
fun RocketListWithTitle(
    title: String = stringResource(id = R.string.rocket_list_title_rockets),
    rockets: List<Rocket>,
    onItemClick: (String, String) -> Unit
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
fun RocketList(rockets: List<Rocket>, onItemClick: (String, String) -> Unit) {
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
fun RocketListSuccess(
    title: String = stringResource(id = R.string.rocket_list_title_rockets),
    rockets: ResponseWrapper<List<Rocket>>,
    onItemClick: (String, String) -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit
) {
    Surface (
        shape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius),
        elevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingMedium, start = paddingMedium, end = paddingMedium),
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = refreshData
        ) {
            LazyColumn {
                item {
                    LargeTitle(title)
                    Spacer(modifier = Modifier.width(spacerSizeSmall))
                }
                items(rockets.data) { rocket ->
                    RocketItem(rocket, onItemClick)
                }
            }
        }
    }
}