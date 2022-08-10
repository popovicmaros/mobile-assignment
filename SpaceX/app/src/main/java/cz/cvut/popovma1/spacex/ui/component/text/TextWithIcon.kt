package cz.cvut.popovma1.spacex.ui.component.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.ui.theme.iconSizeSmall
import cz.cvut.popovma1.spacex.ui.theme.spacerSizeSmall


@Composable
fun TextWithIcon(
    iconSrc: Int,
    iconDescription: String = stringResource(R.string.defalt_icon_desc),
    text: String
) {
    Row(
//        Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(iconSrc),
            contentDescription = iconDescription,
            modifier = Modifier.size(iconSizeSmall)
        )
        Spacer(modifier = Modifier.width(spacerSizeSmall))
        Text(text = text)
    }
    Spacer(modifier = Modifier.height(spacerSizeSmall))
}