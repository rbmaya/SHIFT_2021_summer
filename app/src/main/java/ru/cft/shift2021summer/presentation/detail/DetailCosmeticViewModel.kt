package ru.cft.shift2021summer.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.cft.shift2021summer.domain.Cosmetic
import ru.cft.shift2021summer.domain.CosmeticIsFavorite
import ru.cft.shift2021summer.domain.caching.ChangeCosmeticIsFavoriteUseCase
import ru.cft.shift2021summer.utils.LiveEvent

class DetailCosmeticViewModel @AssistedInject constructor(
    private val changeCosmeticIsFavoriteUseCase: ChangeCosmeticIsFavoriteUseCase,
    @Assisted val cosmetic: Cosmetic
) : ViewModel() {

    val openCosmeticLinkEvent = LiveEvent()

    fun openCosmeticLink() {
        openCosmeticLinkEvent()
    }

    fun changeIsFavoriteCosmetic(idCosmetic: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            try {
                changeCosmeticIsFavoriteUseCase(
                    CosmeticIsFavorite(
                        id = idCosmetic,
                        isFavorite = isFavorite
                    )
                )
                cosmetic.isFavorite = isFavorite
            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(cosmetic: Cosmetic): DetailCosmeticViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            cosmetic: Cosmetic
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(cosmetic) as T
            }
        }
    }
}
