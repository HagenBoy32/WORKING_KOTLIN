package com.droiddataplace.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.droiddataplace.adapter.TransactionAdapter
import com.droiddataplace.databinding.FragmentNewTransactionBinding
import java.util.*
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.findNavController
import com.droiddataplace.MainActivity
import com.droiddataplace.R
import com.droiddataplace.model.TransactionsData
import com.droiddataplace.viewmodel.TransActionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTransactionFragment: Fragment(R.layout.fragment_new_transaction),
    DatePickerFragment.Callbacks {

    private var _binding : FragmentNewTransactionBinding? = null
    private val binding get() = _binding!!


    private lateinit var transactiondata: TransactionsData
    private lateinit var transactionsViewModel : TransActionViewModel
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var mView: View
    private lateinit var confirm_button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("<<NewTransactionFragment>>", "onCreate: ")
        setHasOptionsMenu(true)
    }

///////
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
                  savedInstanceState: Bundle?
    ): View? {

    Log.d("<<NewTransactionFragment>>", "onCreateView: ")
    _binding = FragmentNewTransactionBinding.inflate(inflater, container, false)
    Log.d("<<NewTransactionFragment>>", "inflate ")
    return binding.root

    }

//
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("<<NewTransActionFrag>>", "OnViewCreated() ")
        transactionsViewModel = (activity as MainActivity).transactionViewModel
        mView = view

    }
//*Save a transaction to the data base.Check account_name to verify populated or not
//*If populated then ui indicates the user enterd a value.


         fun savetransaction(view: View) {

         val uiaccount_name              = binding.accountName.text.toString().trim()
         val uiaccount_amount_paid       = binding.amountPaid.text.toString().trim()
         val uiaccount_date_paid         = binding.accountDatePaid.text.toString().trim()
         val uiaccount_cat_credit        = binding.AccountCreditRadioButton.text.toString().trim()
         val uiaccount_cat_health        = binding.AccountHealthRadioButton.toString()
         val uiaccount_cat_food          = binding.firstAccountFoodRadioButton.toString()
         val uiaccount_cat_entertainment = binding.AccountEntertainmentRadioButton.toString()
         val uiaccount_cat_utlity        = binding.confirmButton.toString()

         Log.d("<<NewTransactionFrag>>", "account name is =  " + uiaccount_name)

// If uiaccount_name is not blank or null then we are adding a transaction

         if (uiaccount_name.isNotEmpty()){
            val transactiondata = TransactionsData(0,uiaccount_name,uiaccount_amount_paid,uiaccount_date_paid,
                uiaccount_cat_credit,uiaccount_cat_health,uiaccount_cat_food,uiaccount_cat_entertainment,uiaccount_cat_utlity)


            Log.d("<<NewTransactionFrag>>", "transactionsViewModel.addTransaction(transactiondata) ")

            CoroutineScope(Dispatchers.IO).launch{
                transactionsViewModel.addTransaction(transactiondata)
            }


            Toast.makeText(mView.context,
                "Note Saved Successfully",
                Toast.LENGTH_LONG).show()


             view.findNavController().navigate(R.id.action_newTransctionFragment_to_homeFragment)

        }
        else{
            Toast.makeText(
                mView.context,
                "Please enter transaction name Title",
                Toast.LENGTH_LONG).show()

         }
     }

//Menu Driven//////

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d("<<NoteViewModel>>", "onCreateOptionsMenu ")
        menu.clear()
        inflater.inflate(R.menu.menu_new_transaction, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onDestroy() {
        Log.d("<<NoteViewModel>>", "onDestroy() ")
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("<<NoteViewModel>>", "onOptionsItemSelected ")
        when(item.itemId){

            R.id.menu_save -> {
                Log.d("<<NoteViewModel>>", "menu_saved ")
                savetransaction(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }


// Date Selection
    override fun onDateSelected(date: Date) {

        Log.d("<<NewTransactionFrag>> ", "<<onDateSelected:>>= " + date)

        transactiondata.account_date_Paid = date.toString()

        updateUI()

    }

    private fun updateUI(){

    }

}