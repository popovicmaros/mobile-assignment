package cz.cvut.popovma1.spacex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import cz.cvut.popovma1.spacex.feature.NavGraphs
import cz.cvut.popovma1.spacex.ui.theme.SpaceXTheme
import cz.cvut.popovma1.spacex.util.LoggingUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
        initLog()
    }

    private fun initLog() {
        LoggingUtil.setLoggers(this.applicationContext)
    }
}
