package androidTest.java.khanra17.cleanandroidarchitecture.injection


import androidTest.java.khanra17.cleanandroidarchitecture.idling.ComposeCountingIdlingResource
import androidTest.java.khanra17.cleanandroidarchitecture.repository.IdlingInteractionRepository
import androidTest.java.khanra17.cleanandroidarchitecture.repository.IdlingPostRepository
import androidTest.java.khanra17.cleanandroidarchitecture.repository.IdlingUserRepository
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
import com.khanra17.cleanandroidarchitecture.injection.RepositoryModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class], replaces = [RepositoryModule::class]
)
class IdlingRepositoryModule {

    @Singleton
    @Provides
    fun provideIdlingResource(): ComposeCountingIdlingResource =
        ComposeCountingIdlingResource("repository-idling")

    @Provides
    fun providePostRepository(
        remotePostDataSource: RemotePostDataSource,
        localPostDataSource: LocalPostDataSource,
        countingIdlingResource: ComposeCountingIdlingResource
    ): PostRepository = IdlingPostRepository(
        PostRepositoryImpl(
            remotePostDataSource, localPostDataSource
        ), countingIdlingResource
    )

    @Provides
    fun provideUserRepository(
        remoteUserDataSource: RemoteUserDataSource,
        localUserDataSource: LocalUserDataSource,
        countingIdlingResource: ComposeCountingIdlingResource
    ): UserRepository = IdlingUserRepository(
        UserRepositoryImpl(
            remoteUserDataSource, localUserDataSource
        ), countingIdlingResource
    )

    @Provides
    fun provideInteractionRepository(
        interactionDataSource: LocalInteractionDataSource,
        countingIdlingResource: ComposeCountingIdlingResource
    ): InteractionRepository = IdlingInteractionRepository(
        InteractionRepositoryImpl(
            interactionDataSource
        ), countingIdlingResource
    )
}