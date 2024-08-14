package com.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.data.database.dao.JobDao
import com.data.database.entity.JobEntity

@Database(entities = [JobEntity::class], version = 3, exportSchema = false)
abstract class CalendarDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao
}