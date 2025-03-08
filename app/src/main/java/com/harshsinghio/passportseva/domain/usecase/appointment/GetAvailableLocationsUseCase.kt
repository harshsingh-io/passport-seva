package com.harshsinghio.passportseva.domain.usecase.appointment

import com.harshsinghio.passportseva.domain.model.Location
import com.harshsinghio.passportseva.domain.repository.AppointmentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case to get available Passport Seva Kendra locations.
 */
class GetAvailableLocationsUseCase @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    /**
     * Get available locations, optionally sorted by distance from user coordinates.
     *
     * @param latitude Optional user's latitude for sorting by distance
     * @param longitude Optional user's longitude for sorting by distance
     * @return Flow of list of available locations
     */
    operator fun invoke(
        latitude: Double? = null,
        longitude: Double? = null
    ): Flow<List<Location>> {
        return appointmentRepository.getAvailableLocations(latitude, longitude)
    }
}