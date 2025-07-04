package ru.cft.feature_users_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import ru.cft.common.data.model.UserModel
import ru.cft.feature_users_list.domain.GetUsersListUseCase
import ru.cft.feature_users_list.domain.SaveUsersListUseCase
import ru.cft.feature_users_list.ui.UsersListConverter


class UsersListViewModel(
    private val getUsersListUseCase: GetUsersListUseCase,
    private val saveUsersListUseCase: SaveUsersListUseCase,
    private val usersListConverter: UsersListConverter,
) : ViewModel() {

    private val _state = MutableStateFlow<UsersListState>(UsersListState.Loading)
    val state = _state.asStateFlow()

    private var usersModelList: List<UserModel> = emptyList()

    init {
        loadUsersList()
    }

    fun loadUsersList() {
        viewModelScope.launch {
            _state.value = UsersListState.Loading
            val result = withContext(Dispatchers.IO) {
                getUsersListUseCase()
            }
            result.fold(
                onSuccess = {
                    withContext(Dispatchers.IO) { saveUsersListUseCase(it) }
                    _state.value =
                        UsersListState.Content(users = usersListConverter.convertUsersList(it))
                    usersModelList = it
                },
                onFailure = {
                    _state.value = UsersListState.Failure(it.message ?: "Something went wrong...")
                }
            )
        }
    }

    fun getEncodedUserModel(index: Int): String {
        return Json.encodeToString(usersModelList[index])
    }

}