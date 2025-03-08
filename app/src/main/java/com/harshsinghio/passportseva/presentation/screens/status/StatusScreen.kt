package com.harshsinghio.passportseva.presentation.screens.status

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harshsinghio.passportseva.presentation.common.components.TitleAppBar
import com.harshsinghio.passportseva.presentation.screens.status.components.ApplicationTimeline
import com.harshsinghio.passportseva.presentation.screens.status.components.StatusDetails
import com.harshsinghio.passportseva.presentation.screens.status.components.StatusSearchForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusScreen(
    onNavigateBack: () -> Unit,
    viewModel: StatusViewModel = viewModel()
) {
    val uiState by viewModel.uiState
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TitleAppBar(
                title = "Application Status",
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

            // Search form
            StatusSearchForm(
                applicationNumber = uiState.applicationNumber,
                onApplicationNumberChange = viewModel::updateApplicationNumber,
                onSearch = viewModel::searchStatus,
                isLoading = uiState.isLoading
            )

            // Status details and timeline
            if (uiState.showStatus) {
                var selectedTab by remember { mutableStateOf("status") }

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Card header
                        Column {
                            Text(
                                text = "Application Status",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Application Number: ${uiState.applicationNumber}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Tabs
                        TabRow(selectedTabIndex = if (selectedTab == "status") 0 else 1) {
                            Tab(
                                selected = selectedTab == "status",
                                onClick = { selectedTab = "status" },
                                text = { Text("Status") }
                            )
                            Tab(
                                selected = selectedTab == "timeline",
                                onClick = { selectedTab = "timeline" },
                                text = { Text("Timeline") }
                            )
                        }

                        // Tab content
                        when (selectedTab) {
                            "status" -> StatusDetails(
                                status = uiState.status,
                                applicantName = uiState.applicantName,
                                applicationType = uiState.applicationType,
                                appliedDate = uiState.appliedDate,
                                expectedCompletionDate = uiState.expectedCompletionDate
                            )
                            "timeline" -> ApplicationTimeline(
                                timelineEvents = uiState.timelineEvents
                            )
                        }

                        // Contact support button
                        OutlinedButton(
                            onClick = { /* Contact support */ },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Contact Support")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}