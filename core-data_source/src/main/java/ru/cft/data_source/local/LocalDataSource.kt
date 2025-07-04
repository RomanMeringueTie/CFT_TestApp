package ru.cft.data_source.local

interface LocalDataSource {
    suspend fun saveUsers(newUsers: List<UserModel>)
    suspend fun getUsers(): UsersListResponse?
}