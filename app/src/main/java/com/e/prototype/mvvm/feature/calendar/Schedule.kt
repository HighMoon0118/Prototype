package com.e.prototype.mvvm.feature.calendar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey(autoGenerate = true) val id: String,
    @ColumnInfo val isStudy: Boolean,
    @ColumnInfo val roomId: String,
    @ColumnInfo val title: String,
    @ColumnInfo val content: String,
    @ColumnInfo val hours: Int
)

