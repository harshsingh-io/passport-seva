package com.harshsinghio.passportseva.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Represents a user of the application.
 *
 * @property id Unique identifier for the user
 * @property name Full name of the user
 * @property email Email address of the user
 * @property mobile Mobile number of the user
 * @property profileImageUrl URL to the user's profile image
 * @property dateOfBirth Date of birth of the user
 * @property gender Gender of the user
 * @property address Address of the user
 * @property createdAt Date and time when the user account was created
 * @property lastLogin Date and time of the last login
 * @property isActive Whether the user account is active
 * @property isVerified Whether the user's email/mobile is verified
 * @property userType Type of user (regular, admin, etc.)
 */
data class User(
    val id: String,
    val name: String,
    val email: String,
    val mobile: String,
    val profileImageUrl: String = "",
    val dateOfBirth: LocalDate? = null,
    val gender: Gender? = null,
    val address: Address? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val lastLogin: LocalDateTime? = null,
    val isActive: Boolean = true,
    val isVerified: Boolean = false,
    val userType: UserType = UserType.REGULAR
)

/**
 * Gender enum for user profiles
 */
enum class Gender {
    MALE,
    FEMALE,
    OTHER,
    PREFER_NOT_TO_SAY
}

/**
 * User types for role-based access control
 */
enum class UserType {
    REGULAR,
    PREMIUM,
    ADMIN,
    SUPPORT
}

/**
 * Represents a physical address.
 */
data class Address(
    val street: String,
    val city: String,
    val state: String,
    val pincode: String,
    val country: String = "India"
)

/**
 * Credentials for login
 */
data class LoginCredentials(
    val email: String,
    val password: String
)

/**
 * Data for user registration
 */
data class RegistrationData(
    val name: String,
    val email: String,
    val mobile: String,
    val password: String
)

/**
 * Authentication result
 */
data class AuthResult(
    val user: User?,
    val token: String?,
    val isSuccessful: Boolean,
    val errorMessage: String?
)