package com.cookandroid.team_project.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cookandroid.team_project.data.repository.FootballRepository
import com.cookandroid.team_project.ui.players.AssistsViewModel
import com.cookandroid.team_project.ui.players.ScorersViewModel
import com.cookandroid.team_project.ui.standings.StandingsViewModel

class StandingsVMFactory(
    private val repository: FootballRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StandingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StandingsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class ScorersVMFactory(
    private val repository: FootballRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScorersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScorersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class AssistsVMFactory(
    private val repository: FootballRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssistsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AssistsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


