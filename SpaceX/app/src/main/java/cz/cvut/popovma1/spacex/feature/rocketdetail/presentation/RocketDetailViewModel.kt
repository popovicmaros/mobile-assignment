package cz.cvut.popovma1.spacex.feature.rocketdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.popovma1.spacex.repository.RocketRepository
import cz.cvut.popovma1.spacex.repository.RocketRepositoryImpl
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RocketDetailViewModel(
    private val rocketRepository: RocketRepository
): ViewModel() {

    val rocket = MutableStateFlow(defaultRocket())
    val isRefreshing = MutableStateFlow(false)

    fun getRocket(rocketId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isRefreshing.value = true
            rocketRepository.getRocket(rocketId).collect {
                rocket.value = it
            }
            isRefreshing.value = false
        }
    }

    private fun defaultRocket() = ResponseWrapper<Rocket>(State.LOADING, Rocket.NULL_ROCKET)
}