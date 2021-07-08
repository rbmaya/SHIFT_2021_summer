package ru.cft.shift2021summer.presentation.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.caching.GetSavedCosmeticsUseCase
import ru.cft.shift2021summer.domain.caching.SaveCosmeticsUseCase
import ru.cft.shift2021summer.domain.network.GetCosmeticsUseCase
import ru.cft.shift2021summer.utils.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class CosmeticsListViewModel @Inject constructor(
    private val getCosmeticsUseCase: GetCosmeticsUseCase,
    private val getSavedCosmeticsUseCase: GetSavedCosmeticsUseCase,
    private val saveCosmeticsUseCase: SaveCosmeticsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<CosmeticsListUiState> =
        MutableStateFlow(CosmeticsListUiState.NoValue)
    val uiState = _uiState.asStateFlow()

    val openDetailCosmeticEvent = SingleLiveEvent<Cosmetic>()

    fun openDetailCosmetic(cosmetic: Cosmetic) {
        openDetailCosmeticEvent.value = cosmetic
    }

    fun loadCosmetics(isRefresh: Boolean) {
        viewModelScope.launch {
            _uiState.value = CosmeticsListUiState.Loading
            try {
                var list: List<Cosmetic> = emptyList()
                if (!isRefresh) {
                    list = getSavedCosmeticsUseCase()
                }
                if (list.isEmpty() || isRefresh) {
                    list = getCosmeticsUseCase()
                    saveCosmeticsUseCase(list)
                }
                _uiState.value = CosmeticsListUiState.Success(list)
            } catch (exc: Exception) {
                _uiState.value = CosmeticsListUiState.Error(exc)
            }
        }
    }

    sealed class CosmeticsListUiState {
        object NoValue : CosmeticsListUiState()
        object Loading : CosmeticsListUiState()
        data class Success(val cosmetics: List<Cosmetic>) : CosmeticsListUiState()
        data class Error(val exc: Exception) : CosmeticsListUiState()
    }
}