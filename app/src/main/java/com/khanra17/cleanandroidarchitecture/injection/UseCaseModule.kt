package com.khanra17.cleanandroidarchitecture.injection

import com.khanra17.cleanandroidarchitecture.domain.repository.InteractionRepository
import com.khanra17.cleanandroidarchitecture.domain.repository.PostRepository
import com.khanra17.cleanandroidarchitecture.domain.repository.UserRepository
import com.khanra17.cleanandroidarchitecture.domain.usecase.GetPostUseCase
import com.khanra17.cleanandroidarchitecture.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import com.khanra17.cleanandroidarchitecture.domain.usecase.GetUserUseCase
import com.khanra17.cleanandroidarchitecture.domain.usecase.UpdateInteractionUseCase
import com.khanra17.cleanandroidarchitecture.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideUseCaseConfiguration(): UseCase.Configuration = UseCase.Configuration(Dispatchers.IO)

    @Provides
    fun provideGetPostsWithUsersWithInteractionUseCase(
        configuration: UseCase.Configuration,
        postRepository: PostRepository,
        userRepository: UserRepository,
        interactionRepository: InteractionRepository
    ): GetPostsWithUsersWithInteractionUseCase = GetPostsWithUsersWithInteractionUseCase(
        configuration, postRepository, userRepository, interactionRepository
    )

    @Provides
    fun provideGetPostUseCase(
        configuration: UseCase.Configuration, postRepository: PostRepository
    ): GetPostUseCase = GetPostUseCase(
        configuration, postRepository
    )

    @Provides
    fun provideGetUserUseCase(
        configuration: UseCase.Configuration, userRepository: UserRepository
    ): GetUserUseCase = GetUserUseCase(
        configuration, userRepository
    )

    @Provides
    fun provideUpdateInteractionUseCase(
        configuration: UseCase.Configuration, interactionRepository: InteractionRepository
    ): UpdateInteractionUseCase = UpdateInteractionUseCase(
        configuration, interactionRepository
    )
}