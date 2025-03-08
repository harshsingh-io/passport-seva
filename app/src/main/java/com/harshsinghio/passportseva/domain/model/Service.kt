package com.harshsinghio.passportseva.domain.model

/**
 * Represents a service offered by Passport Seva.
 *
 * @property id Unique identifier for the service
 * @property title Display title for the service
 * @property description Short description of the service
 * @property iconType Type of icon to display (mapped to an actual icon in the UI)
 * @property route Navigation route for the service (optional)
 * @property category Service category for grouping (optional)
 * @property order Display order for sorting (optional)
 * @property enabled Whether the service is enabled (optional)
 * @property feeAmount Fee amount for the service (optional)
 * @property processingTime Estimated processing time (optional)
 * @property requiredDocuments List of required documents (optional)
 */
data class Service(
    val id: String,
    val title: String,
    val description: String,
    val iconType: String,
    val route: String = "",
    val category: String = "General",
    val order: Int = 0,
    val enabled: Boolean = true,
    val feeAmount: Double = 0.0,
    val processingTime: String = "",
    val requiredDocuments: List<String> = emptyList()
)

/**
 * Categories for grouping services
 */
enum class ServiceCategory(val displayName: String) {
    GENERAL("General"),
    PASSPORT("Passport"),
    VISA("Visa"),
    DOCUMENTS("Documents"),
    VERIFICATION("Verification"),
    OTHER("Other")
}