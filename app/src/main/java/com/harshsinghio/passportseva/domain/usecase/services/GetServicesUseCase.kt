package com.harshsinghio.passportseva.domain.usecase.services

import com.harshsinghio.passportseva.domain.model.Service
import com.harshsinghio.passportseva.domain.model.ServiceCategory
import com.harshsinghio.passportseva.domain.repository.ServicesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case to get available services.
 */
class GetServicesUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository
) {
    /**
     * Get all available services.
     *
     * @param onlyEnabled Whether to return only enabled services
     * @return Flow of list of services
     */
    operator fun invoke(onlyEnabled: Boolean = true): Flow<List<Service>> {
        return servicesRepository.getServices(onlyEnabled)
    }

    /**
     * Get services by category.
     *
     * @param category The category to filter by
     * @param onlyEnabled Whether to return only enabled services
     * @return Flow of list of services in the specified category
     */
    fun getByCategory(
        category: ServiceCategory,
        onlyEnabled: Boolean = true
    ): Flow<List<Service>> {
        return servicesRepository.getServicesByCategory(category, onlyEnabled)
    }

    /**
     * Search for services by query string.
     *
     * @param query Search query
     * @param onlyEnabled Whether to return only enabled services
     * @return Flow of list of matching services
     */
    fun search(query: String, onlyEnabled: Boolean = true): Flow<List<Service>> {
        return servicesRepository.searchServices(query, onlyEnabled)
    }

    /**
     * Get a service by its ID.
     *
     * @param serviceId ID of the service
     * @return Flow of the service if found
     */
    fun getById(serviceId: String): Flow<Service?> {
        return servicesRepository.getServiceById(serviceId)
    }
}