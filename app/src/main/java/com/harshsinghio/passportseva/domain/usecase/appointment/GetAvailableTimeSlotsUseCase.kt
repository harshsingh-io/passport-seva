package com.harshsinghio.passportseva.domain.usecase.appointment

import com.harshsinghio.passportseva.domain.model.TimeSlot
import com.harshsinghio.passportseva.domain.repository.AppointmentRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

/**
 * Use case to get available time slots for appointment booking.
 */
class GetAvailableTimeSlotsUseCase @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    /**
     * Get available time slots for a specific date at a location.
     *
     * @param locationId ID of the Passport Seva Kendra location
     * @param date The date for which to get time slots
     * @return Flow of list of available time slots
     */
    operator fun invoke(locationId: String, date: LocalDate): Flow<List<TimeSlot>> {
        return appointmentRepository.getAvailableTimeSlots(locationId, date)
    }
}