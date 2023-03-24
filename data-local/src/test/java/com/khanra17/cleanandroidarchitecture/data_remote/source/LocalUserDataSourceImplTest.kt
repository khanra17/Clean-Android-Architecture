package com.khanra17.cleanandroidarchitecture.data_remote.source

import com.khanra17.cleanandroidarchitecture.data_local.db.user.UserDao
import com.khanra17.cleanandroidarchitecture.data_local.db.user.UserEntity
import com.khanra17.cleanandroidarchitecture.data_local.source.LocalUserDataSourceImpl
import com.khanra17.cleanandroidarchitecture.domain.entity.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LocalUserDataSourceImplTest {

    private val userDao = mock<UserDao>()
    private val userDataSource = LocalUserDataSourceImpl(userDao)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetUsers() = runTest {
        val localUsers = listOf(UserEntity(1, "name", "username", "email"))
        val expectedUsers = listOf(User(1, "name", "username", "email"))
        whenever(userDao.getUsers()).thenReturn(flowOf(localUsers))
        val result = userDataSource.getUsers().first()
        Assert.assertEquals(expectedUsers, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testAddUsers() = runTest {
        val localUsers = listOf(UserEntity(1, "name", "username", "email"))
        val users = listOf(User(1, "name", "username", "email"))
        userDataSource.addUsers(users)
        verify(userDao).insertUsers(localUsers)
    }

}