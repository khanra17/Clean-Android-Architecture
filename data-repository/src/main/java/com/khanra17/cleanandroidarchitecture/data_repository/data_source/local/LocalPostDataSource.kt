package com.khanra17.cleanandroidarchitecture.data_repository.data_source.local

import com.khanra17.cleanandroidarchitecture.domain.entity.Post
import kotlinx.coroutines.flow.Flow

interface LocalPostDataSource {

    fun getPosts(): Flow<List<Post>>

    suspend fun addPosts(posts: List<Post>)
}