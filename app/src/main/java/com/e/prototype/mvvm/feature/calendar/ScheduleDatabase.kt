package com.e.prototype.mvvm.feature.calendar

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Schedule::class), version=1)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun getScheduleDao(): ScheduleDao
}