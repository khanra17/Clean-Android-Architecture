package androidTest.java.khanra17.cleanandroidarchitecture.injection

import androidTest.java.khanra17.cleanandroidarchitecture.remote.MockRemotePostDataSource
import androidTest.java.khanra17.cleanandroidarchitecture.remote.MockRemoteUserDataSource
import com.khanra17.cleanandroidarchitecture.data_remote.injection.RemoteDataSourceModule
import com.khanra17.cleanandroidarchitecture.data_repository.data_source.remote.RemotePostDataSource
import com.khanra17.cleanandroidarchitecture.data_repository.data_source.remote.RemoteUserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class], replaces = [RemoteDataSourceModule::class]
)
abstract class MockRemoteDataSourceModule {

    @Binds
    abstract fun bindPostDataSource(postDataSourceImpl: MockRemotePostDataSource): RemotePostDataSource

    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl: MockRemoteUserDataSource): RemoteUserDataSource
}