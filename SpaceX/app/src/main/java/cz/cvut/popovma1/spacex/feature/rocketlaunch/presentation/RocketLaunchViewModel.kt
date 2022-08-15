package cz.cvut.popovma1.spacex.feature.rocketlaunch.presentation

import androidx.lifecycle.ViewModel
import cz.cvut.popovma1.spacex.repository.RocketLaunchRepository
import kotlinx.coroutines.flow.StateFlow

class RocketLaunchViewModel(
    private val rocketLaunchRepository: RocketLaunchRepository
) : ViewModel() {

    val isLifted: StateFlow<Boolean> get() = rocketLaunchRepository.isPhoneLifted()

    fun registerLiftSensor(orientation: Int) {
        rocketLaunchRepository.registerSensor(orientation)
    }

    fun unregisterLiftSensor() {
        rocketLaunchRepository.unregisterSensor()
    }
}
