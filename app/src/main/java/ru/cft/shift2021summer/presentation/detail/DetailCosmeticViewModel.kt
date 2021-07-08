package ru.cft.shift2021summer.presentation.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.cft.shift2021summer.utils.LiveEvent
import javax.inject.Inject

@HiltViewModel
class DetailCosmeticViewModel @Inject constructor(

) : ViewModel() {
    val openCosmeticLinkEvent = LiveEvent()

    fun openCosmeticLink(){
        openCosmeticLinkEvent()
    }
}