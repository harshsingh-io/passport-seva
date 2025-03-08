package com.harshsinghio.passportseva.presentation.screens.services

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.harshsinghio.passportseva.domain.model.Service

/**
 * ViewModel for the Services screen
 */
class ServicesViewModel : ViewModel() {

    // UI state
    private val _uiState = mutableStateOf(ServicesUiState())
    val uiState: State<ServicesUiState> = _uiState

    // Services list (mock data for now)
    private val _services = mutableStateOf<List<Service>>(createMockServices())
    val services: State<List<Service>> = _services

    /**
     * Load services from the repository
     */
    fun loadServices() {
        // In a real app, this would use the use case to fetch services
        // For demonstration, we're using mock data
    }

    /**
     * Handle service click events
     *
     * @param service The selected service
     */
    fun onServiceSelected(service: Service) {
        _uiState.value = _uiState.value.copy(
            selectedServiceId = service.id
        )
    }

    /**
     * Create mock services (temporary, would be replaced with actual data)
     */
    private fun createMockServices(): List<Service> {
        return listOf(
            Service(
                id = "appointment",
                title = "Appointment Availability",
                description = "Check and book available appointment slots",
                iconType = "calendar"
            ),
            Service(
                id = "document",
                title = "Document Advisor",
                description = "Get guidance on required documents",
                iconType = "file_text"
            ),
            Service(
                id = "fee",
                title = "Fee Calculator",
                description = "Calculate fees for passport services",
                iconType = "credit_card"
            ),
            Service(
                id = "locate",
                title = "Locate Centre",
                description = "Find nearest Passport Seva Kendra",
                iconType = "map_pin"
            ),
            Service(
                id = "annexures",
                title = "Annexures/Affidavits",
                description = "Download required forms and documents",
                iconType = "file_text"
            ),
            Service(
                id = "grievance",
                title = "Grievance/Feedback",
                description = "Submit your concerns or suggestions",
                iconType = "message_square"
            ),
            Service(
                id = "faq",
                title = "FAQ",
                description = "Find answers to common questions",
                iconType = "help_circle"
            )
        )
    }
}

/**
 * UI state for the Services screen
 */
data class ServicesUiState(
    val isLoading: Boolean = false,
    val selectedServiceId: String = "",
    val error: String = ""
)