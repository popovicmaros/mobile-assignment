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

    fun getRocket(id: Int, rocketId: String) {
        viewModelScope.launch {
            isRefreshing.value = true
            downloadRocket(id, rocketId)
            isRefreshing.value = false
        }
    }

    fun refreshRocket(id: Int, rocketId: String) {
        isRefreshing.value = true

        val downloadJob = viewModelScope.launch { downloadRocket(id, rocketId) }
        val delayJob = viewModelScope.launch { delay(2000) } // always show progressbar for at least 2s

        viewModelScope.launch {
            downloadJob.join()
            delayJob.join()
            isRefreshing.value = false
        }
    }

    private suspend fun downloadRocket(id: Int, rocketId: String) {
        withContext(Dispatchers.IO) {
            rocketRepository.getRocket(id, rocketId).collect {
                rocket.value = it
            }
        }
    }

    private fun defaultRocket() = ResponseWrapper<Rocket>(State.LOADING, Rocket.NULL_ROCKET)
}