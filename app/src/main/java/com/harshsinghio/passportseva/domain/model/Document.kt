package com.harshsinghio.passportseva.domain.model

/**
 * Represents a document required for passport services.
 *
 * @property id Unique identifier for the document
 * @property title Display title of the document
 * @property description Short description of the document
 * @property category Category of the document
 * @property isRequired Whether the document is mandatory
 * @property notes Additional notes about the document
 */
data class Document(
    val id: String,
    val title: String,
    val description: String = "",
    val category: String = "",
    val isRequired: Boolean = true,
    val notes: String = ""
)

/**
 * Represents a document category with its associated documents.
 *
 * @property id Unique identifier for the category
 * @property title Display title of the category
 * @property description Short description of the category
 * @property documents List of documents in this category
 */
data class DocumentCategory(
    val id: String,
    val title: String,
    val description: String = "",
    val documents: List<Document> = emptyList()
)