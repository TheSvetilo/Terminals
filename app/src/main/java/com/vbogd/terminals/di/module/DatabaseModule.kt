package com.vbogd.terminals.di.module

import android.content.Context
import androidx.room.Room
import com.vbogd.terminals.data.AppDatabase
import com.vbogd.terminals.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            Constants.ORDER_DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}