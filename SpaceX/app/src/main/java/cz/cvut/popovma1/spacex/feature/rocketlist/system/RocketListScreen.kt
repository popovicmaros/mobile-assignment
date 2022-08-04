package cz.cvut.popovma1.spacex.feature.rocketlist.system

import cz.cvut.popovma1.spacex.ui.component.topappbar.ContentWithTopBar
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.ui.theme.*
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.State
import quanti.com.kotlinlog.Log

@Composable
fun RocketListScreen(
    rockets: ResponseWrapper<List<Rocket>>,
    onItemClick: (String, String) -> Unit,
) {
    // setup snackbar
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Log.d("@Composable RocketListScreen recomposed")

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



