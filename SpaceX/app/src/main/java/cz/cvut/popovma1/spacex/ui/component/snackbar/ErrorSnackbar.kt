package cz.cvut.popovma1.spacex.ui.component.snackbar

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import cz.cvut.popovma1.spacex.R
import kotlinx.coroutines.CoroutineScope

@Composable
fun ShowLoadingErrorSnackbar(
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    onActionPerformed: () -> Unit = {},
    onDismissed: () -> Unit = {}
) {
    val message = stringResource(R.string.error_snackbar_message)
    val actionLabel = stringResource(R.string.error_snackbar_action)

    LaunchedEffect(key1 = true) {
        showCustomSnackbar(
            coroutineScope = coroutineScope,
            scaffoldState = scaffoldState,
            message = message,
            actionLabel = actionLabel,
            onActionPerformed = onActionPerformed,
            onDismissed = onDismissed
        )
    }
}
