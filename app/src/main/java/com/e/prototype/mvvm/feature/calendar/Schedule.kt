package com.e.prototype.mvvm.feature.calendar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @ColumnInfo val id: String,
    @ColumnInfo val studyId: String,
    @ColumnInfo val title: String,
    @ColumnInfo val content: String,
    @ColumnInfo val date: String,
    @ColumnInfo val hours: Int
)

