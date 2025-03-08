package com.harshsinghio.passportseva.presentation.screens.status.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.harshsinghio.passportseva.presentation.common.theme.Blue100
import com.harshsinghio.passportseva.presentation.common.theme.Blue600

@Composable
fun StatusDetails(
    status: String,
    applicantName: String,
    applicationType: String,
    appliedDate: String,
    expectedCompletionDate: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Current status
        StatusCard(status)

        // Application details
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DetailItem(
                icon = Icons.Default.Description,
                label = "Application Type",
                value = applicationType
            )

            Divider()

            DetailItem(
                icon = Icons.Default.Description,
                label = "Applicant Name",
                value = applicantName
            )

            Divider()

            DetailItem(
                icon = Icons.Default.Description,
                label = "Applied On",
                value = appliedDate
            )

            Divider()

            DetailItem(
                icon = Icons.Default.Description,
                label = "Expected Completion",
                value = expectedCompletionDate
            )
        }
    }
}

@Composable
fun StatusCard(status: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(Blue100)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Blue600.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = null,
                tint = Blue600
            )
        }

        Column {
            Text(
                text = "Current Status",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = status,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Blue600,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun DetailItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Medium
                )
            )
        }

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall
        )
    }
}