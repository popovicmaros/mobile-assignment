package cz.cvut.popovma1.spacex.feature.rocketdetail.presentation

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

class RocketDetailViewModel(
    private val rocketRepository: RocketRepository
) : ViewModel() {

    private val _rocket = MutableStateFlow(defaultRocket())
    val rocket = _rocket.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    fun getRocket(id: Int) {
        Log.d("getRocket called")
        val isDownloaded = (rocket.value.state == State.SUCCESS)
        if (!isDownloaded) {
            viewModelScope.launch {
                _isRefreshing.value = true
                downloadRocket(id)
                _isRefreshing.value = false
            }
        }
    }

    fun refreshRocket(id: Int) {
        _isRefreshing.value = true

        val downloadJob = viewModelScope.launch { downloadRocket(id) }
        val delayJob = viewModelScope.launch { delay(MIN_PROGRESS_BAR_TIME_VISIBLE) }

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
