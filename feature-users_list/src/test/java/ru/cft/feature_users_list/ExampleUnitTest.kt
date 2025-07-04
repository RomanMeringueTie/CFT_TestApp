package ru.cft.feature_users_list

import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify
import ru.cft.feature_users_list.di.usersListModule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class KoinTest {
    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun moduleVerify() {
        usersListModule.verify()
    }
}