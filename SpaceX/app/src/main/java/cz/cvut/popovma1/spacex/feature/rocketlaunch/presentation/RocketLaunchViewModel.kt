package cz.cvut.popovma1.spacex.feature.rocketlaunch.presentation

import cz.cvut.popovma1.spacex.repository.RocketLaunchRepository
import kotlinx.coroutines.flow.MutableStateFlow

class RocketLaunchViewModel(
    private val rocketLaunchRepository: RocketLaunchRepository
) {

    private val _isLifted: MutableStateFlow<Boolean> = rocketLaunchRepository.isPhoneLifted()
    val isLifted get() = _isLifted

    fun registerLiftSensor(orientation: Int) {
        rocketLaunchRepository.registerSensor(orientation)
    }

    fun unregisterLiftSensor() {
        rocketLaunchRepository.unregisterSensor()
    }
}
