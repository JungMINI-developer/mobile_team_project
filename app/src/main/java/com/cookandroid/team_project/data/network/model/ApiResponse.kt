package com.cookandroid.team_project.data.network.model

data class ApiResponse<T>(
    val response: List<T> = emptyList()
)


