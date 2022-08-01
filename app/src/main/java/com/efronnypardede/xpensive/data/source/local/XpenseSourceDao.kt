package com.efronnypardede.xpensive.data.source.local

import androidx.room.*
import com.efronnypardede.xpensive.data.entity.XpenseSource

@Dao()
interface XpenseSourceDao {
    @Query(value = "SELECT * FROM xpense_sources")
    suspend fun findAll(): List<XpenseSource>

    @Query(value = "SELECT * FROM xpense_sources WHERE id = :id")
    suspend fun findById(id: Long): XpenseSource?

    @Insert
    suspend fun insert(xpenseSource: XpenseSource): Long

    @Update
    suspend fun update(xpenseSource: XpenseSource): Int

    @Delete
    suspend fun delete(xpenseSource: XpenseSource): Int
}