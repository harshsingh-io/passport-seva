package com.harshsinghio.passportseva.presentation.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.harshsinghio.passportseva.domain.model.Address
import com.harshsinghio.passportseva.domain.model.Gender
import com.harshsinghio.passportseva.domain.model.User
import com.harshsinghio.passportseva.domain.model.UserType
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * ViewModel for the Profile screen
 */
class ProfileViewModel : ViewModel() {

    // UI state
    private val _uiState = mutableStateOf(ProfileUiState())
    val uiState: State<ProfileUiState> = _uiState

    // Active section for navigation
    private val _activeSection = mutableStateOf<String?>(null)
    val activeSection: State<String?> = _activeSection

    // Mock user data (in a real app, this would come from a repository)
    val userData: User = User(
        id = "12345",
        name = "John Doe",
        email = "john.doe@example.com",
        mobile = "+91 98765 43210",
        profileImageUrl = "",
        dateOfBirth = LocalDate.of(1990, 1, 15),
        gender = Gender.MALE,
        address = Address(
            street = "123 Main Street",
            city = "New Delhi",
            state = "Delhi",
            pincode = "110001",
            country = "India"
        ),
        createdAt = LocalDateTime.now(),
        lastLogin = LocalDateTime.now(),
        isActive = true,
        isVerified = true,
        userType = UserType.REGULAR
    )

    init {
        // Populate UI state from user data
        _uiState.value = ProfileUiState(
            isLoading = false,
            userName = userData.name,
            userEmail = userData.email,
            userMobile = userData.mobile,
            isVerified = userData.isVerified,
            profileImageUrl = userData.profileImageUrl
        )
    }

    /**
     * Set the active section for navigation
     *
     * @param section The section to navigate to, or null for the main profile screen
     */
    fun setActiveSection(section: String?) {
        _activeSection.value = section
    }

    /**
     * Update user profile information
     *
     * @param updatedUser The updated user data
     */
    fun updateUserProfile(updatedUser: User) {
        // In a real app, this would call a repository method
        // and update the user data in the database

        // Update the UI state
        _uiState.value = _uiState.value.copy(
            userName = updatedUser.name,
            userEmail = updatedUser.email,
            userMobile = updatedUser.mobile
        )

        // Return to main profile screen
        _activeSection.value = null
    }
}

/**
 * UI state for the Profile screen
 */
data class ProfileUiState(
    val isLoading: Boolean = true,
    val userName: String = "",
    val userEmail: String = "",
    val userMobile: String = "",
    val isVerified: Boolean = false,
    val profileImageUrl: String = "",
    val error: String = ""
)