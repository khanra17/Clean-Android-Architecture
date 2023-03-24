package com.khanra17.cleanandroidarchitecture.list

import com.khanra17.cleanandroidarchitecture.presentation_common.state.UiSingleEvent

sealed class PostListUiSingleEvent : UiSingleEvent {

    data class OpenUserScreen(val navRoute: String) : PostListUiSingleEvent()
    data class OpenPostScreen(val navRoute: String) : PostListUiSingleEvent()
}