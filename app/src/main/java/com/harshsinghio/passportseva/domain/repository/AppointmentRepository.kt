package com.harshsinghio.passportseva.domain.repository

import com.harshsinghio.passportseva.domain.model.Appointment
import com.harshsinghio.passportseva.domain.model.AppointmentRequest
import com.harshsinghio.passportseva.domain.model.AvailableDate
import com.harshsinghio.passportseva.domain.model.Location
import com.harshsinghio.passportseva.domain.model.TimeSlot
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * Repository interface for appointment-related operations.
 */
interface AppointmentRepository {

    /**
     * Get available Passport Seva Kendra locations.
     *
     * @param latitude Optional user's latitude for sorting by distance
     * @param longitude Optional user's longitude for sorting by distance
     * @return Flow of list of available locations
     */
    fun getAvailableLocations(
        latitude: Double? = null,
        longitude: Double? = null
    ): Flow<List<Location>>

    /**
     * Get available dates for appointment booking at a specific location.
     *
     * @param locationId ID of the Passport Seva Kendra location
     * @param startDate Optional start date for the range
     * @param endDate Optional end date for the range
     * @return Flow of list of available dates
     */
    fun getAvailableDates(
        locationId: String,
        startDate: LocalDate? = null,
        endDate: LocalDate? = null
    ): Flow<List<AvailableDate>>

    /**
     * Get available time slots for a specific date at a location.
     *
     * @param locationId ID of the Passport Seva Kendra location
     * @param date The date for which to get time slots
     * @return Flow of list of available time slots
     */
    fun getAvailableTimeSlots(
        locationId: String,
        date: LocalDate
    ): Flow<List<TimeSlot>>

    /**
     * Book a new appointment.
     *
     * @param appointmentRequest Details of the appointment to book
     * @return Flow with the created appointment if successful
     */
    suspend fun bookAppointment(appointmentRequest: AppointmentRequest): Result<Appointment>

    /**
     * Cancel an existing appointment.
     *
     * @param appointmentId ID of the appointment to cancel
     * @return Flow with boolean indicating success
     */
    suspend fun cancelAppointment(appointmentId: String): Result<Boolean>

    /**
     * Reschedule an existing appointment.
     *
     * @param appointmentId ID of the appointment to reschedule
     * @param date New date for the appointment
     * @param timeSlotId ID of the new time slot
     * @return Flow with the updated appointment if successful
     */
    suspend fun rescheduleAppointment(
        appointmentId: String,
        date: LocalDate,
        timeSlotId: String
    ): Result<Appointment>

    /**
     * Get all appointments for a user.
     *
     * @param userId ID of the user
     * @return Flow of list of appointments
     */
    fun getUserAppointments(userId: String): Flow<List<Appointment>>

    /**
     * Get details of a specific appointment.
     *
     * @param appointmentId ID of the appointment
     * @return Flow with the appointment if found
     */
    fun getAppointmentById(appointmentId: String): Flow<Appointment?>
}