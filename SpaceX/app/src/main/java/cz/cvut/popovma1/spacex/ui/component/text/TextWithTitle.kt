package cz.cvut.popovma1.spacex.ui.component.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cz.cvut.popovma1.spacex.ui.component.column.ColumnWithTitle

@Composable
fun TextWithTitle(title: String, content: String) {
    ColumnWithTitle(title = title) {
        Text(text = content)
    }
}
