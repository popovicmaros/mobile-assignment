package cz.cvut.popovma1.spacex.ui.component.topappbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ContentWithTopBar(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    content: @Composable BoxScope.() -> Unit,
) {
    Scaffold(
        topBar = topBar,
        scaffoldState = scaffoldState
    ) { contentPadding ->
        Box(
            modifier = modifier.fillMaxSize().padding(contentPadding),
            content = content
        )
    }
}
