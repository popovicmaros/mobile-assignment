package cz.cvut.popovma1.spacex.repository

import cz.cvut.popovma1.spacex.repository.api.SpaceXApi
import cz.cvut.popovma1.spacex.repository.database.RocketDatabase
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
    private val api: SpaceXApi,
    private val database: RocketDatabase
): RocketRepository {

    private val rocketDao = database.rocketDao()

    override fun getRockets(): Flow<ResponseWrapper<List<Rocket>>> = flow {
        try {
            val apiResponse: List<RocketNetwork> = api.getRockets()
            val mappedResponse: List<Rocket> = apiResponse.map {
                RocketNetworkMapper().mapToRocket(it)
            }
            Log.d("getRockets() response = $mappedResponse")

            rocketDao.insertAll(mappedResponse)
            val dbResponse = rocketDao.getAllRockets()

            if(dbResponse.isNotEmpty()) {
                // success
                emit(ResponseWrapper(state = State.SUCCESS, data = dbResponse))
            } else {
                // empty
                emit(ResponseWrapper(state = State.NO_DATA, data = listOf<Rocket>()))
            }
        } catch (e: Exception) {
            // error
            e.printStackTrace()
            emit(ResponseWrapper(state = State.ERROR, data = listOf<Rocket>()))
        }
//        fakeGetRockets()
    }

    override fun getRocket(rocketId: String): Flow<ResponseWrapper<Rocket>> = flow {
        try {
            val response = api.getRocket(rocketId)
            // success
            val mappedResponse = RocketNetworkMapper().mapToRocket(response)
            Log.d("getRocket() response = $mappedResponse")
            emit(ResponseWrapper(
                state = State.SUCCESS,
                data = mappedResponse
            ))
        } catch (e: Exception) {
            // error
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