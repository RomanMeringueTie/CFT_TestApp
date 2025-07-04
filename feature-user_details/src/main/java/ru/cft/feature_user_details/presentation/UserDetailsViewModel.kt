package ru.cft.feature_user_details.presentation

import androidx.lifecycle.ViewModel
import ru.cft.common.data.model.UserModel
import ru.cft.feature_user_details.ui.UserDetailsConverter
import ru.cft.feature_user_details.ui.UserUI

class UserDetailsViewModel(
    private val userModel: UserModel,
    private val userDetailsConverter: UserDetailsConverter,
) : ViewModel() {

    val userUI: UserUI = userDetailsConverter.convertUser(userModel)

}