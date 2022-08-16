package cz.cvut.popovma1.spacex.repository.gyroscope

import kotlinx.coroutines.flow.StateFlow

interface PhoneLiftDetection {

    val isLifted: StateFlow<Boolean>

    fun registerSensor(orientation: Int)

    fun unregisterSensor()

    fun resetIsLifted()
}
