package com.khanra17.cleanandroidarchitecture.presentation_user.single

import com.khanra17.cleanandroidarchitecture.domain.entity.Result
import com.khanra17.cleanandroidarchitecture.domain.usecase.GetUserUseCase
import com.khanra17.cleanandroidarchitecture.presentaion_user.single.UserConverter
import com.khanra17.cleanandroidarchitecture.presentaion_user.single.UserModel
import com.khanra17.cleanandroidarchitecture.presentaion_user.single.UserViewModel
import com.khanra17.cleanandroidarchitecture.presentation_common.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UserViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    private val useCase = mock<GetUserUseCase>()
    private val converter = mock<UserConverter>()
    private val viewModel = UserViewModel(useCase, converter)

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testLoadUser() = runTest {
        assertEquals(UiState.Loading, viewModel.userFlow.value)
        val userId = 1L
        val uiState = mock<UiState<UserModel>>()
        val result = mock<Result<GetUserUseCase.Response>>()
        whenever(useCase.execute(GetUserUseCase.Request(userId))).thenReturn(
            flowOf(
                result
            )
        )
        whenever(converter.convert(result)).thenReturn(uiState)
        viewModel.loadUser(userId)
        assertEquals(uiState, viewModel.userFlow.value)
    }
}