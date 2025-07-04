package ru.cft.data_source.di

import androidx.datastore.core.DataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.cft.data_source.local.LocalDataSource
import ru.cft.data_source.local.ModelToProtoConverter
import ru.cft.data_source.local.ProtoToModelConverter
import ru.cft.data_source.local.UsersListResponse
import ru.cft.data_source.local.usersListDataStore
import ru.cft.data_source.network.HttpClient
import ru.cft.data_source.network.RemoteDataSource
import ru.cft.data_source.local.LocalDataSourceImpl
import ru.cft.data_source.network.RemoteDataSourceImpl


val dataSourceModule = module {
    singleOf(::HttpClient)
    singleOf(::RemoteDataSourceImpl) { bind<RemoteDataSource>() }
    single<DataStore<UsersListResponse>> {
        val context = androidContext()
        context.usersListDataStore
    }
    singleOf(::ProtoToModelConverter)
    singleOf(::ModelToProtoConverter)
    singleOf(::LocalDataSourceImpl) { bind<LocalDataSource>() }
}