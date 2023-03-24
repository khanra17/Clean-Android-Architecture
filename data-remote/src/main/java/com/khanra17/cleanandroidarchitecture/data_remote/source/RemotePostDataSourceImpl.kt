package com.khanra17.cleanandroidarchitecture.data_remote.source

import com.khanra17.cleanandroidarchitecture.data_remote.networking.post.PostApiModel
import com.khanra17.cleanandroidarchitecture.data_remote.networking.post.PostService
import com.khanra17.cleanandroidarchitecture.data_repository.data_source.remote.RemotePostDataSource
import com.khanra17.cleanandroidarchitecture.domain.entity.Post
import com.khanra17.cleanandroidarchitecture.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemotePostDataSourceImpl @Inject constructor(private val postService: PostService) :
    RemotePostDataSource {

    override fun getPosts(): Flow<List<Post>> = flow {
        emit(postService.getPosts())
    }.map { posts ->
        posts.map { postApiModel ->
            convert(postApiModel)
        }
    }.catch {
        throw UseCaseException.PostException(it)
    }

    override fun getPost(id: Long): Flow<Post> = flow {
        emit(postService.getPost(id))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.PostException(it)
    }

    private fun convert(postApiModel: PostApiModel) =
        Post(postApiModel.id, postApiModel.userId, postApiModel.title, postApiModel.body)
}