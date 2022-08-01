package com.efronnypardede.xpensive.data.source.local

import android.content.Context
import androidx.annotation.DrawableRes
import com.efronnypardede.xpensive.R
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class XpenseTypeMapper @Inject constructor(
    @ActivityContext private val context: Context,
) {
    val xpenseTypeMap: Map<Int, String> = mapOf(
        R.id.xpense_type_personal to context.getString(R.string.xpense_type_personal),
        R.id.xpense_type_business to context.getString(R.string.xpense_type_business),
        R.id.xpense_type_daily to context.getString(R.string.xpense_type_daily),
        R.id.xpense_type_debt to context.getString(R.string.xpense_type_debt),
        R.id.xpense_type_grocery to context.getString(R.string.xpense_type_grocery),
        R.id.xpense_type_other to context.getString(R.string.xpense_type_other),
    )

    fun fromIdToString(id: Int): String {
        return xpenseTypeMap[id] ?: xpenseTypeMap.entries.last().value
    }

    fun fromStringToId(name: String): Int {
        return xpenseTypeMap.entries.firstOrNull { it.value == name }?.key
            ?: xpenseTypeMap.entries.last().key
    }

    @DrawableRes
    fun fromIdToIcon(id: Int): Int {
        return when (id) {
            R.id.xpense_type_personal -> R.drawable.ic_diamond_linear_fill
            R.id.xpense_type_business -> R.drawable.ic_chart_linear_fill
            R.id.xpense_type_daily -> R.drawable.ic_calendar_curreny_linear_fill
            R.id.xpense_type_debt -> R.drawable.ic_debt_linear_fill
            R.id.xpense_type_grocery -> R.drawable.ic_grocery_linear_fill
            else -> R.drawable.ic_coins_linear_fill
        }
    }
}
