package com.khanra17.cleanandroidarchitecture.list

import androidx.lifecycle.viewModelScope
import com.khanra17.cleanandroidarchitecture.domain.entity.Interaction
import com.khanra17.cleanandroidarchitecture.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import com.khanra17.cleanandroidarchitecture.domain.usecase.UpdateInteractionUseCase
import com.khanra17.cleanandroidarchitecture.presentation_common.navigation.NavRoutes
import com.khanra17.cleanandroidarchitecture.presentation_common.navigation.PostInput
import com.khanra17.cleanandroidarchitecture.presentation_common.navigation.UserInput
import com.khanra17.cleanandroidarchitecture.presentation_common.state.MviViewModel
import com.khanra17.cleanandroidarchitecture.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val useCase: GetPostsWithUsersWithInteractionUseCase,
    private val converter: PostListConverter,
    private val updateInteractionUseCase: UpdateInteractionUseCase
) : MviViewModel<PostListModel, UiState<PostListModel>, PostListUiAction, PostListUiSingleEvent>() {

    override fun initState(): UiState<PostListModel> = UiState.Loading

    override fun handleAction(action: PostListUiAction) {
        when (action) {
            is PostListUiAction.Load -> {
                loadPosts()
            }

            is PostListUiAction.PostClick -> {
                updateInteraction(action.interaction)
                submitSingleEvent(
                    PostListUiSingleEvent.OpenPostScreen(
                        NavRoutes.Post.routeForPost(
                            PostInput(action.postId)
                        )
                    )
                )
            }

            is PostListUiAction.UserClick -> {
                updateInteraction(action.interaction)
                submitSingleEvent(
                    PostListUiSingleEvent.OpenUserScreen(
                        NavRoutes.User.routeForUser(
                            UserInput(action.userId)
                        )
                    )
                )
            }
        }

    }

    private fun loadPosts() {
        viewModelScope.launch {
            useCase.execute(GetPostsWithUsersWithInteractionUseCase.Request).map {
                    converter.convert(it)
                }.collect {
                    submitState(it)
                }
        }

    }

    private fun updateInteraction(interaction: Interaction) {
        viewModelScope.launch {
            updateInteractionUseCase.execute(
                UpdateInteractionUseCase.Request(
                    interaction.copy(
                        totalClicks = interaction.totalClicks + 1
                    )
                )
            ).collect()
        }
    }
}