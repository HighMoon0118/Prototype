package com.e.prototype.mvvm.feature.community.gossip

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.e.prototype.mvvm.feature.calendar.Schedule
import com.e.prototype.mvvm.feature.calendar.ScheduleDao
import com.e.prototype.mvvm.feature.calendar.ScheduleDatabase
import com.e.prototype.mvvm.feature.community.post.Post
import com.e.prototype.mvvm.feature.community.post.PostDao
import com.e.prototype.mvvm.feature.community.post.PostDatabase


class GossipViewModel(application: Application) : AndroidViewModel(application) {

    private val _gossipList: LiveData<List<Post>>
    val gossipList: LiveData<List<Post>> get() = _gossipList
    private val postDao: PostDao

    init {
        val db: PostDatabase? = PostDatabase.getInstance(application)
        postDao = db?.getPostDao()!!
        _gossipList = GetAllScheduleAsyncTask(postDao, 0).execute().get()
    }

    private class GetAllScheduleAsyncTask constructor(private val postDao: PostDao, private val kind: Int) : AsyncTask<Unit, Unit, LiveData<List<Post>>>() {
        override fun doInBackground(vararg params: Unit?): LiveData<List<Post>> {
            Log.d("데이터 하나 추가?????", "ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ")
            return postDao.getPosts(kind)
        }
    }
}