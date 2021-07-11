package ru.cft.shift2021summer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindingListeners()
        setTheme(R.style.Theme_Shift2021Summer)
    }

    private fun bindingListeners() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val navController = findNavController(R.id.nav_host_fragment)
                when (tab?.position) {
                    0 -> {
                        navController.navigate(R.id.cosmeticsListFragment)
                    }
                    1 -> {
                        navController.navigate(R.id.favoriteCosmeticsFragment)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}