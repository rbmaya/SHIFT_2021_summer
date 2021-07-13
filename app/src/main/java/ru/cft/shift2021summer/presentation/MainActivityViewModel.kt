package ru.cft.shift2021summer.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.cft.shift2021summer.domain.search_options.GetAllGroupsUseCase
import ru.cft.shift2021summer.domain.search_options.SearchGroup
import ru.cft.shift2021summer.utils.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAllGroupsUseCase: GetAllGroupsUseCase
): ViewModel(){
    val loadSearchOptionsEvent = SingleLiveEvent<List<SearchGroup>>()

    fun loadSearchOptions(){
        val options = getAllGroupsUseCase()
        loadSearchOptionsEvent.value = options
    }
}