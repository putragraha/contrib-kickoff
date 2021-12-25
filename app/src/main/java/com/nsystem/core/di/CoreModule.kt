package com.nsystem.core.di

import android.content.Context
import androidx.room.Room
import com.nsystem.core.ApplicationDatabase
import com.nsystem.core.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): ApplicationDatabase {
        return Room
            .databaseBuilder(context, ApplicationDatabase::class.java, Constants.DB_NAME)
            .build()
    }
}