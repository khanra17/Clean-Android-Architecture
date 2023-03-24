package com.khanra17.cleanandroidarchitecture.data_local.source

import com.khanra17.cleanandroidarchitecture.data_local.db.post.PostDao
import com.khanra17.cleanandroidarchitecture.data_local.db.post.PostEntity
import com.khanra17.cleanandroidarchitecture.data_repository.data_source.local.LocalPostDataSource
import com.khanra17.cleanandroidarchitecture.domain.entity.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPostDataSourceImpl @Inject constructor(private val postDao: PostDao) :
    LocalPostDataSource {

    override fun getPosts(): Flow<List<Post>> = postDao.getPosts().map { posts ->
        posts.map {
            Post(it.id, it.userId, it.title, it.body)
        }
    }

    override suspend fun addPosts(posts: List<Post>) = postDao.insertPosts(posts.map {
        PostEntity(it.id, it.userId, it.title, it.body)
    })
}