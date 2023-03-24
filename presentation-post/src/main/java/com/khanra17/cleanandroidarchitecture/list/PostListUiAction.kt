package com.khanra17.cleanandroidarchitecture.list

import com.khanra17.cleanandroidarchitecture.domain.entity.Interaction
import com.khanra17.cleanandroidarchitecture.presentation_common.state.UiAction

sealed class PostListUiAction : UiAction {

    object Load : PostListUiAction()
    data class UserClick(val userId: Long, val interaction: Interaction) : PostListUiAction()
    data class PostClick(val postId: Long, val interaction: Interaction) : PostListUiAction()
}