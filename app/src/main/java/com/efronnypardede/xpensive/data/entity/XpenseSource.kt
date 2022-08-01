package com.efronnypardede.xpensive.data.entity

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.NOCASE
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = XpenseSource.TABLE_NAME,
    indices = [
        Index(
            value = [XpenseSource.NAME_COLUMN],
            orders = [Index.Order.ASC],
            unique = true,
        ),
    ],
)
data class XpenseSource(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = ID_COLUMN) val id: Long = 0L,
    @ColumnInfo(name = NAME_COLUMN, collate = NOCASE) val name: String,
    @ColumnInfo(name = DESCRIPTION_COLUMN) val description: String,
    @ColumnInfo(name = BALANCE_COLUMN) val balance: Double?,
) {
    companion object {
        const val TABLE_NAME = "xpense_sources"
        const val ID_COLUMN = "id"
        const val NAME_COLUMN = "name"
        const val DESCRIPTION_COLUMN = "description"
        const val BALANCE_COLUMN = "balance"
    }
}
