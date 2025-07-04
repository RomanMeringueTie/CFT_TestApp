package ru.cft.test_app.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data object UsersList: Route

    @Serializable
    data class UserDetails(val encodedUser: String): Route
}