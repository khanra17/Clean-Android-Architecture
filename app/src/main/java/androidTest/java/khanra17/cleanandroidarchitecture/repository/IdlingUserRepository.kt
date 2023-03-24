package androidTest.java.khanra17.cleanandroidarchitecture.repository

import androidTest.java.khanra17.cleanandroidarchitecture.idling.ComposeCountingIdlingResource
import androidTest.java.khanra17.cleanandroidarchitecture.idling.attachIdling
import com.khanra17.cleanandroidarchitecture.domain.entity.User
import com.khanra17.cleanandroidarchitecture.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class IdlingUserRepository(
    private val userRepository: UserRepository,
    private val countingIdlingResource: ComposeCountingIdlingResource
) : UserRepository {
    override fun getUsers(): Flow<List<User>> =
        userRepository.getUsers().attachIdling(countingIdlingResource)

    override fun getUser(id: Long): Flow<User> =
        userRepository.getUser(id).attachIdling(countingIdlingResource)
}