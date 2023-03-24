package androidTest.java.khanra17.cleanandroidarchitecture.repository

import androidTest.java.khanra17.cleanandroidarchitecture.idling.ComposeCountingIdlingResource
import androidTest.java.khanra17.cleanandroidarchitecture.idling.attachIdling
import com.khanra17.cleanandroidarchitecture.domain.entity.Interaction
import com.khanra17.cleanandroidarchitecture.domain.repository.InteractionRepository
import kotlinx.coroutines.flow.Flow

class IdlingInteractionRepository(
    private val interactionRepository: InteractionRepository,
    private val countingIdlingResource: ComposeCountingIdlingResource
) : InteractionRepository {

    override fun getInteraction(): Flow<Interaction> {
        return interactionRepository.getInteraction().attachIdling(countingIdlingResource)
    }

    override fun saveInteraction(interaction: Interaction): Flow<Interaction> {
        return interactionRepository.saveInteraction(interaction)
            .attachIdling(countingIdlingResource)
    }
}