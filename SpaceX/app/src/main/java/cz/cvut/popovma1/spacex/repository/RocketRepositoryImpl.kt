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
        downloadApiToDb()
        emitDataFromDb()
    }

    private suspend fun FlowCollector<ResponseWrapper<List<Rocket>>>.downloadApiToDb() {
        try {
            // download data & save to db
            val apiResponse: List<RocketNetwork> = api.getRockets()
            val mappedResponse: List<Rocket> = apiResponse.map {
                RocketNetworkMapper().mapToRocket(it)
            }
            Log.d("getRockets() response = $mappedResponse")
            rocketDao.insertAll(mappedResponse)
        } catch (e: Exception) {
            // error downloading
            e.printStackTrace()
            emit(ResponseWrapper(state = State.ERROR, data = listOf()))
        }
    }

    private suspend fun FlowCollector<ResponseWrapper<List<Rocket>>>.emitDataFromDb() {
        // display data from db
        try {
            val dbResponse = rocketDao.getAll()
            if (!dbResponse.isNullOrEmpty()) {
                // success
                emit(ResponseWrapper(state = State.SUCCESS, data = dbResponse))
            } else {
                // empty
                emit(ResponseWrapper(state = State.NO_DATA, data = listOf()))
            }
        } catch (e: Exception) {
            // db error
            e.printStackTrace()
            emit(ResponseWrapper(state = State.ERROR, data = listOf()))
        }
    }

    override fun getRocket(id: Int, rocketId: String): Flow<ResponseWrapper<Rocket>> = flow {
        val dbResponse: Rocket? = rocketDao.getRocket(id)
        dbResponse?.let {
            emit(ResponseWrapper(state = State.SUCCESS, data = dbResponse))
        } ?: run {
            // used in rocketDetail, where data is already expected to be saved in db by rocketList
            emit(ResponseWrapper(state = State.ERROR, data = Rocket.NULL_ROCKET))
        }
    }

}