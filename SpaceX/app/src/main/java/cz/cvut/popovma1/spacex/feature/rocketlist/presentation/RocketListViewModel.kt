package cz.cvut.popovma1.spacex.feature.rocketlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.popovma1.spacex.repository.RocketRepository
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import cz.cvut.popovma1.spacex.util.Constants.MIN_PROGRESS_BAR_TIME_VISIBLE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import quanti.com.kotlinlog.Log

class RocketListViewModel(
    private val rocketRepository: RocketRepository
) : ViewModel() {

    private val _rockets = MutableStateFlow(defaultRocketsResponse())
    val rockets = _rockets.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        getRockets()
    }

    private fun getRockets() {
        viewModelScope.launch {
            _isRefreshing.value = true
            downloadRockets()
            _isRefreshing.value = false
        }
    }

    fun refreshRockets() {
        Log.d("refreshRockets() called")
        _isRefreshing.value = true
        val downloadJob = viewModelScope.launch { downloadRockets() }
        val delayJob = viewModelScope.launch { delay(MIN_PROGRESS_BAR_TIME_VISIBLE) }

        viewModelScope.launch {
            downloadJob.join()
            delayJob.join()
            _isRefreshing.value = false
        }
    }

    private suspend fun downloadRockets() {
        withContext(Dispatchers.IO) {
            rocketRepository.getRockets().collect {
                _rockets.value = it
            }
        }
    }

    private fun defaultRocketsResponse() = ResponseWrapper<List<Rocket>>(State.LOADING, listOf())
}
