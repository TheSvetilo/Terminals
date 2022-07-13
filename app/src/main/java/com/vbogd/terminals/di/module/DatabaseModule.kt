package com.vbogd.terminals.di.module

import android.content.Context
import androidx.room.Room
import com.vbogd.terminals.data.AppDatabase
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
            "Orders.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}