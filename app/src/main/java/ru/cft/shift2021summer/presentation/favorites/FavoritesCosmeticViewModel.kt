package ru.cft.shift2021summer.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.caching.*
import ru.cft.shift2021summer.utils.CosmeticsUiState
import ru.cft.shift2021summer.utils.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class FavoritesCosmeticViewModel @Inject constructor(
    val getFavoriteCosmeticsUseCase: GetFavoriteCosmeticsUseCase,
    val getFavoriteCosmeticsByNameUseCase: GetFavoriteCosmeticsByNameUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<CosmeticsUiState> =
        MutableStateFlow(CosmeticsUiState.NoValue)
    val uiState = _uiState.asStateFlow()

    val openDetailCosmeticEvent = SingleLiveEvent<Cosmetic>()

    fun openDetailCosmetic(cosmetic: Cosmetic) {
        openDetailCosmeticEvent.value = cosmetic
    }

    fun loadCosmetics() {
        viewModelScope.launch {
            try {
                val list = getFavoriteCosmeticsUseCase()
                if (list.isEmpty()) {
                    _uiState.value = CosmeticsUiState.NoResults
                }
                _uiState.value = CosmeticsUiState.Success(list)
            } catch (exc: Exception) {
                _uiState.value = CosmeticsUiState.Error(exc)
            }
        }
    }

    fun queryTextChanged(newText: String?) {
        if (newText == null || newText.isBlank()) {
            loadCosmetics()
        } else {
            viewModelScope.launch {
                try {
                    val list = getFavoriteCosmeticsByNameUseCase(newText)
                    if (list.isEmpty()) {
                        _uiState.value = CosmeticsUiState.NoResults
                    }
                    _uiState.value = CosmeticsUiState.Success(list)

                } catch (exc: java.lang.Exception) {
                    _uiState.value = CosmeticsUiState.Error(exc)
                }
            }
        }
    }

}