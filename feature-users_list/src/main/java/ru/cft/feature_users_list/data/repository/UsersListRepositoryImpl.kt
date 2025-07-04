package ru.cft.feature_users_list.data.repository

import ru.cft.common.data.model.UserModel
import ru.cft.data_source.local.LocalDataSource
import ru.cft.data_source.local.ModelToProtoConverter
import ru.cft.data_source.local.ProtoToModelConverter
import ru.cft.data_source.network.RemoteDataSource


class UsersListRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val protoToModelConverter: ProtoToModelConverter,
    private val modelToProtoConverter: ModelToProtoConverter,
) : UsersListRepository {

    override suspend fun getUsers(): List<UserModel> {
        val localData = localDataSource.getUsers()
        if (localData != null) {
            return protoToModelConverter.convertResults(localData).results
        }
        val remoteData = remoteDataSource.getUsers()
        return remoteData
    }

    override suspend fun saveUsers(users: List<UserModel>) {
        val usersProto = modelToProtoConverter.convertUsersList(users)
        localDataSource.saveUsers(usersProto)
    }
}
