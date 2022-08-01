package com.efronnypardede.xpensive.data.entity

import androidx.room.*
import java.util.*

@Entity(
    tableName = "xpense_histories",
    foreignKeys = [
        ForeignKey(
            entity = XpenseSource::class,
            parentColumns = ["id"],
            childColumns = ["source_id"],
            onDelete = ForeignKey.RESTRICT,
        ),
    ],
    indices = [
        Index(
            value = ["date"],
            orders = [Index.Order.DESC],
        ),
    ],
)
data class XpenseHistory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "source_id", index = true) val sourceId: Long,
    @ColumnInfo(name = "type_id", index = true) val typeId: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "date") val date: Date,
)
