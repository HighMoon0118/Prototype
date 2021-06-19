package com.e.prototype.mvvm.feature.community.post

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.e.prototype.mvvm.feature.community.post.Post
import com.e.prototype.mvvm.feature.community.post.PostDao

@Database(entities = arrayOf(Post::class), version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun getPostDao(): PostDao

    companion object {
        private var Instance: PostDatabase? = null

        fun getInstance(context: Context): PostDatabase? {
            if(Instance == null) {
                synchronized(PostDatabase::class) {
                    Instance = Room.databaseBuilder(
                        context,
                        PostDatabase::class.java,
                        "post"
                    ).build()
                }
            }
            return Instance
        }
    }
}