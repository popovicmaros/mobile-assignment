package cz.cvut.popovma1.spacex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.cvut.popovma1.spacex.util.LoggingUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SpaceX)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLog()
    }

    private fun initLog() {
        LoggingUtil.setLoggers(this.applicationContext)
    }
}
