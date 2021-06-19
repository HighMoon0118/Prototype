package com.e.prototype.mvvm.feature.community.post

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.e.prototype.mvvm.feature.calendar.Schedule
import com.e.prototype.mvvm.feature.calendar.ScheduleDao
import com.e.prototype.mvvm.feature.community.post.Post
import com.e.prototype.mvvm.feature.community.post.PostDao
import com.e.prototype.mvvm.feature.community.post.PostDatabase
import com.e.prototype.mvvm.feature.main.ScheduleViewModel

class PostDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val postDao: PostDao

    init {
        val db: PostDatabase? =  PostDatabase.getInstance(application)
        postDao = db?.getPostDao()!!
    }

    fun getPost(id: Int): Post {
        return GetPost(postDao, id).execute().get()
    }

    private class GetPost constructor(private val postDao: PostDao, private val id: Int) : AsyncTask<Unit, Unit, Post>() {
        override fun doInBackground(vararg params: Unit?): Post {
            return postDao.getPost(id)
        }
    }
}