// app/src/main/java/com/harshsinghio/passportseva/presentation/screens/feecalculator/FeeCalculatorViewModel.kt
package com.harshsinghio.passportseva.presentation.screens.feecalculator

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class FeeCalculatorViewModel : ViewModel() {

    private val _uiState = mutableStateOf(FeeCalculatorUiState())
    val uiState: State<FeeCalculatorUiState> = _uiState

    // Function to load fee data from JSON
    fun loadFeeData(context: Context) {
        viewModelScope.launch {
            try {
                val jsonString = loadJsonFromAsset(context, "passport-fees.json")
                val jsonObject = JSONObject(jsonString)

                _uiState.value = _uiState.value.copy(
                    feeData = jsonObject,
                    isLoading = false
                )

                // Calculate initial fee
                val calculatedFee = calculateFee()
                _uiState.value = _uiState.value.copy(calculatedFee = calculatedFee)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Failed to load fee data: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    // Load JSON from assets
    private fun loadJsonFromAsset(context: Context, fileName: String): String {
        val inputStream = context.assets.open(fileName)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?

        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }

        bufferedReader.close()
        return stringBuilder.toString()
    }

    // Update application type
    fun updateApplicationType(type: String) {
        _uiState.value = _uiState.value.copy(
            applicationType = type
        )

        val calculatedFee = calculateFee()
        _uiState.value = _uiState.value.copy(calculatedFee = calculatedFee)
    }

    // Update service type
    fun updateServiceType(type: String) {
        _uiState.value = _uiState.value.copy(
            serviceType = type
        )

        val calculatedFee = calculateFee()
        _uiState.value = _uiState.value.copy(calculatedFee = calculatedFee)
    }

    // Update age group
    fun updateAgeGroup(ageGroup: String) {
        _uiState.value = _uiState.value.copy(
            ageGroup = ageGroup
        )

        val calculatedFee = calculateFee()
        _uiState.value = _uiState.value.copy(calculatedFee = calculatedFee)
    }

    // Update actual age (which determines the age group)
    fun updateAge(age: Int) {
        val ageGroup = when {
            age < 15 -> "less_than_15_years"
            age < 18 -> "between_15_18_years"
            else -> "18_years_and_above"
        }

        _uiState.value = _uiState.value.copy(
            age = age,
            ageGroup = ageGroup
        )

        val calculatedFee = calculateFee()
        _uiState.value = _uiState.value.copy(calculatedFee = calculatedFee)
    }

    // Update number of pages
    fun updatePages(pages: String) {
        _uiState.value = _uiState.value.copy(
            pages = pages
        )

        val calculatedFee = calculateFee()
        _uiState.value = _uiState.value.copy(calculatedFee = calculatedFee)
    }

    // Update scheme
    fun updateScheme(scheme: String) {
        _uiState.value = _uiState.value.copy(
            scheme = scheme
        )

        val calculatedFee = calculateFee()
        _uiState.value = _uiState.value.copy(calculatedFee = calculatedFee)
    }

    // Update reissue reason
    fun updateReissueReason(reason: String) {
        _uiState.value = _uiState.value.copy(
            reissueReason = reason
        )

        val calculatedFee = calculateFee()
        _uiState.value = _uiState.value.copy(calculatedFee = calculatedFee)
    }

    // Update lost/damaged status
    fun updateLostStatus(status: String) {
        _uiState.value = _uiState.value.copy(
            lostStatus = status
        )

        val calculatedFee = calculateFee()
        _uiState.value = _uiState.value.copy(calculatedFee = calculatedFee)
    }

    // Calculate the fee based on current selections
    private fun calculateFee(): Double {
        val state = _uiState.value
        val feeData = state.feeData ?: return 0.0

        try {
            when (state.applicationType) {
                "passport" -> {
                    // Get passport data
                    val passportData = feeData.getJSONObject("passport")

                    // Get service type data (fresh or reissue)
                    val serviceTypeData = passportData.getJSONObject(state.serviceType)

                    // Get age group data
                    val ageGroupData = serviceTypeData.getJSONObject(state.ageGroup)

                    // Calculate fee based on service type
                    return when (state.serviceType) {
                        "fresh" -> {
                            // For fresh passports
                            when (state.ageGroup) {
                                "less_than_15_years" -> {
                                    // Simple flat fee for under 15
                                    ageGroupData.getDouble(state.scheme)
                                }
                                else -> {
                                    // For 15+ years, fee depends on number of pages
                                    ageGroupData.getJSONObject(state.scheme).getDouble(state.pages)
                                }
                            }
                        }
                        "reissue" -> {
                            // For passport reissue
                            when {
                                // For lost or damaged passports
                                state.reissueReason == "lost_damaged" -> {
                                    val lostData = ageGroupData.getJSONObject(state.reissueReason)
                                        .getJSONObject(state.lostStatus)

                                    when (state.ageGroup) {
                                        "less_than_15_years" -> {
                                            // Flat fee for under 15
                                            lostData.getDouble(state.scheme)
                                        }
                                        else -> {
                                            // Page-dependent fee for 15+
                                            lostData.getJSONObject(state.scheme).getDouble(state.pages)
                                        }
                                    }
                                }
                                // For other reissue reasons
                                else -> {
                                    val reasonData = ageGroupData.getJSONObject(state.reissueReason)

                                    when (state.ageGroup) {
                                        "less_than_15_years" -> {
                                            // Flat fee for under 15
                                            reasonData.getDouble(state.scheme)
                                        }
                                        else -> {
                                            // Page-dependent fee for 15+
                                            reasonData.getJSONObject(state.scheme).getDouble(state.pages)
                                        }
                                    }
                                }
                            }
                        }
                        else -> 0.0
                    }
                }
                "pcc" -> {
                    // Simple flat fee for PCC
                    return feeData.getJSONObject("pcc").getDouble("fee")
                }
                "identity_certificate" -> {
                    // Fee based on service type for identity certificate
                    return feeData.getJSONObject("identity_certificate")
                        .getJSONObject(state.serviceType)
                        .getDouble("fee")
                }
                "surrender_certificate" -> {
                    // Simple flat fee for surrender certificate
                    return feeData.getJSONObject("surrender_certificate").getDouble("fee")
                }
                "background_verification_gep" -> {
                    // Simple flat fee for background verification
                    return feeData.getJSONObject("background_verification_gep").getDouble("fee")
                }
                else -> return 0.0
            }
        } catch (e: Exception) {
            // If there's an error in fee calculation, log it and return 0
            println("Error calculating fee: ${e.message}")
            e.printStackTrace()
            return 0.0
        }
    }

    // Calculate if discount should be applied
    fun shouldApplyDiscount(): Boolean {
        val state = _uiState.value
        return state.applicationType == "passport" &&
                state.serviceType == "fresh" &&
                (state.age < 8 || state.age > 60)
    }

    // Get final fee with discount if applicable
    fun getFinalFee(): Double {
        val baseFee = _uiState.value.calculatedFee
        return if (shouldApplyDiscount()) baseFee * 0.9 else baseFee
    }
}

// UI State for Fee Calculator
data class FeeCalculatorUiState(
    val isLoading: Boolean = true,
    val error: String = "",
    val feeData: JSONObject? = null,

    // Form fields
    val applicationType: String = "passport",
    val serviceType: String = "fresh",
    val ageGroup: String = "18_years_and_above",
    val age: Int = 30,
    val pages: String = "36_pages",
    val scheme: String = "normal",
    val reissueReason: String = "validity_expired",
    val lostStatus: String = "expired_yes",

    // Result
    val calculatedFee: Double = 0.0
)