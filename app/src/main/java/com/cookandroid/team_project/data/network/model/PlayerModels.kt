package com.cookandroid.team_project.data.network.model

data class PlayerStatsWrapper(
    val player: Player,
    val statistics: List<PlayerStatistics> = emptyList()
)

data class Player(
    val id: Int?,
    val name: String?,
    val photo: String?
)

data class PlayerStatistics(
    val team: TeamRef?,
    val goals: PlayerGoals?
)

data class TeamRef(
    val id: Int?,
    val name: String?,
    val logo: String?
)

data class PlayerGoals(
    val total: Int?,
    val assists: Int?
)


