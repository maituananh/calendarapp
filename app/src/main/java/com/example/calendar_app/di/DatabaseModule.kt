package com.example.calendar_app.di

import android.content.Context
import androidx.room.Room
import com.data.database.CalendarDatabase
import com.data.database.dao.JobDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesCalendarDatabase(@ApplicationContext context: Context): CalendarDatabase =
        Room.databaseBuilder(context, CalendarDatabase::class.java, "calendar")
            .allowMainThreadQueries().build()

    @Provides
    fun providesJobDao(database: CalendarDatabase): JobDao = database.jobDao()
}