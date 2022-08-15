package cz.cvut.popovma1.spacex.repository

import kotlinx.coroutines.flow.StateFlow

interface RocketLaunchRepository {
    fun isPhoneLifted(): StateFlow<Boolean>
    fun registerSensor(orientation: Int)
    fun unregisterSensor()
}
