package ru.cft.feature_users_list.domain

import ru.cft.common.data.model.UserModel


interface SaveUsersListUseCase {
    suspend operator fun invoke(usersList: List<UserModel>)
}