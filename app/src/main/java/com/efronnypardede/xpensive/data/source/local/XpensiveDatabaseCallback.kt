package com.efronnypardede.xpensive.data.source.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.efronnypardede.xpensive.R
import com.efronnypardede.xpensive.data.entity.XpenseSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class XpensiveDatabaseCallback @Inject constructor(
    @ApplicationContext private val context: Context
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        val contentValues = ContentValues().apply {
            val defaultName = context.getString(R.string.default_expense_source)
            val defaultDescription = context.getString(R.string.default_expense_source_description)
            put(XpenseSource.ID_COLUMN, 1L)
            put(XpenseSource.NAME_COLUMN, defaultName)
            put(XpenseSource.DESCRIPTION_COLUMN, defaultDescription)
        }

        db.apply {
            beginTransaction()
            insert(XpenseSource.TABLE_NAME, SQLiteDatabase.CONFLICT_REPLACE, contentValues)
            endTransaction()
        }
    }
}
