package com.efronnypardede.xpensive.data.repository

import com.efronnypardede.xpensive.data.entity.XpenseHistory
import com.efronnypardede.xpensive.data.entity.XpenseHistoryDatabaseView
import com.efronnypardede.xpensive.data.source.XpenseHistoryDataSource
import javax.inject.Inject

class XpenseHistoryDataRepository @Inject constructor(
    private val localDataSource: XpenseHistoryDataSource,
) : XpenseHistoryRepository {
    override suspend fun fetchAll(): List<XpenseHistoryDatabaseView> {
        return localDataSource.fetchAll()
    }

    override suspend fun addNew(history: XpenseHistory): Long {
        return localDataSource.addNew(history)
    }
}
