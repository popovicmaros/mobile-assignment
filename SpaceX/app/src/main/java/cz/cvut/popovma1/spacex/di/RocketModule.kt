package cz.cvut.popovma1.spacex.di

import cz.cvut.popovma1.spacex.feature.rocketdetail.presentation.RocketDetailViewModel
import cz.cvut.popovma1.spacex.feature.rocketlist.presentation.RocketListViewModel
import cz.cvut.popovma1.spacex.repository.RocketRepository
import cz.cvut.popovma1.spacex.repository.RocketRepositoryImpl
import cz.cvut.popovma1.spacex.repository.api.SpaceXApi
import cz.cvut.popovma1.spacex.repository.database.RocketDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rocketModule = module {

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
}
