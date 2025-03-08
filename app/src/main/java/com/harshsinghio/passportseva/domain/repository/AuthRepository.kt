package com.harshsinghio.passportseva.domain.repository

import com.harshsinghio.passportseva.domain.model.AuthResult
import com.harshsinghio.passportseva.domain.model.LoginCredentials
import com.harshsinghio.passportseva.domain.model.RegistrationData
import com.harshsinghio.passportseva.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for authentication and user-related operations.
 */
interface AuthRepository {

    /**
     * Login a user with email/password credentials.
     *
     * @param credentials The login credentials
     * @return Result containing AuthResult with user data and token if successful
     */
    suspend fun login(credentials: LoginCredentials): Result<AuthResult>

    /**
     * Register a new user.
     *
     * @param registrationData The registration data
     * @return Result containing AuthResult with user data and token if successful
     */
    suspend fun register(registrationData: RegistrationData): Result<AuthResult>

    /**
     * Login or register with Google OAuth.
     *
     * @param idToken Google OAuth ID token
     * @return Result containing AuthResult with user data and token if successful
     */
    suspend fun loginWithGoogle(idToken: String): Result<AuthResult>

    /**
     * Login or register with DigiLocker.
     *
     * @param authCode DigiLocker authorization code
     * @return Result containing AuthResult with user data and token if successful
     */
    suspend fun loginWithDigiLocker(authCode: String): Result<AuthResult>

    /**
     * Logout the current user.
     *
     * @return Result indicating success or failure
     */
    suspend fun logout(): Result<Boolean>

    /**
     * Get the currently logged-in user.
     *
     * @return Flow of the current user, null if not logged in
     */
    fun getCurrentUser(): Flow<User?>

    /**
     * Check if a user is currently logged in.
     *
     * @return Flow of boolean indicating login status
     */
    fun isLoggedIn(): Flow<Boolean>

    /**
     * Send password reset email.
     *
     * @param email User's email address
     * @return Result indicating success or failure
     */
    suspend fun sendPasswordResetEmail(email: String): Result<Boolean>

    /**
     * Verify user's email address.
     *
     * @param token Verification token
     * @return Result indicating success or failure
     */
    suspend fun verifyEmail(token: String): Result<Boolean>

    /**
     * Update user profile information.
     *
     * @param user Updated user data
     * @return Result containing the updated user if successful
     */
    suspend fun updateUserProfile(user: User): Result<User>
}