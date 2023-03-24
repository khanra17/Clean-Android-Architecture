package com.khanra17.cleanandroidarchitecture.injection

import com.khanra17.cleanandroidarchitecture.data_repository.data_source.local.LocalInteractionDataSource
import com.khanra17.cleanandroidarchitecture.data_repository.data_source.local.LocalPostDataSource
import com.khanra17.cleanandroidarchitecture.data_repository.data_source.local.LocalUserDataSource
import com.khanra17.cleanandroidarchitecture.data_repository.data_source.remote.RemotePostDataSource
import com.khanra17.cleanandroidarchitecture.data_repository.data_source.remote.RemoteUserDataSource
import com.khanra17.cleanandroidarchitecture.data_repository.repository.InteractionRepositoryImpl
import com.khanra17.cleanandroidarchitecture.data_repository.repository.PostRepositoryImpl
import com.khanra17.cleanandroidarchitecture.data_repository.repository.UserRepositoryImpl
import com.khanra17.cleanandroidarchitecture.domain.repository.InteractionRepository
import com.khanra17.cleanandroidarchitecture.domain.repository.PostRepository
import com.khanra17.cleanandroidarchitecture.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Provides
    fun providePostRepository(
        remotePostDataSource: RemotePostDataSource, localPostDataSource: LocalPostDataSource
    ): PostRepository = PostRepositoryImpl(
        remotePostDataSource, localPostDataSource
    )

    @Provides
    fun provideUserRepository(
        remoteUserDataSource: RemoteUserDataSource, localUserDataSource: LocalUserDataSource
    ): UserRepository = UserRepositoryImpl(
        remoteUserDataSource, localUserDataSource
    )

    @Provides
    fun provideInteractionRepository(
        interactionDataSource: LocalInteractionDataSource
    ): InteractionRepository = InteractionRepositoryImpl(
        interactionDataSource
    )
}