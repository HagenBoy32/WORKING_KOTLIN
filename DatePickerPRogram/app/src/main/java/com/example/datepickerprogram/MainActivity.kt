package com.example.datepickerprogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.util.Log
import android.widget.Button
import android.widget.TextView

import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var buttonPickDate: Button
    private lateinit var textViewSelectedDate: TextView
    private var selectedDate: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("<<MainActivity>>", "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPickDate = findViewById(R.id.buttonPickDate)
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate)

        buttonPickDate.setOnClickListener {
            Log.d("<<buttonPickDate.setOnClickListener>>", "calling showDatePickerDialog ")
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        Log.d("<<in-->>", "showDatePickerDialog: ")
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedDate.set(year, month, dayOfMonth)
                updateSelectedDateInView()
            },
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )
        Log.d("<<datePicker.show is", "next ")
        datePicker.show()
    }

    private fun updateSelectedDateInView() {
        Log.d("<<in Fun -->>", "updateSelectedDateInView: ")
        val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate.time)
        Log.d("updateSelectedDateInView()", "<<val formattedDate is = >> " + formattedDate )
        textViewSelectedDate.text = formattedDate
    }
}

