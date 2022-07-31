package com.efronnypardede.xpensive.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "xpense_sources",
    indices = [
        Index(
            value = ["name"],
            orders = [Index.Order.ASC],
            unique = true,
        ),
    ],
)
data class XpenseSource(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val description: String,
    val balance: Double = 0.0,
)
