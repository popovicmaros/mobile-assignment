package cz.cvut.popovma1.spacex.repository.gyroscope

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.flow.MutableStateFlow
import quanti.com.kotlinlog.Log

class PhoneLiftDetectionImpl(private val activity: Activity?) : PhoneLiftDetection, SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private val _isLifted = MutableStateFlow(false)
    override val isLifted get() = _isLifted

    init {
        registerSensor()
    }

    private fun registerSensor() {
        Log.d("X axis sensor registered")
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)?.also {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_UI,
                SensorManager.SENSOR_DELAY_UI,
            )
        }
    }

    override fun unregisterSensor() {
        // todo call in onDestroy
        Log.d("X axis sensor unregistered")
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            val xRotation = event.values[X_AXIS]
            Log.d("X axis rotation: $xRotation")
            if (xRotation >= TRIGGER_LIFT_VALUE) {
                Log.d("X axis rotation: Launch !")
                _isLifted.value = true
            }
/*
            if (xRotation < -0.5) {
                Log.d("X axis rotation: False !")
                _isLifted.value = false
            }
*/
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // do nothing
    }

    companion object {
        const val TRIGGER_LIFT_VALUE = 2
        const val X_AXIS = 0
    }
}
