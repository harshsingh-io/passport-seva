// app/src/main/java/com/harshsinghio/passportseva/presentation/screens/faq/components/FaqSubcategoryItem.kt
package com.harshsinghio.passportseva.presentation.screens.faq.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import com.harshsinghio.passportseva.domain.model.FaqSubcategory

@Composable
fun FaqSubcategoryItem(
    subcategory: FaqSubcategory,
    isExpanded: Boolean,
    expandedQuestionIds: Set<String>,
    onToggle: () -> Unit,
    onQuestionToggle: (String) -> Unit,
    onQuestionFeedback: (String, Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Subcategory header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onToggle)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = subcategory.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand"
            )
        }

        // Questions (visible when expanded)
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                subcategory.questions.forEach { question ->
                    FaqQuestionItem(
                        question = question,
                        isExpanded = expandedQuestionIds.contains(question.id),
                        onToggle = { onQuestionToggle(question.id) },
                        onFeedback = { isHelpful ->
                            onQuestionFeedback(question.id, isHelpful)
                        }
                    )
                }
            }
        }

        Divider()
    }
}