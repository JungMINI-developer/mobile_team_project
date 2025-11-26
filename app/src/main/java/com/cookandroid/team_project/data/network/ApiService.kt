package com.cookandroid.team_project.data.network

import com.cookandroid.team_project.data.network.model.ApiResponse
import com.cookandroid.team_project.data.network.model.PlayerStatsWrapper
import com.cookandroid.team_project.data.network.model.StandingsLeagueWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("standings")
    suspend fun getStandings(
        @Query("league") league: Int,
        @Query("season") season: Int
    ): ApiResponse<StandingsLeagueWrapper>

    @GET("players/topscorers")
    suspend fun getTopScorers(
        @Query("league") league: Int,
        @Query("season") season: Int
    ): ApiResponse<PlayerStatsWrapper>

    @GET("players/topassists")
    suspend fun getTopAssists(
        @Query("league") league: Int,
        @Query("season") season: Int
    ): ApiResponse<PlayerStatsWrapper>
}


