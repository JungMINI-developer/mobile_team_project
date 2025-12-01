package com.cookandroid.team_project.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cookandroid.team_project.ui.players.AssistsFragment
import com.cookandroid.team_project.ui.players.ScorersFragment
import com.cookandroid.team_project.ui.standings.StandingsFragment

class MainPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StandingsFragment()
            1 -> ScorersFragment()
            2 -> AssistsFragment()
            else -> StandingsFragment()
        }
    }
}


