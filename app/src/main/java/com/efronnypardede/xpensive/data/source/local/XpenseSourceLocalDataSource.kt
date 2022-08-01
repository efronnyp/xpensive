package com.efronnypardede.xpensive.data.source.local

import com.efronnypardede.xpensive.data.entity.XpenseSource
import com.efronnypardede.xpensive.data.source.XpenseSourceDataSource
import javax.inject.Inject

class XpenseSourceLocalDataSource @Inject constructor(
    private val xpenseSourceDao: XpenseSourceDao,
) : XpenseSourceDataSource {
    override suspend fun fetchAll(): List<XpenseSource> {
        return xpenseSourceDao.findAll()
    }

    override suspend fun fetchById(id: Long): XpenseSource? {
        return xpenseSourceDao.findById(id)
    }

    override suspend fun addNew(xpenseSource: XpenseSource): Long {
        return xpenseSourceDao.insert(xpenseSource)
    }

    override suspend fun update(xpenseSource: XpenseSource): Int {
        return xpenseSourceDao.update(xpenseSource)
    }
}