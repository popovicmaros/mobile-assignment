package cz.cvut.popovma1.spacex.ui.component.topappbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cz.cvut.popovma1.spacex.ui.component.text.Title
import cz.cvut.popovma1.spacex.ui.theme.paddingNone

@Composable
fun CenteredTitleTopBar(
    title: String,
    content: @Composable RowScope.() -> Unit
) {
//    TopBarSpaceXTheme {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        contentPadding = PaddingValues(horizontal = paddingNone)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
            Title(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
//    }
}
