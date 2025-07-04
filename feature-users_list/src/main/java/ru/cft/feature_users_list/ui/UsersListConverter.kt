package ru.cft.feature_users_list.ui

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import ru.cft.common.data.model.Location
import ru.cft.common.data.model.Name
import ru.cft.common.data.model.UserModel


class UsersListConverter {

    fun convertUsersList(usersList: List<UserModel>): ImmutableList<UserUI> {
        return usersList.map { convertUser(it) }.toPersistentList()
    }

    private fun convertUser(userModel: UserModel): UserUI {
        return UserUI(
            fullName = convertName(userModel.name),
            picture = userModel.picture?.medium ?: "",
            phone = userModel.phone ?: "Unknown Phone",
            address = convertAddress(userModel.location)
        )
    }

    private fun convertName(name: Name?): String {
        return name?.let {
            "${it.title} ${it.first} ${it.last}"
        } ?: "Unknown Name"
    }

    private fun convertAddress(location: Location?): String {
        return location?.let {
            "${it.country}, ${it.city}, ${it.street?.name ?: "-"}, ${it.street?.number ?: "-"}"
        } ?: "Unknown Address"
    }
}