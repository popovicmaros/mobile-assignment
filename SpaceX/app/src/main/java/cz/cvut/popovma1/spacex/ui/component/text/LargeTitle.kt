package cz.cvut.popovma1.spacex.ui.component.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LargeTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h3,
        fontWeight = FontWeight.Bold
    )
}
