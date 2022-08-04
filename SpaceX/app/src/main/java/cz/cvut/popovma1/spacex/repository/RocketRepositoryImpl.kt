package cz.cvut.popovma1.spacex.repository

import cz.cvut.popovma1.spacex.repository.api.SpaceXApi
import cz.cvut.popovma1.spacex.repository.entity.RocketNetwork
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
import java.lang.Exception

class RocketRepositoryImpl(
    private val api: SpaceXApi
): RocketRepository {

    override fun getRockets(): Flow<ResponseWrapper<List<Rocket>>> = flow {
        try {
            val response: List<RocketNetwork> = api.getRockets()
            val mappedResponse: List<Rocket> = response.map { RocketNetworkMapper().mapToRocket(it) }
            Log.d("getRockets() response = $mappedResponse")
            emit(ResponseWrapper(
                state = State.SUCCESS,
                data = mappedResponse
            ))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResponseWrapper(
                state = State.ERROR, // TODO error state shows 2 snackbars (only on app start)
                data = listOf<Rocket>()
            ))
        }
//        fakeGetRockets()
    }

    override fun getRocket(rocketId: String): Flow<ResponseWrapper<Rocket>> = flow {
        try {
            val response = api.getRocket(rocketId)
            val mappedResponse = RocketNetworkMapper().mapToRocket(response)
            Log.d("getRocket() response = $mappedResponse")
            emit(ResponseWrapper(
                state = State.SUCCESS,
                data = mappedResponse
            ))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResponseWrapper(
                state = State.ERROR,
                data = Rocket.NULL_ROCKET
            ))
        }
//        fakeGetRocket(id)
    }

    // TODO remove
    private suspend fun FlowCollector<ResponseWrapper<Rocket>>.fakeGetRocket(
        id: Int
    ) {
        delay(500) // todo remove "loading"
        //        emit(ResponseWrapper(State.ERROR, Rocket.NULL_ROCKET))
        emit(
            ResponseWrapper(
                State.SUCCESS,
                RocketsSampleData.getRocket(id) // TODO replace this with data from flow
            )
        )
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