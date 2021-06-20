package com.e.prototype.mvvm.feature.calendar

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.listeners.OnDayLongClickListener
import com.e.prototype.R
import com.e.prototype.databinding.FragmentCalendarBinding
import com.e.prototype.mvvm.feature.main.ScheduleViewModel
import kotlinx.android.synthetic.main.dialog_calendar.view.*
import java.text.SimpleDateFormat
import java.util.*


class CalendarFragment: Fragment(), ScheduleDialog.NoticeDialogListener {

    private lateinit var mContext: Context
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private val events = mutableListOf<EventDay>()

    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.run {
            viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(this.application))
                .get(ScheduleViewModel::class.java)
        }

        _binding = FragmentCalendarBinding.inflate(inflater, container,false) // view binding

        val nameObserver = Observer<List<Schedule>> { scheduleList ->
            for (schedule in scheduleList) {
                val date = schedule.date.split("-").map { it.toInt() }
                val calendar = Calendar.getInstance()
                calendar.set(date[0], date[1]-1, date[2])
                events.add(EventDay(calendar, R.drawable.sample_three_icons))
                binding.calendarMaterial.setEvents(events)
            }
        }

        viewModel.scheduleList.observe(viewLifecycleOwner, nameObserver)


        binding.calendarMaterial.setOnDayLongClickListener(object : OnDayLongClickListener {
            override fun onDayLongClick(eventDay: EventDay) {
                val date = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(eventDay.calendar.time).toString()
                val dialog = ScheduleDialog().apply {
                    arguments = Bundle().apply {
                        putString("date", date)
                    }
                }
                dialog.show(childFragmentManager  , "ScheduleDialog")
            }
        })

        binding.calendarMaterial.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                val date = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(eventDay.calendar.time).toString()
                for (schedule in viewModel.scheduleList.value!!) {
                    if (schedule.date == date) {
                        val toast = Toast.makeText(activity!!.application , schedule.content, LENGTH_SHORT)
                        toast.show()
                    }
                }
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

    override fun onDialogPositiveClick(view: View, date: String) {
        val title = view.editTextTitle.text.toString()
        val content = view.editTextContent.text.toString()

        val schedule = createSchedule(title, content, date)
        viewModel.insert(schedule)
    }

    override fun onDialogNegativeClick(view: View, date: String) {
        Log.d("취소", "취소")
    }

    private fun createSchedule(title: String, content: String, date: String): Schedule {
        return Schedule(
            isStudy = false,
            title = title,
            content = content,
            date = date,
            hours = 1
        )
    }
}