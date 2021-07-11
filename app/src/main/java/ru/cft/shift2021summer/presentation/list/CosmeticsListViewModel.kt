package ru.cft.shift2021summer.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.caching.GetCosmeticByNameUseCase
import ru.cft.shift2021summer.domain.caching.GetSavedCosmeticsUseCase
import ru.cft.shift2021summer.domain.caching.SaveCosmeticsUseCase
import ru.cft.shift2021summer.domain.network.GetCosmeticsUseCase
import ru.cft.shift2021summer.utils.CosmeticsUiState
import ru.cft.shift2021summer.utils.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class CosmeticsListViewModel @Inject constructor(
    private val getCosmeticsUseCase: GetCosmeticsUseCase,
    private val getSavedCosmeticsUseCase: GetSavedCosmeticsUseCase,
    private val saveCosmeticsUseCase: SaveCosmeticsUseCase,
    private val getCosmeticByNameUseCase: GetCosmeticByNameUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<CosmeticsUiState> =
        MutableStateFlow(CosmeticsUiState.NoValue)
    val uiState = _uiState.asStateFlow()

    val openDetailsEvent = SingleLiveEvent<Cosmetic>()

    fun openDetailCosmetic(cosmetic: Cosmetic) {
        openDetailsEvent.value = cosmetic
    }

    fun loadCosmetics(isRefresh: Boolean) {
        viewModelScope.launch {
            _uiState.value = CosmeticsUiState.Loading
            try {
                var list: List<Cosmetic> = emptyList()
                if (!isRefresh) {
                    list = getSavedCosmeticsUseCase()
                }
                if (list.isEmpty() || isRefresh) {
                    list = getCosmeticsUseCase()
                    saveCosmeticsUseCase(list)
                }
                _uiState.value = CosmeticsUiState.Success(list)
            } catch (exc: Exception) {
                _uiState.value = CosmeticsUiState.Error(exc)
            }
        }
    }

    fun queryTextChanged(newText: String?) {
        if (newText == null || newText.isBlank()) {
            loadCosmetics(false)
        } else {
            viewModelScope.launch {
                _uiState.value = CosmeticsUiState.Loading
                try {
                    val list = getCosmeticByNameUseCase(newText)
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