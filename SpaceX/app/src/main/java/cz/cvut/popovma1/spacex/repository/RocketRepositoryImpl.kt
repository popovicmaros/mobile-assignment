package cz.cvut.popovma1.spacex.repository

import cz.cvut.popovma1.spacex.repository.api.SpaceXApi
import cz.cvut.popovma1.spacex.repository.mapper.RocketNetworkMapper
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import quanti.com.kotlinlog.Log

class RocketRepositoryImpl(
    private val api: SpaceXApi
): RocketRepository {

    override fun getRockets(): Flow<ResponseWrapper<List<Rocket>>> = flow {
        val response = api.getRockets().map { RocketNetworkMapper().mapToRocket(it) }
        Log.d("getRockets() response = $response")
        emit(ResponseWrapper(
            State.SUCCESS,
            response
        ))
//        fakeGetRockets()
    }

    override fun getRocket(id: Int): Flow<ResponseWrapper<Rocket>> = flow {
        delay(500) // todo remove "loading"
//        emit(ResponseWrapper(State.ERROR, Rocket.NULL_ROCKET))
        emit(ResponseWrapper(
            State.SUCCESS,
            RocketsSampleData.getRocket(id) // TODO replace this with data from flow
        ))
    }

    // TODO remove
    private suspend fun FlowCollector<ResponseWrapper<List<Rocket>>>.fakeGetRockets() {
        delay(2000) // todo remove "loading"
        //        emit(ResponseWrapper(State.ERROR, listOf()))
        emit(
            ResponseWrapper(
                State.SUCCESS,
                RocketsSampleData.getRocketsList() // TODO replace this with data from flow
            )
        )
    }
}