package com.efronnypardede.xpensive.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.efronnypardede.xpensive.data.entity.XpenseHistory
import com.efronnypardede.xpensive.data.entity.XpenseHistoryDatabaseView
import com.efronnypardede.xpensive.data.entity.XpenseSource

@Database(
    entities = [
        XpenseHistory::class,
        XpenseSource::class,
    ],
    views = [XpenseHistoryDatabaseView::class],
    version = 1,
)
@TypeConverters(DatabaseDataConverter::class)
abstract class XpensiveDatabase : RoomDatabase() {
    abstract val xpenseHistoryDao: XpenseHistoryDao
    abstract val xpenseSourceDao: XpenseSourceDao
}
