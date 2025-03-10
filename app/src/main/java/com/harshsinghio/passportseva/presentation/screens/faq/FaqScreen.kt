// app/src/main/java/com/harshsinghio/passportseva/presentation/screens/faq/FaqScreen.kt
package com.harshsinghio.passportseva.presentation.screens.faq

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harshsinghio.passportseva.domain.model.FaqCategory
import com.harshsinghio.passportseva.presentation.common.components.SearchBar
import com.harshsinghio.passportseva.presentation.screens.faq.components.FaqCategoryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqScreen(
    onNavigateBack: () -> Unit,
    viewModel: FaqViewModel = viewModel()
) {
    val uiState by viewModel.uiState

    // Filter categories based on search query
    val filteredCategories = remember(uiState.faqCategories, uiState.searchQuery) {
        if (uiState.searchQuery.isBlank()) {
            uiState.faqCategories
        } else {
            // Filter logic to find matching questions
            uiState.faqCategories.mapNotNull { category ->
                // Find matching subcategories
                val matchingSubcategories = category.subcategories.mapNotNull { subcategory ->
                    // Find matching questions
                    val matchingQuestions = subcategory.questions.filter { question ->
                        question.title.contains(uiState.searchQuery, ignoreCase = true) ||
                                question.answer.contains(uiState.searchQuery, ignoreCase = true)
                    }

                    if (matchingQuestions.isNotEmpty()) {
                        subcategory.copy(questions = matchingQuestions)
                    } else if (subcategory.title.contains(uiState.searchQuery, ignoreCase = true)) {
                        subcategory
                    } else {
                        null
                    }
                }

                if (matchingSubcategories.isNotEmpty()) {
                    category.copy(subcategories = matchingSubcategories)
                } else if (category.title.contains(uiState.searchQuery, ignoreCase = true)) {
                    category
                } else {
                    null
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Frequently Asked Questions") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search bar
            SearchBar(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = "Search FAQs...",
                initialValue = uiState.searchQuery,
                onValueChange = viewModel::updateSearchQuery
            )

            if (uiState.isLoading) {
                // Loading indicator
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (filteredCategories.isEmpty()) {
                // No results found
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No results found",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Try different keywords or browse all categories",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        Button(
                            onClick = { viewModel.updateSearchQuery("") },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text("Clear Search")
                        }
                    }
                }
            } else {
                // FAQ categories
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(filteredCategories) { category ->
                        FaqCategoryItem(
                            category = category,
                            isExpanded = uiState.expandedCategoryIds.contains(category.id),
                            expandedSubcategoryIds = uiState.expandedSubcategoryIds,
                            expandedQuestionIds = uiState.expandedQuestionIds,
                            onToggle = { viewModel.toggleCategory(category.id) },
                            onSubcategoryToggle = { viewModel.toggleSubcategory(it) },
                            onQuestionToggle = { viewModel.toggleQuestion(it) },
                            onQuestionFeedback = { questionId, isHelpful ->
                                viewModel.submitFeedback(questionId, isHelpful)
                            }
                        )
                    }
                }
            }
        }
    }
}