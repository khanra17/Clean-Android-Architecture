package com.khanra17.cleanandroidarchitecture.data_local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.khanra17.cleanandroidarchitecture.data_repository.data_source.local.LocalInteractionDataSource
import com.khanra17.cleanandroidarchitecture.domain.entity.Interaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal val KEY_TOTAL_TAPS = intPreferencesKey("key_total_taps")

class LocalInteractionDataSourceImpl(private val dataStore: DataStore<Preferences>) :
    LocalInteractionDataSource {

    override fun getInteraction(): Flow<Interaction> {
        return dataStore.data.map {
            Interaction(it[KEY_TOTAL_TAPS] ?: 0)
        }
    }

    override suspend fun saveInteraction(interaction: Interaction) {
        dataStore.edit {
            it[KEY_TOTAL_TAPS] = interaction.totalClicks
        }
    }

}