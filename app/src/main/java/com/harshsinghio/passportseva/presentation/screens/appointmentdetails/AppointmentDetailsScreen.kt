package com.harshsinghio.passportseva.presentation.screens.appointmentdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harshsinghio.passportseva.presentation.common.components.ExpandableSection
import com.harshsinghio.passportseva.presentation.common.theme.Blue600

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentDetailsScreen(
    onNavigateBack: () -> Unit,
    viewModel: AppointmentDetailsViewModel = viewModel()
) {
    val uiState by viewModel.uiState
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "APPOINTMENT DETAILS",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue600,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            // Alert Note
            AlertNote()

            // Office Details
            OfficeDetailsSection(uiState.selectedOffice ?: "PSK Bhopal")

            // Appointment Types and Content
            AppointmentTypesSection(
                selectedTab = uiState.selectedTab,
                onTabSelected = viewModel::updateSelectedTab,
                selectedTimeSlot = uiState.selectedTimeSlot,
                onTimeSlotSelected = viewModel::selectTimeSlot
            )

            // Share Button Section
            ShareButtonSection()
        }
    }
}

@Composable
fun AlertNote() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFF8E1))
            .border(
                width = 1.dp,
                color = Color(0xFFFFB74D),
                shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
            )
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(4.dp, height = 100.dp)
                .background(Color(0xFFFF9800))
        )

        Text(
            text = "Note: The number of appointments shown below does not include certain categories of applicants who are allowed to walk-in without appointment. Please refer to the Passport Office Page section on the home page of Passport Seva portal to check such categories.",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF795548)
        )
    }
}

@Composable
fun OfficeDetailsSection(officeName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp)
    ) {
        Text(
            text = officeName,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = "Passport Seva Kendra, 2nd Floor, Office Block, DB City Mall, Arera Hills, Bhopal Madhya Pradesh",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun AppointmentTypesSection(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    selectedTimeSlot: String?,
    onTimeSlotSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        // Tabs
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { onTabSelected(0) },
                text = { Text("Normal") }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { onTabSelected(1) },
                text = { Text("Tatkal") }
            )
            Tab(
                selected = selectedTab == 2,
                onClick = { onTabSelected(2) },
                text = { Text("PCC") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tab Content based on selected tab
        when (selectedTab) {
            0 -> NormalAppointmentContent(selectedTimeSlot, onTimeSlotSelected)
            1 -> TatkalAppointmentContent()
            2 -> PCCAppointmentContent()
        }
    }
}

@Composable
fun NormalAppointmentContent(
    selectedTimeSlot: String?,
    onTimeSlotSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Available Slots Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Header with slot count
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Passport Normal Appointment",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Badge(
                        containerColor = Color(0xFFE8F5E9),
                        contentColor = Color(0xFF2E7D32)
                    ) {
                        Text("250 slots", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                    }
                }

                // Date info
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Available for 18/03/2025",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Time slots grid - Using Column and Row instead of LazyVerticalGrid
                TimeSlotGrid(selectedTimeSlot, onTimeSlotSelected)
            }
        }

        // Appointment Summary (if a time slot is selected)
        if (selectedTimeSlot != null) {
            AppointmentSummary(selectedTimeSlot)
        }

        // Expandable Sections
        ExpandableSection(
            title = "Required Documents",
            icon = Icons.Default.Description,
            initialExpanded = false
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("• Proof of Identity (Aadhar Card/Voter ID/PAN Card)")
                Text("• Proof of Address (Utility Bill/Bank Statement)")
                Text("• Proof of Date of Birth (Birth Certificate/School Certificate)")
                Text("• Recent Passport Size Photographs (4)")
            }
        }

        ExpandableSection(
            title = "Fees & Payment",
            icon = Icons.Default.Description,
            initialExpanded = false
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Application Fee")
                    Text("₹1,500")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Processing Fee")
                    Text("₹500")
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Total",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        "₹2,000",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }

        ExpandableSection(
            title = "Help & Support",
            icon = Icons.Default.Help,
            initialExpanded = false
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("For any assistance, please contact:")
                Text("Helpline: 1800-258-1800")
                Text("Email: help@passportindia.gov.in")
            }
        }
    }
}

@Composable
fun TatkalAppointmentContent() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header with slot count
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Passport Tatkal Appointment",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Medium
                    )
                )

                Badge(
                    containerColor = Color(0xFFFFF8E1),
                    contentColor = Color(0xFFF57F17)
                ) {
                    Text("70 slots", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
            }

            // Date info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Available for 17/03/2025",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Time slots grid - Using fixed Column and Row layout
            StaticTimeSlotGrid()
        }
    }
}

@Composable
fun PCCAppointmentContent() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header with slot count
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PCC Appointment",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Medium
                    )
                )

                Badge(
                    containerColor = Color(0xFFE3F2FD),
                    contentColor = Color(0xFF1565C0)
                ) {
                    Text("20 slots", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
            }

            // Date info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Available for 11/03/2025",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Time slots grid - Using fixed Column and Row layout
            StaticTimeSlotGrid()
        }
    }
}

// Fixed time slot grid using Column and Row (not LazyVerticalGrid)
@Composable
fun TimeSlotGrid(
    selectedTimeSlot: String?,
    onTimeSlotSelected: (String) -> Unit
) {
    val timeSlots = listOf("09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45")

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // First row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (i in 0 until minOf(4, timeSlots.size)) {
                TimeSlotItem(
                    time = timeSlots[i],
                    isSelected = timeSlots[i] == selectedTimeSlot,
                    onClick = { onTimeSlotSelected(timeSlots[i]) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Second row
        if (timeSlots.size > 4) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (i in 4 until minOf(8, timeSlots.size)) {
                    TimeSlotItem(
                        time = timeSlots[i],
                        isSelected = timeSlots[i] == selectedTimeSlot,
                        onClick = { onTimeSlotSelected(timeSlots[i]) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun TimeSlotItem(
    time: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                if (isSelected) Blue600 else MaterialTheme.colorScheme.surface
            )
            .border(
                width = 1.dp,
                color = if (isSelected) Blue600 else MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.bodySmall,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
        )
    }
}

// Non-interactive time slot grid for Tatkal and PCC tabs
@Composable
fun StaticTimeSlotGrid() {
    val timeSlots = listOf("09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45")

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // First row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (i in 0 until minOf(4, timeSlots.size)) {
                StaticTimeSlotItem(
                    time = timeSlots[i],
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Second row
        if (timeSlots.size > 4) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (i in 4 until minOf(8, timeSlots.size)) {
                    StaticTimeSlotItem(
                        time = timeSlots[i],
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun StaticTimeSlotItem(
    time: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun AppointmentSummary(selectedTimeSlot: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
//        border = CardDefaults.outlinedCardBorder().copy(width = 1.dp, color = Blue600.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Appointment Summary",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Medium
                )
            )

            // Location
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Blue600,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(top = 2.dp)
                )

                Column {
                    Text(
                        text = "PSK Bhopal",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(
                        text = "Passport Seva Kendra, 2nd Floor, Office Block, DB City Mall, Arera Hills, Bhopal",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Date
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = Blue600,
                    modifier = Modifier.size(24.dp)
                )

                Column {
                    Text(
                        text = "18 March, 2025",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(
                        text = "Normal Appointment",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Time
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    tint = Blue600,
                    modifier = Modifier.size(24.dp)
                )

                Column {
                    Text(
                        text = "$selectedTimeSlot AM",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(
                        text = "Please arrive 15 minutes early",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Book button
            Button(
                onClick = { /* Book appointment */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Book This Appointment")
            }
        }
    }
}

@Composable
fun ShareButtonSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Button(
            onClick = { /* Share appointment details */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "Share Appointment Details",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}