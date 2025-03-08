package com.harshsinghio.passportseva.domain.usecase.auth

import com.harshsinghio.passportseva.domain.model.AuthResult
import com.harshsinghio.passportseva.domain.model.LoginCredentials
import com.harshsinghio.passportseva.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Use case to login a user with email/password credentials.
 */
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Login a user with email/password credentials.
     *
     * @param email User's email address
     * @param password User's password
     * @return Result containing authentication result
     */
    suspend operator fun invoke(email: String, password: String): Result<AuthResult> {
        // Basic validation
        if (email.isBlank()) {
            return Result.failure(IllegalArgumentException("Email cannot be empty"))
        }

        if (password.isBlank()) {
            return Result.failure(IllegalArgumentException("Password cannot be empty"))
        }

        val credentials = LoginCredentials(email, password)
        return authRepository.login(credentials)
    }

    /**
     * Login with Google OAuth.
     *
     * @param idToken Google OAuth ID token
     * @return Result containing authentication result
     */
    suspend fun loginWithGoogle(idToken: String): Result<AuthResult> {
        if (idToken.isBlank()) {
            return Result.failure(IllegalArgumentException("Google ID token cannot be empty"))
        }

        return authRepository.loginWithGoogle(idToken)
    }

    /**
     * Login with DigiLocker.
     *
     * @param authCode DigiLocker authorization code
     * @return Result containing authentication result
     */
    suspend fun loginWithDigiLocker(authCode: String): Result<AuthResult> {
        if (authCode.isBlank()) {
            return Result.failure(IllegalArgumentException("DigiLocker auth code cannot be empty"))
        }

        return authRepository.loginWithDigiLocker(authCode)
    }
}