package com.efronnypardede.xpensive.data.source

import com.efronnypardede.xpensive.data.entity.XpenseSource

interface XpenseSourceDataSource {
    suspend fun fetchAll(): List<XpenseSource>
    suspend fun fetchById(id: Long): XpenseSource?
    suspend fun addNew(xpenseSource: XpenseSource): Long
    suspend fun update(xpenseSource: XpenseSource): Int
}
