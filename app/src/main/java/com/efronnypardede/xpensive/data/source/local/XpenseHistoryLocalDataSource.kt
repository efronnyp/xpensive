package com.efronnypardede.xpensive.data.source.local

import com.efronnypardede.xpensive.data.entity.XpenseHistory
import com.efronnypardede.xpensive.data.entity.XpenseHistoryDatabaseView
import com.efronnypardede.xpensive.data.source.XpenseHistoryDataSource
import javax.inject.Inject

class XpenseHistoryLocalDataSource @Inject constructor(
    private val dao: XpenseHistoryDao,
) : XpenseHistoryDataSource {
    override suspend fun fetchAll(): List<XpenseHistoryDatabaseView> {
        return dao.findAll()
    }

    override suspend fun addNew(history: XpenseHistory): Long {
        return dao.insert(history)
    }
}
