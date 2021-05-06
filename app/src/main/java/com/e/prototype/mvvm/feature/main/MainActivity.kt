package com.e.prototype.mvvm.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.e.prototype.R
import com.e.prototype.databinding.ActivityMainBinding
import com.e.prototype.mvvm.feature.calendar.CalendarFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, CalendarFragment())
            .commit()
    }

}
