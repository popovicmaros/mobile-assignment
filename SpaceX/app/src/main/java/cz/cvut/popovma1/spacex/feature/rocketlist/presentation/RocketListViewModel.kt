package cz.cvut.popovma1.spacex.feature.rocketlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.popovma1.spacex.repository.RocketRepository
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import quanti.com.kotlinlog.Log

class RocketListViewModel(
    private val rocketRepository: RocketRepository //= RocketRepositoryImpl() /* todo <- remove init */
): ViewModel() {

    val rockets = MutableStateFlow(defaultRocketsResponse())
    val isRefreshing = MutableStateFlow(false)

    init {
        getRockets()
    }

    private fun getRockets() {
        viewModelScope.launch(Dispatchers.IO) {
            isRefreshing.value = true
            rocketRepository.getRockets().collect {
                rockets.value = it
            }
            isRefreshing.value = false
        }
    }

    fun refresh() {
        Log.d("refresh() called")
        getRockets()
    }

    private fun defaultRocketsResponse() = ResponseWrapper<List<Rocket>>(State.LOADING, listOf())
}