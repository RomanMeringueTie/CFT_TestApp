package ru.cft.feature_users_list.data.repository

import ru.cft.common.data.model.UserModel


interface UsersListRepository {
    suspend fun getUsers(): List<UserModel>
    suspend fun saveUsers(users: List<UserModel>)
}