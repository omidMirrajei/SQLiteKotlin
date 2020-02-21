package com.applike.sqlitekotlin.data.local.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.applike.sqlitekotlin.data.local.db.AppDatabase
import com.applike.sqlitekotlin.data.model.Team

class TeamDao(appDatabase: AppDatabase) : BaseDao<Team>(appDatabase) {

    override fun save(entity: Team): Boolean {
        val db = appDatabase.writableDatabase

        updateContentValues(entity, contentValues)

        val result = db.insert(AppDatabase.TEAM_TABLE, null, contentValues)
        db.close()

        if (result > 0) return true
        return false
    }

    override fun save(id: String, entity: Team): Boolean {
        val db = appDatabase.writableDatabase

        updateContentValues(entity, contentValues)

        val result = db.update(
            AppDatabase.TEAM_TABLE,
            contentValues,
            AppDatabase.TEAM_ID + " = ?",
            arrayOf(id)
        )
        db.close()

        if (result > 0) return true
        return false
    }

    override fun findAll(): List<Team> {
        val db = appDatabase.readableDatabase

        query = "SELECT * FROM ${AppDatabase.TEAM_TABLE}"
        val cursor = db.rawQuery(query, null)

        return cursorToList(cursor, db)
    }

    override fun find(columnName: String, columnValue: String): List<Team> {
        val db = appDatabase.readableDatabase

        query = "SELECT * FROM ${AppDatabase.TEAM_TABLE} WHERE $columnName = ?"
        val cursor = db.rawQuery(query, arrayOf(columnValue))

        return cursorToList(cursor, db)
    }

    override fun delete(id: String): Boolean {
        val db = appDatabase.writableDatabase
        val result = db.delete(AppDatabase.TEAM_TABLE, AppDatabase.TEAM_ID + " = ?", arrayOf(id))
        db.close()

        if (result > 0) return true
        return false
    }

    override fun updateContentValues(entity: Team, contentValues: ContentValues) {
        contentValues.clear()
        contentValues.put(AppDatabase.TEAM_NAME, entity.name)
        contentValues.put(AppDatabase.TEAM_GROUND, entity.ground)
        contentValues.put(AppDatabase.TEAM_MANAGER, entity.manager)
    }

    override fun cursorToList(cursor: Cursor, db: SQLiteDatabase): List<Team> {
        data.clear()
        if (cursor.moveToFirst()) {
            // از while فقط استفاده شود از آیتم دوم شروع میشود.
            // از do استفاده میکنیم تا یه دور قبل از اینکه شرط رو اجرا کنه اجرا بشه.
            do {
                val id = cursor.getString(cursor.getColumnIndex(AppDatabase.TEAM_ID))
                val name = cursor.getString(cursor.getColumnIndex(AppDatabase.TEAM_NAME))
                val ground = cursor.getString(cursor.getColumnIndex(AppDatabase.TEAM_GROUND))
                val manager = cursor.getString(cursor.getColumnIndex(AppDatabase.TEAM_MANAGER))
                data.add(Team(id.toLong(), name, ground, manager))
            } while (cursor.moveToNext())
        }
        // باید cursor قبل از db بسته شود
        cursor.close()
        db.close()

        return data
    }
}