package cz.cvut.popovma1.spacex.repository

import cz.cvut.popovma1.spacex.repository.gyroscope.PhoneLiftDetection
import kotlinx.coroutines.flow.MutableStateFlow

class RocketLaunchRepositoryImpl(private val phoneLiftDetection: PhoneLiftDetection) : RocketLaunchRepository {

    override fun isPhoneLifted(): MutableStateFlow<Boolean> = phoneLiftDetection.isLifted
    override fun unregisterLiftSensor() {
        phoneLiftDetection.unregisterSensor()
    }
}
