package ru.cft.feature_users_list.domain

import ru.cft.common.data.model.UserModel


interface GetUsersListUseCase {
    suspend operator fun invoke(): Result<List<UserModel>>
}