package com.khanra17.cleanandroidarchitecture.presentaion_user.single

import com.khanra17.cleanandroidarchitecture.presentation_common.state.UiAction

sealed class UserUiAction : UiAction {

    data class Load(val userId: Long) : UserUiAction()
}