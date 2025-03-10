// app/src/main/java/com/harshsinghio/passportseva/domain/model/Annexure.kt
package com.harshsinghio.passportseva.domain.model

/**
 * Represents an annexure or affidavit document
 */
data class Annexure(
    val id: String,
    val title: String,
    val annexureCode: String = "",
    val fileUrl: String,
    val docId: String = ""
)