package com.cookandroid.team_project

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.cookandroid.team_project.ui.Leagues
import com.cookandroid.team_project.ui.MainViewModel
import com.cookandroid.team_project.ui.MainPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var spinnerLeague: Spinner
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Spinner - league selection
        spinnerLeague = findViewById(R.id.spinnerLeague)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Leagues.names())
        spinnerLeague.adapter = adapter
        spinnerLeague.setSelection(0, false)
        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mainViewModel.setLeagueByIndex(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // ViewPager2 설정
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = MainPagerAdapter(this)
        viewPager.isUserInputEnabled = true

        // Bottom navigation
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_standings -> {
                    viewPager.currentItem = 0; true
                }
                R.id.nav_scorers -> {
                    viewPager.currentItem = 1; true
                }
                R.id.nav_assists -> {
                    viewPager.currentItem = 2; true
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_standings
            viewPager.currentItem = 0
        }

        // ViewPager 스와이프 시 BottomNav와 연동
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNav.setOnItemSelectedListener(null)
                bottomNav.selectedItemId = when (position) {
                    0 -> R.id.nav_standings
                    1 -> R.id.nav_scorers
                    2 -> R.id.nav_assists
                    else -> R.id.nav_standings
                }
                bottomNav.setOnItemSelectedListener { item: MenuItem ->
                    when (item.itemId) {
                        R.id.nav_standings -> {
                            viewPager.currentItem = 0; true
                        }
                        R.id.nav_scorers -> {
                            viewPager.currentItem = 1; true
                        }
                        R.id.nav_assists -> {
                            viewPager.currentItem = 2; true
                        }
                        else -> false
                    }
                }
            }
        })
    }
}