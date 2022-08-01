package com.efronnypardede.xpensive.data.repository

import android.util.Log
import com.efronnypardede.xpensive.data.entity.XpenseSource
import com.efronnypardede.xpensive.data.source.XpenseSourceDataSource
import javax.inject.Inject

class XpenseSourceDataRepository @Inject constructor(
    private val localDataSource: XpenseSourceDataSource,
) : XpenseSourceRepository {

    override suspend fun fetchAll(): List<XpenseSource> {
        return localDataSource.fetchAll().also {
            for (d in it) {
                Log.d("EP", "$d")
            }
        }
    }

    override suspend fun addNew(xpenseSource: XpenseSource): Long {
        return localDataSource.addNew(xpenseSource)
    }
}
