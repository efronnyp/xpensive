package com.efronnypardede.xpensive.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.efronnypardede.xpensive.data.entity.XpenseHistory

@Dao
interface XpenseHistoryDao {
    @Query("SELECT * FROM xpense_histories ORDER BY date DESC")
    suspend fun findAll(): List<XpenseHistory>

    @Insert
    suspend fun insert(history: XpenseHistory): Long
}
