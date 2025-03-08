package com.harshsinghio.passportseva.presentation.screens.appointment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.harshsinghio.passportseva.domain.model.Location
import com.harshsinghio.passportseva.domain.model.TimeSlot
import com.harshsinghio.passportseva.presentation.common.util.DateTimeUtil

class AppointmentViewModel : ViewModel() {

    // UI state
    private val _uiState = mutableStateOf(AppointmentUiState())
    val uiState: State<AppointmentUiState> = _uiState

    // Available locations (in a real app, this would be fetched from a repository)
    val availableLocations = listOf(
        Location(
            id = "1",
            name = "Passport Seva Kendra, Delhi",
            address = "Bhikaji Cama Place, New Delhi",
            distance = "5.2 km"
        ),
        Location(
            id = "2",
            name = "Passport Seva Kendra, Gurgaon",
            address = "Sector 15, Gurgaon",
            distance = "12.8 km"
        ),
        Location(
            id = "3",
            name = "Post Office Passport Seva Kendra",
            address = "Janakpuri, New Delhi",
            distance = "8.5 km"
        )
    )

    // Available dates (in a real app, this would be fetched based on the selected location)
    val availableDates = listOf(
        AvailableDateItem("2025-03-10", true),
        AvailableDateItem("2025-03-11", true),
        AvailableDateItem("2025-03-12", true),
        AvailableDateItem("2025-03-13", false),
        AvailableDateItem("2025-03-14", true),
        AvailableDateItem("2025-03-15", false),
        AvailableDateItem("2025-03-16", false),
        AvailableDateItem("2025-03-17", true),
        AvailableDateItem("2025-03-18", true)
    )

    // Available time slots (in a real app, this would be fetched based on the selected date)
    val availableTimeSlots = listOf(
        TimeSlot(id = "1", time = "09:00 AM", isAvailable = true),
        TimeSlot(id = "2", time = "09:30 AM", isAvailable = true),
        TimeSlot(id = "3", time = "10:00 AM", isAvailable = true),
        TimeSlot(id = "4", time = "10:30 AM", isAvailable = false),
        TimeSlot(id = "5", time = "11:00 AM", isAvailable = true),
        TimeSlot(id = "6", time = "11:30 AM", isAvailable = true),
        TimeSlot(id = "7", time = "12:00 PM", isAvailable = false),
        TimeSlot(id = "8", time = "12:30 PM", isAvailable = false),
        TimeSlot(id = "9", time = "02:00 PM", isAvailable = true),
        TimeSlot(id = "10", time = "02:30 PM", isAvailable = true),
        TimeSlot(id = "11", time = "03:00 PM", isAvailable = true),
        TimeSlot(id = "12", time = "03:30 PM", isAvailable = true)
    )

    // Select location
    fun selectLocation(location: Location) {
        _uiState.value = _uiState.value.copy(
            selectedLocation = location,
            isAppointmentValid = validateAppointment(
                location,
                _uiState.value.selectedDate,
                _uiState.value.selectedTimeSlot
            )
        )
    }

    // Select date
    fun selectDate(date: String) {
        _uiState.value = _uiState.value.copy(
            selectedDate = date,
            isAppointmentValid = validateAppointment(
                _uiState.value.selectedLocation,
                date,
                _uiState.value.selectedTimeSlot
            )
        )
    }

    // Select time slot
    fun selectTimeSlot(timeSlot: TimeSlot) {
        _uiState.value = _uiState.value.copy(
            selectedTimeSlot = timeSlot,
            isAppointmentValid = validateAppointment(
                _uiState.value.selectedLocation,
                _uiState.value.selectedDate,
                timeSlot
            )
        )
    }

    // Show confirmation dialog
    fun showConfirmationDialog() {
        _uiState.value = _uiState.value.copy(showConfirmationDialog = true)
    }

    // Dismiss confirmation dialog
    fun dismissConfirmationDialog() {
        _uiState.value = _uiState.value.copy(showConfirmationDialog = false)
    }

    // Confirm appointment
    fun confirmAppointment() {
        // In a real app, this would save the appointment to a repository
        _uiState.value = _uiState.value.copy(
            isAppointmentConfirmed = true,
            showConfirmationDialog = false
        )

        // Additional logic could be added here, such as navigating to a confirmation screen
    }

    // Validate appointment
    private fun validateAppointment(
        location: Location?,
        date: String,
        timeSlot: TimeSlot?
    ): Boolean {
        return location != null && date.isNotEmpty() && timeSlot != null
    }
}

// UI State class
data class AppointmentUiState(
    val selectedLocation: Location? = null,
    val selectedDate: String = "",
    val selectedTimeSlot: TimeSlot? = null,
    val isAppointmentValid: Boolean = false,
    val isAppointmentConfirmed: Boolean = false,
    val showConfirmationDialog: Boolean = false
)

// Helper class for available dates
data class AvailableDateItem(
    val date: String,
    val isAvailable: Boolean
) {
    val day: String
        get() = DateTimeUtil.getDayOfWeek(date)

    val dayNumber: String
        get() = DateTimeUtil.getDayNumber(date)
}