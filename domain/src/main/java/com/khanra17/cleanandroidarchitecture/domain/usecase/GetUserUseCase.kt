package com.khanra17.cleanandroidarchitecture.domain.usecase

import com.khanra17.cleanandroidarchitecture.domain.entity.User
import com.khanra17.cleanandroidarchitecture.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUserUseCase(
    configuration: Configuration, private val userRepository: UserRepository
) : UseCase<GetUserUseCase.Request, GetUserUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        userRepository.getUser(request.userId).map {
            Response(it)
        }

    data class Request(val userId: Long) : UseCase.Request
    data class Response(val user: User) : UseCase.Response
}