package com.harshsinghio.passportseva.domain.usecase.auth

import com.harshsinghio.passportseva.domain.model.AuthResult
import com.harshsinghio.passportseva.domain.model.RegistrationData
import com.harshsinghio.passportseva.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Use case to register a new user.
 */
class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Register a new user.
     *
     * @param name User's full name
     * @param email User's email address
     * @param mobile User's mobile number
     * @param password User's password
     * @return Result containing authentication result
     */
    suspend operator fun invoke(
        name: String,
        email: String,
        mobile: String,
        password: String
    ): Result<AuthResult> {
        // Basic validation
        val validationResult = validateRegistrationData(name, email, mobile, password)
        if (validationResult.isFailure) {
            return Result.failure(validationResult.exceptionOrNull() ?: Exception("Validation failed"))
        }

        val registrationData = RegistrationData(
            name = name,
            email = email,
            mobile = mobile,
            password = password
        )

        return authRepository.register(registrationData)
    }

    /**
     * Validate registration data.
     *
     * @param name User's full name
     * @param email User's email address
     * @param mobile User's mobile number
     * @param password User's password
     * @return Result indicating if validation passed
     */
    private fun validateRegistrationData(
        name: String,
        email: String,
        mobile: String,
        password: String
    ): Result<Boolean> {
        if (name.isBlank()) {
            return Result.failure(IllegalArgumentException("Name cannot be empty"))
        }

        if (email.isBlank()) {
            return Result.failure(IllegalArgumentException("Email cannot be empty"))
        }

        if (!email.contains("@")) {
            return Result.failure(IllegalArgumentException("Invalid email format"))
        }

        if (mobile.isBlank()) {
            return Result.failure(IllegalArgumentException("Mobile number cannot be empty"))
        }

        if (mobile.length < 10) {
            return Result.failure(IllegalArgumentException("Mobile number must be at least 10 digits"))
        }

        if (password.isBlank()) {
            return Result.failure(IllegalArgumentException("Password cannot be empty"))
        }

        if (password.length < 6) {
            return Result.failure(IllegalArgumentException("Password must be at least 6 characters"))
        }

        return Result.success(true)
    }
}