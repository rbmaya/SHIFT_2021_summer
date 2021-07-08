package ru.cft.shift2021summer.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.GetCosmeticsUseCase
import ru.cft.shift2021summer.utils.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class CosmeticsListViewModel @Inject constructor(
    private val getCosmeticsUseCase: GetCosmeticsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<CosmeticsListUiState> =
        MutableStateFlow(CosmeticsListUiState.NoValue)
    val uiState = _uiState.asStateFlow()

    val openDetailCosmeticEvent = SingleLiveEvent<Cosmetic>()

    fun openDetailCosmetic(cosmetic: Cosmetic){
        openDetailCosmeticEvent.value = cosmetic
    }

    fun loadCosmetics() {
        viewModelScope.launch {
            _uiState.value = CosmeticsListUiState.Loading
            try {
                val list = getCosmeticsUseCase()
                _uiState.value = CosmeticsListUiState.Success(list)
            } catch (exc: Exception){
                exc.printStackTrace()
                _uiState.value = CosmeticsListUiState.Error(exc)
            }
        }
    }

    sealed class CosmeticsListUiState{
        object NoValue: CosmeticsListUiState()
        object Loading: CosmeticsListUiState()
        data class Success(val cosmetics: List<Cosmetic>): CosmeticsListUiState()
        data class Error(val exc: Exception): CosmeticsListUiState()
    }
}