package ru.cft.feature_users_list.domain

import ru.cft.common.data.model.UserModel
import ru.cft.feature_users_list.data.repository.UsersListRepository


class GetUsersListUseCaseImpl(private val repository: UsersListRepository) : GetUsersListUseCase {
    override suspend fun invoke(): Result<List<UserModel>> {
        try {
            val result = repository.getUsers()
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}