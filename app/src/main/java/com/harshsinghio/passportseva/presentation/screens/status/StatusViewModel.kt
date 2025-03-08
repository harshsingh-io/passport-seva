package com.harshsinghio.passportseva.presentation.screens.status

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StatusViewModel : ViewModel() {

    private val _uiState = mutableStateOf(StatusUiState())
    val uiState: State<StatusUiState> = _uiState

    // Update application number
    fun updateApplicationNumber(applicationNumber: String) {
        _uiState.value = _uiState.value.copy(applicationNumber = applicationNumber)
    }

    // Search for application status
    fun searchStatus() {
        if (_uiState.value.applicationNumber.isBlank()) {
            return
        }

        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                // Simulate network delay
                delay(1500)

                // In a real app, this would fetch data from a repository
                // For this example, we'll use mock data
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    showStatus = true,
                    status = "Police Verification in Progress",
                    applicantName = "Rahul Kumar",
                    applicationType = "Fresh Passport",
                    appliedDate = "March 1, 2025",
                    expectedCompletionDate = "March 22, 2025",
                    timelineEvents = createMockTimelineEvents()
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An error occurred while fetching status"
                )
            }
        }
    }

    // Create mock timeline events
    private fun createMockTimelineEvents(): List<TimelineEvent> {
        return listOf(
            TimelineEvent(
                id = "1",
                title = "Application Submitted",
                date = "March 1, 2025",
                time = "10:30 AM",
                description = "Your application has been successfully submitted",
                isCompleted = true
            ),
            TimelineEvent(
                id = "2",
                title = "Document Verification",
                date = "March 5, 2025",
                time = "11:45 AM",
                description = "Your documents have been verified at the Passport Seva Kendra",
                isCompleted = true
            ),
            TimelineEvent(
                id = "3",
                title = "Police Verification Initiated",
                date = "March 8, 2025",
                time = "09:15 AM",
                description = "Police verification process has been initiated",
                isCompleted = true
            ),
            TimelineEvent(
                id = "4",
                title = "Police Verification Completed",
                date = "",
                time = "",
                description = "",
                isCompleted = false,
                isPending = true
            ),
            TimelineEvent(
                id = "5",
                title = "Passport Printing",
                date = "",
                time = "",
                description = "",
                isCompleted = false
            ),
            TimelineEvent(
                id = "6",
                title = "Passport Dispatched",
                date = "",
                time = "",
                description = "",
                isCompleted = false
            )
        )
    }
}

// UI State class
data class StatusUiState(
    val applicationNumber: String = "",
    val isLoading: Boolean = false,
    val showStatus: Boolean = false,
    val error: String = "",
    val status: String = "",
    val applicantName: String = "",
    val applicationType: String = "",
    val appliedDate: String = "",
    val expectedCompletionDate: String = "",
    val timelineEvents: List<TimelineEvent> = emptyList()
)

// Timeline Event class
data class TimelineEvent(
    val id: String,
    val title: String,
    val date: String,
    val time: String,
    val description: String,
    val isCompleted: Boolean,
    val isPending: Boolean = false
)