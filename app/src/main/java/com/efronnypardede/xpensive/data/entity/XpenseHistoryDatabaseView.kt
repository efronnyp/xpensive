package com.efronnypardede.xpensive.data.entity

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import java.util.*

@DatabaseView(
    value = """
        SELECT h.id, h.description, s.name AS source, h.amount, h.type_id, h.date
        FROM xpense_histories h
        INNER JOIN xpense_sources s ON h.source_id = s.id
        ORDER BY h.date DESC
    """,
    viewName = "xpense_history_view"
)
data class XpenseHistoryDatabaseView(
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "source") val source: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "type_id") val typeId: Int,
    @ColumnInfo(name = "date") val date: Date,
)
