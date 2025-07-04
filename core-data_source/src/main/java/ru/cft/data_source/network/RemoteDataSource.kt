package ru.cft.data_source.network

import ru.cft.common.data.model.UserModel

interface RemoteDataSource {
    suspend fun getUsers(): List<UserModel>
}