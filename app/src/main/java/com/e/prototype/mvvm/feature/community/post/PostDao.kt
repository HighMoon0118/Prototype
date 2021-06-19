package com.e.prototype.mvvm.feature.community.post

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDao {
    @Insert
    fun insert(post: Post)

    @Update
    fun update(post: Post)

    @Delete
    fun delete(post: Post)

    @Query("SELECT * From Post WHERE id == (:id)")
    fun getPost(id: Int): Post

    @Query("SELECT * FROM Post WHERE kind == (:kind)")
    fun getPosts(kind: Int): LiveData<List<Post>>
}