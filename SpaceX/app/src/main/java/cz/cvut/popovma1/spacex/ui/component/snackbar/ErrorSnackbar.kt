package cz.cvut.popovma1.spacex.ui.component.snackbar

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cz.cvut.popovma1.spacex.R
import kotlinx.coroutines.CoroutineScope

/** read comments in CustomSnackbar.showCustomSnackbar() for help */
@Composable
fun ShowLoadingErrorSnackbar(
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    onActionPerformed: () -> Unit = {},
    onDismissed: () -> Unit = {}
) {
    showCustomSnackbar(
        coroutineScope = coroutineScope,
        scaffoldState = scaffoldState,
        message = stringResource(R.string.error_snackbar_message),
        actionLabel = stringResource(R.string.error_snackbar_action),
        onActionPerformed = onActionPerformed,
        onDismissed = onDismissed
    )
}
