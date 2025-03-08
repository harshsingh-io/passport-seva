package com.harshsinghio.passportseva.domain.usecase.services

import com.harshsinghio.passportseva.domain.model.QuickAction
import com.harshsinghio.passportseva.domain.repository.ServicesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case to get quick actions for the home screen.
 */
class GetQuickActionsUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    /**
     * Get all quick actions for the home screen.
     *
     * @param onlyEnabled Whether to return only enabled quick actions
     * @return Flow of list of quick actions
     */
    operator fun invoke(onlyEnabled: Boolean = true): Flow<List<QuickAction>> {
        return servicesRepository.getQuickActions(onlyEnabled)
    }
}