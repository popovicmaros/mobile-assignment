package cz.cvut.popovma1.spacex.ui.component.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Title(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.h6,
        textAlign = textAlign,
        color = color,
        fontWeight = FontWeight.SemiBold
    )
}
