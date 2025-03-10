// app/src/main/java/com/harshsinghio/passportseva/data/repository/AnnexureRepositoryImpl.kt
package com.harshsinghio.passportseva.data.repository

import com.harshsinghio.passportseva.domain.model.Annexure
import com.harshsinghio.passportseva.domain.repository.AnnexureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnnexureRepositoryImpl @Inject constructor() : AnnexureRepository {

    override fun getAnnexures(): Flow<List<Annexure>> = flow {
        // In a real app, this would fetch from a network source or local database
        // For now, we'll use mock data
        emit(getMockAnnexures())
    }

    override fun getAnnexureById(id: String): Flow<Annexure?> = flow {
        emit(getMockAnnexures().find { it.id == id })
    }

    private fun getMockAnnexures(): List<Annexure> {
        return listOf(
            Annexure(
                id = "1",
                title = "Identity Certificate",
                annexureCode = "A",
                fileUrl = "/documents/annexure-a.pdf"
            ),
            Annexure(
                id = "2",
                title = "Declaration of Parent/Guardian for Minor Passports (one parent not given consent)",
                annexureCode = "C",
                fileUrl = "/documents/annexure-c.pdf"
            ),
            Annexure(
                id = "3",
                title = "Declaration of Parent/Guardian for Minor Passports",
                annexureCode = "D",
                fileUrl = "/documents/annexure-d.pdf"
            ),
            Annexure(
                id = "4",
                title = "Specimen Affidavit for a passport in lieu of lost/damaged passport",
                annexureCode = "F",
                fileUrl = "/documents/annexure-f.pdf"
            ),
            Annexure(
                id = "5",
                title = "No Objection Certificate",
                annexureCode = "G",
                fileUrl = "/documents/annexure-g.pdf"
            ),
            Annexure(
                id = "6",
                title = "Prior Intimation Letter",
                annexureCode = "H",
                fileUrl = "/documents/annexure-h.pdf"
            ),
            Annexure(
                id = "7",
                title = "A Declaration affirming the particulars furnished in the application about the minor",
                annexureCode = "I",
                fileUrl = "/documents/annexure-i.pdf"
            ),
            Annexure(
                id = "8",
                title = "Authority letter",
                annexureCode = "",
                fileUrl = "/documents/authority-letter.pdf"
            )
        )
    }
}