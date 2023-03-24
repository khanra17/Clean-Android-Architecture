package com.khanra17.cleanandroidarchitecture.single

import android.content.Context
import com.khanra17.cleanandroidarchitecture.domain.usecase.GetPostUseCase
import com.khanra17.cleanandroidarchitecture.presentation_common.state.CommonResultConverter
import com.khanra17.cleanandroidarchitecture.presentation_post.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PostConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetPostUseCase.Response, PostModel>() {

    override fun convertSuccess(data: GetPostUseCase.Response): PostModel {
        return PostModel(
            context.getString(R.string.title, data.post.title),
            context.getString(R.string.body, data.post.body)
        )
    }
}