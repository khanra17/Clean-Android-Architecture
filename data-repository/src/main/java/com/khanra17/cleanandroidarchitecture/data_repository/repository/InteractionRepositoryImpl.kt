package com.khanra17.cleanandroidarchitecture.data_repository.repository

import com.khanra17.cleanandroidarchitecture.data_repository.data_source.local.LocalInteractionDataSource
import com.khanra17.cleanandroidarchitecture.domain.entity.Interaction
import com.khanra17.cleanandroidarchitecture.domain.repository.InteractionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class InteractionRepositoryImpl(
    private val interactionDataSource: LocalInteractionDataSource
) : InteractionRepository {

    override fun getInteraction(): Flow<Interaction> = interactionDataSource.getInteraction()

    override fun saveInteraction(interaction: Interaction): Flow<Interaction> = flow {
        interactionDataSource.saveInteraction(interaction)
        this.emit(Unit)
    }.flatMapLatest {
        getInteraction()
    }
}