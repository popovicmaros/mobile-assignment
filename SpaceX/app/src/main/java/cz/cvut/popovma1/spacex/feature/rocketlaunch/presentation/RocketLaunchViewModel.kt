package cz.cvut.popovma1.spacex.feature.rocketlaunch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.popovma1.spacex.repository.RocketLaunchRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import quanti.com.kotlinlog.Log

class RocketLaunchViewModel(
    private val rocketLaunchRepository: RocketLaunchRepository
) : ViewModel() {

    val isLifted: StateFlow<Boolean> = rocketLaunchRepository.isPhoneLifted()

    fun registerLiftSensor(orientation: Int) {
        val isSensorNeeded = !isLifted.value
        if (isSensorNeeded) {
            viewModelScope.launch {
                rocketLaunchRepository.registerSensor(orientation)
            }
        }
    }

    fun unregisterLiftSensor() {
        viewModelScope.launch {
            // unregister after screen rotation
            rocketLaunchRepository.unregisterSensor()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("onCleared")

        // unregister after back pressed
        rocketLaunchRepository.unregisterSensor()
        rocketLaunchRepository.resetIsLifted()
    }
}
