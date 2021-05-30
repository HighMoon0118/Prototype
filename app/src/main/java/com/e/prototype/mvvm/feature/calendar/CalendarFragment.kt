package com.e.prototype.mvvm.feature.calendar

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.utils.getMonthsToDate
import com.applandeo.materialcalendarview.utils.isToday
import com.e.prototype.R
import com.e.prototype.databinding.FragmentCalendarBinding
import com.e.prototype.mvvm.feature.main.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.util.*
import kotlin.math.log


class CalendarFragment: Fragment(), ScheduleDialog.NoticeDialogListener {

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

        binding.calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                var calendar: Calendar = eventDay.calendar

                val dialog = ScheduleDialog()
                dialog.show(childFragmentManager  , "ScheduleDialog")
            }
        })


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

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        Log.d("확인", "확인")
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Log.d("취소", "취소")
    }

}