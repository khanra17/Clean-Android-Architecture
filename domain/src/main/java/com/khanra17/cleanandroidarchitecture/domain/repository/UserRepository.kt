package com.khanra17.cleanandroidarchitecture.domain.repository

import com.khanra17.cleanandroidarchitecture.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>

    fun getUser(id: Long): Flow<User>
}