package com.example.daily

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daily.databinding.ActivityMainBinding
import com.example.daily.myviewModel.LogsViewModel
import com.example.daily.myviewModel.LogsViewModelFactory
import com.example.daily.room.Logs
import com.example.daily.room.LogsDatabase
import com.example.daily.room.LogsRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.example.daily.viewUI.MyRecyclerViewAdapater
import java.util.Date


class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding

    private lateinit var logsViewModel: LogsViewModel

    private lateinit var savebutton: Button
    private lateinit var buttonPickDate: Button
    private lateinit var logsToUpdateOrDelete: Logs

    private lateinit var textViewSelectedDate: TextView
    private var selectedDate: Calendar = Calendar.getInstance()
    private lateinit var editTextDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("<<MainActivity>>", "onCreate: ")



        editTextDate = findViewById(R.id.date_ET)

        Log.d("<<MainActivity>>", "onCreate: ")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val dao = LogsDatabase.getInstance(application).logsDAO
        val repository = LogsRepository(dao)
        val factory = LogsViewModelFactory(repository)

        logsViewModel = ViewModelProvider(this,
            factory).get(LogsViewModel::class.java)

        val currentDate = Date()
        logsViewModel.setDate(currentDate)

        Log.d("<<MainActivity>>", "currentDate = " + currentDate)



        binding.logsViewModel = logsViewModel

        binding.lifecycleOwner = this

        initRecyclerView()

        buttonPickDate = findViewById(R.id.Date_btn)

        buttonPickDate.setOnClickListener{
            Log.d("<<MainActivity>>", "buttonPickDate ")
            showDatePickerDialog()
//
//         //   BmiResponse
//            LogsViewModel.BmiResponse.observe(this, Observer { BmiResponse ->
//                bmiResponse?.let {
//                    Log.d("MainActivity", "BMI: ${it.bmi}, Health: ${it.health}, Healthy Range: ${it.healthy_bmi_range}")
//                }
//            })
//
//            // Fetch BMI data
//            bmiViewModel.fetchBmi(weight = 150, height = 68)
//        }

        }

        savebutton = findViewById(R.id.Update_btn)

        savebutton.setOnClickListener {

            Log.d("<<MainActivity>>", "savebutton.setOnClickListener ")

        }

   }

    private fun showDatePickerDialog() {

        Log.d("<<MainActivity>>", "in fun showDatePickerDialog() ")

        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedDate.set(year, month, dayOfMonth)

                updateSelectedDateInView()

                // This starts the flow of inserting a row into our database
                //
                Log.d("<<LogsViewModel>>", "formattedDate " )
            },

            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
            //***********************************************************//
            // Pass the selected date to another function in

        )
        Log.d("<<MainActivity->>", "datePicker.show ")
        datePicker.show()

    }

    private fun updateSelectedDateInView() {

        Log.d("<<MainActivity>>", "updateSelectedDateInView: ")

        val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate.time)

        Log.d("<MainActivity", "formattedDate = " + formattedDate)


        logsViewModel.processDate(formattedDate)

        logsViewModel.bmiResponse.observe(this, Observer { bmiResponse ->
            bmiResponse?.let {
                Log.d("MainActivity", "BMI: ${it.bmi}, Health: ${it.health}, Healthy Range: ${it.healthy_bmi_range}")
            }
        })

        // Fetch BMI data
        logsViewModel.fetchBmi(weight = 150, height = 68)


    }

    private fun initRecyclerView()
    {
        Log.d("<<MainActivity>>", "initRecyclerView: ")
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        DisplayLogsList()
    }

    private fun DisplayLogsList() {
        Log.d("<<MainActivity>>", "DisplayLogsList ")
        logsViewModel.logs.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapater(
                it, {selectedItem: Logs -> listItemClicked(selectedItem)}
            )
        })
    }


    private fun listItemClicked(selectedItem: Logs) {
        Log.d("<<MainActivity>>", "Listitemclicked ")
        Toast.makeText(this,
            "Selected weight is ${selectedItem.weight}",
            Toast.LENGTH_LONG).show()

        logsViewModel.initUpdateAndDelete(selectedItem)


    }


}