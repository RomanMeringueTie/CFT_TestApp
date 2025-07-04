package ru.cft.test_app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.loadKoinModules
import ru.cft.feature_user_details.di.userDetailsModule
import ru.cft.feature_users_list.di.usersListModule


class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
        }
        loadKoinModules(listOf(usersListModule, userDetailsModule))
    }
}