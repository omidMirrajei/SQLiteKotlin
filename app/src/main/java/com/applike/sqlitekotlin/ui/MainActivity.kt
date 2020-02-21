package com.applike.sqlitekotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.applike.sqlitekotlin.R
import com.applike.sqlitekotlin.data.local.db.AppDatabase
import com.applike.sqlitekotlin.data.local.db.dao.PlayerDao
import com.applike.sqlitekotlin.data.local.db.dao.TeamDao
import com.applike.sqlitekotlin.data.model.Player
import com.applike.sqlitekotlin.data.model.Team
import com.applike.sqlitekotlin.data.repository.AppRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appDatabase = AppDatabase(this)

        /*val barcelona = Team(1, "Barcelona", "New Camp")
        val readMadrid = Team("Real Madrid", "Santiago")
        val juventus = Team("Juventus", "Alianz")*/

        /* val messi = Player("Messi", 1)
         val suarez = Player("Suarez", 1)
         val ronaldo = Player("CR7", 2)*/


        val teamDao = TeamDao(appDatabase)
        val playerDao = PlayerDao(appDatabase)

        val appRepository = AppRepository(teamDao, playerDao)


        /*appRepository.saveTeam(barcelona)
        appRepository.saveTeam(readMadrid)
        appRepository.saveTeam(juventus)*/

        /*appRepository.savePlayer(messi)
        appRepository.savePlayer(suarez)
        appRepository.savePlayer(ronaldo)*/

        // Log.i("FIND", appRepository.findTeamById("5").toString())

        /*val result = appRepository.deleteTeam("5")
        Log.i("TestDelete", result.toString())*/

//        val result = appRepository.updateTeam("3", Team("As Room", "San Siro"))
//        Log.i("TestDelete", result.toString())


        appRepository.saveTeam(Team(name = "Arcenal", ground = "STest", manager = "Perez"))
        appRepository.findAllTeams().forEach { team -> Log.i("Team", team.toString()) }
//        appRepository.findAllPlayers().forEach { team -> Log.i("Player", team.toString())}

//        Log.i("Team", appRepository.findTeamByPlayer("3").toString())
//        appRepository.findPlayersByTeam(barcelona).forEach {
//            Log.i("FindPlayer", it.toString())
//        }


    }
}
