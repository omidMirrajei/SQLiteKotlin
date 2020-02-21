package com.applike.sqlitekotlin.data.local.db.dao

import android.content.ContentValues
import com.applike.sqlitekotlin.data.local.db.AppDatabase
import com.applike.sqlitekotlin.data.local.db.ContentValuesUpdater
import com.applike.sqlitekotlin.data.local.db.CursorConverter
import com.applike.sqlitekotlin.data.model.Team

abstract class BaseDao<T>(val appDatabase: AppDatabase) :
    ContentValuesUpdater<T>, CursorConverter<T>{

    val contentValues = ContentValues()
    val data : MutableList<T> = ArrayList()
    var query = ""

    abstract fun save(entity: T): Boolean

    abstract fun save(id: String, entity: T): Boolean

    abstract fun findAll(): List<T>

    abstract fun find(columnName: String, columnValue: String): List<T>

    abstract fun delete(id: String): Boolean
}