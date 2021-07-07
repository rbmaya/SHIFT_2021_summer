package ru.cft.shift2021summer.domain

import kotlinx.coroutines.flow.flow
import ru.cft.shift2021summer.presentation.list.CosmeticsListViewModel
import javax.inject.Inject

class GetCosmeticsUseCase @Inject constructor(
    private val cosmeticsRepository: CosmeticsRepository
) {
    suspend operator fun invoke() = flow {
        emit(CosmeticsListViewModel.CosmeticsListUiState.Loading)
        try {
            val flow = cosmeticsRepository.getCosmetics()
            emit(CosmeticsListViewModel.CosmeticsListUiState.Success(flow))
        } catch (exc: Exception) {
            emit(CosmeticsListViewModel.CosmeticsListUiState.Error(exc))
        }
    }

}