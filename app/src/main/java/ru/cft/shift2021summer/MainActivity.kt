package ru.cft.shift2021summer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.cft.shift2021summer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}