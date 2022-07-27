import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.presentation.theme.iconSizeMedium
import cz.cvut.popovma1.spacex.presentation.theme.paddingMedium
import cz.cvut.popovma1.spacex.presentation.theme.paddingSmall

@Composable
fun LaunchButton(onLaunchClick: () -> Unit) {
    Text(
        text = stringResource(id = R.string.rocket_launch_title),
        modifier = Modifier
            .clickable { onLaunchClick() }
            .padding(horizontal = paddingMedium)
    )
}
