package com.harshsinghio.passportseva.presentation.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * ViewModel for the Profile screen
 */
class ProfileViewModel : ViewModel() {

    // UI state
    private val _uiState = mutableStateOf(ProfileUiState())
    val uiState: State<ProfileUiState> = _uiState

    init {
        // In a real app, load the user profile from a repository
        _uiState.value = ProfileUiState(
            isLoading = false,
            userName = "John Doe",
            userEmail = "john.doe@example.com"
        )
    }

    /**
     * Handle settings item click
     *
     * @param item The settings item that was clicked
     */
    fun onSettingsItemClick(item: String) {
        // In a real app, navigate or perform action based on the clicked item
        println("Clicked on settings item: $item")
    }

    /**
     * Handle logout
     */
    fun logout() {
        // In a real app, this would clear user session and navigate to login
        println("User logged out")
    }
}

/**
 * UI state for the Profile screen
 */
data class ProfileUiState(
    val isLoading: Boolean = true,
    val userName: String = "",
    val userEmail: String = "",
    val error: String = ""
)