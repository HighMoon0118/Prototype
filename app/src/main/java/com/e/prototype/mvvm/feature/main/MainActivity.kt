package com.e.prototype.mvvm.feature.main

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.e.prototype.R
import com.e.prototype.mvvm.feature.calendar.CalendarFragment
import com.e.prototype.mvvm.feature.calendar.ScheduleDialog

class MainActivity : AppCompatActivity(){


    private val viewModel: ScheduleViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(ScheduleViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, CalendarFragment())
            .commit()


    }
}
