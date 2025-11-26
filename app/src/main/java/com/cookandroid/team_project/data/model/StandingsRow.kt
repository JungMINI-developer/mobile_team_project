package com.cookandroid.team_project.data.model

data class StandingsRow(
    val rank: Int,
    val teamName: String,
    val played: Int,
    val win: Int,
    val draw: Int,
    val lose: Int,
    val points: Int,
    val goalDiff: Int,
    val teamLogoUrl: String?
)


