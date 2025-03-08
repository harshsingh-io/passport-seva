package com.harshsinghio.passportseva.domain.repository

import com.harshsinghio.passportseva.domain.model.Status
import com.harshsinghio.passportseva.domain.model.StatusEvent
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for passport application status operations.
 */
interface StatusRepository {

    /**
     * Get the status of a passport application by application number.
     *
     * @param applicationNumber The passport application number
     * @return Flow of the status if found
     */
    fun getApplicationStatus(applicationNumber: String): Flow<Status?>

    /**
     * Get all status events for an application.
     *
     * @param applicationNumber The passport application number
     * @return Flow of list of status events
     */
    fun getStatusEvents(applicationNumber: String): Flow<List<StatusEvent>>

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
    ): Result<Boolean>

    /**
     * Unsubscribe from status updates for an application.
     *
     * @param applicationNumber The passport application number
     * @param userId ID of the user unsubscribing
     * @return Result indicating success or failure
     */
    suspend fun unsubscribeFromStatusUpdates(
        applicationNumber: String,
        userId: String
    ): Result<Boolean>

    /**
     * Get all application statuses for a user.
     *
     * @param userId ID of the user
     * @return Flow of list of status objects
     */
    fun getUserApplicationStatuses(userId: String): Flow<List<Status>>

    /**
     * Check if a police verification is scheduled.
     *
     * @param applicationNumber The passport application number
     * @return Flow of verification date if scheduled, null otherwise
     */
    fun getPoliceVerificationDate(applicationNumber: String): Flow<String?>

    /**
     * Submit feedback for an application.
     *
     * @param applicationNumber The passport application number
     * @param feedback Feedback text
     * @param rating Optional rating (1-5)
     * @return Result indicating success or failure
     */
    suspend fun submitFeedback(
        applicationNumber: String,
        feedback: String,
        rating: Int? = null
    ): Result<Boolean>
}