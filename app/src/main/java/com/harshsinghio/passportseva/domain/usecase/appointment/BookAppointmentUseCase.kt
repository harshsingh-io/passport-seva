package com.harshsinghio.passportseva.domain.usecase.appointment

import com.harshsinghio.passportseva.domain.model.Appointment
import com.harshsinghio.passportseva.domain.model.AppointmentRequest
import com.harshsinghio.passportseva.domain.repository.AppointmentRepository
import javax.inject.Inject

/**
 * Use case to book a new passport appointment.
 */
class BookAppointmentUseCase @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    /**
     * Book a new appointment.
     *
     * @param userId ID of the user booking the appointment
     * @param locationId ID of the selected Passport Seva Kendra location
     * @param date String representation of the appointment date (YYYY-MM-DD)
     * @param timeSlotId ID of the selected time slot
     * @param applicantName Name of the applicant
     * @param appointmentType Type of appointment (e.g., "Fresh Passport", "Renewal")
     * @return Result containing the created appointment if successful
     */
    suspend operator fun invoke(
        userId: String,
        locationId: String,
        date: String,
        timeSlotId: String,
        applicantName: String,
        appointmentType: String
    ): Result<Appointment> {
        val appointmentRequest = AppointmentRequest(
            userId = userId,
            locationId = locationId,
            date = date,
            timeSlotId = timeSlotId,
            applicantName = applicantName,
            appointmentType = appointmentType
        )

        return appointmentRepository.bookAppointment(appointmentRequest)
    }
}