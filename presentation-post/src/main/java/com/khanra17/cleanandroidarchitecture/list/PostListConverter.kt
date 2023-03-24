package com.khanra17.cleanandroidarchitecture.list

import android.content.Context
import com.khanra17.cleanandroidarchitecture.domain.entity.Interaction
import com.khanra17.cleanandroidarchitecture.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import com.khanra17.cleanandroidarchitecture.presentation_common.state.CommonResultConverter
import com.khanra17.cleanandroidarchitecture.presentation_post.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PostListConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetPostsWithUsersWithInteractionUseCase.Response, PostListModel>() {

    override fun convertSuccess(data: GetPostsWithUsersWithInteractionUseCase.Response): PostListModel {
        return PostListModel(
            headerText = context.getString(
                R.string.total_click_count,
                data.interaction.totalClicks
            ),
            items = data.posts.map {
                PostListItemModel(
                    it.post.id,
                    it.user.id,
                    context.getString(R.string.author, it.user.name),
                    context.getString(R.string.title, it.post.title)
                )
            },
            interaction = data.interaction
        )
    }
}