package com.e.prototype.mvvm.feature.community.comment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val userId: String,
    @ColumnInfo val postId: String,
    @ColumnInfo val content: String,
    @ColumnInfo val createdAt: String,
)

