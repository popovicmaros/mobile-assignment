package cz.cvut.popovma1.spacex.ui.component.topappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import cz.cvut.popovma1.spacex.ui.component.text.Title
import cz.cvut.popovma1.spacex.ui.theme.paddingMedium
import cz.cvut.popovma1.spacex.ui.theme.paddingNone
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeSmall

@Composable
fun TopBarCustom(
    title: String,
    backButtonText: String,
    onBackButtonClick: () -> Unit,
    actionButtonText: String? = null,
    onActionButtonClick: (() -> Unit)? = null
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        contentPadding = PaddingValues(horizontal = paddingNone)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            contentAlignment = Alignment.Center, // space around?
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    BackButton(text = backButtonText, onBackClick = onBackButtonClick)
                }
                Title(
                    text = title,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    OptionalActionButton(actionButtonText, onActionButtonClick)
                }
            }
        }
    }
}

@Composable
fun OptionalActionButton(actionButtonText: String?, onActionButtonClick: (() -> Unit)?) {
    if (actionButtonText != null && onActionButtonClick != null) {
        TopBarItem(onClick = onActionButtonClick) {
            Text(
                text = actionButtonText,
                modifier = Modifier.padding(horizontal = paddingMedium)
            )
        }
    } else {
        Spacer(
            modifier = Modifier
                .width(spacerSizeSmall)
                .background(Color.Magenta)
        )
    }
}
