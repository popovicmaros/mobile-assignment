package cz.cvut.popovma1.spacex.ui.component.stateful.informationStateful

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme

@Composable
fun Error(
    isRefreshing: Boolean = false,
    refreshData: () -> Unit = {},
) {
    InformationRefresh(
        title = stringResource(id = R.string.error_screen_text_error),
        painter = painterResource(id = R.drawable.ic_baseline_error_24),
        contentDescription = stringResource(id = R.string.error_screen_error_icon_desc),
        isRefreshing = isRefreshing,
        refreshData = refreshData
    )
}

@Preview
@Composable
fun PreviewErrorScreen() {
    SpaceXTheme {
        Error()
    }
}
