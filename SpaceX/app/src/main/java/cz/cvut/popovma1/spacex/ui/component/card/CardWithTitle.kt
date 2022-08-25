package cz.cvut.popovma1.spacex.ui.component.card

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.popovma1.spacex.ui.component.column.ColumnWithTitle
import cz.cvut.popovma1.spacex.ui.theme.cornerRadius

@Composable
fun CardWithTitle(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(cornerRadius),
        modifier = Modifier.fillMaxWidth(),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        ColumnWithTitle(
            title = title,
            modifier = modifier,
            content = content
        )
    }
}
