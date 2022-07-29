package cz.cvut.popovma1.spacex.feature.rocketList.presentation

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

class RocketListViewModel(private val rocketRepository: RocketRepository = RocketRepositoryImpl() /* todo <- remove init */): ViewModel() {

    val rockets = MutableStateFlow(ResponseWrapper<List<Rocket>>(State.LOADING, null))

    init {
        getRockets()
    }

    private fun getRockets() {
        viewModelScope.launch(Dispatchers.IO) {
            rocketRepository.getAllRockets().collect {
                rockets.value = it
            }
        }
    }
}