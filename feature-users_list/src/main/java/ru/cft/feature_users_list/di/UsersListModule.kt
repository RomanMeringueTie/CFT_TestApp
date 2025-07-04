package ru.cft.feature_users_list.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.cft.data_source.di.dataSourceModule
import ru.cft.feature_users_list.data.repository.UsersListRepository
import ru.cft.feature_users_list.data.repository.UsersListRepositoryImpl
import ru.cft.feature_users_list.domain.GetUsersListUseCase
import ru.cft.feature_users_list.domain.GetUsersListUseCaseImpl
import ru.cft.feature_users_list.domain.SaveUsersListUseCase
import ru.cft.feature_users_list.domain.SaveUsersListUseCaseImpl
import ru.cft.feature_users_list.presentation.UsersListViewModel
import ru.cft.feature_users_list.ui.UsersListConverter


val usersListModule = module {
    includes(dataSourceModule)
    singleOf(::UsersListRepositoryImpl) { bind<UsersListRepository>() }
    singleOf(::GetUsersListUseCaseImpl) { bind<GetUsersListUseCase>() }
    singleOf(::SaveUsersListUseCaseImpl) { bind<SaveUsersListUseCase>() }
    singleOf(::UsersListConverter)
    viewModelOf(::UsersListViewModel)
}
