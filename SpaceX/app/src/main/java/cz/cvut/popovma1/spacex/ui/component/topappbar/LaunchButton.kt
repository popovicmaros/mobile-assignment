import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.cvut.popovma1.spacex.R
import cz.cvut.popovma1.spacex.ui.theme.paddingMedium

@Composable
fun LaunchButton(onLaunchClick: () -> Unit) {
    Text(
        text = stringResource(id = R.string.rocket_launch_title),
        modifier = Modifier
            .clickable { onLaunchClick() }
            .padding(horizontal = paddingMedium)
    )
}
