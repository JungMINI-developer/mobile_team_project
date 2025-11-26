package com.cookandroid.team_project.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class LeagueSelection(
    val leagueId: Int,
    val season: Int
)

object Leagues {
    // API-Football League IDs
    const val PREMIER_LEAGUE = 39
    const val LA_LIGA = 140
    const val SERIE_A = 135
    const val BUNDESLIGA = 78

    fun names(): List<String> = listOf("프리미어리그", "라리가", "세리에 A", "분데스리가")
    fun idForIndex(index: Int): Int = when (index) {
        0 -> PREMIER_LEAGUE
        1 -> LA_LIGA
        2 -> SERIE_A
        3 -> BUNDESLIGA
        else -> PREMIER_LEAGUE
    }
}

class MainViewModel : ViewModel() {
    // 시즌 연도를 2023년으로 고정
    private val fixedSeasonYear: Int = 2023
    private val _selection = MutableLiveData(
        LeagueSelection(Leagues.PREMIER_LEAGUE, fixedSeasonYear)
    )
    val selection: LiveData<LeagueSelection> = _selection

    fun setLeagueByIndex(index: Int) {
        val season = _selection.value?.season ?: fixedSeasonYear
        _selection.value = LeagueSelection(Leagues.idForIndex(index), season)
    }
}


