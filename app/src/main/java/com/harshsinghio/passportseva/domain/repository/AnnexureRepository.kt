// app/src/main/java/com/harshsinghio/passportseva/domain/repository/AnnexureRepository.kt
package com.harshsinghio.passportseva.domain.repository

import com.harshsinghio.passportseva.domain.model.Annexure
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for annexures and affidavits
 */
interface AnnexureRepository {
    /**
     * Get all available annexures and affidavits
     */
    fun getAnnexures(): Flow<List<Annexure>>

    /**
     * Get annexure by ID
     */
    fun getAnnexureById(id: String): Flow<Annexure?>
}