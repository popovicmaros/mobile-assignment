package cz.cvut.popovma1.spacex.repository

import kotlinx.coroutines.flow.MutableStateFlow

interface RocketLaunchRepository {
    fun isPhoneLifted(): MutableStateFlow<Boolean>
    fun unregisterLiftSensor()
}
