package ru.cft.shift2021summer.domain

import javax.inject.Inject

class GetCosmeticsUseCase @Inject constructor(
    private val cosmeticsRepository: CosmeticsRepository
) {
    suspend operator fun invoke() = cosmeticsRepository.getCosmetics()

}