package com.harshsinghio.passportseva.domain.model

import java.time.LocalTime

/**
 * Represents a time slot for appointment booking.
 *
 * @property id Unique identifier for the time slot
 * @property time Display time for the slot (e.g., "09:00 AM")
 * @property startTime Start time of the slot
 * @property endTime End time of the slot
 * @property isAvailable Whether the slot is available for booking
 * @property maxCapacity Maximum number of appointments that can be booked in this slot
 * @property bookedCount Number of appointments already booked in this slot
 */
data class TimeSlot(
    val id: String,
    val time: String,
    val startTime: LocalTime = LocalTime.NOON, // Default value as placeholder
    val endTime: LocalTime = LocalTime.NOON, // Default value as placeholder
    val isAvailable: Boolean = true,
    val maxCapacity: Int = 10,
    val bookedCount: Int = 0
)