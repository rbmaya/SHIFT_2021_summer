package ru.cft.shift2021summer.utils

import ru.cft.shift2021summer.domain.Cosmetic

sealed class CosmeticsUiState {
    object NoValue : CosmeticsUiState()
    object Loading : CosmeticsUiState()
    data class Success(val cosmetics: List<Cosmetic>) : CosmeticsUiState()
    data class Error(val exc: Exception) : CosmeticsUiState()
    object NoResults: CosmeticsUiState()
}
