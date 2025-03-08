package com.harshsinghio.passportseva.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harshsinghio.passportseva.domain.model.QuickAction
import com.harshsinghio.passportseva.presentation.common.components.QuickActionItem

/**
 * Section displaying quick actions in a grid
 *
 * @param quickActions List of quick actions to display
 * @param onQuickActionClick Callback when a quick action is clicked
 * @param modifier Modifier to be applied to the section
 */
@Composable
fun QuickActionsSection(
    quickActions: List<QuickAction>,
    onQuickActionClick: (QuickAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Section title
        Text(
            text = "Quick Actions",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Grid of quick actions (4 items in a row)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            quickActions.forEach { action ->
                QuickActionItem(
                    quickAction = action,
                    onClick = { onQuickActionClick(action) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}