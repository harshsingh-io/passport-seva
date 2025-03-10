// app/src/main/java/com/harshsinghio/passportseva/presentation/screens/faq/FaqViewModel.kt
package com.harshsinghio.passportseva.presentation.screens.faq

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.harshsinghio.passportseva.domain.model.FaqCategory
import com.harshsinghio.passportseva.domain.model.FaqQuestion
import com.harshsinghio.passportseva.domain.model.FaqSubcategory

/**
 * ViewModel for the FAQ screen
 */
class FaqViewModel : ViewModel() {

    private val _uiState = mutableStateOf(FaqUiState())
    val uiState: State<FaqUiState> = _uiState

    init {
        _uiState.value = FaqUiState(
            isLoading = false,
            faqCategories = createFaqData()
        )
    }

    /**
     * Toggle expansion of a category
     */
    fun toggleCategory(categoryId: String) {
        val currentExpanded = _uiState.value.expandedCategoryIds.toMutableSet()
        if (currentExpanded.contains(categoryId)) {
            currentExpanded.remove(categoryId)
        } else {
            currentExpanded.add(categoryId)
        }
        _uiState.value = _uiState.value.copy(expandedCategoryIds = currentExpanded)
    }

    /**
     * Toggle expansion of a subcategory
     */
    fun toggleSubcategory(subcategoryId: String) {
        val currentExpanded = _uiState.value.expandedSubcategoryIds.toMutableSet()
        if (currentExpanded.contains(subcategoryId)) {
            currentExpanded.remove(subcategoryId)
        } else {
            currentExpanded.add(subcategoryId)
        }
        _uiState.value = _uiState.value.copy(expandedSubcategoryIds = currentExpanded)
    }

    /**
     * Toggle expansion of a question
     */
    fun toggleQuestion(questionId: String) {
        val currentExpanded = _uiState.value.expandedQuestionIds.toMutableSet()
        if (currentExpanded.contains(questionId)) {
            currentExpanded.remove(questionId)
        } else {
            currentExpanded.add(questionId)
        }
        _uiState.value = _uiState.value.copy(expandedQuestionIds = currentExpanded)
    }

    /**
     * Update search query
     */
    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    /**
     * Submit feedback for a question
     */
    fun submitFeedback(questionId: String, isHelpful: Boolean) {
        // In a real app, this would send feedback to a server
        println("Feedback for question $questionId: ${if (isHelpful) "Helpful" else "Not helpful"}")
    }

    /**
     * Create FAQ data from the provided FAQ.txt
     */
    private fun createFaqData(): List<FaqCategory> {
        return listOf(
            FaqCategory(
                id = "services_available",
                title = "Services Available",
                subcategories = listOf(
                    FaqSubcategory(
                        id = "passport_services",
                        title = "Passport Services Overview",
                        questions = listOf(
                            FaqQuestion(
                                id = "minor",
                                title = "Minor",
                                answer = "Passport services for minors include fresh passport application, reissue of passport, and changes in personal particulars. Special documentation requirements apply for minors."
                            ),
                            FaqQuestion(
                                id = "senior",
                                title = "Senior Citizen",
                                answer = "Senior citizens (aged 60 and above) are eligible for special provisions including separate counters at PSKs, relaxed documentation requirements, and priority processing."
                            ),
                            FaqQuestion(
                                id = "students",
                                title = "Students",
                                answer = "Students can apply for passports with their educational institution's address proof. For students studying abroad, additional documentation may be required."
                            )
                        )
                    ),
                    FaqSubcategory(
                        id = "special_minors",
                        title = "Special Cases Of Minors Requiring Passports",
                        questions = listOf(
                            FaqQuestion(
                                id = "single_parent",
                                title = "Exclusion of father/mother name from Passport of minor in single parent custody",
                                answer = "To exclude a parent's name from a minor's passport in single parent custody cases, submit court custody documents, an affidavit, and a No Objection Certificate from the other parent (if available)."
                            ),
                            FaqQuestion(
                                id = "divorce_pending",
                                title = "Divorce pending cases",
                                answer = "For minors with parents in pending divorce cases, both parents must provide consent. If one parent cannot provide consent, a court order authorizing passport issuance or sole custody decree is required."
                            )
                        )
                    )
                )
            ),
            FaqCategory(
                id = "where_apply",
                title = "Where to apply?",
                subcategories = listOf(
                    FaqSubcategory(
                        id = "psk",
                        title = "Passport Seva Kendra (PSK)",
                        questions = listOf(
                            FaqQuestion(
                                id = "what_is_psk",
                                title = "What is a Passport Seva Kendra (PSK)/ Post Office Passport Seva Kendra (POPSK)?",
                                answer = "A Passport Seva Kendra (PSK) is a dedicated facility for processing passport applications with modern technology and trained staff. Post Office Passport Seva Kendras (POPSKs) are smaller centers operated in collaboration with Department of Posts to extend passport services to more locations."
                            )
                        )
                    ),
                    FaqSubcategory(
                        id = "online",
                        title = "Online",
                        questions = listOf(
                            FaqQuestion(
                                id = "online_reissue",
                                title = "Can I apply online for re-issue of Passport?",
                                answer = "Yes, you can apply online for re-issue of passport through the Passport Seva Online Portal. After completing the online application, schedule an appointment and visit the selected PSK with required documents for verification."
                            ),
                            FaqQuestion(
                                id = "online_fresh",
                                title = "How can I apply for a Passport online?",
                                answer = "To apply online: 1) Visit passport.gov.in 2) Register an account 3) Fill the application form 4) Pay the fee online 5) Schedule an appointment 6) Visit the PSK with required documents on the appointed date."
                            )
                        )
                    )
                )
            ),
            FaqCategory(
                id = "application_form",
                title = "Application Form",
                subcategories = listOf(
                    FaqSubcategory(
                        id = "form_overview",
                        title = "Application Form Overview",
                        questions = listOf(
                            FaqQuestion(
                                id = "form_types",
                                title = "What are the various Passport services and which form should be filled in?",
                                answer = "For fresh passport and reissue of passport, use Form-1. For diplomatic/official passport, use Form-2. For Police Clearance Certificate (PCC), use Form-3. For miscellaneous services, use Form-4."
                            )
                        )
                    )
                )
            ),
            FaqCategory(
                id = "fee_payment",
                title = "Fee Payment",
                subcategories = listOf(
                    FaqSubcategory(
                        id = "payment_overview",
                        title = "Fee Payment Overview",
                        questions = listOf(
                            FaqQuestion(
                                id = "fee_amount",
                                title = "What is the fee for applying for a Passport?",
                                answer = "The fee varies by passport type and age: For adults (18+ years), 36-page passport: ₹1,500, 60-page passport: ₹2,000. For minors (under 15 years): ₹1,000 for either type. Tatkal processing has additional charges: ₹2,000 for adults, ₹1,500 for minors."
                            ),
                            FaqQuestion(
                                id = "payment_methods",
                                title = "How can I make the payment? What are the accepted methods of payment?",
                                answer = "You can pay online through credit/debit cards, net banking, or UPI. At PSK/POPSK, you can pay using cash, credit/debit cards, or demand drafts. Some locations also have ATM facilities."
                            )
                        )
                    )
                )
            ),
            FaqCategory(
                id = "police_verification",
                title = "Police Verification",
                subcategories = listOf(
                    FaqSubcategory(
                        id = "pv_overview",
                        title = "Police Verification Overview",
                        questions = listOf(
                            FaqQuestion(
                                id = "pv_pending",
                                title = "I applied for my Passport 6 months ago and the police verification is still pending? What should I do?",
                                answer = "Check your application status online. If police verification is still pending after 6 months, contact your local police station and the Regional Passport Office with your application details. Submit a written complaint or email the RPO's official email address."
                            ),
                            FaqQuestion(
                                id = "pv_required",
                                title = "Why is Police Verification required for issuance of Passport?",
                                answer = "Police verification ensures the applicant has no criminal record or pending cases that might prevent passport issuance. It confirms the applicant's identity, address, and citizenship status as part of national security protocols."
                            )
                        )
                    )
                )
            )
        )
    }
}

/**
 * UI state for the FAQ screen
 */
data class FaqUiState(
    val isLoading: Boolean = true,
    val faqCategories: List<FaqCategory> = emptyList(),
    val expandedCategoryIds: Set<String> = emptySet(),
    val expandedSubcategoryIds: Set<String> = emptySet(),
    val expandedQuestionIds: Set<String> = emptySet(),
    val searchQuery: String = "",
    val error: String = ""
)