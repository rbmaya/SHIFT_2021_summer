package ru.cft.shift2021summer.utils

class LiveEvent : SingleLiveEvent<Unit>() {

    operator fun invoke() {
        this.value = Unit
    }
}