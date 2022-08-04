package cz.cvut.popovma1.spacex.util

import android.content.Context
import quanti.com.kotlinlog.Log
import quanti.com.kotlinlog.android.AndroidLogger
import quanti.com.kotlinlog.base.LogLevel
import quanti.com.kotlinlog.base.LoggerBundle

object LoggingUtil {

    fun setLoggers(context: Context) {
        // Log initialisation
        Log.initialise(context)

        //forwards all log to android logcat
        val androidBundle = LoggerBundle(minimalLogLevel = LogLevel.VERBOSE)
        Log.addLogger(AndroidLogger(androidBundle))
    }
}
