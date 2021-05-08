package com.e.prototype.mvvm.feature.calendar

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.e.prototype.R
import com.e.prototype.databinding.ActivityMainBinding
import com.e.prototype.databinding.FragmentCalendarBinding
import com.e.prototype.mvvm.feature.main.MainActivity
import com.e.prototype.mvvm.feature.main.ScheduleViewModel

class CalendarFragment: Fragment() {

    private lateinit var mContext: Context
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScheduleViewModel by lazy {
        ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory(Application())).get(ScheduleViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCalendarBinding.inflate(inflater, container,false) // view binding

        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}