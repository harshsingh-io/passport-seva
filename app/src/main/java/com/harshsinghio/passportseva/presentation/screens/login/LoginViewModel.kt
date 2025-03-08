package com.harshsinghio.passportseva.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    // UI state for form fields
    private val _uiState = mutableStateOf(LoginUiState())
    val uiState: State<LoginUiState> = _uiState

    // Authentication state
    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    // Update UI state functions
    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun updateMobile(mobile: String) {
        _uiState.value = _uiState.value.copy(mobile = mobile)
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(showPassword = !_uiState.value.showPassword)
    }

    // Login function
    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                _loginState.value = _loginState.value.copy(isLoading = true)

                // Simulate network delay
                delay(1500)

                // Simple validation
                if (_uiState.value.email.isBlank() || _uiState.value.password.isBlank()) {
                    _loginState.value = _loginState.value.copy(
                        error = "Email and password cannot be empty",
                        isLoading = false
                    )
                    return@launch
                }

                // In a real app, this would call the authentication repository
                // For this example, we'll just simulate a successful login
                _loginState.value = _loginState.value.copy(
                    isLoading = false,
                    isLoggedIn = true
                )

                onSuccess()

            } catch (e: Exception) {
                _loginState.value = _loginState.value.copy(
                    error = e.message ?: "An error occurred during login",
                    isLoading = false
                )
            }
        }
    }

    // Register function
    fun register(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                _loginState.value = _loginState.value.copy(isLoading = true)

                // Simulate network delay
                delay(1500)

                // Simple validation
                if (_uiState.value.name.isBlank() || _uiState.value.email.isBlank() ||
                    _uiState.value.mobile.isBlank() || _uiState.value.password.isBlank()) {
                    _loginState.value = _loginState.value.copy(
                        error = "All fields are required",
                        isLoading = false
                    )
                    return@launch
                }

                // In a real app, this would call the authentication repository
                // For this example, we'll just simulate a successful registration
                _loginState.value = _loginState.value.copy(
                    isLoading = false,
                    isRegistered = true
                )

                onSuccess()

            } catch (e: Exception) {
                _loginState.value = _loginState.value.copy(
                    error = e.message ?: "An error occurred during registration",
                    isLoading = false
                )
            }
        }
    }

    // Social login functions
    fun loginWithGoogle() {
        _loginState.value = _loginState.value.copy(
            error = "Google login is not implemented in this demo"
        )
    }

    fun loginWithDigiLocker() {
        _loginState.value = _loginState.value.copy(
            error = "DigiLocker login is not implemented in this demo"
        )
    }

    // Clear error
    fun clearError() {
        _loginState.value = _loginState.value.copy(error = "")
    }
}

// UI State class
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val mobile: String = "",
    val showPassword: Boolean = false
)

// Login State class
data class LoginState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isRegistered: Boolean = false,
    val error: String = ""
)