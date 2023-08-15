package com.droiddataplace
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*
private const val ARG_DATE = "date"
class DatePickerFragment : DialogFragment() {

    //Communication via callbacks
    interface Callbacks {
        fun onDateSelected(date: Date)
    }


    val dateListener = DatePickerDialog.OnDateSetListener {
            _: DatePicker, year: Int, month: Int, day: Int ->

        val resultDate : Date = GregorianCalendar(year, month, day).time
        Log.d("<<DatePickerFragment>>", "<<dateListener>>" + resultDate)


        targetFragment?.let { fragment ->
            (fragment as Callbacks).onDateSelected(resultDate)
        }
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val date = arguments?.getSerializable(ARG_DATE) as Date

        calendar.time = date
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )

    }

    companion object {
        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }

            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }
}
