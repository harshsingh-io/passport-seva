package com.harshsinghio.passportseva.domain.repository

import com.harshsinghio.passportseva.domain.model.QuickAction
import com.harshsinghio.passportseva.domain.model.Service
import com.harshsinghio.passportseva.domain.model.ServiceCategory
import com.harshsinghio.passportseva.domain.model.Update
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for services, quick actions, and updates.
 */
interface ServicesRepository {

    /**
     * Get all available services.
     *
     * @param onlyEnabled Whether to return only enabled services
     * @return Flow of list of services
     */
    fun getServices(onlyEnabled: Boolean = true): Flow<List<Service>>

    /**
     * Get services by category.
     *
     * @param category The category to filter by
     * @param onlyEnabled Whether to return only enabled services
     * @return Flow of list of services in the specified category
     */
    fun getServicesByCategory(
        category: ServiceCategory,
        onlyEnabled: Boolean = true
    ): Flow<List<Service>>

    /**
     * Get a service by its ID.
     *
     * @param serviceId ID of the service
     * @return Flow of the service if found
     */
    fun getServiceById(serviceId: String): Flow<Service?>

    /**
     * Search for services by query string.
     *
     * @param query Search query
     * @param onlyEnabled Whether to return only enabled services
     * @return Flow of list of matching services
     */
    fun searchServices(
        query: String,
        onlyEnabled: Boolean = true
    ): Flow<List<Service>>

    /**
     * Get all quick actions for the home screen.
     *
     * @param onlyEnabled Whether to return only enabled quick actions
     * @return Flow of list of quick actions
     */
    fun getQuickActions(onlyEnabled: Boolean = true): Flow<List<QuickAction>>

    /**
     * Get a quick action by its ID.
     *
     * @param quickActionId ID of the quick action
     * @return Flow of the quick action if found
     */
    fun getQuickActionById(quickActionId: String): Flow<QuickAction?>

    /**
     * Get all updates and notifications.
     *
     * @param count Maximum number of updates to return
     * @return Flow of list of updates
     */
    fun getUpdates(count: Int = 10): Flow<List<Update>>

    /**
     * Get an update by its ID.
     *
     * @param updateId ID of the update
     * @return Flow of the update if found
     */
    fun getUpdateById(updateId: String): Flow<Update?>

    /**
     * Mark an update as read.
     *
     * @param updateId ID of the update
     * @return Result indicating success or failure
     */
    suspend fun markUpdateAsRead(updateId: String): Result<Boolean>

    /**
     * Calculate the fee for a service.
     *
     * @param serviceId ID of the service
     * @param parameters Additional parameters for fee calculation
     * @return Result containing the calculated fee amount
     */
    suspend fun calculateFee(
        serviceId: String,
        parameters: Map<String, Any>
    ): Result<Double>

    /**
     * Get required documents for a service.
     *
     * @param serviceId ID of the service
     * @param parameters Additional parameters that might affect document requirements
     * @return Flow of list of required document descriptions
     */
    fun getRequiredDocuments(
        serviceId: String,
        parameters: Map<String, Any> = emptyMap()
    ): Flow<List<String>>
}