package androidTest.java.khanra17.cleanandroidarchitecture.repository

import androidTest.java.khanra17.cleanandroidarchitecture.idling.ComposeCountingIdlingResource
import androidTest.java.khanra17.cleanandroidarchitecture.idling.attachIdling
import com.khanra17.cleanandroidarchitecture.domain.entity.Post
import com.khanra17.cleanandroidarchitecture.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class IdlingPostRepository(
    private val postRepository: PostRepository,
    private val countingIdlingResource: ComposeCountingIdlingResource
) : PostRepository {
    override fun getPosts(): Flow<List<Post>> =
        postRepository.getPosts().attachIdling(countingIdlingResource)

    override fun getPost(id: Long): Flow<Post> =
        postRepository.getPost(id).attachIdling(countingIdlingResource)
}