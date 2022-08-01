package com.efronnypardede.xpensive.data.repository

import com.efronnypardede.xpensive.data.entity.XpenseHistory
import com.efronnypardede.xpensive.data.entity.XpenseHistoryDatabaseView

interface XpenseHistoryRepository {
    suspend fun fetchAll(): List<XpenseHistoryDatabaseView>
    suspend fun addNew(history: XpenseHistory): Long
}
