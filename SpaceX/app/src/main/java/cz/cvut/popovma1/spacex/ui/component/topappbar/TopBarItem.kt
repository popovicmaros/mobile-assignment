package cz.cvut.popovma1.spacex.ui.component.topappbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import cz.cvut.popovma1.spacex.ui.theme.Blue200

@Composable
fun TopBarItem(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxHeight(),
        content = {
            ProvideTextStyle(
                value = TextStyle(color = Blue200),
                content = {
                    content() // RowScope
                }
            )
        }
    )
}
