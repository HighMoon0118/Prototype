package com.e.prototype.mvvm.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.e.prototype.R
import com.e.prototype.databinding.ActivityMainBinding
import com.e.prototype.mvvm.feature.calendar.CalendarFragment
import com.e.prototype.mvvm.feature.community.CommunityFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.navigation.setOnNavigationItemSelectedListener(this)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, CalendarFragment())
            .commit()


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.bnv_calendar -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, CalendarFragment())
                    .commit()
                return true
            }
            R.id.bnv_community -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, CommunityFragment())
                    .commit()
                return true
            }
        }
        return false
    }
}
