package cz.cvut.popovma1.spacex.ui.component.stateful.informationStateful

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme

@Composable
fun NoData(
    isRefreshing: Boolean = false,
    refreshData: () -> Unit = {},
) {
    InformationRefresh(
        title = stringResource(id = R.string.no_data_screen_text_no_data),
        painter = painterResource(id = R.drawable.ic_baseline_info_24),
        contentDescription = stringResource(id = R.string.no_data_icon_desc),
        isRefreshing = isRefreshing,
        refreshData = refreshData
    )
}

@Preview
@Composable
fun PreviewNoDataScreen() {
    SpaceXTheme {
        NoData()
    }
}