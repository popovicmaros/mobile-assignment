package cz.cvut.popovma1.spacex.ui.component.snackbar

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import quanti.com.kotlinlog.Log

/**
 * This is not a Composable, it's a function that makes Snackbar show up
 *
 * Can be used only with Scaffold (but can be used outside Scaffold) with setup:
 * val [scaffoldState]: ScaffoldState = rememberScaffoldState()
 * val [coroutineScope]: CoroutineScope = rememberCoroutineScope()
 *
 * pass [scaffoldState] into showCustomSnackbar() function and also into Scaffold
 */

fun showCustomSnackbar(
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    message: String,
//    actionLabel: String,
//    onActionPerformed: () -> Unit = {},
//    onDismissed: () -> Unit = {}
) {
    coroutineScope.launch {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = message,
//            actionLabel = actionLabel
        )

//        when(result) {
//            SnackbarResult.ActionPerformed -> {
//                Log.d("SnackbarResult.ActionPerformed ($actionLabel)")
//                onActionPerformed()
//            }
//            SnackbarResult.Dismissed -> {
//                Log.d("SnackbarResult.Dismissed")
//                onDismissed()
//            }
//        }
    }
}
