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
    private val rocketRepository: RocketRepository = RocketRepositoryImpl() /* todo <- remove init */,
): ViewModel() {

    val rocket = MutableStateFlow(ResponseWrapper<Rocket>(State.LOADING, Rocket.NULL_ROCKET))

    fun getRocket(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            rocketRepository.getRocket(id).collect {
                rocket.value = it
            }
        }
    }
}