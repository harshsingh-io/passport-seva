package com.harshsinghio.passportseva.presentation.screens.annexures

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshsinghio.passportseva.domain.model.Annexure
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AnnexuresViewModel : ViewModel() {

    private val _uiState = mutableStateOf(AnnexuresUiState())
    val uiState: State<AnnexuresUiState> = _uiState

    init {
        loadAnnexures()
    }

    private fun loadAnnexures() {
        getAnnexures().onEach { annexures ->
            _uiState.value = _uiState.value.copy(
                annexures = annexures,
                isLoading = false
            )
        }.launchIn(viewModelScope)
    }

    private fun getAnnexures() = flow {
        emit(getMockAnnexures())
    }

    // AnnexuresViewModel.kt
    private fun getMockAnnexures(): List<Annexure> {
        return listOf(
            Annexure(
                id = "1",
                title = "Identity Certificate",
                annexureCode = "A",
                fileUrl = "https://drive.google.com/file/d/11OfR3uZT6aQixsaMT7uJ-03sY1mOnP21/view",
                docId = "11OfR3uZT6aQixsaMT7uJ-03sY1mOnP21"
            ),
            Annexure(
                id = "2",
                title = "Declaration of Parent/Guardian for Minor Passports (one parent not given consent)",
                annexureCode = "C",
                fileUrl = "https://drive.google.com/file/d/1ndqtpIUuvE7aqq6tmG5JN3l7ZicigAGw/view",
                docId = "1ndqtpIUuvE7aqq6tmG5JN3l7ZicigAGw"
            ),
            Annexure(
                id = "3",
                title = "Declaration of Parent/Guardian for Minor Passports",
                annexureCode = "D",
                fileUrl = "https://drive.google.com/file/d/1NfMXfgb1VwpJxX6-AvBr28Uob4XuXDgD/view",
                docId = "1NfMXfgb1VwpJxX6-AvBr28Uob4XuXDgD"
            ),
            Annexure(
                id = "4",
                title = "Specimen Affidavit for a passport in lieu of lost/damaged passport",
                annexureCode = "F",
                fileUrl = "https://drive.google.com/file/d/1ImjdkSbO3qpbwCbmn0C7j_Ij15Inj2-f/view",
                docId = "1ImjdkSbO3qpbwCbmn0C7j_Ij15Inj2-f"
            ),
            Annexure(
                id = "5",
                title = "No Objection Certificate",
                annexureCode = "G",
                fileUrl = "https://drive.google.com/file/d/14Z329ordziq5Qp4tOVbGmwmuiuVOQLvI/view",
                docId = "14Z329ordziq5Qp4tOVbGmwmuiuVOQLvI"
            ),
            Annexure(
                id = "6",
                title = "Prior Intimation Letter",
                annexureCode = "H",
                fileUrl = "https://drive.google.com/file/d/1L8LmkQwkYRFei-59EU4gXaQAOtpr3PF3/view",
                docId = "1L8LmkQwkYRFei-59EU4gXaQAOtpr3PF3"
            ),
            Annexure(
                id = "7",
                title = "A Declaration affirming the particulars furnished in the application about the minor",
                annexureCode = "I",
                fileUrl = "https://drive.google.com/file/d/1AdkGuCDpDG-9RB1P7wm548Bq_iEWTkrn/view",
                docId = "1AdkGuCDpDG-9RB1P7wm548Bq_iEWTkrn"
            ),
            Annexure(
                id = "8",
                title = "Authority letter",
                annexureCode = "",
                fileUrl = "https://drive.google.com/file/d/1wj6Y2ou_sRQ_TVIX7bOkPVwgQ19xZpJl/view",
                docId = "1wj6Y2ou_sRQ_TVIX7bOkPVwgQ19xZpJl"
            )
        )
    }

    fun openDocument(document: Annexure) {
        _uiState.value = _uiState.value.copy(
            openDocument = document,
            currentDocIndex = _uiState.value.annexures.indexOf(document)
        )
    }

    fun closeDocument() {
        _uiState.value = _uiState.value.copy(
            openDocument = null
        )
    }

    fun navigateDocument(direction: String) {
        val currentIndex = _uiState.value.currentDocIndex
        val documents = _uiState.value.annexures

        if (direction == "next" && currentIndex < documents.size - 1) {
            _uiState.value = _uiState.value.copy(
                currentDocIndex = currentIndex + 1,
                openDocument = documents[currentIndex + 1]
            )
        } else if (direction == "prev" && currentIndex > 0) {
            _uiState.value = _uiState.value.copy(
                currentDocIndex = currentIndex - 1,
                openDocument = documents[currentIndex - 1]
            )
        }
    }
}

data class AnnexuresUiState(
    val isLoading: Boolean = true,
    val annexures: List<Annexure> = emptyList(),
    val openDocument: Annexure? = null,
    val currentDocIndex: Int = 0,
    val error: String = ""
)