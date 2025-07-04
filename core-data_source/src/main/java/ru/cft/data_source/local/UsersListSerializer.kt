package ru.cft.data_source.local

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream


object UsersListSerializer : Serializer<UsersListResponse> {
    override val defaultValue: UsersListResponse = UsersListResponse.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UsersListResponse {
        try {
            return UsersListResponse.parseFrom(input)
        } catch (exception: Exception) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UsersListResponse, output: OutputStream) = t.writeTo(output)
}