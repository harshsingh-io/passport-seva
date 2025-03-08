package com.harshsinghio.passportseva.domain.usecase.services

import com.harshsinghio.passportseva.domain.model.Update
import com.harshsinghio.passportseva.domain.repository.ServicesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case to get updates and notifications.
 */
class GetUpdatesUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    /**
     * Get all updates and notifications.
     *
     * @param count Maximum number of updates to return
     * @return Flow of list of updates
     */
    operator fun invoke(count: Int = 10): Flow<List<Update>> {
        return servicesRepository.getUpdates(count)
    }

    /**
     * Get an update by its ID.
     *
     * @param updateId ID of the update
     * @return Flow of the update if found
     */
    fun getById(updateId: String): Flow<Update?> {
        return servicesRepository.getUpdateById(updateId)
    }

    /**
     * Mark an update as read.
     *
     * @param updateId ID of the update
     * @return Result indicating success or failure
     */
    suspend fun markAsRead(updateId: String): Result<Boolean> {
        return servicesRepository.markUpdateAsRead(updateId)
    }
}