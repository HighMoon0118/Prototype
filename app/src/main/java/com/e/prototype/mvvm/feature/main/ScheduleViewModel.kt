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

    private val _scheduleList: LiveData<List<Schedule>>
    val scheduleList: LiveData<List<Schedule>> get() = _scheduleList

    private val scheduleDao: ScheduleDao

    init {
       val db: ScheduleDatabase = Room.databaseBuilder(application, ScheduleDatabase::class.java, "schedule").build()
       scheduleDao = db.getScheduleDao()
        _scheduleList = GetAllScheduleAsyncTask(scheduleDao).execute().get()
    }

    fun insert(schedule: Schedule) {
        InsertScheduleAsyncTask(scheduleDao).execute(schedule)
    }
    fun update(schedule: Schedule) {
        UpdateScheduleAsyncTask(scheduleDao).execute(schedule)
    }
    fun delete(schedule: Schedule) {
        DeleteScheduleAsyncTask(scheduleDao).execute(schedule)
    }

    private class GetAllScheduleAsyncTask constructor(private val scheduleDao: ScheduleDao) : AsyncTask<Unit, Unit, LiveData<List<Schedule>>>() {
        override fun doInBackground(vararg params: Unit?): LiveData<List<Schedule>> {
            return scheduleDao.getAll()
        }
    }

    private class InsertScheduleAsyncTask constructor(private val scheduleDao: ScheduleDao) : AsyncTask<Schedule, Unit, Unit>() {
        override fun doInBackground(vararg schedule: Schedule?) {
            scheduleDao.insert(schedule[0]!!)
        }
    }

    private class UpdateScheduleAsyncTask constructor(private val scheduleDao: ScheduleDao) : AsyncTask<Schedule, Unit, Unit>() {
        override fun doInBackground(vararg schedule: Schedule?) {
            scheduleDao.update(schedule[0]!!)
        }
    }

    private class DeleteScheduleAsyncTask constructor(private val scheduleDao: ScheduleDao) : AsyncTask<Schedule, Unit, Unit>() {
        override fun doInBackground(vararg schedule: Schedule?) {
            scheduleDao.delete(schedule[0]!!)
        }
    }
}