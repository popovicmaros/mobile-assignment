package cz.cvut.popovma1.spacex.repository.gyroscope

import android.content.Context
import android.content.res.Configuration
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import quanti.com.kotlinlog.Log

class PhoneLiftDetectionImpl(private val applicationContext: Context?) :
    PhoneLiftDetection, SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var orientation: Int? = null

    private val _isLifted = MutableStateFlow(false)
    override val isLifted = _isLifted.asStateFlow()

    private var landscapeTriggerValue: Int? = null

    override fun registerSensor(orientation: Int) {
        // call this in onCreate
        Log.d("registerSensor")

        sensorManager = applicationContext?.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorManager?.let { sensorManager ->
            registerGyroscope(sensorManager)
            registerGravity(sensorManager)
        }

        this.orientation = orientation
        landscapeTriggerValue = null
    }

    private fun registerGravity(sensorManager: SensorManager) {
        sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)?.also {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL,
                SensorManager.SENSOR_DELAY_NORMAL,
            )
        }
    }

    private fun registerGyroscope(sensorManager: SensorManager) {
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
        // call this in onDestroy
        Log.d("unregisterSensor")
        sensorManager?.unregisterListener(this)
        landscapeTriggerValue = null
    }

    override fun resetIsLifted() {
        // call this in viewModel's onCleared() to reset rocket lift after onBackPressed()
        _isLifted.value = false
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        sensorEvent?.let { event ->
            when (orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    detectPhoneLift(
                        event = event,
                        axis = X_AXIS,
                        triggerValue = TRIGGER_LIFT_VALUE_PORTRAIT
                    )
                }
                Configuration.ORIENTATION_LANDSCAPE -> {
                    landscapeTriggerValue?.let { triggerValue ->
                        detectPhoneLift(
                            event = event,
                            axis = Y_AXIS,
                            triggerValue = triggerValue
                        )
                    } ?: run {
                        // detect and set only once (not on every onSensorChanged())
                        landscapeTriggerValue = detectLandscapeTriggerValue(event)
                    }
                }
            }
        }
    }

    private fun detectLandscapeTriggerValue(event: SensorEvent): Int? {
        val isGravityEvent: Boolean = (event.sensor?.type == Sensor.TYPE_GRAVITY)
        if (isGravityEvent) {
            val gravity = event.values[X_AXIS]
            return if (gravity > 0) {
                TRIGGER_LIFT_VALUE_LANDSCAPE_LEFT
            } else {
                TRIGGER_LIFT_VALUE_LANDSCAPE_RIGHT
            }
        }
        return null
    }

    private fun detectPhoneLift(event: SensorEvent, axis: Int, triggerValue: Int) {
        val isGyroscopeEvent: Boolean = (event.sensor?.type == Sensor.TYPE_GYROSCOPE)
        if (isGyroscopeEvent) {
            val rotationValue = event.values[axis]
            Log.d("rotation value: $rotationValue")

            val shouldLaunch: Boolean = when (triggerValue) {
                TRIGGER_LIFT_VALUE_PORTRAIT,
                TRIGGER_LIFT_VALUE_LANDSCAPE_RIGHT -> { rotationValue >= triggerValue }
                TRIGGER_LIFT_VALUE_LANDSCAPE_LEFT -> { rotationValue <= triggerValue }
                else -> { false }
            }

            if (shouldLaunch) {
                Log.d("rotation value: Launch !")
                _isLifted.value = true
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    companion object {
        private const val TRIGGER_LIFT_VALUE = 4
        const val TRIGGER_LIFT_VALUE_PORTRAIT = TRIGGER_LIFT_VALUE
        const val TRIGGER_LIFT_VALUE_LANDSCAPE_LEFT = -1 * TRIGGER_LIFT_VALUE
        const val TRIGGER_LIFT_VALUE_LANDSCAPE_RIGHT = TRIGGER_LIFT_VALUE

        const val X_AXIS = 0
        const val Y_AXIS = 1
    }
}
