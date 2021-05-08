package com.e.prototype.mvvm.feature.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface ScheduleDao {
    @Insert
    fun insert(schedule: Schedule)

    @Update
    fun update(schedule: Schedule)

    @Delete
    fun delete(schedule: Schedule)

    @Query("SELECT * FROM Schedule")
    fun getAll(): MutableLiveData<List<Schedule>>

    @Query("SELECT * From Schedule WHERE id == (:id)")
    fun getSchedule(id: String): Schedule
}