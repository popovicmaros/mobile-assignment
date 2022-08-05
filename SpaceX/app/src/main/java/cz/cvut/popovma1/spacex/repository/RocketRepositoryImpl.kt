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
            if(response.isNotEmpty()) {
                getRocketsSuccess(response)
            } else {
                getRocketsEmpty()
            }
        } catch (e: Exception) {
            getRocketsError(e)
        }
//        fakeGetRockets()
    }

    private suspend fun FlowCollector<ResponseWrapper<List<Rocket>>>.getRocketsError(
        e: Exception
    ) {
        e.printStackTrace()
        emit(
            ResponseWrapper(
                state = State.ERROR, // TODO error state shows 2 snackbars (only on app start)
                data = listOf<Rocket>()
            )
        )
    }

    private suspend fun FlowCollector<ResponseWrapper<List<Rocket>>>.getRocketsEmpty() {
        emit(
            ResponseWrapper(
                state = State.NO_DATA,
                data = listOf<Rocket>()
            )
        )
    }

    private suspend fun FlowCollector<ResponseWrapper<List<Rocket>>>.getRocketsSuccess(
        response: List<RocketNetwork>
    ) {
        val mappedResponse: List<Rocket> = response.map { RocketNetworkMapper().mapToRocket(it) }
        Log.d("getRockets() response = $mappedResponse")
        emit(
            ResponseWrapper(
                state = State.SUCCESS,
                data = mappedResponse
            )
        )
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
//        fakeGetRocket()
    }

    // TODO remove
    private suspend fun FlowCollector<ResponseWrapper<Rocket>>.fakeGetRocket() {
        delay(2000)
        //        emit(ResponseWrapper(State.ERROR, Rocket.NULL_ROCKET))
        emit(
            ResponseWrapper(
                State.SUCCESS,
                RocketsSampleData.getRocket()
            )
        )
    }

    // TODO remove
    private suspend fun FlowCollector<ResponseWrapper<List<Rocket>>>.fakeGetRockets() {
        delay(2000)
        //        emit(ResponseWrapper(State.ERROR, listOf()))
        emit(
            ResponseWrapper(
                State.SUCCESS,
                RocketsSampleData.getRocketsList()
            )
        )
    }
}