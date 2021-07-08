package ru.cft.shift2021summer.domain.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCosmeticsUseCase @Inject constructor(
    private val cosmeticsRepository: CosmeticsRepository
) {
    suspend operator fun invoke() = cosmeticsRepository.getCosmetics()

}