package com.applike.sqlitekotlin.data.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "test.db"
        private const val DATABASE_VERSION = 3

        const val TEAM_TABLE = "teams"
        const val TEAM_ID = "id"
        const val TEAM_NAME = "name"
        const val TEAM_GROUND = "ground"
        const val TEAM_MANAGER = "manager"

        const val PLAYER_TABLE = "players"
        const val PLAYER_ID = "id"
        const val PLAYER_NAME = "name"
        const val PLAYER_TEAM_ID = "team_id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableTeam = "CREATE TABLE IF NOT EXISTS $TEAM_TABLE (" +
                "$TEAM_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$TEAM_NAME TEXT, " +
                "$TEAM_GROUND TEXT)" +
                "$TEAM_MANAGER TEXT"
        db!!.execSQL(createTableTeam)

        val createTablePlayer = "CREATE TABLE IF NOT EXISTS $PLAYER_TABLE (" +
                "$PLAYER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$PLAYER_NAME TEXT, " +
                "$PLAYER_TEAM_ID INTEGER, " +
                "FOREIGN KEY($PLAYER_TEAM_ID) REFERENCES $TEAM_TABLE($TEAM_ID))"
        db.execSQL(createTablePlayer)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2 ) {
            upgradeVersion2()
        } else if (oldVersion < 3) {
            upgradeVersion3(db)
        }
    }

    private fun upgradeVersion2() {

    }

    private fun upgradeVersion3(db : SQLiteDatabase?) {
        db!!.execSQL("ALTER TABLE $TEAM_TABLE ADD COLUMN $TEAM_MANAGER TEXT")
    }
}