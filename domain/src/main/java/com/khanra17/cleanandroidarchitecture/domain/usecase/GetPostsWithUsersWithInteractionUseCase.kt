package com.khanra17.cleanandroidarchitecture.domain.usecase

import com.khanra17.cleanandroidarchitecture.domain.entity.Interaction
import com.khanra17.cleanandroidarchitecture.domain.entity.PostWithUser
import com.khanra17.cleanandroidarchitecture.domain.repository.InteractionRepository
import com.khanra17.cleanandroidarchitecture.domain.repository.PostRepository
import com.khanra17.cleanandroidarchitecture.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetPostsWithUsersWithInteractionUseCase(
    configuration: Configuration,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val interactionRepository: InteractionRepository
) : UseCase<GetPostsWithUsersWithInteractionUseCase.Request, GetPostsWithUsersWithInteractionUseCase.Response>(
    configuration
) {

    override fun process(request: Request): Flow<Response> = combine(
        postRepository.getPosts(), userRepository.getUsers(), interactionRepository.getInteraction()
    ) { posts, users, interaction ->
        val postUsers = posts.map { post ->
            val user = users.first {
                it.id == post.userId
            }
            PostWithUser(post, user)
        }
        Response(postUsers, interaction)
    }

    object Request : UseCase.Request

    data class Response(
        val posts: List<PostWithUser>, val interaction: Interaction
    ) : UseCase.Response
}