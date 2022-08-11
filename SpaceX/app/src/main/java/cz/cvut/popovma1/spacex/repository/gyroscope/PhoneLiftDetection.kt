package cz.cvut.popovma1.spacex.repository.gyroscope

import kotlinx.coroutines.flow.MutableStateFlow

interface PhoneLiftDetection {

    val isLifted: MutableStateFlow<Boolean>

//    fun registerSensor()

    fun unregisterSensor()
}
