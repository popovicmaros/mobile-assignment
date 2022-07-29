package cz.cvut.popovma1.spacex.repository

import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RocketRepositoryImpl: RocketRepository {

    override fun getAllRockets(): Flow<ResponseWrapper<List<Rocket>>> = flow {
        delay(2000) // todo remove "loading"
//        emit(ResponseWrapper(State.ERROR, listOf()))
        emit(ResponseWrapper(
            State.SUCCESS,
            RocketsSampleData.getRocketsList() // TODO replace this with data from flow
        ))
    }

    override fun getRocket(id: Int): Flow<ResponseWrapper<Rocket>> = flow {
        delay(500) // todo remove "loading"
//        emit(ResponseWrapper(State.ERROR, Rocket.NULL_ROCKET))
        emit(ResponseWrapper(
            State.SUCCESS,
            RocketsSampleData.getRocket(id) // TODO replace this with data from flow
        ))
    }
}