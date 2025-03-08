package com.harshsinghio.passportseva.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Represents the status of a passport application.
 *
 * @property applicationNumber Unique application number
 * @property applicantName Name of the applicant
 * @property applicationType Type of application (e.g., "Fresh Passport", "Renewal")
 * @property currentStatus Current status of the application
 * @property appliedDate Date when the application was submitted
 * @property expectedCompletionDate Expected date of completion
 * @property lastUpdated Date and time of the last status update
 * @property events List of timeline events in the application process
 */
data class Status(
    val applicationNumber: String,
    val applicantName: String,
    val applicationType: String,
    val currentStatus: String,
    val appliedDate: LocalDate,
    val expectedCompletionDate: LocalDate,
    val lastUpdated: LocalDateTime,
    val events: List<StatusEvent>
)

/**
 * Represents an event in the passport application timeline.
 *
 * @property id Unique identifier for the event
 * @property title Title of the event
 * @property description Description of what happened
 * @property timestamp Date and time when the event occurred
 * @property status Status of this event (completed, pending, etc.)
 * @property order Chronological order of the event
 */
data class StatusEvent(
    val id: String,
    val title: String,
    val description: String,
    val timestamp: LocalDateTime?,
    val status: EventStatus,
    val order: Int
)

/**
 * Status of an event in the timeline
 */
enum class EventStatus {
    COMPLETED,
    PENDING,
    IN_PROGRESS,
    FAILED,
    SCHEDULED,
    NOT_STARTED
}