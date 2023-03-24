package com.khanra17.cleanandroidarchitecture.domain.usecase

import com.khanra17.cleanandroidarchitecture.domain.entity.Post
import com.khanra17.cleanandroidarchitecture.domain.repository.PostRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class GetPostUseCaseTest {

    private val postRepository = mock<PostRepository>()
    private val useCase = GetPostUseCase(
        mock(), postRepository
    )

    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runTest {
        val request = GetPostUseCase.Request(0L)
        val post = Post(1L, 1L, "title", "body")
        whenever(postRepository.getPost(request.postId)).thenReturn(flowOf(post))
        val response = useCase.process(request).first()
        assertEquals(GetPostUseCase.Response(post), response)
    }
}