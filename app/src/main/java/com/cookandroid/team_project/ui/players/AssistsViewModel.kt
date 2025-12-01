package com.cookandroid.team_project.ui.players

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookandroid.team_project.data.model.PlayerRow
import com.cookandroid.team_project.data.repository.FootballRepository
import kotlinx.coroutines.launch

class AssistsViewModel(
    private val repository: FootballRepository
) : ViewModel() {
    private val _rows = MutableLiveData<List<PlayerRow>>(emptyList())
    val rows: LiveData<List<PlayerRow>> = _rows

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun load(leagueId: Int, season: Int) {
        _loading.value = true
        viewModelScope.launch {
            try {
                _rows.value = repository.getTopAssists(leagueId, season)
            } catch (e: Exception) {
                Log.e("AssistsViewModel", "getTopAssists failed", e)
                _rows.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
}


