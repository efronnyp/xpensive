package com.efronnypardede.xpensive.di

import android.content.Context
import androidx.room.Room
import com.efronnypardede.xpensive.data.source.local.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideXpensiveDatabase(
        @ApplicationContext context: Context,
        callback: XpensiveDatabaseCallback
    ): XpensiveDatabase =
        Room.databaseBuilder(context, XpensiveDatabase::class.java, "xpensive.db")
            .addCallback(callback)
            .build()

    @Provides
    @Singleton
    fun provideXpenseSourceDao(database: XpensiveDatabase): XpenseSourceDao =
        database.xpenseSourceDao

    @Provides
    @Singleton
    fun provideXpenseHistoryDao(database: XpensiveDatabase): XpenseHistoryDao =
        database.xpenseHistoryDao

    @Provides
    @Singleton
    fun provideNumberFormatter(): NumberFormat {
        return NumberFormat.getInstance().also {
            if (it is DecimalFormat) {
                it.decimalFormatSymbols.apply {
                    currencySymbol = ""
                    groupingSeparator = '.'
                }
            }
        }
    }

    @Provides
    @Singleton
    @FullDateFormatter
    fun provideFullDateFormatter(): DateFormat {
        return SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    }
}
