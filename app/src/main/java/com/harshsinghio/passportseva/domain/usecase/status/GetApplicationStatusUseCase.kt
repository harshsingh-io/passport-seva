package com.harshsinghio.passportseva.domain.usecase.status

import com.harshsinghio.passportseva.domain.model.Status
import com.harshsinghio.passportseva.domain.model.StatusEvent
import com.harshsinghio.passportseva.domain.repository.StatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

/**
 * Use case to get passport application status.
 */
class GetApplicationStatusUseCase @Inject constructor(
    private val statusRepository: StatusRepository
) {
    /**
     * Get the status of a passport application by application number.
     *
     * @param applicationNumber The passport application number
     * @return Flow of the status if found
     */
    operator fun invoke(applicationNumber: String): Flow<Status?> {
        return statusRepository.getApplicationStatus(applicationNumber)
    }

    /**
     * Get all status events for an application.
     *
     * @param applicationNumber The passport application number
     * @return Flow of list of status events
     */
    fun getStatusEvents(applicationNumber: String): Flow<List<StatusEvent>> {
        return statusRepository.getStatusEvents(applicationNumber)
    }

    /**
     * Get status with full details including all events.
     *
     * @param applicationNumber The passport application number
     * @return Flow combining status and events
     */
    fun getFullStatusWithEvents(applicationNumber: String): Flow<Pair<Status?, List<StatusEvent>>> {
        val statusFlow = statusRepository.getApplicationStatus(applicationNumber)
        val eventsFlow = statusRepository.getStatusEvents(applicationNumber)

        return statusFlow.combine(eventsFlow) { status, events ->
            Pair(status, events)
        }
    }

    /**
     * Subscribe to status updates for an application.
     *
     * @param applicationNumber The passport application number
     * @param userId ID of the user subscribing
     * @return Result indicating success or failure
     */
    suspend fun subscribeToStatusUpdates(
        applicationNumber: String,
        userId: String
    ): Result<Boolean> {
        return statusRepository.subscribeToStatusUpdates(applicationNumber, userId)
    }

    /**
     * Get all application statuses for a user.
     *
     * @param userId ID of the user
     * @return Flow of list of status objects
     */
    fun getUserApplicationStatuses(userId: String): Flow<List<Status>> {
        return statusRepository.getUserApplicationStatuses(userId)
    }
}