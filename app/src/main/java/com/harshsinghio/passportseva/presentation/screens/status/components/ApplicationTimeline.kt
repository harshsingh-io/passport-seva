package com.harshsinghio.passportseva.presentation.screens.status.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.harshsinghio.passportseva.presentation.common.theme.Blue600
import com.harshsinghio.passportseva.presentation.screens.status.TimelineEvent

@Composable
fun ApplicationTimeline(
    timelineEvents: List<TimelineEvent>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        timelineEvents.forEachIndexed { index, event ->
            TimelineItem(
                event = event,
                isLast = index == timelineEvents.size - 1
            )
        }
    }
}

@Composable
fun TimelineItem(
    event: TimelineEvent,
    isLast: Boolean
) {
    // Determine colors based on event status
    val dotColor = when {
        event.isCompleted -> Blue600
        event.isPending -> Color.Transparent
        else -> Color.LightGray
    }

    val dotBorderColor = when {
        event.isCompleted -> Blue600
        event.isPending -> Blue600
        else -> Color.LightGray
    }

    val lineColor = if (isLast) Color.Transparent else if (event.isCompleted) Blue600 else Color.LightGray

    val textColor = when {
        event.isCompleted -> MaterialTheme.colorScheme.onSurface
        event.isPending -> MaterialTheme.colorScheme.onSurface
        else -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
    }

    Row(
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        // Timeline dot and line
        Column(
            modifier = Modifier.width(24.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            // Dot
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(dotColor)
                    .border(
                        width = 1.dp,
                        color = dotBorderColor,
                        shape = CircleShape
                    )
            )

            // Line
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f)
                        .background(lineColor)
                )
            }
        }

        // Event details
        Column(
            modifier = Modifier
                .padding(start = 12.dp, bottom = if (isLast) 0.dp else 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Title and date
            Column {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = textColor
                    )
                )

                if (event.date.isNotEmpty()) {
                    Text(
                        text = "${event.date} at ${event.time}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                } else if (event.isPending) {
                    Text(
                        text = "Pending",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }

            // Description
            if (event.description.isNotEmpty()) {
                Text(
                    text = event.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }
    }
}