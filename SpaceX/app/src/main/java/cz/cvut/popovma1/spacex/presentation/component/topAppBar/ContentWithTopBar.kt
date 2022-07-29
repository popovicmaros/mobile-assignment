package cz.cvut.popovma1.spacex.presentation.component.topAppBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ContentWithTopBar(
    topBar: @Composable () -> Unit = {},
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    content: @Composable BoxScope.() -> Unit,
) {
    Scaffold(
        topBar = topBar,
        scaffoldState = scaffoldState
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding),
            content = content
        )
    }

}
