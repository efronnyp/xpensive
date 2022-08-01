package com.efronnypardede.xpensive.data.repository

import com.efronnypardede.xpensive.data.entity.XpenseSource
import com.efronnypardede.xpensive.data.source.XpenseSourceDataSource
import javax.inject.Inject

class XpenseSourceDataRepository @Inject constructor(
    private val localDataSource: XpenseSourceDataSource,
) : XpenseSourceRepository {

    override suspend fun fetchAll(): List<XpenseSource> {
        return localDataSource.fetchAll()
    }

    override suspend fun addNew(xpenseSource: XpenseSource): Long {
        return localDataSource.addNew(xpenseSource)
    }
}
