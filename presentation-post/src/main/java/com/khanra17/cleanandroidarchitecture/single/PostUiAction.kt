package com.khanra17.cleanandroidarchitecture.single

import com.khanra17.cleanandroidarchitecture.presentation_common.state.UiAction

sealed class PostUiAction : UiAction {

    data class Load(val postId: Long) : PostUiAction()
}