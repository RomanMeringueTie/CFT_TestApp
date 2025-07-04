package ru.cft.feature_users_list.domain

import ru.cft.common.data.model.UserModel
import ru.cft.feature_users_list.data.repository.UsersListRepository


class SaveUsersListUseCaseImpl(private val repository: UsersListRepository) : SaveUsersListUseCase {
    override suspend fun invoke(usersList: List<UserModel>) {
        repository.saveUsers(usersList)
    }
}