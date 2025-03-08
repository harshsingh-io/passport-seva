package com.harshsinghio.passportseva.domain.usecase.auth

import com.harshsinghio.passportseva.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Use case to logout the current user.
 */
class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Logout the current user.
     *
     * @return Result indicating success or failure
     */
    suspend operator fun invoke(): Result<Boolean> {
        return authRepository.logout()
    }
}