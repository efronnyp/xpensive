package com.efronnypardede.xpensive.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.efronnypardede.xpensive.data.entity.XpenseHistory
import com.efronnypardede.xpensive.data.entity.XpenseHistoryDatabaseView

@Dao
interface XpenseHistoryDao {
    @Query("SELECT * FROM xpense_history_view")
    suspend fun findAll(): List<XpenseHistoryDatabaseView>

    @Query("SELECT * FROM xpense_history_view WHERE id = :id")
    suspend fun findById(id: Long): XpenseHistoryDatabaseView?

    @Insert
    suspend fun insert(history: XpenseHistory): Long
}
