package com.e.prototype.mvvm.feature.calendar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val isStudy: Boolean,
    @ColumnInfo val studyId: String? = null,
    @ColumnInfo val roomId: String? = null,
    @ColumnInfo val title: String,
    @ColumnInfo val content: String,
    @ColumnInfo val date: String,
    @ColumnInfo val hours: Int
)

