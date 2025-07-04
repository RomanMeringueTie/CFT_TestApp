package ru.cft.data_source.local

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first

class LocalDataSourceImpl(private val dataStore: DataStore<UsersListResponse>) : LocalDataSource {
    override suspend fun saveUsers(newUsers: List<UserModel>) {
        dataStore.updateData { currentData ->
            currentData.toBuilder()
                .clearResults()
                .addAllResults(newUsers)
                .build()
        }
    }

    override suspend fun getUsers(): UsersListResponse? {
        val data = dataStore.data.first()
        if (data == UsersListSerializer.defaultValue)
            return null
        return data
    }
} 