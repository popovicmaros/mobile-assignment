import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.ui.theme.iconSizeMedium

@Composable
fun BackButton(text: String, onBackClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onBackClick() }
//            .padding(horizontal = padding)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_baseline_keyboard_arrow_left_24),
            contentDescription = stringResource(
                id = R.string.icon_back
            ),
            modifier = Modifier
                .size(iconSizeMedium)
        )
        Text(text = text)
    }
}