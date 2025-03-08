package com.harshsinghio.passportseva.domain.model

import java.time.LocalDate
import java.time.LocalTime

/**
 * Represents a passport appointment booking.
 *
 * @property id Unique identifier for the appointment
 * @property userId ID of the user who booked the appointment
 * @property location The location where the appointment is booked
 * @property date The date of the appointment
 * @property timeSlot The time slot of the appointment
 * @property applicantName Name of the applicant
 * @property appointmentType Type of appointment (e.g., "Fresh Passport", "Renewal")
 * @property status Current status of the appointment
 * @property bookingDate Date when the appointment was booked
 */
data class Appointment(
    val id: String,
    val userId: String,
    val location: Location,
    val date: LocalDate,
    val timeSlot: TimeSlot,
    val applicantName: String,
    val appointmentType: String,
    val status: String,
    val bookingDate: LocalDate
)

/**
 * Model for creating a new appointment
 */
data class AppointmentRequest(
    val userId: String,
    val locationId: String,
    val date: String,
    val timeSlotId: String,
    val applicantName: String,
    val appointmentType: String
)

/**
 * Available dates with booking information
 */
data class AvailableDate(
    val date: LocalDate,
    val isAvailable: Boolean,
    val availableSlots: Int
)