package cz.cvut.popovma1.spacex.ui.component.stateful

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Text(
            text = stringResource(id = R.string.loading_screen_text_loading),
        )
    }
}

@Composable
@Preview
fun LoadingScreenPreview() {
    SpaceXTheme {
        Loading()
    }
}
