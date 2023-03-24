package com.khanra17.cleanandroidarchitecture.data_repository.data_source.local

import com.khanra17.cleanandroidarchitecture.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface LocalUserDataSource {

    fun getUsers(): Flow<List<User>>

    suspend fun addUsers(users: List<User>)
}