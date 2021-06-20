package com.e.prototype.mvvm.feature.main

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e.prototype.mvvm.feature.community.post.Post
import com.e.prototype.mvvm.feature.community.post.PostDao
import com.e.prototype.mvvm.feature.community.post.PostDatabase

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val _currentItem = MutableLiveData<Int>(0)
    val currentItem: MutableLiveData<Int> get() = _currentItem

    private val _gossipList: LiveData<List<Post>>
    private val _interviewList: LiveData<List<Post>>
    private val _coverLetterList: LiveData<List<Post>>
//    private val _myPostList: LiveData<List<Post>>
    val gossipList: LiveData<List<Post>> get() = _gossipList
    val coverLetterList: LiveData<List<Post>> get() = _coverLetterList
    val interviewList: LiveData<List<Post>> get() = _interviewList
//    val myPostList: LiveData<List<Post>> get() = _myPostList
    private val postDao: PostDao

    init {

        val db: PostDatabase? = PostDatabase.getInstance(application)
        postDao = db?.getPostDao()!!
        _gossipList = GetPostsAsyncTask(postDao, 0).execute().get()
        _coverLetterList = GetPostsAsyncTask(postDao, 1).execute().get()
        _interviewList = GetPostsAsyncTask(postDao, 2).execute().get()
//        _myPostList = GetPostsAsyncTask(postDao, 0).execute().get()
    }

    private class GetPostsAsyncTask constructor(private val postDao: PostDao, private val kind: Int):
        AsyncTask<Unit, Unit, LiveData<List<Post>>>() {

        override fun doInBackground(vararg params: Unit?): LiveData<List<Post>> {
            Log.d("데이터 하나 추가?????", "ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ")
            return postDao.getPosts(kind)
        }
    }

    fun setCurrentItem(item: Int) {
        _currentItem.value = item
    }
}