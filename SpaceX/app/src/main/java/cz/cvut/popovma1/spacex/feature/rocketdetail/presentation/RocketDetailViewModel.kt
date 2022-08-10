package cz.cvut.popovma1.spacex.feature.rocketdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.popovma1.spacex.repository.RocketRepository
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class RocketDetailViewModel(
    private val rocketRepository: RocketRepository
): ViewModel() {

    private val _rocket = MutableStateFlow(defaultRocket())
    val rocket get() = _rocket

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing get() = _isRefreshing

    fun getRocket(id: Int) {
        viewModelScope.launch {
            _isRefreshing.value = true
            downloadRocket(id)
            _isRefreshing.value = false
        }
    }

    fun refreshRocket(id: Int) {
        _isRefreshing.value = true

        val downloadJob = viewModelScope.launch { downloadRocket(id) }
        val delayJob = viewModelScope.launch { delay(2000) } // always show progressbar for at least 2s

        viewModelScope.launch {
            downloadJob.join()
            delayJob.join()
            _isRefreshing.value = false
        }
    }

    private suspend fun downloadRocket(id: Int) {
        withContext(Dispatchers.IO) {
            rocketRepository.getRocket(id).collect {
                _rocket.value = it
            }
        }
    }

    private fun defaultRocket() = ResponseWrapper<Rocket>(State.LOADING, Rocket.NULL_ROCKET)
}