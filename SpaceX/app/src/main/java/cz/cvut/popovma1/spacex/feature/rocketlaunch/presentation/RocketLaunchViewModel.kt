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

    val isLifted: StateFlow<Boolean> get() = rocketLaunchRepository.isPhoneLifted()

    fun registerLiftSensor(orientation: Int) {
        // TODO? use coroutine?
        viewModelScope.launch {
            rocketLaunchRepository.registerSensor(orientation)
        }
    }

    fun unregisterLiftSensor() {
        // TODO? use coroutine?
        // after back press, viewModelScope dies before this method is called in onDestroy()
        viewModelScope.launch {
            // unregisterSensor() is called here only during screen rotation
            // unregisterSensor() for back press is instead called in onCleared
            rocketLaunchRepository.unregisterSensor()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("onCleared")

        // actions after back pressed
        rocketLaunchRepository.unregisterSensor()
        rocketLaunchRepository.resetIsLifted()
    }
}
