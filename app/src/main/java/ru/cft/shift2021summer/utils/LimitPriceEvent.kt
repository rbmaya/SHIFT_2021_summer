package ru.cft.shift2021summer.utils

sealed class LimitPriceEvent() {
    data class PriceLimits(
        val valueFrom: Float,
        val valueTo: Float
    ) : LimitPriceEvent()
}
