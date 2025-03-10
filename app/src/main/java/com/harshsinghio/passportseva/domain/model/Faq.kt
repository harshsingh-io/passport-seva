// app/src/main/java/com/harshsinghio/passportseva/domain/model/Faq.kt
package com.harshsinghio.passportseva.domain.model

/**
 * Represents a FAQ category with its subcategories
 */
data class FaqCategory(
    val id: String,
    val title: String,
    val subcategories: List<FaqSubcategory> = emptyList()
)

/**
 * Represents a FAQ subcategory with its questions
 */
data class FaqSubcategory(
    val id: String,
    val title: String,
    val questions: List<FaqQuestion> = emptyList()
)

/**
 * Represents a single FAQ question and answer
 */
data class FaqQuestion(
    val id: String,
    val title: String,
    val answer: String = ""
)