package com.applike.sqlitekotlin.data.repository

import com.applike.sqlitekotlin.data.local.db.AppDatabase
import com.applike.sqlitekotlin.data.local.db.dao.PlayerDao
import com.applike.sqlitekotlin.data.local.db.dao.TeamDao
import com.applike.sqlitekotlin.data.model.Player
import com.applike.sqlitekotlin.data.model.Team

class AppRepository(private val teamDao: TeamDao, val playerDao: PlayerDao) {

    fun saveTeam(team: Team): Boolean {
        return teamDao.save(team)
    }

    fun savePlayer(player: Player): Boolean {
        return playerDao.save(player)
    }

    fun updateTeam(id: String, team: Team): Boolean {
        return teamDao.save(id, team)
    }

    fun findAllTeams(): List<Team> {
        return teamDao.findAll()
    }

    fun findAllPlayers(): List<Player> {
        return playerDao.findAll()
    }

    fun findTeamById(id: String): Team {
        return teamDao.find(AppDatabase.TEAM_ID, id)[0]
    }

    fun findPlayerById(id: String): Player {
        return playerDao.find(AppDatabase.PLAYER_ID, id)[0]
    }

    fun findTeamsByName(name: String): List<Team> {
        return teamDao.find(AppDatabase.TEAM_NAME, name)
    }

    fun findTeamsByGround(ground: String): List<Team> {
        return teamDao.find(AppDatabase.TEAM_GROUND, ground)
    }

    fun deleteTeam(id: String): Boolean {
        return teamDao.delete(id)
    }

    fun findTeamByPlayer(playerId: String): Team {
        return findTeamById(findPlayerById(playerId).teamId.toString())
    }

    fun findPlayersByTeam(teamId: String): List<Player> {
        return playerDao.find(AppDatabase.PLAYER_TEAM_ID, teamId)
    }

    fun findPlayersByTeam(team: Team): List<Player> {
        return playerDao.find(AppDatabase.PLAYER_TEAM_ID, team.id.toString())
    }
}