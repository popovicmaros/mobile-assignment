package cz.cvut.popovma1.spacex.repository

import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import kotlinx.coroutines.flow.Flow

interface RocketRepository {

    fun getRockets(): Flow<ResponseWrapper<List<Rocket>>>

    fun getRocket(id: Int): Flow<ResponseWrapper<Rocket>>

}