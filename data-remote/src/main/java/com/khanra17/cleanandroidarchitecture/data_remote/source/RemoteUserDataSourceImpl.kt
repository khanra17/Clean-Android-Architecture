package com.khanra17.cleanandroidarchitecture.data_remote.source

import com.khanra17.cleanandroidarchitecture.data_remote.networking.user.UserApiModel
import com.khanra17.cleanandroidarchitecture.data_remote.networking.user.UserService
import com.khanra17.cleanandroidarchitecture.data_repository.data_source.remote.RemoteUserDataSource
import com.khanra17.cleanandroidarchitecture.domain.entity.UseCaseException
import com.khanra17.cleanandroidarchitecture.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(private val userService: UserService) :
    RemoteUserDataSource {

    override fun getUsers(): Flow<List<User>> = flow {
        emit(userService.getUsers())
    }.map { users ->
        users.map { userApiModel ->
            convert(userApiModel)
        }
    }.catch {
        throw UseCaseException.UserException(it)
    }

    override fun getUser(id: Long): Flow<User> = flow {
        emit(userService.getUser(id))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.UserException(it)
    }

    private fun convert(userApiModel: UserApiModel) =
        User(userApiModel.id, userApiModel.name, userApiModel.username, userApiModel.email)
}