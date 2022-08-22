package cz.cvut.popovma1.spacex.ui.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import cz.cvut.popovma1.spacex.ui.theme.cornerRadius
import cz.cvut.popovma1.spacex.ui.theme.paddingSmall
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
            Modifier.padding(paddingSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = topText,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = bottomText,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Light,
            )
        }
    }
}
