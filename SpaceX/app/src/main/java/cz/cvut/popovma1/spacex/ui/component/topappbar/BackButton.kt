package cz.cvut.popovma1.spacex.ui.component.topappbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.ui.theme.iconSizeMedium
import cz.cvut.popovma1.spacex.ui.theme.paddingSmall

@Composable
fun BackButton(text: String, onBackClick: () -> Unit) {
    TopAppBarItem(onClick = onBackClick) {
        Image(
            painter = painterResource(R.drawable.ic_baseline_keyboard_arrow_left_24),
            contentDescription = stringResource(
                id = R.string.icon_back
            ),
            modifier = Modifier
                .size(iconSizeMedium)
        )
        Text(text = text, modifier = Modifier.padding(end = paddingSmall))
    }
}
