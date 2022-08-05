package cz.cvut.popovma1.spacex.ui.component.snackbar

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.CoroutineScope

/** read comments in CustomSnackbar.showCustomSnackbar() for help */
fun showLoadingErrorSnackbar(
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    onActionPerformed: () -> Unit = {},
    onDismissed: () -> Unit = {}
) {
    showCustomSnackbar(
        coroutineScope = coroutineScope,
        scaffoldState = scaffoldState,
        message = "Loading error",
        actionLabel = "Refresh",
        onActionPerformed = onActionPerformed,
        onDismissed = onDismissed
    )
}