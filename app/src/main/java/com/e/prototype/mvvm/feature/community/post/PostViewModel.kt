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
import com.e.prototype.mvvm.feature.community.post.Post
import com.e.prototype.mvvm.feature.community.post.PostDao
import com.e.prototype.mvvm.feature.community.post.PostDatabase

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val postDao: PostDao

    init {
        val db: PostDatabase? =  PostDatabase.getInstance(application)
        postDao = db?.getPostDao()!!
    }

    fun insert(post: Post) {
        InsertPostAsyncTask(postDao).execute(post)
    }

    private class InsertPostAsyncTask constructor(private val postDao: PostDao) : AsyncTask<Post, Unit, Unit>() {
        override fun doInBackground(vararg post: Post?) {
            postDao.insert(post[0]!!)
        }
    }
}