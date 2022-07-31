package com.efronnypardede.xpensive.data.source.local

import androidx.room.*
import com.efronnypardede.xpensive.data.entity.XpenseSource

@Dao()
interface XpenseSourceDao {
    @Query(value = "SELECT * FROM xpense_sources ORDER BY name")
    suspend fun findAll(): List<XpenseSource>

    @Insert
    suspend fun insert(xpenseSource: XpenseSource): Int

    @Update
    suspend fun update(xpenseSource: XpenseSource): XpenseSource

    @Delete
    suspend fun delete(xpenseSource: XpenseSource): Int
}