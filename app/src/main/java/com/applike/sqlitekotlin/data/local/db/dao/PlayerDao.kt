package com.applike.sqlitekotlin.data.local.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.applike.sqlitekotlin.data.local.db.AppDatabase
import com.applike.sqlitekotlin.data.model.Player

class PlayerDao(appDatabase: AppDatabase) : BaseDao<Player>(appDatabase) {

    override fun save(entity: Player): Boolean {
        val db = appDatabase.writableDatabase

        updateContentValues(entity, contentValues)

        val result = db.insert(AppDatabase.PLAYER_TABLE, null, contentValues)
        db.close()

        if (result > 0) return true
        return false
    }

    override fun save(id: String, entity: Player): Boolean {
        val db = appDatabase.writableDatabase

        updateContentValues(entity, contentValues)

        val result = db.update(
            AppDatabase.PLAYER_TABLE,
            contentValues,
            AppDatabase.PLAYER_ID + " = ?",
            arrayOf(id)
        )
        db.close()

        if (result > 0) return true
        return false
    }

    override fun findAll(): List<Player> {
        val db = appDatabase.readableDatabase

        query = "SELECT * FROM ${AppDatabase.PLAYER_TABLE}"
        val cursor = db.rawQuery(query, null)

        return cursorToList(cursor, db)
    }

    override fun find(columnName: String, columnValue: String): List<Player> {
        val db = appDatabase.readableDatabase

        query = "SELECT * FROM ${AppDatabase.PLAYER_TABLE} WHERE $columnName = ?"
        val cursor = db.rawQuery(query, arrayOf(columnValue))

        return cursorToList(cursor, db)
    }

    override fun delete(id: String): Boolean {
        val db = appDatabase.writableDatabase
        val result =
            db.delete(AppDatabase.PLAYER_TABLE, AppDatabase.PLAYER_ID + " = ?", arrayOf(id))
        db.close()

        if (result > 0) return true
        return false
    }

    override fun updateContentValues(entity: Player, contentValues: ContentValues) {
        contentValues.clear()
        contentValues.put(AppDatabase.PLAYER_NAME, entity.name)
        contentValues.put(AppDatabase.PLAYER_TEAM_ID, entity.teamId)
    }

    override fun cursorToList(cursor: Cursor, db: SQLiteDatabase): List<Player> {
        data.clear()
        if (cursor.moveToFirst()) {
            // از while فقط استفاده شود از آیتم دوم شروع میشود.
            // از do استفاده میکنیم تا یه دور قبل از اینکه شرط رو اجرا کنه اجرا بشه.
            do {
                val id = cursor.getString(cursor.getColumnIndex(AppDatabase.PLAYER_ID))
                val name = cursor.getString(cursor.getColumnIndex(AppDatabase.PLAYER_NAME))
                val teamId = cursor.getString(cursor.getColumnIndex(AppDatabase.PLAYER_TEAM_ID))
                data.add(Player(id.toLong(), name, teamId.toLong()))
            } while (cursor.moveToNext())
        }
        // باید cursor قبل از db بسته شود
        cursor.close()
        db.close()

        return data
    }

}