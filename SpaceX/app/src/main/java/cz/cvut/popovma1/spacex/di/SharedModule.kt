package cz.cvut.popovma1.spacex.di

import androidx.room.Room
import cz.cvut.popovma1.spacex.repository.api.SpaceXApi
import cz.cvut.popovma1.spacex.repository.database.RocketDao
import cz.cvut.popovma1.spacex.repository.database.RocketDatabase
import cz.cvut.popovma1.spacex.util.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val sharedModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.SPACEX_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    single<SpaceXApi> {
        get<Retrofit>().create(SpaceXApi::class.java)
    }

    single<RocketDatabase> {
        Room.databaseBuilder(
            androidContext(),
            RocketDatabase::class.java,
            Constants.ROCKET_DB_NAME
        ).build()
    }
    single<RocketDao> {
        get<RocketDatabase>().rocketDao()
    }
}
