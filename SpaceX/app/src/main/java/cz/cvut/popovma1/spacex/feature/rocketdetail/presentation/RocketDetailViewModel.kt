package cz.cvut.popovma1.spacex.feature.rocketdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.popovma1.spacex.repository.RocketRepository
import cz.cvut.popovma1.spacex.repository.RocketRepositoryImpl
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class RocketDetailViewModel(
    private val rocketRepository: RocketRepository
): ViewModel() {

    val rocket = MutableStateFlow(defaultRocket())
    val isRefreshing = MutableStateFlow(false)

    fun getRocket(rocketId: String) {
        viewModelScope.launch {
            isRefreshing.value = true
            downloadRocket(rocketId)
            isRefreshing.value = false
        }
    }

    fun refreshRocket(rocketId: String) {
        var delayJob: Job? = null
        val downloadJob = viewModelScope.launch {
            isRefreshing.value = true
            downloadRocket(rocketId)
            if(delayJob?.isActive != true) {
                isRefreshing.value = false
            }
        }
        delayJob = viewModelScope.launch {
            delay(2000)
            if(!downloadJob.isActive) {
                isRefreshing.value = false
            }
        }
    }

    private suspend fun downloadRocket(rocketId: String) {
        withContext(Dispatchers.IO) {
            rocketRepository.getRocket(rocketId).collect {
                rocket.value = it
            }
        }
    }

    private fun defaultRocket() = ResponseWrapper<Rocket>(State.LOADING, Rocket.NULL_ROCKET)
}