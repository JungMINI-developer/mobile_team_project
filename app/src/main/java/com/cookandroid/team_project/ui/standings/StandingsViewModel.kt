package com.cookandroid.team_project.ui.standings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookandroid.team_project.data.model.StandingsRow
import com.cookandroid.team_project.data.repository.FootballRepository
import kotlinx.coroutines.launch

class StandingsViewModel(
    private val repository: FootballRepository
) : ViewModel() {
    private val _rows = MutableLiveData<List<StandingsRow>>(emptyList())
    val rows: LiveData<List<StandingsRow>> = _rows

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun load(leagueId: Int, season: Int) {
        _loading.value = true
        viewModelScope.launch {
            try {
                _rows.value = repository.getStandings(leagueId, season)
            } catch (e: Exception) {
                Log.e("StandingsViewModel", "getStandings failed", e)
                _rows.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
}

