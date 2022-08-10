package cz.cvut.popovma1.spacex.repository.api

import cz.cvut.popovma1.spacex.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// tmp object
object SpaceXRetrofitApi {
    val spaceXApi = Retrofit.Builder()
        .baseUrl(Constants.SPACEX_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(SpaceXApi::class.java)
}
