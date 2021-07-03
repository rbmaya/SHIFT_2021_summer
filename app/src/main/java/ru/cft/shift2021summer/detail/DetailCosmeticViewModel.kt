package ru.cft.shift2021summer.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.cft.shift2021summer.Cosmetic
import ru.cft.shift2021summer.CosmeticsRepository

class DetailCosmeticViewModel(
    private val cosmeticsRepository: CosmeticsRepository,
) : ViewModel() {
    private val _cosmetic: MutableLiveData<Cosmetic> = MutableLiveData()
    val cosmetic: LiveData<Cosmetic> = _cosmetic

    fun loadCosmetic(id: Long){
        _cosmetic.value = cosmeticsRepository.getById(id)
    }
}