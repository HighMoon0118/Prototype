package com.e.prototype.mvvm.feature.calendar

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import com.applandeo.materialcalendarview.getDatesRange
import com.applandeo.materialcalendarview.utils.getMonthsToDate
import com.e.prototype.R
import com.e.prototype.databinding.DialogCalendarBinding
import java.lang.IllegalStateException
import java.time.Year
import java.util.*

class ScheduleDialog : DialogFragment() {

    internal lateinit var listener: NoticeDialogListener
    private var _binding: DialogCalendarBinding? = null
    private val binding get() = _binding!!

    interface NoticeDialogListener {
        fun onDialogPositiveClick(view: View, date: String)
        fun onDialogNegativeClick(view: View, date: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val date =  arguments?.getString("date")
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            _binding = DialogCalendarBinding.inflate(inflater)
            val view = binding.root

            builder.setView(binding.root)
                .setTitle(date)
                .setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onDialogPositiveClick(view, date!!)
                    })
                .setNegativeButton(
                    R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onDialogNegativeClick(view, date!!)
                    })

            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = parentFragment as NoticeDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}