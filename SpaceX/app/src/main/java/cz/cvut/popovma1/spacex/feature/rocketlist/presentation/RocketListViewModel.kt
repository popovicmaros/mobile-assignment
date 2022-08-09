package cz.cvut.popovma1.spacex.feature.rocketlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.popovma1.spacex.repository.RocketRepository
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import quanti.com.kotlinlog.Log

class RocketListViewModel(
    private val rocketRepository: RocketRepository
): ViewModel() {

    val rockets = MutableStateFlow(defaultRocketsResponse())
    val isRefreshing = MutableStateFlow(false)

    init {
        getRockets()
    }

    private fun getRockets() {
        viewModelScope.launch {
            isRefreshing.value = true
            downloadRockets()
            isRefreshing.value = false
        }
    }

    fun refreshRockets() {
        Log.d("refreshRockets() called")
        isRefreshing.value = true
        val downloadJob = viewModelScope.launch { downloadRockets() }
        val delayJob = viewModelScope.launch { delay(2000) } // always show progressbar for at least 2s

        viewModelScope.launch {
            downloadJob.join()
            delayJob.join()
            isRefreshing.value = false
        }
    }

    private suspend fun downloadRockets() {
        withContext(Dispatchers.IO) {
            rocketRepository.getRockets().collect {
                rockets.value = it
            }
        }
    }

    private fun defaultRocketsResponse() = ResponseWrapper<List<Rocket>>(State.LOADING, listOf())
}