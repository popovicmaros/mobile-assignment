package cz.cvut.popovma1.spacex.feature.rocketList.system

import cz.cvut.popovma1.spacex.presentation.component.topAppBar.ContentWithTopBar
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.presentation.theme.*
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.State

@Composable
fun RocketListScreen(
    rockets: ResponseWrapper<List<Rocket>>,
    onItemClick: (Int, String) -> Unit,
) {
    // setup snackbar
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    ContentWithTopBar(
        topBar = { TopAppBar(
            title = { Text(stringResource(id = R.string.title_activity_rocket_list)) },
        )},
        scaffoldState = scaffoldState
    ) {
        RocketListWithTitleScrollable(
            rockets = rockets,
            onItemClick = onItemClick,
            scaffoldState = scaffoldState
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    SpaceXTheme {
        RocketListScreen(
            rockets = ResponseWrapper(State.SUCCESS, RocketsSampleData.getRocketsList()),
            onItemClick = { _, _ -> },
        )
    }
}



