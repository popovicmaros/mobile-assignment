package cz.cvut.popovma1.spacex.di

import cz.cvut.popovma1.spacex.feature.rocketlaunch.presentation.RocketLaunchViewModel
import cz.cvut.popovma1.spacex.repository.RocketLaunchRepository
import cz.cvut.popovma1.spacex.repository.RocketLaunchRepositoryImpl
import cz.cvut.popovma1.spacex.repository.gyroscope.PhoneLiftDetection
import cz.cvut.popovma1.spacex.repository.gyroscope.PhoneLiftDetectionImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rocketLaunchModule = module {

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
