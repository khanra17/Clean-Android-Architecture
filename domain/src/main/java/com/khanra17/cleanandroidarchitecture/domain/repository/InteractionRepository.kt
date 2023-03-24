package com.khanra17.cleanandroidarchitecture.domain.repository

import com.khanra17.cleanandroidarchitecture.domain.entity.Interaction
import kotlinx.coroutines.flow.Flow

interface InteractionRepository {

    fun getInteraction(): Flow<Interaction>

    fun saveInteraction(interaction: Interaction): Flow<Interaction>
}