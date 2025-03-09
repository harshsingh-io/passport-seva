package com.harshsinghio.passportseva.presentation.screens.appointment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.harshsinghio.passportseva.presentation.common.components.TitleAppBar
import com.harshsinghio.passportseva.presentation.navigation.Screen
import com.harshsinghio.passportseva.presentation.screens.appointment.components.DatePicker
import com.harshsinghio.passportseva.presentation.screens.appointment.components.LocationItem
import com.harshsinghio.passportseva.presentation.screens.appointment.components.TimeSlotPicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(
    onNavigateBack: () -> Unit,
    onAppointmentBooked: () -> Unit,
    navController: NavController,
    viewModel: AppointmentViewModel = viewModel()
) {
    val uiState by viewModel.uiState
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TitleAppBar(
                title = "Appointment Booking",
                onBackClick = onNavigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            // Location Selection
            LocationSelectionSection(viewModel, navController)

            // Date and Time Selection
            DateTimeSelectionSection(viewModel)

            // Appointment Summary
            AppointmentSummarySection(
                viewModel = viewModel,
                onAppointmentBooked = onAppointmentBooked
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // Show confirmation dialog if needed
    if (uiState.showConfirmationDialog) {
        AlertDialog(
            onDismissRequest = viewModel::dismissConfirmationDialog,
            title = { Text("Confirm Appointment") },
            text = { Text("Are you sure you want to book this appointment?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.confirmAppointment()
                        onAppointmentBooked()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = viewModel::dismissConfirmationDialog) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun LocationSelectionSection(viewModel: AppointmentViewModel, navController: NavController) {
    val uiState by viewModel.uiState
    val locations = viewModel.availableLocations

    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Select Passport Office",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Choose a Passport Seva Kendra near you",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Location dropdown (could be implemented, but for simplicity, just showing the list)

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                locations.forEach { location ->
                    LocationItem(
                        location = location,
                        isSelected = uiState.selectedLocation?.id == location.id,
                        onClick = {
                            viewModel.selectLocation(location)
                            navController.navigate(Screen.AppointmentDetails.route)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DateTimeSelectionSection(viewModel: AppointmentViewModel) {
    val uiState by viewModel.uiState

    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Select Date & Time",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Choose your preferred appointment slot",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var selectedTab by remember { mutableStateOf("date") }
            TabRow(selectedTabIndex = if (selectedTab == "date") 0 else 1) {
                Tab(
                    selected = selectedTab == "date",
                    onClick = { selectedTab = "date" },
                    text = { Text("Date") }
                )
                Tab(
                    selected = selectedTab == "time",
                    onClick = { selectedTab = "time" },
                    text = { Text("Time") }
                )
            }

            when (selectedTab) {
                "date" -> DatePicker(
                    dates = viewModel.availableDates,
                    selectedDate = uiState.selectedDate,
                    onDateSelected = viewModel::selectDate,
                    onNextClick = { selectedTab = "time" }
                )
                "time" -> TimeSlotPicker(
                    selectedDate = uiState.selectedDate,
                    timeSlots = viewModel.availableTimeSlots,
                    selectedTimeSlot = uiState.selectedTimeSlot,
                    onTimeSlotSelected = viewModel::selectTimeSlot
                )
            }
        }
    }
}

@Composable
private fun AppointmentSummarySection(
    viewModel: AppointmentViewModel,
    onAppointmentBooked: () -> Unit
) {
    val uiState by viewModel.uiState

    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Appointment Summary",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Review your appointment details",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Location
            uiState.selectedLocation?.let { location ->
                SummaryItem(
                    title = "Location",
                    value = location.name,
                    details = location.address
                )
            }

            // Date & Time
            if (uiState.selectedDate.isNotEmpty() || uiState.selectedTimeSlot != null) {
                SummaryItem(
                    title = "Date & Time",
                    value = if (uiState.selectedDate.isNotEmpty())
                        com.harshsinghio.passportseva.presentation.common.util.DateTimeUtil.formatToFullDate(uiState.selectedDate)
                    else
                        "Not selected",
                    details = uiState.selectedTimeSlot?.time ?: "Not selected"
                )
            }

            Button(
                onClick = { viewModel.showConfirmationDialog() },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.isAppointmentValid
            ) {
                Text("Confirm Appointment")
            }
        }
    }
}

@Composable
private fun SummaryItem(
    title: String,
    value: String,
    details: String = ""
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
        if (details.isNotEmpty()) {
            Text(
                text = details,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}