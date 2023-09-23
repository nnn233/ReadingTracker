package com.example.readingtracker.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.readingtracker.R
import com.example.readingtracker.databinding.ActivityMainBinding
import com.example.readingtracker.presentation.fragments.home.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_fragment -> {
                    createHomeFragment()
                    true
                }

                R.id.books_fragment -> {
                    // Respond to navigation item 2 reselection
                    true
                }

                R.id.statistics_fragment -> {
                    // Respond to navigation item 3 reselection
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun createHomeFragment() {
        val fragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.main_fragment,
                fragment
            )
            .addToBackStack(null)
            .commit()
    }
}