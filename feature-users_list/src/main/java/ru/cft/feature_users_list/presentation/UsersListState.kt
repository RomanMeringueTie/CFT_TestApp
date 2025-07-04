package ru.cft.feature_users_list.presentation

import ru.cft.feature_users_list.ui.UserUI


sealed interface UsersListState {

    data object Loading : UsersListState

    data class Content(val users: List<UserUI>) : UsersListState

    data class Failure(val message: String) : UsersListState
}