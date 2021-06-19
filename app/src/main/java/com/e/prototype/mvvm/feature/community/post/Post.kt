package com.e.prototype.mvvm.feature.community.post

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    @ColumnInfo val userId: String,
    @ColumnInfo var kind: Int,
    @ColumnInfo var title: String,
    @ColumnInfo var content: String,
    @ColumnInfo var createdAt: String,
    @ColumnInfo val updatedAt: String?,
)

