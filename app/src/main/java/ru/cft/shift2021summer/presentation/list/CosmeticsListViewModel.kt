package ru.cft.shift2021summer.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.caching.*
import ru.cft.shift2021summer.domain.network.GetCosmeticsUseCase
import ru.cft.shift2021summer.presentation.favorites.FavoritesCosmeticViewModel
import ru.cft.shift2021summer.utils.CosmeticsUiState
import ru.cft.shift2021summer.utils.LimitPriceEvent
import ru.cft.shift2021summer.utils.SingleLiveEvent
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class CosmeticsListViewModel @Inject constructor(
    private val getCosmeticsUseCase: GetCosmeticsUseCase,
    private val getSavedCosmeticsUseCase: GetSavedCosmeticsUseCase,
    private val saveCosmeticsUseCase: SaveCosmeticsUseCase,
    private val getCosmeticByNameUseCase: GetCosmeticByNameUseCase,
    private val getCosmeticsByBrandUseCase: GetCosmeticByBrandUseCase,
    private val getCosmeticsByProductTypeUseCase: GetCosmeticByProductTypeUseCase,
    private val getCosmeticsByPriceUseCase: GetCosmeticsByPriceUseCase,
    private val getMinCosmeticsPriceUseCase: GetMinCosmeticsPriceUseCase,
    private val getMaxCosmeticsPriceUseCase: GetMaxCosmeticsPriceUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<CosmeticsUiState> =
        MutableStateFlow(CosmeticsUiState.NoValue)
    val uiState = _uiState.asStateFlow()

    val openDetailsEvent = SingleLiveEvent<Cosmetic>()
    val limitPriceEvent = SingleLiveEvent<LimitPriceEvent>()

    fun openDetailCosmetic(cosmetic: Cosmetic) {
        openDetailsEvent.value = cosmetic
    }

    fun loadPriceLimits() {
        viewModelScope.launch {
            try {
                val minPrice = (getMinCosmeticsPriceUseCase() * 100f).roundToInt() / 100f
                val maxPrice = (getMaxCosmeticsPriceUseCase() * 100f).roundToInt() / 100f
                limitPriceEvent.value = LimitPriceEvent.PriceLimits(
                    valueFrom = minPrice,
                    valueTo = maxPrice
                )
            } catch (exc: Exception) {
                _uiState.value = CosmeticsUiState.Error(exc)
            }
        }
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

    private fun loadCosmeticsByName(name: String){
        viewModelScope.launch {
            _uiState.value = CosmeticsUiState.Loading
            try {
                val list = getCosmeticByNameUseCase(name)
                if (list.isEmpty()) {
                    _uiState.value = CosmeticsUiState.NoResults
                }
                _uiState.value = CosmeticsUiState.Success(list)
            } catch (exc: Exception) {
                _uiState.value = CosmeticsUiState.Error(exc)
            }
        }
    }

    fun loadCosmeticsByBrand(name: String){
        viewModelScope.launch {
            _uiState.value = CosmeticsUiState.Loading
            try {
                val list = getCosmeticsByBrandUseCase(name)
                if (list.isEmpty()) {
                    _uiState.value = CosmeticsUiState.NoResults
                }
                _uiState.value = CosmeticsUiState.Success(list)
            } catch (exc: Exception) {
                _uiState.value = CosmeticsUiState.Error(exc)
            }
        }
    }

    fun loadCosmeticsByProductType(name: String){
        viewModelScope.launch {
            _uiState.value = CosmeticsUiState.Loading
            try {
                val list = getCosmeticsByProductTypeUseCase(name)
                if (list.isEmpty()) {
                    _uiState.value = CosmeticsUiState.NoResults
                }
                _uiState.value = CosmeticsUiState.Success(list)
            } catch (exc: Exception) {
                _uiState.value = CosmeticsUiState.Error(exc)
            }
        }
    }

    fun loadCosmeticsByPrice(valueFrom: Float, valueTo: Float) {
        viewModelScope.launch {
            _uiState.value = CosmeticsUiState.Loading
            try {
                val list =
                    getCosmeticsByPriceUseCase(valueFrom = valueFrom, valueTo = valueTo)
                if (list.isEmpty()) {
                    _uiState.value = CosmeticsUiState.NoResults
                }
                _uiState.value = CosmeticsUiState.Success(list)
            } catch (exc: Exception) {
                _uiState.value = CosmeticsUiState.Error(exc)
            }
        }
    }

    fun queryTextChanged(name: String?) {
        if (name == null || name.isBlank()) {
            loadCosmetics(false)
        } else {
            loadCosmeticsByName(name)
        }
    }
}