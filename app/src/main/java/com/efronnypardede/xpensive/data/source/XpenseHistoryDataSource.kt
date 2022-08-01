package com.efronnypardede.xpensive.data.source

import com.efronnypardede.xpensive.data.entity.XpenseHistory
import com.efronnypardede.xpensive.data.entity.XpenseHistoryDatabaseView

interface XpenseHistoryDataSource {
    suspend fun fetchAll(): List<XpenseHistoryDatabaseView>
    suspend fun addNew(history: XpenseHistory): Long
}
