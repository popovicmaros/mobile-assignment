package cz.cvut.popovma1.spacex.ui.component.column

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.cvut.popovma1.spacex.ui.component.text.Title
import cz.cvut.popovma1.spacex.ui.theme.spacerSize

@Composable
fun ColumnWithTitle(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier) {
        Title(text = title)
        Spacer(modifier = Modifier.height(spacerSize))
        content()
    }
}
