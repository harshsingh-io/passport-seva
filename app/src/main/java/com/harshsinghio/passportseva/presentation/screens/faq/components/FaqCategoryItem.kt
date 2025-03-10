// app/src/main/java/com/harshsinghio/passportseva/presentation/screens/faq/components/FaqCategoryItem.kt
package com.harshsinghio.passportseva.presentation.screens.faq.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.harshsinghio.passportseva.domain.model.FaqCategory
import com.harshsinghio.passportseva.presentation.common.theme.Blue100

@Composable
fun FaqCategoryItem(
    category: FaqCategory,
    isExpanded: Boolean,
    expandedSubcategoryIds: Set<String>,
    expandedQuestionIds: Set<String>,
    onToggle: () -> Unit,
    onSubcategoryToggle: (String) -> Unit,
    onQuestionToggle: (String) -> Unit,
    onQuestionFeedback: (String, Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Category header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onToggle)
                    .background(if (isExpanded) Blue100 else MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand"
                )
            }

            // Subcategories (visible when expanded)
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column {
                    category.subcategories.forEach { subcategory ->
                        FaqSubcategoryItem(
                            subcategory = subcategory,
                            isExpanded = expandedSubcategoryIds.contains(subcategory.id),
                            expandedQuestionIds = expandedQuestionIds,
                            onToggle = { onSubcategoryToggle(subcategory.id) },
                            onQuestionToggle = onQuestionToggle,
                            onQuestionFeedback = onQuestionFeedback
                        )
                    }
                }
            }
        }
    }
}