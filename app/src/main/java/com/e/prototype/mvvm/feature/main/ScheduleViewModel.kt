package com.e.prototype.mvvm.feature.main

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.e.prototype.mvvm.feature.calendar.Schedule
import com.e.prototype.mvvm.feature.calendar.ScheduleDao
import com.e.prototype.mvvm.feature.calendar.ScheduleDatabase

class ScheduleViewModel(application: Application) : AndroidViewModel(application) {

    private val _scheduleList: MutableLiveData<List<Schedule>>
    val scheduleList: LiveData<List<Schedule>> get() = _scheduleList

    private val scheduleDao: ScheduleDao

    init {
       val db: ScheduleDatabase = Room.databaseBuilder(application, ScheduleDatabase::class.java, "schedule").build()
       scheduleDao = db.getScheduleDao()
       _scheduleList = db.getScheduleDao().getAll()
    }

    fun insert(schedule: Schedule) {
        InsertScheduleAsyncTask(scheduleDao).execute(schedule)
    }

    private class InsertScheduleAsyncTask constructor(private val scheduleDao: ScheduleDao) : AsyncTask<Schedule, Unit, Unit>() {
        override fun doInBackground(vararg schedule: Schedule?) {
            scheduleDao.insert(schedule[0]!!)
        }
    }
}