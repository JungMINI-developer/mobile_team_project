package com.cookandroid.team_project.data.network.model

data class StandingsLeagueWrapper(
    val league: StandingsLeague
)

data class StandingsLeague(
    val id: Int?,
    val name: String?,
    val country: String?,
    val season: Int?,
    val standings: List<List<StandingTeam>> = emptyList()
)

data class StandingTeam(
    val rank: Int,
    val team: Team,
    val points: Int,
    val goalsDiff: Int,
    val all: AllRecord
)

data class Team(
    val id: Int?,
    val name: String?,
    val logo: String?
)

data class AllRecord(
    val played: Int,
    val win: Int,
    val draw: Int,
    val lose: Int,
    val goals: Goals
)

data class Goals(
    val `for`: Int?,
    val against: Int?
)


