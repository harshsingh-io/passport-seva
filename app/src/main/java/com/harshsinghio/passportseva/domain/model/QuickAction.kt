package com.harshsinghio.passportseva.domain.model

/**
 * Represents a quick action item shown on the home screen.
 *
 * @property id Unique identifier for the quick action
 * @property label Display text for the quick action
 * @property iconType Type of icon to display (mapped to an actual icon in the UI)
 * @property route Navigation route for the action (optional)
 * @property order Display order for sorting (optional)
 * @property enabled Whether the quick action is enabled (optional)
 */
data class QuickAction(
    val id: String,
    val label: String,
    val iconType: String,
    val route: String = "",
    val order: Int = 0,
    val enabled: Boolean = true
)