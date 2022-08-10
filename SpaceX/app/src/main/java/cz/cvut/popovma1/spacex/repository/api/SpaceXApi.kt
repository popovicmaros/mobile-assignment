package cz.cvut.popovma1.spacex.repository.api

import cz.cvut.popovma1.spacex.repository.entity.RocketNetwork
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApi {

    @GET("rockets")
    suspend fun getRockets(): List<RocketNetwork>

/*
    @GET("rockets/{name}")
    suspend fun getRocket(@Path("name") name: String): RocketNetwork
*/

}