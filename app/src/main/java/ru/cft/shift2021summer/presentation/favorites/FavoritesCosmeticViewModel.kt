package ru.cft.shift2021summer.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.caching.favorites.*
import ru.cft.shift2021summer.utils.CosmeticsUiState
import ru.cft.shift2021summer.utils.LimitPriceEvent
import ru.cft.shift2021summer.utils.SingleLiveEvent
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class FavoritesCosmeticViewModel @Inject constructor(
    private val getFavoriteCosmeticsUseCase: GetFavoriteCosmeticsUseCase,
    private val getFavoriteCosmeticsByNameUseCase: GetFavoriteCosmeticsByNameUseCase,
    private val getFavoriteCosmeticsByBrandUseCase: GetFavoriteCosmeticsByBrandUseCase,
    private val getFavoriteCosmeticsByProductTypeUseCase: GetFavoriteCosmeticsByProductTypeUseCase,
    private val getMinFavoriteCosmeticsPriceUseCase: GetMinFavoriteCosmeticsPriceUseCase,
    private val getMaxFavoriteCosmeticsPriceUseCase: GetMaxFavoriteCosmeticsPriceUseCase,
    private val getFavoriteCosmeticsByPriceUseCase: GetFavoriteCosmeticsByPriceUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<CosmeticsUiState> =
        MutableStateFlow(CosmeticsUiState.NoValue)
    val uiState = _uiState.asStateFlow()

    val openDetailCosmeticEvent = SingleLiveEvent<Cosmetic>()
    val limitPriceEvent = SingleLiveEvent<LimitPriceEvent>()

    fun openDetailCosmetic(cosmetic: Cosmetic) {
        openDetailCosmeticEvent.value = cosmetic
    }

    fun loadPriceLimits() {
        viewModelScope.launch {
            try {
                val minPrice = (getMinFavoriteCosmeticsPriceUseCase() * 100f).roundToInt() / 100f
                val maxPrice = (getMaxFavoriteCosmeticsPriceUseCase() * 100f).roundToInt() / 100f
                limitPriceEvent.value = LimitPriceEvent.PriceLimits(
                    valueFrom = minPrice,
                    valueTo = maxPrice
                )
            } catch (exc: Exception) {
                _uiState.value = CosmeticsUiState.Error(exc)
            }
        }
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

    private fun loadCosmeticsByName(name: String) {
        viewModelScope.launch {
            try {
                val list = getFavoriteCosmeticsByNameUseCase(name)
                if (list.isEmpty()) {
                    _uiState.value = CosmeticsUiState.NoResults
                }
                _uiState.value = CosmeticsUiState.Success(list)

            } catch (exc: Exception) {
                _uiState.value = CosmeticsUiState.Error(exc)
            }
        }
    }

    fun loadCosmeticsByBrand(name: String) {
        viewModelScope.launch {
            _uiState.value = CosmeticsUiState.Loading
            try {
                val list = getFavoriteCosmeticsByBrandUseCase(name)
                if (list.isEmpty()) {
                    _uiState.value = CosmeticsUiState.NoResults
                }
                _uiState.value = CosmeticsUiState.Success(list)
            } catch (exc: Exception) {
                _uiState.value = CosmeticsUiState.Error(exc)
            }
        }
    }

    fun loadCosmeticsByProductType(name: String) {
        viewModelScope.launch {
            _uiState.value = CosmeticsUiState.Loading
            try {
                val list = getFavoriteCosmeticsByProductTypeUseCase(name)
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
                    getFavoriteCosmeticsByPriceUseCase(valueFrom = valueFrom, valueTo = valueTo)
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
            loadCosmeticsByName(newText)
        }
    }

}