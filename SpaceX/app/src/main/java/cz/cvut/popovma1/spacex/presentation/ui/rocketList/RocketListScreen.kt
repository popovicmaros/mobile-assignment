package cz.cvut.popovma1.spacex.presentation.ui.rocketList

import BackButton
import cz.cvut.popovma1.spacex.presentation.component.topAppBar.ContentWithTopBar
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.Rocket
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.presentation.theme.*

@Composable
fun RocketListScreen(
    rockets: List<Rocket>,
    onItemClick: (Int) -> Unit,
) {
    ContentWithTopBar(
        topBar = { TopAppBar(
            title = { Text(stringResource(id = R.string.title_activity_rocket_list)) },
        )}
    ) {
        RocketListWithTitleScrollable(
            rockets = rockets,
            onItemClick = onItemClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SpaceXTheme {
        RocketListScreen(
            rockets = RocketsSampleData.getRocketsList(),
            onItemClick = {},
        )
    }
}



