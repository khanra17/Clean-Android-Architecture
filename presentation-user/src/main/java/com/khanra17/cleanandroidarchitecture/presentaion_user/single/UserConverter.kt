package com.khanra17.cleanandroidarchitecture.presentaion_user.single

import android.content.Context
import com.khanra17.cleanandroidarchitecture.domain.usecase.GetUserUseCase
import com.khanra17.cleanandroidarchitecture.presentation_common.state.CommonResultConverter
import com.khanra17.cleanandroidarchitecture.presentation_user.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetUserUseCase.Response, UserModel>() {

    override fun convertSuccess(data: GetUserUseCase.Response): UserModel {
        return UserModel(
            context.getString(R.string.name, data.user.name),
            context.getString(R.string.username, data.user.username),
            context.getString(R.string.email, data.user.email)
        )
    }
}