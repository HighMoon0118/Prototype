package com.e.prototype.mvvm.feature.community.post

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel

class AddPostViewModel(application: Application) : AndroidViewModel(application) {

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