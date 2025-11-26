package com.cookandroid.team_project

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.cookandroid.team_project.ui.Leagues
import com.cookandroid.team_project.ui.MainViewModel
import com.cookandroid.team_project.ui.players.AssistsFragment
import com.cookandroid.team_project.ui.players.ScorersFragment
import com.cookandroid.team_project.ui.standings.StandingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var spinnerLeague: Spinner
    private lateinit var bottomNav: BottomNavigationView

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

        // Bottom navigation
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_standings -> {
                    showFragment(StandingsFragment()); true
                }
                R.id.nav_scorers -> {
                    showFragment(ScorersFragment()); true
                }
                R.id.nav_assists -> {
                    showFragment(AssistsFragment()); true
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_standings
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}