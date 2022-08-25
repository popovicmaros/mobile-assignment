package cz.cvut.popovma1.spacex.repository

import cz.cvut.popovma1.spacex.repository.api.SpaceXApi
import cz.cvut.popovma1.spacex.repository.database.RocketDao
import cz.cvut.popovma1.spacex.repository.entity.RocketNetwork
import cz.cvut.popovma1.spacex.repository.mapper.RocketNetworkMapper
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import quanti.com.kotlinlog.Log
import java.lang.Exception

class RocketRepositoryImpl(
    private val api: SpaceXApi,
    private val rocketDao: RocketDao,
    private val rocketNetworkMapper: RocketNetworkMapper
) : RocketRepository {

    override fun getRockets(): Flow<ResponseWrapper<List<Rocket>>> = flow {
        downloadApiToDb()
        emitDataFromDb()
    }

    private suspend fun downloadApiToDb() {
        try {
            val apiResponse: List<RocketNetwork> = api.getRockets()
            val modelResponse: List<Rocket> = apiResponse.map {
                rocketNetworkMapper.mapToRocket(it)
            }
            Log.d("getRockets() response = $modelResponse")
            rocketDao.insertAll(modelResponse)
        } catch (e: Exception) {
            Log.d("error downloading")
            e.printStackTrace()
        }
    }

    private suspend fun FlowCollector<ResponseWrapper<List<Rocket>>>.emitDataFromDb() {
        try {
            val dbResponse = rocketDao.getAll()
            if (!dbResponse.isNullOrEmpty()) {
                emit(ResponseWrapper(state = State.SUCCESS, data = dbResponse))
            } else {
                emit(ResponseWrapper(state = State.NO_DATA, data = listOf()))
            }
        } catch (e: Exception) {
            Log.d("db error")
            e.printStackTrace()
            emit(ResponseWrapper(state = State.ERROR, data = listOf()))
        }
    }

    override fun getRocket(id: Int): Flow<ResponseWrapper<Rocket>> = flow {
        val dbResponse: Rocket? = rocketDao.getRocket(id)
        dbResponse?.let {
            emit(ResponseWrapper(state = State.SUCCESS, data = dbResponse))
        } ?: run {
            emit(ResponseWrapper(state = State.ERROR, data = Rocket.NULL_ROCKET))
        }
    }
}
