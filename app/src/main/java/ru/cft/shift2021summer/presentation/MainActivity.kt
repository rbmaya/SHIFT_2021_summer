package ru.cft.shift2021summer.presentation

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.slider.RangeSlider
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.databinding.ActivityMainBinding
import ru.cft.shift2021summer.domain.search_options.SearchGroup

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val optionsAdapter = OptionsAdapter()
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingListeners()
        setTheme(R.style.Theme_Shift2021Summer)
        setToolbar()
        mainActivityViewModel.loadSearchOptions()
        mainActivityViewModel.loadSearchOptionsEvent.observe(this, {
            optionsAdapter.listSearchGroups = it
        })
        binding.expandedList.setAdapter(optionsAdapter)
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

    fun setListenerOnRemoveSearchOptions(listener: View.OnClickListener) {
        binding.allCosmetics.setOnClickListener(listener)
    }

    fun setValuesOnPriceSlider(valueFrom: Float, valueTo: Float){
        val slider = binding.priceSlider
        slider.valueFrom = valueFrom
        slider.valueTo = valueTo
        slider.setValues(valueFrom, valueTo)
        slider.setLabelFormatter {
            getString(R.string.price_limits, it)
        }
    }

    fun setListenerOnPriceSlider(listener: RangeSlider.OnSliderTouchListener){
        binding.priceSlider.addOnSliderTouchListener(listener)
    }

    fun getSelectedSearchGroupName(groupPosition: Int): String {
        return (optionsAdapter.getGroup(groupPosition) as SearchGroup).name
    }

    fun getSelectedSearchOptions(groupPosition: Int, childPosition: Int): String {
        return optionsAdapter.getChild(groupPosition, childPosition) as String
    }

    fun setSearchOptionsListeners(listener: ExpandableListView.OnChildClickListener) {
        binding.expandedList.setOnChildClickListener(listener)
    }

    fun closeDrawers() {
        binding.drawerLayout.closeDrawers()
    }

    fun setToolbar(toolbar: MaterialToolbar = binding.mainToolbar) {
        binding.mainToolbar.isVisible = (toolbar == binding.mainToolbar)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
}