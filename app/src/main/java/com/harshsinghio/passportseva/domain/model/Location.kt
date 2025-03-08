package com.harshsinghio.passportseva.domain.model

/**
 * Represents a Passport Seva Kendra location.
 *
 * @property id Unique identifier for the location
 * @property name Name of the passport office
 * @property address Physical address of the office
 * @property city City where the office is located
 * @property state State where the office is located
 * @property pincode Postal code of the office
 * @property phone Contact phone number
 * @property email Contact email address
 * @property latitude Geographical latitude
 * @property longitude Geographical longitude
 * @property distance Distance from the user's location (optional)
 * @property type Type of office (PSK, POPSK, etc.)
 * @property workingHours Office working hours
 * @property isActive Whether the location is currently active
 */
data class Location(
    val id: String,
    val name: String,
    val address: String,
    val city: String = "",
    val state: String = "",
    val pincode: String = "",
    val phone: String = "",
    val email: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val distance: String = "",
    val type: String = "PSK",
    val workingHours: String = "9:00 AM - 5:00 PM",
    val isActive: Boolean = true
)