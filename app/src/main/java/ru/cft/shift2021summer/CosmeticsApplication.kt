package ru.cft.shift2021summer

import android.app.Application

class CosmeticsApplication: Application(){
    lateinit var cosmeticsRepository: CosmeticsRepository

    override fun onCreate() {
        super.onCreate()
        cosmeticsRepository = CosmeticsRepository()
    }
}