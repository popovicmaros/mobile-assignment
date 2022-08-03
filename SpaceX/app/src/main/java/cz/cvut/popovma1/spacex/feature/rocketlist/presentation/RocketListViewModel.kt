package cz.cvut.popovma1.spacex.feature.rocketlist.presentation

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

class RocketListViewModel(
    private val rocketRepository: RocketRepository //= RocketRepositoryImpl() /* todo <- remove init */
): ViewModel() {

    val rockets = defaultRockets()

    init {
        getRockets()
    }

    private fun getRockets() {
        viewModelScope.launch(Dispatchers.IO) {
            rocketRepository.getRockets().collect {
                rockets.value = it
            }
        }
    }

    private fun defaultRockets() =
        MutableStateFlow(ResponseWrapper<List<Rocket>>(State.LOADING, listOf()))
}