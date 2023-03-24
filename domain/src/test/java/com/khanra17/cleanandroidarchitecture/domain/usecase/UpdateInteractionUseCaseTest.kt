package com.khanra17.cleanandroidarchitecture.domain.usecase

import com.khanra17.cleanandroidarchitecture.domain.entity.Interaction
import com.khanra17.cleanandroidarchitecture.domain.repository.InteractionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UpdateInteractionUseCaseTest {

    private val interactionRepository = mock<InteractionRepository>()
    private val useCase = UpdateInteractionUseCase(
        mock(), interactionRepository
    )

    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runTest {
        val interaction = Interaction(10)
        val request = UpdateInteractionUseCase.Request(interaction)
        whenever(interactionRepository.saveInteraction(interaction)).thenReturn(flowOf(interaction))
        val response = useCase.process(request).first()
        Assert.assertEquals(UpdateInteractionUseCase.Response, response)
    }

}