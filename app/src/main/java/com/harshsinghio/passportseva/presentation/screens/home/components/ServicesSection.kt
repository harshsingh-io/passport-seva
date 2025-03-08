package com.harshsinghio.passportseva.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harshsinghio.passportseva.domain.model.Service
import com.harshsinghio.passportseva.presentation.common.components.ServiceItem
import com.harshsinghio.passportseva.presentation.common.theme.Blue600

/**
 * Section displaying services in a vertical list
 *
 * @param services List of services to display
 * @param onServiceClick Callback when a service is clicked
 * @param onViewAllClick Callback when the "View All" button is clicked
 * @param modifier Modifier to be applied to the section
 */
@Composable
fun ServicesSection(
    services: List<Service>,
    onServiceClick: (Service) -> Unit,
    onViewAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Header with title and "View All" button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Services",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            )
            TextButton(onClick = onViewAllClick) {
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Blue600
                    )
                )
            }
        }

        // List of services
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            services.forEach { service ->
                ServiceItem(
                    service = service,
                    onClick = { onServiceClick(service) }
                )
            }
        }
    }
}