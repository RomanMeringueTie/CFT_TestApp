package ru.cft.data_source.network

import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.cft.common.data.model.UserModel
import ru.cft.common.data.model.UsersListResponse

class RemoteDataSourceImpl(private val httpClient: HttpClient) : RemoteDataSource {
    override suspend fun getUsers(): List<UserModel> {
        val results: UsersListResponse = httpClient.httpClient.get("?results=100&seed=abc").body()
        return results.results
    }
} 