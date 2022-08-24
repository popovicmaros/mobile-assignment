package cz.cvut.popovma1.spacex.feature.rocketlist.system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.ui.component.text.LargeTitle
import cz.cvut.popovma1.spacex.ui.theme.cornerRadius
import cz.cvut.popovma1.spacex.ui.theme.dividerIndent
import cz.cvut.popovma1.spacex.ui.theme.paddingLarge
import cz.cvut.popovma1.spacex.ui.theme.paddingMedium
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeLarge
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeSmall
import cz.cvut.popovma1.spacex.ui.theme.surfaceElevation

@Composable
fun RocketListSuccess(
    title: String = stringResource(id = R.string.rocket_list_title_rockets),
    rockets: ResponseWrapper<List<Rocket>>,
    onItemClick: (Rocket) -> Unit,
    isRefreshing: Boolean,
    refreshData: () -> Unit
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = refreshData
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = paddingLarge, start = paddingMedium, end = paddingMedium)
        ) {
            item {
                Spacer(modifier = Modifier.height(spacerSizeLarge))
                LargeTitle(title)
                Spacer(modifier = Modifier.height(spacerSizeSmall))
            }
            item {
                RocketList(rockets = rockets, onItemClick = onItemClick)
                Spacer(modifier = Modifier.height(spacerSizeSmall))
            }
        }
    }
}

@Composable
private fun RocketList(
    rockets: ResponseWrapper<List<Rocket>>,
    onItemClick: (Rocket) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(cornerRadius),
        elevation = surfaceElevation,
        color = MaterialTheme.colors.background
    ) {
        Column {
            rockets.data.forEachIndexed { i, rocket ->
                if (i != 0) {
                    Divider(
                        startIndent = dividerIndent,
                        color = MaterialTheme.colors.surface
                    )
                }
                RocketItem(
                    rocket = rocket,
                    onItemClick = onItemClick
                )
            }
        }
    }
}
