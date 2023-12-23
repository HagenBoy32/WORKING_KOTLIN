package com.droiddataplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.droiddataplace.data.TransActionDataBase
import com.droiddataplace.databinding.ActivityMainBinding
import com.droiddataplace.repository.TransactionRepository
import com.droiddataplace.viewmodel.TransActionViewModel
import com.droiddataplace.viewmodel.TransactionViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var transactionViewModel : TransActionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("<<MainActivity>>", ".onCreate: ")


        binding = ActivityMainBinding.inflate(layoutInflater)


        Log.d("<<MainActivity>>", ".inflate:(layoutInflater) ")


        setContentView(binding!!.root)

        setUpViewModel()

        Log.d("<<MainActivity>>", "after binding<ActivityMainBinding> ")

    }
///View Models connect ui to Database I/O....../////
    private fun setUpViewModel() {

        Log.d("<<MainActivity->>", "setUpViewModel: ")

        val transactionRepository = TransactionRepository(TransActionDataBase(this))

        val viewModelProviderFactory = TransactionViewModelFactory(application,
            transactionRepository)

        transactionViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory)
            .get(TransActionViewModel::class.java)



    }


}