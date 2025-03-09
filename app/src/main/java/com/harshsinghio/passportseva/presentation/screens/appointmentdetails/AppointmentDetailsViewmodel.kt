package com.harshsinghio.passportseva.presentation.screens.appointmentdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppointmentDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    // UI state for the appointment details screen
    private val _uiState = mutableStateOf(AppointmentDetailsUiState())
    val uiState: State<AppointmentDetailsUiState> = _uiState
    
    // Initialize with data from arguments if available
    init {
        // In a real app, we would retrieve the office name from the savedStateHandle
        // or fetch data from a repository
        savedStateHandle.get<String>("officeName")?.let { officeName ->
            _uiState.value = _uiState.value.copy(selectedOffice = officeName)
        }
    }
    
    /**
     * Update the selected tab (Normal, Tatkal, PCC)
     */
    fun updateSelectedTab(tabIndex: Int) {
        _uiState.value = _uiState.value.copy(selectedTab = tabIndex)
    }
    
    /**
     * Select a time slot
     */
    fun selectTimeSlot(timeSlot: String) {
        _uiState.value = _uiState.value.copy(selectedTimeSlot = timeSlot)
    }
    
    /**
     * Book the appointment
     */
    fun bookAppointment() {
        // In a real app, this would call a use case to book the appointment
        _uiState.value = _uiState.value.copy(isLoading = true)
        
        // Simulate API call
        // In a real app, we would use a coroutine to make the API call
        // and update the UI state based on the result
    }
    
    /**
     * Share appointment details
     */
    fun shareAppointmentDetails() {
        // In a real app, this would implement sharing functionality
    }
}

/**
 * UI state for the appointment details screen
 */
data class AppointmentDetailsUiState(
    val selectedOffice: String? = null,
    val selectedTab: Int = 0, // 0: Normal, 1: Tatkal, 2: PCC
    val selectedTimeSlot: String? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)