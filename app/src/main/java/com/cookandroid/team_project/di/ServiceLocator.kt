package com.cookandroid.team_project.di

import com.cookandroid.team_project.data.network.ApiClient
import com.cookandroid.team_project.data.repository.FootballRepository

object ServiceLocator {
    val repository: FootballRepository by lazy {
        FootballRepository(ApiClient.service)
    }
}


