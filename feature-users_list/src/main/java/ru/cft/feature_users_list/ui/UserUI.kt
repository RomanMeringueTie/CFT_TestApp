package ru.cft.feature_users_list.ui

import kotlinx.serialization.Serializable


@Serializable
data class UserUI(
    val fullName: String,
    val picture: String,
    val phone: String,
    val address: String
)
