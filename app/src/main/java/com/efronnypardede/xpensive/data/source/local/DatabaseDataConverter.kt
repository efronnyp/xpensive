package com.efronnypardede.xpensive.data.source.local

import androidx.room.TypeConverter
import java.util.*

object DatabaseDataConverter {
    @TypeConverter
    fun fromDateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun fromTimestampToDate(timestamp: Long?): Date? = timestamp?.let(::Date)
}
