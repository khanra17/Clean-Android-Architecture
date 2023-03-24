package com.khanra17.cleanandroidarchitecture.domain.repository

import com.khanra17.cleanandroidarchitecture.domain.entity.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPosts(): Flow<List<Post>>

    fun getPost(id: Long): Flow<Post>
}