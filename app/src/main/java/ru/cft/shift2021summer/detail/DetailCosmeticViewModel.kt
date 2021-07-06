package ru.cft.shift2021summer.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.cft.shift2021summer.Cosmetic
import ru.cft.shift2021summer.CosmeticsRepository

class DetailCosmeticViewModel @AssistedInject constructor(
    cosmeticsRepository: CosmeticsRepository,
    @Assisted id: Long
) : ViewModel() {
    private val _cosmetic: MutableLiveData<Cosmetic> = MutableLiveData(cosmeticsRepository.getById(id))
    val cosmetic: LiveData<Cosmetic> = _cosmetic

    @AssistedFactory
    interface DetailCosmeticViewModelFactory {
        fun create(id: Long): DetailCosmeticViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: DetailCosmeticViewModelFactory,
            id: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }
}