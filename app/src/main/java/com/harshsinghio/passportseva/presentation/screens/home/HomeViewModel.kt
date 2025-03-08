package com.harshsinghio.passportseva.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.harshsinghio.passportseva.domain.model.QuickAction
import com.harshsinghio.passportseva.domain.model.Service
import com.harshsinghio.passportseva.domain.model.Update

/**
 * ViewModel for the Home screen
 */
class HomeViewModel : ViewModel() {

    private val _activeTab = mutableStateOf("home")
    val activeTab: State<String> = _activeTab

    private val _quickActions = mutableStateOf(createQuickActions())
    val quickActions: State<List<QuickAction>> = _quickActions

    private val _services = mutableStateOf(createServices())
    val services: State<List<Service>> = _services

    private val _updates = mutableStateOf(createUpdates())
    val updates: State<List<Update>> = _updates

    /**
     * Set the active tab
     *
     * @param tab The tab to set as active
     */
    fun setActiveTab(tab: String) {
        _activeTab.value = tab
    }

    /**
     * Handle a click on a menu item
     *
     * @param item The clicked item
     */
    fun handleMenuItemClick(item: String) {
        // In a real app, this would log analytics or perform some action
        // For now, just print the clicked item
        println("Clicked on: $item")
    }

    /**
     * Create the list of quick actions
     *
     * @return List of QuickAction objects
     */
    private fun createQuickActions(): List<QuickAction> {
        return listOf(
            QuickAction("login", "Login", "login"),
            QuickAction("register", "Register", "user_plus"),
            QuickAction("book", "Book", "calendar"),
            QuickAction("track", "Track", "search")
        )
    }

    /**
     * Create the list of services
     *
     * @return List of Service objects
     */
    private fun createServices(): List<Service> {
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
            )
        )
    }

    /**
     * Create the list of updates
     *
     * @return List of Update objects
     */
    private fun createUpdates(): List<Update> {
        return listOf(
            Update(
                id = "1",
                title = "New Online Services",
                description = "Apply for Police Clearance Certificate online",
                isNew = true
            ),
            Update(
                id = "2",
                title = "Holiday Notice",
                description = "All Passport Seva Kendras will be closed on March 25th",
                isNew = false
            )
        )
    }
}