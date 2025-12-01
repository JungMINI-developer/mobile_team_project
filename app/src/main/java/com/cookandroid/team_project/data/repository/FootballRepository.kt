package com.cookandroid.team_project.data.repository

import com.cookandroid.team_project.data.model.PlayerRow
import com.cookandroid.team_project.data.model.StandingsRow
import com.cookandroid.team_project.data.network.ApiService
import com.cookandroid.team_project.data.network.model.PlayerStatsWrapper
import com.cookandroid.team_project.data.network.model.StandingTeam
import com.cookandroid.team_project.data.network.model.StandingsLeagueWrapper

class FootballRepository(
    private val api: ApiService
) {
    suspend fun getStandings(leagueId: Int, season: Int): List<StandingsRow> {
        val result = api.getStandings(leagueId, season)
        val leagueWrapper: StandingsLeagueWrapper = result.response.firstOrNull()
            ?: return emptyList()
        val table: List<StandingTeam> = leagueWrapper.league.standings.firstOrNull()
            ?: emptyList()
        return table.map { s ->
            StandingsRow(
                rank = s.rank,
                teamName = s.team.name.orEmpty(),
                played = s.all.played,
                win = s.all.win,
                draw = s.all.draw,
                lose = s.all.lose,
                points = s.points,
                goalDiff = s.goalsDiff,
                teamLogoUrl = s.team.logo
            )
        }
    }

    suspend fun getTopScorers(leagueId: Int, season: Int): List<PlayerRow> {
        val list = api.getTopScorers(leagueId, season).response
        return mapPlayerList(list) { wrapper ->
            // goals.total
            wrapper.statistics.firstOrNull()?.goals?.total ?: 0
        }
    }

    suspend fun getTopAssists(leagueId: Int, season: Int): List<PlayerRow> {
        val list = api.getTopAssists(leagueId, season).response
        return mapPlayerList(list) { wrapper ->
            // goals.assists
            wrapper.statistics.firstOrNull()?.goals?.assists ?: 0
        }
    }

    private fun mapPlayerList(
        list: List<PlayerStatsWrapper>,
        valueSelector: (PlayerStatsWrapper) -> Int
    ): List<PlayerRow> {
        return list.mapIndexed { index, wrapper ->
            val stats = wrapper.statistics.firstOrNull()
            PlayerRow(
                rank = index + 1,
                playerName = wrapper.player.name.orEmpty(),
                teamName = stats?.team?.name,
                statValue = valueSelector(wrapper),
                matches = stats?.games?.appearences ?: 0,
                playerPhotoUrl = wrapper.player.photo,
                teamLogoUrl = stats?.team?.logo
            )
        }
    }
}


