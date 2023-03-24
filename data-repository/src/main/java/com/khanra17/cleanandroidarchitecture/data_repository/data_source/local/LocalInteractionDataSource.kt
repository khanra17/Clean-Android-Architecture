package com.khanra17.cleanandroidarchitecture.data_repository.data_source.local

import com.khanra17.cleanandroidarchitecture.domain.entity.Interaction
import kotlinx.coroutines.flow.Flow

interface LocalInteractionDataSource {

    fun getInteraction(): Flow<Interaction>

    suspend fun saveInteraction(interaction: Interaction)
}