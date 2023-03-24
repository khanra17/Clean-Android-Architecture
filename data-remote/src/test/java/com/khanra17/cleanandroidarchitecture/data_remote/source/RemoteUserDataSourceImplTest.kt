package com.khanra17.cleanandroidarchitecture.data_remote.source

import com.khanra17.cleanandroidarchitecture.data_remote.networking.user.UserApiModel
import com.khanra17.cleanandroidarchitecture.data_remote.networking.user.UserService
import com.khanra17.cleanandroidarchitecture.domain.entity.UseCaseException
import com.khanra17.cleanandroidarchitecture.domain.entity.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteUserDataSourceImplTest {

    private val userService = mock<UserService>()
    private val userDataSource = RemoteUserDataSourceImpl(userService)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetUsers() = runTest {
        val remoteUsers = listOf(UserApiModel(1, "name", "username", "email"))
        val expectedUsers = listOf(User(1, "name", "username", "email"))
        whenever(userService.getUsers()).thenReturn(remoteUsers)
        val result = userDataSource.getUsers().first()
        Assert.assertEquals(expectedUsers, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetUser() = runTest {
        val id = 1L
        val remoteUser = UserApiModel(id, "name", "username", "email")
        val user = User(id, "name", "username", "email")
        whenever(userService.getUser(id)).thenReturn(remoteUser)
        val result = userDataSource.getUser(id).first()
        Assert.assertEquals(user, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetUsersThrowsError() = runTest {
        whenever(userService.getUsers()).thenThrow(RuntimeException())
        userDataSource.getUsers().catch {
            Assert.assertTrue(it is UseCaseException.UserException)
        }.collect()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetUserThrowsError() = runTest {
        val id = 1L
        whenever(userService.getUser(id)).thenThrow(RuntimeException())
        userDataSource.getUser(id).catch {
            Assert.assertTrue(it is UseCaseException.UserException)
        }.collect()
    }
}