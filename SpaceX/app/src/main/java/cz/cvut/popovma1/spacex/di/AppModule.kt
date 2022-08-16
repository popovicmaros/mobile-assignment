package cz.cvut.popovma1.spacex.di

import androidx.room.Room
import cz.cvut.popovma1.spacex.feature.rocketdetail.presentation.RocketDetailViewModel
import cz.cvut.popovma1.spacex.feature.rocketlaunch.presentation.RocketLaunchViewModel
import cz.cvut.popovma1.spacex.feature.rocketlist.presentation.RocketListViewModel
import cz.cvut.popovma1.spacex.repository.RocketLaunchRepository
import cz.cvut.popovma1.spacex.repository.RocketLaunchRepositoryImpl
import cz.cvut.popovma1.spacex.repository.RocketRepository
import cz.cvut.popovma1.spacex.repository.RocketRepositoryImpl
import cz.cvut.popovma1.spacex.repository.api.SpaceXApi
import cz.cvut.popovma1.spacex.repository.database.RocketDao
import cz.cvut.popovma1.spacex.repository.database.RocketDatabase
import cz.cvut.popovma1.spacex.repository.gyroscope.PhoneLiftDetection
import cz.cvut.popovma1.spacex.repository.gyroscope.PhoneLiftDetectionImpl
import cz.cvut.popovma1.spacex.util.Constants
import cz.cvut.popovma1.spacex.util.Constants.SPACEX_URL
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(SPACEX_URL)
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

    single<RocketRepository> {
        RocketRepositoryImpl(
            api = get<SpaceXApi>(),
            rocketDao = get<RocketDatabase>().rocketDao(),
            // TODO? rocketNetworkMapper = get<RocketNetWorkMapper>()
        )
    }

    viewModel<RocketListViewModel> {
        RocketListViewModel(rocketRepository = get<RocketRepository>())
    }

    viewModel<RocketDetailViewModel> {
        RocketDetailViewModel(rocketRepository = get<RocketRepository>())
    }

    single<PhoneLiftDetection> {
        PhoneLiftDetectionImpl(androidContext())
    }
    single<RocketLaunchRepository> {
        RocketLaunchRepositoryImpl(phoneLiftDetection = get<PhoneLiftDetection>())
    }
    viewModel<RocketLaunchViewModel> {
        RocketLaunchViewModel(rocketLaunchRepository = get<RocketLaunchRepository>())
    }
}
