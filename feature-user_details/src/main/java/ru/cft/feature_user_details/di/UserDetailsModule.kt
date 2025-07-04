package ru.cft.feature_user_details.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.cft.common.data.model.UserModel
import ru.cft.feature_user_details.presentation.UserDetailsViewModel
import ru.cft.feature_user_details.ui.UserDetailsConverter

val userDetailsModule = module {
    singleOf(::UserDetailsConverter)
    viewModel { (userModel: UserModel) -> UserDetailsViewModel(userModel, get()) }
}