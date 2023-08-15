package com.droiddataplace


import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.droiddataplace.databinding.FragmentTransactionBinding
import java.text.SimpleDateFormat
import java.util.*


class TransactionFragment: Fragment(),DatePickerFragment.Callbacks  {


    //lateinit var accountName: acct_name
    private lateinit var textViewSelectedDate: TextView
    private lateinit var confirm_button: Button
    private var selectedDate: Calendar = Calendar.getInstance()
    private var mDate = 0
    private var mMonth: kotlin.Int = 0
    private var mYear: kotlin.Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.d("<<TransactionFragment>>", "onCreateView: ")

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTransactionBinding>(
            inflater, R.layout.fragment_transaction, container, false)

        Log.d("<<TransactionFragment>>", "DataBiding and inflating fragment_transaction ")

       // confirm_button = view?.findViewById(R.id.confirm_button)!!
        // Bind this fragment class to the layout
       val acctname     =  binding.transaction?.acct_name
       val acctamtpaid  =  binding.transaction?.account_amount_paid
     //  val acctdatepaid =  binding.transaction?.account_date_Paid
       lateinit var acctdatepaid: EditText
       // buttonPickDate = findViewById(R.id.buttonPickDate)
        //acct_date_paid = ""
        confirm_button.setOnClickListener{
            Log.d("<<>>", "<<>> ")
            showDatePickerDialog()

            val Cal = Calendar.getInstance()

            Log.d("<<>>", "<<>> ")

            mMonth = Cal[Calendar.MONTH]
            mDate = Cal[Calendar.DATE]
            mYear = Cal[Calendar.YEAR]

            Log.d("<<TransactionFragment>>", "<<acct_date_paid>>" + acctdatepaid)
            Log.d("<<TransactionFragment>>", "<<mMonth>>" + mMonth)
            Log.d("<<TransactionFragment>>", "<<mDate >>" + mDate)
            Log.d("<T<ransactionFragment>>", "<<mDate >>" + mYear)






        }

        // Set the onClickListener for the submitButton
        binding.confirmButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            Log.d("<<TransactionFragment>>", "binding.confirmButton.setOnClickListener ")
            Log.d("<<TransactionFragment>>", "acctname     " + acctname)
            Log.d("<<TransactionFragment>>", "acctamtpaid  " + acctamtpaid)
            Log.d("<<TransactionFragment>>", "acctdatepaid " + acctdatepaid)
        }
        return binding.root
    }

    private fun showDatePickerDialog() {
        Log.d("<<in-->>", "showDatePickerDialog: ")
        val datePicker = DatePickerDialog(
            requireContext(),
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
        val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate.time)

      //  TransactionsData.account_date_paid.text = formattedDate


    }

    override fun onDateSelected(date: Date) {
        TODO("Not yet implemented")
    }


}