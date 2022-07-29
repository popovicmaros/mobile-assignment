package cz.cvut.popovma1.spacex.repository

import cz.cvut.popovma1.spacex.RocketsSampleData
import cz.cvut.popovma1.spacex.repository.model.ResponseWrapper
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RocketRepositoryImpl: RocketRepository {
    override fun getAllRockets(): Flow<ResponseWrapper<List<Rocket>>> = flow {
        emit(ResponseWrapper(State.SUCCESS, RocketsSampleData.getRocketsList())) // dummy
    }
}