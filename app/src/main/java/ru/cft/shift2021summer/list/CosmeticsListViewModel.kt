package ru.cft.shift2021summer.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.cft.shift2021summer.Cosmetic
import ru.cft.shift2021summer.CosmeticsRepository
import javax.inject.Inject

@HiltViewModel
class CosmeticsListViewModel @Inject constructor(
    private val cosmeticsRepository: CosmeticsRepository
) : ViewModel() {

    private val _cosmeticsList: MutableLiveData<List<Cosmetic>> = MutableLiveData(emptyList())
    val cosmeticsList: LiveData<List<Cosmetic>> = _cosmeticsList

    fun loadCosmetics() {
        _cosmeticsList.value = cosmeticsRepository.getAll()
    }
}