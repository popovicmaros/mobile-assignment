package cz.cvut.popovma1.spacex.ui.component.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Title(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6
    )
}
