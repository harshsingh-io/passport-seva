package com.harshsinghio.passportseva.presentation.screens.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _uiState = mutableStateOf(RegisterUiState())
    val uiState: State<RegisterUiState> = _uiState

    // Form update functions
    fun updateGivenName(name: String) {
        _uiState.value = _uiState.value.copy(givenName = name)
    }

    fun updateSurname(surname: String) {
        _uiState.value = _uiState.value.copy(surname = surname)
    }

    fun updateDateOfBirth(dob: String) {
        _uiState.value = _uiState.value.copy(dateOfBirth = dob)
    }

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)

        // If login ID is set to be same as email, update it too
        if (_uiState.value.loginIdSameAsEmail) {
            _uiState.value = _uiState.value.copy(loginId = email)
        }
    }

    fun updateLoginIdSameAsEmail(isSame: Boolean) {
        _uiState.value = _uiState.value.copy(
            loginIdSameAsEmail = isSame,
            loginId = if (isSame) _uiState.value.email else _uiState.value.loginId
        )
    }

    fun updateLoginId(loginId: String) {
        _uiState.value = _uiState.value.copy(loginId = loginId)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = confirmPassword)
    }

    fun updateHintQuestion(question: String) {
        _uiState.value = _uiState.value.copy(hintQuestion = question)
    }

    fun updateHintAnswer(answer: String) {
        _uiState.value = _uiState.value.copy(hintAnswer = answer)
    }

    fun updateCaptcha(captcha: String) {
        _uiState.value = _uiState.value.copy(captcha = captcha)
    }

    fun updatePassportOffice(office: String) {
        _uiState.value = _uiState.value.copy(passportOffice = office)
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(showPassword = !_uiState.value.showPassword)
    }

    fun toggleConfirmPasswordVisibility() {
        _uiState.value = _uiState.value.copy(showConfirmPassword = !_uiState.value.showConfirmPassword)
    }

    fun toggleHintAnswerVisibility() {
        _uiState.value = _uiState.value.copy(showHintAnswer = !_uiState.value.showHintAnswer)
    }

    // Check login ID availability
    fun checkLoginIdAvailability() {
        viewModelScope.launch {
            if (_uiState.value.loginId.isBlank()) {
                _uiState.value = _uiState.value.copy(
                    error = "Login ID cannot be empty"
                )
                return@launch
            }

            _uiState.value = _uiState.value.copy(isCheckingAvailability = true)

            // Simulate network request
            delay(1000)

            // Simulate available login ID
            val isAvailable = !_uiState.value.loginId.contains("taken")

            _uiState.value = _uiState.value.copy(
                isLoginIdAvailable = isAvailable,
                isCheckingAvailability = false,
                loginIdChecked = true,
                error = if (!isAvailable) "This login ID is already taken" else ""
            )
        }
    }

    // Register user
    fun register(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                // Validate form
                val validationError = validateForm()
                if (validationError.isNotEmpty()) {
                    _uiState.value = _uiState.value.copy(error = validationError)
                    return@launch
                }

                _uiState.value = _uiState.value.copy(isLoading = true)

                // Simulate registration process
                delay(2000)

                // Registration successful
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRegistered = true
                )

                onSuccess()

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    // Validate form data
    private fun validateForm(): String {
        with(_uiState.value) {
            if (passportOffice.isBlank()) return "Please select a passport office"
            if (givenName.isBlank()) return "Given name is required"
            if (surname.isBlank()) return "Surname is required"
            if (dateOfBirth.isBlank()) return "Date of birth is required"
            if (email.isBlank()) return "Email is required"
            if (!email.contains("@")) return "Please enter a valid email address"
            if (loginId.isBlank()) return "Login ID is required"
            if (password.isBlank()) return "Password is required"
            if (password.length < 8) return "Password must be at least 8 characters long"
            if (password != confirmPassword) return "Passwords do not match"
            if (hintQuestion.isBlank()) return "Please select a hint question"
            if (hintAnswer.isBlank()) return "Hint answer is required"
            if (captcha.isBlank()) return "Please enter the captcha"
            if (!loginIdChecked || !isLoginIdAvailable) return "Please check login ID availability first"

            return ""
        }
    }

    // Clear form
    fun clearForm() {
        _uiState.value = RegisterUiState()
    }

    // Clear error message
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = "")
    }
}

// UI State for Registration
data class RegisterUiState(
    val passportOffice: String = "",
    val givenName: String = "",
    val surname: String = "",
    val dateOfBirth: String = "",
    val email: String = "",
    val loginIdSameAsEmail: Boolean = false,
    val loginId: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val hintQuestion: String = "",
    val hintAnswer: String = "",
    val captcha: String = "",

    // UI Control states
    val showPassword: Boolean = false,
    val showConfirmPassword: Boolean = false,
    val showHintAnswer: Boolean = false,

    // Login ID availability check
    val isCheckingAvailability: Boolean = false,
    val isLoginIdAvailable: Boolean = false,
    val loginIdChecked: Boolean = false,

    // Form submission
    val isLoading: Boolean = false,
    val isRegistered: Boolean = false,
    val error: String = ""
)