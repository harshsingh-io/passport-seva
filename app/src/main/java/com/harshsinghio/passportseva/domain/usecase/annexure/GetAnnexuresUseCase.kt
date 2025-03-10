// app/src/main/java/com/harshsinghio/passportseva/domain/usecase/annexure/GetAnnexuresUseCase.kt
package com.harshsinghio.passportseva.domain.usecase.annexure

import com.harshsinghio.passportseva.domain.model.Annexure
import com.harshsinghio.passportseva.domain.repository.AnnexureRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case to get all annexures and affidavits
 */
class GetAnnexuresUseCase @Inject constructor(
    private val annexureRepository: AnnexureRepository
) {
    operator fun invoke(): Flow<List<Annexure>> {
        return annexureRepository.getAnnexures()
    }
}