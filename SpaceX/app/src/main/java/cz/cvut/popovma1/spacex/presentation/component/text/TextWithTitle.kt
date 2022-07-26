package cz.cvut.popovma1.spacex.presentation.component.text

import cz.cvut.popovma1.spacex.presentation.component.column.ColumnWithTitle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun TextWithTitle(title: String, content: String) {
    ColumnWithTitle(title = title) {
        Text(text = content)
    }
}
