package com.harshsinghio.passportseva.domain.model

import java.time.LocalDateTime

/**
 * Represents an update or notification in the application.
 *
 * @property id Unique identifier for the update
 * @property title Title of the update
 * @property description Detailed description of the update
 * @property timestamp Date and time when the update was published
 * @property isNew Whether this is a new update that should be highlighted
 * @property category Category of the update for grouping/filtering
 * @property priority Priority level to determine display order
 * @property imageUrl Optional URL to an image related to the update
 * @property actionUrl Optional URL for a call-to-action
 * @property actionText Optional text for the call-to-action button
 */
data class Update(
    val id: String,
    val title: String,
    val description: String,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val isNew: Boolean = false,
    val category: UpdateCategory = UpdateCategory.GENERAL,
    val priority: UpdatePriority = UpdatePriority.NORMAL,
    val imageUrl: String = "",
    val actionUrl: String = "",
    val actionText: String = ""
)

/**
 * Categories for updates and notifications
 */
enum class UpdateCategory {
    GENERAL,
    ANNOUNCEMENT,
    MAINTENANCE,
    HOLIDAY,
    POLICY,
    PROMOTION
}

/**
 * Priority levels for updates
 */
enum class UpdatePriority {
    LOW,
    NORMAL,
    HIGH,
    URGENT
}