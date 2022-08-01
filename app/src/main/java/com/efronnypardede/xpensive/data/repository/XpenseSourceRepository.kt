package com.efronnypardede.xpensive.data.repository

import com.efronnypardede.xpensive.data.entity.XpenseSource

interface XpenseSourceRepository {
    suspend fun fetchAll(): List<XpenseSource>
    suspend fun addNew(xpenseSource: XpenseSource): Long
}
