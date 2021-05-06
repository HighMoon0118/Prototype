package com.e.prototype.mvvm.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.prototype.mvvm.feature.calendar.Schedule

class SharedViewModel : ViewModel() {

    val schedules : MutableLiveData<Schedule> by lazy {
        MutableLiveData<Schedule>()
    }

}