package ru.cft.data_source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore


val Context.usersListDataStore: DataStore<UsersListResponse> by dataStore(
    fileName = "users_list.pb",
    serializer = UsersListSerializer
)