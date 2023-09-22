package com.example.readingtracker.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.readingtracker.R
import com.example.readingtracker.presentation.fragments.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
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