// app/src/main/java/com/harshsinghio/passportseva/presentation/screens/feecalculator/FeeCalculatorScreen.kt
package com.harshsinghio.passportseva.presentation.screens.feecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harshsinghio.passportseva.presentation.common.components.LoadingIndicator
import com.harshsinghio.passportseva.presentation.common.components.TitleAppBar
import com.harshsinghio.passportseva.presentation.common.theme.Blue100
import com.harshsinghio.passportseva.presentation.common.theme.Blue600

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeeCalculatorScreen(
    onNavigateBack: () -> Unit,
    viewModel: FeeCalculatorViewModel = viewModel()
) {
    val uiState by viewModel.uiState
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val finalFee = viewModel.getFinalFee()
    val isDiscountApplicable = viewModel.shouldApplyDiscount()

    // Load fee data when the screen is first displayed
    LaunchedEffect(key1 = true) {
        viewModel.loadFeeData(context)
    }

    Scaffold(
        topBar = {
            TitleAppBar(
                title = "Fee Calculator",
                onBackClick = onNavigateBack
            )
        }
    ) { paddingValues ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                LoadingIndicator()
            }
        } else if (uiState.error.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: ${uiState.error}")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Fields marked with asterisk (*) are mandatory",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Application Type
                FormCard {
                    CustomDropdownField(
                        label = "Select Application Type*",
                        value = uiState.applicationType,
                        onValueChange = viewModel::updateApplicationType,
                        options = listOf(
                            "passport" to "Passport",
                            "pcc" to "Police Clearance Certificate",
                            "identity_certificate" to "Identity Certificate",
                            "surrender_certificate" to "Surrender Certificate",
                            "background_verification_gep" to "Background Verification (GEP)"
                        )
                    )

                    // Show service type for passport and identity certificate
                    if (uiState.applicationType == "passport" || uiState.applicationType == "identity_certificate") {
                        CustomDropdownField(
                            label = "Type of Service*",
                            value = uiState.serviceType,
                            onValueChange = viewModel::updateServiceType,
                            options = listOf(
                                "fresh" to "Fresh (New Application)",
                                "reissue" to "Reissue (Renewal/Replacement)"
                            )
                        )
                    }

                    // Passport specific fields
                    if (uiState.applicationType == "passport") {
                        // Age input
                        AgeInputField(
                            age = uiState.age,
                            onAgeChange = viewModel::updateAge
                        )

                        // Show pages option for age > 15
                        if (uiState.ageGroup != "less_than_15_years") {
                            CustomDropdownField(
                                label = "Number of pages in Booklet*",
                                value = uiState.pages,
                                onValueChange = viewModel::updatePages,
                                options = listOf(
                                    "36_pages" to "36 Pages",
                                    "60_pages" to "60 Pages"
                                )
                            )
                        }

                        // Show reissue reason for reissue service type
                        if (uiState.serviceType == "reissue") {
                            CustomDropdownField(
                                label = "Reason for Reissue*",
                                value = uiState.reissueReason,
                                onValueChange = viewModel::updateReissueReason,
                                options = listOf(
                                    "validity_expired" to "Validity Expired",
                                    "delete_ecr_change_particulars" to "Delete ECR/Change Particulars",
                                    "exhaustion_of_pages" to "Exhaustion of Pages",
                                    "lost_damaged" to "Lost or Damaged"
                                )
                            )

                            // Show lost status for lost/damaged reissue reason
                            if (uiState.reissueReason == "lost_damaged") {
                                CustomDropdownField(
                                    label = "Was the lost/damaged passport expired?*",
                                    value = uiState.lostStatus,
                                    onValueChange = viewModel::updateLostStatus,
                                    options = listOf(
                                        "expired_yes" to "Yes, it was expired",
                                        "expired_no" to "No, it was valid"
                                    )
                                )
                            }
                        }

                        // Scheme
                        CustomDropdownField(
                            label = "Required Scheme*",
                            value = uiState.scheme,
                            onValueChange = viewModel::updateScheme,
                            options = listOf(
                                "normal" to "Normal",
                                "tatkaal" to "Tatkaal (Urgent)"
                            )
                        )
                    }
                }

                // Calculate Fee Button
                Button(
                    onClick = { /* Already calculated automatically */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue600
                    )
                ) {
                    Text("Calculate Fee")
                }

                // Fee Result
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Fee Details",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        Divider()

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Applicable Fee",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Text(
                                    text = "₹ ${finalFee.toInt()}.00",
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Blue600
                                    )
                                )

                                if (isDiscountApplicable) {
                                    SuggestionChip(
                                        onClick = { },
                                        label = {
                                            Text(
                                                "10% discount applied",
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        },
                                        colors = SuggestionChipDefaults.suggestionChipColors(
                                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                                            labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    )
                                }
                            }

                            // Note section for discount info
                            Surface(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 16.dp),
                                color = Blue100,
                                shape = MaterialTheme.shapes.small
                            ) {
                                Row(
                                    modifier = Modifier.padding(8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Icon(
                                        Icons.Default.Info,
                                        contentDescription = "Note",
                                        tint = Blue600,
                                        modifier = Modifier.size(16.dp)
                                    )

                                    Text(
                                        text = "10% discount for minors under 8 and seniors over 60 on fresh applications only",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Blue600,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }
                    }
                }

                // Additional information card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Payment options section
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = Blue600
                            )

                            Text(
                                text = "Payment Options",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Text(
                            text = "Fees can be paid online through debit/credit cards, net banking, or at PSK through cash/cards.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Blue100,
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "ATM facility available at PSKs for all Bank Cards",
                                style = MaterialTheme.typography.bodySmall,
                                color = Blue600
                            )
                        }

                        // Important notes
                        Divider()

                        Text(
                            text = "Important Information",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            BulletPoint("Fees are subject to change without prior notice")
                            BulletPoint("Additional verification charges may apply in some cases")
                            BulletPoint("All fees are non-refundable once application is submitted")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BulletPoint(text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun FormCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    options: List<Pair<String, String>>
) {
    var expanded by remember { mutableStateOf(false) }
    val displayName = options.find { it.first == value }?.second ?: value

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = displayName,
                onValueChange = { },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                shape = RoundedCornerShape(8.dp)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { (key, displayText) ->
                    DropdownMenuItem(
                        text = { Text(displayText) },
                        onClick = {
                            onValueChange(key)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeInputField(
    age: Int,
    onAgeChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Applicant's Age*",
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = age.toString(),
                onValueChange = { newValue ->
                    if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                        onAgeChange(newValue.toIntOrNull() ?: 0)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(100.dp),
                shape = RoundedCornerShape(8.dp)
            )

            Text("years")

            // Age group indicator
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .background(
                        color = Blue100,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                val ageGroupText = when {
                    age < 15 -> "Below 15 years"
                    age < 18 -> "Between 15-18 years"
                    else -> "18 years & above"
                }

                Text(
                    text = "Age Group: $ageGroupText",
                    style = MaterialTheme.typography.bodySmall,
                    color = Blue600
                )
            }
        }
    }
}