// presentation/screens/documentadvisor/DocumentAdvisorViewModel.kt
package com.harshsinghio.passportseva.presentation.screens.documentadvisor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.harshsinghio.passportseva.domain.model.Document
import com.harshsinghio.passportseva.domain.model.DocumentCategory

class DocumentAdvisorViewModel : ViewModel() {

    private val _uiState = mutableStateOf(DocumentAdvisorUiState())
    val uiState: State<DocumentAdvisorUiState> = _uiState

    init {
        // Initialize with sample document categories
        _uiState.value = DocumentAdvisorUiState(
            documentCategories = createSampleDocumentCategories(),
            isLoading = false
        )
    }

    fun selectCategory(categoryId: String) {
        _uiState.value = _uiState.value.copy(
            selectedCategoryId = categoryId
        )
    }

    fun expandDocument(documentId: String) {
        val currentExpanded = _uiState.value.expandedDocumentIds.toMutableSet()
        if (currentExpanded.contains(documentId)) {
            currentExpanded.remove(documentId)
        } else {
            currentExpanded.add(documentId)
        }
        _uiState.value = _uiState.value.copy(
            expandedDocumentIds = currentExpanded
        )
    }

    private fun createSampleDocumentCategories(): List<DocumentCategory> {
        return listOf(
            DocumentCategory(
                id = "fresh_passport",
                title = "Document Required for Fresh Passport",
                description = "Documents needed when applying for a new passport",
                documents = listOf(
                    Document(
                        id = "id_proof",
                        title = "Proof of Identity",
                        description = "Any one of the following documents",
                        notes = "• Aadhaar Card\n• PAN Card\n• Voter ID\n• Driving License\n• Government ID Card"
                    ),
                    Document(
                        id = "address_proof",
                        title = "Proof of Address",
                        description = "Any one of the following documents",
                        notes = "• Aadhaar Card\n• Utility Bill (not older than 3 months)\n• Bank Statement\n• Rent Agreement\n• Government ID Card"
                    ),
                    Document(
                        id = "birth_proof",
                        title = "Proof of Date of Birth",
                        description = "Any one of the following documents",
                        notes = "• Birth Certificate\n• School Leaving Certificate\n• Aadhaar Card\n• PAN Card"
                    ),
                    Document(
                        id = "photos",
                        title = "Passport Sized Photographs",
                        description = "Recent color photographs",
                        notes = "• 4.5 cm x 3.5 cm size\n• White background\n• Without spectacles\n• 4 photographs required"
                    )
                )
            ),
            DocumentCategory(
                id = "reissue_passport",
                title = "Document Required for Re-issue of Passport",
                description = "Documents needed when applying for passport renewal",
                documents = listOf(
                    Document(
                        id = "old_passport",
                        title = "Original Old Passport",
                        description = "Original passport document",
                        notes = "• Must not be damaged\n• Must provide original document"
                    ),
                    Document(
                        id = "self_attested_copies",
                        title = "Self-attested Copies",
                        description = "Copies of first and last page",
                        notes = "• First page with photograph\n• Last page with address\n• Self-attested with signature"
                    ),
                    Document(
                        id = "address_proof_reissue",
                        title = "Proof of Address",
                        description = "Only if address has changed",
                        notes = "• Aadhaar Card\n• Utility Bill (not older than 3 months)\n• Bank Statement\n• Rent Agreement"
                    ),
                    Document(
                        id = "photos_reissue",
                        title = "Passport Sized Photographs",
                        description = "Recent color photographs",
                        notes = "• 4.5 cm x 3.5 cm size\n• White background\n• Without spectacles\n• 2 photographs required"
                    )
                )
            ),
            DocumentCategory(
                id = "pcc_services",
                title = "Document Required for PCC and Miscl. services",
                description = "Documents needed for Police Clearance Certificate and other services",
                documents = listOf(
                    Document(
                        id = "passport_pcc",
                        title = "Original Valid Passport",
                        description = "Must be currently valid",
                        notes = "• Original passport document\n• Must not be expired"
                    ),
                    Document(
                        id = "address_proof_pcc",
                        title = "Proof of Current Residence",
                        description = "Current address verification",
                        notes = "• Utility Bill (not older than 3 months)\n• Bank Statement\n• Rent Agreement\n• Letter from employer"
                    ),
                    Document(
                        id = "purpose_letter",
                        title = "Purpose Letter",
                        description = "Letter stating purpose of PCC",
                        notes = "• Mention country name\n• Reason for requirement\n• Official letter from employer/university if applicable"
                    )
                )
            ),
            DocumentCategory(
                id = "diplomatic_passport",
                title = "Documents Required for Diplomatic/Official Passport",
                description = "Documents needed for diplomatic or official passport",
                documents = listOf(
                    Document(
                        id = "note_verbale",
                        title = "Note Verbale from the Ministry",
                        description = "Official letter from the ministry",
                        notes = "• Original letter with official seal\n• Signed by authorized signatory"
                    ),
                    Document(
                        id = "authorization_letter",
                        title = "Authorization Letter",
                        description = "Letter from the department",
                        notes = "• Letter authorizing for diplomatic passport\n• Details of official duty/posting"
                    ),
                    Document(
                        id = "identity_card",
                        title = "Identity Card of the Applicant",
                        description = "Official government ID",
                        notes = "• Original Identity Card\n• Copy for submission"
                    ),
                    Document(
                        id = "purpose_letter_diplomatic",
                        title = "Official Letter",
                        description = "Letter stating purpose and duration",
                        notes = "• Purpose of travel\n• Duration of assignment\n• Countries to be visited"
                    )
                )
            ),
            DocumentCategory(
                id = "gep_verification",
                title = "Documents Required for Background Verification for GEP",
                description = "Documents needed for Global Entry Program verification",
                documents = listOf(
                    Document(
                        id = "identity_proof_gep",
                        title = "Identity Proof",
                        description = "Valid identification documents",
                        notes = "• Passport\n• Aadhaar Card\n• PAN Card"
                    ),
                    Document(
                        id = "address_proof_gep",
                        title = "Address Proof",
                        description = "Current and permanent address",
                        notes = "• Utility Bill\n• Bank Statement\n• Aadhaar Card\n• Rent Agreement"
                    ),
                    Document(
                        id = "education_certificates",
                        title = "Educational Certificates",
                        description = "Academic qualifications",
                        notes = "• Degree Certificate\n• Marksheets\n• Other educational qualifications"
                    ),
                    Document(
                        id = "employment_history",
                        title = "Employment History Documentation",
                        description = "Proof of employment",
                        notes = "• Employment Letters\n• Pay Slips\n• Service Certificate\n• Contract Letters"
                    )
                )
            )
        )
    }
}

data class DocumentAdvisorUiState(
    val documentCategories: List<DocumentCategory> = emptyList(),
    val selectedCategoryId: String = "",
    val expandedDocumentIds: Set<String> = emptySet(),
    val isLoading: Boolean = true,
    val error: String = ""
)