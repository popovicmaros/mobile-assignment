package cz.cvut.popovma1.spacex.ui.component.card

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import cz.cvut.popovma1.spacex.ui.theme.cornerRadius
import cz.cvut.popovma1.spacex.ui.theme.paddingMedium
import cz.cvut.popovma1.spacex.ui.theme.squareCardSize

@Composable
fun SquareCardWithTwoTexts(
    size: Dp = squareCardSize,
    backgroundColor: Color = MaterialTheme.colors.surface,
    topText: String,
    bottomText: String
) {
    Card(
        shape = RoundedCornerShape(cornerRadius),
        backgroundColor = backgroundColor,
        modifier = Modifier.size(size)
    ) {
        Column(
            Modifier.padding(paddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = topText,
                style = MaterialTheme.typography.h5
            )
            Text(
                text = bottomText,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

