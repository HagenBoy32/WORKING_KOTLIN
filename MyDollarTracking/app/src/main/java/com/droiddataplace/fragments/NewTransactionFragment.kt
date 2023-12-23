package com.droiddataplace.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.droiddataplace.MainActivity
import com.droiddataplace.R
import com.droiddataplace.model.TransactionsData
import com.droiddataplace.viewmodel.TransActionViewModel
//import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ARG_ACCT_ID = "ARG_ACCT_ID"
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0



class NewTransactionFragment: Fragment(R.layout.fragment_new_transaction),DatePickerFragment.Callbacks

{
    private lateinit var transactionsdata: TransactionsData
    private var _binding : FragmentNewTransactionBinding? = null
    private val binding get() = _binding!!
    private lateinit var acct_date_paid: EditText
    private lateinit var amount_paid: EditText
    private lateinit var account_name_field: EditText

    var radioButtonChecked: RadioButton? = null

    private lateinit var AccountUtility_RadioButton: RadioButton
    private lateinit var AccountHealth_RadioButton:  RadioButton
    private lateinit var AccountEntertainment_RadioButton:  RadioButton
    private lateinit var AccountCredit_RadioButton:  RadioButton
    private lateinit var AccountFood_RadioButton:  RadioButton
    private lateinit var transactiondata: TransactionsData
    private lateinit var transactionsViewModel : TransActionViewModel
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var mView: View
    private lateinit var confirm_button: Button

    //  var isRemoved: Boolean = false
    private var mDate = 0
    private var mMonth: kotlin.Int = 0
    private var mYear: kotlin.Int = 0


   // private val transactionsViewModel: TransActionViewModel by lazy {
  //      ViewModelProviders.of(this).get(TransactionsDetailViewModel::class.java)
  //  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("<<NewTransactionFragment>>", "onCreate: ")
     //   transactiondata = TransactionsData(id)
     //   val transId: UUID  = arguments?.getSerializable(ARG_ACCT_ID) as UUID
        setHasOptionsMenu(true)


        //transactionsViewModel.loadAccount(transId)
    }

///////
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    val view = inflater.inflate(R.layout.fragment_new_transaction, container, false)

    acct_date_paid = view.findViewById(R.id.account_date_Paid)
    amount_paid    = view.findViewById(R.id.amount_paid)
    account_name_field =view.findViewById(R.id.account_name)

    //val radioGroup: RadioGroup = findViewById(R.id.radioGroup)

    AccountUtility_RadioButton=view.findViewById(R.id.AccountUtility_RadioButton)
    AccountHealth_RadioButton=view.findViewById(R.id.AccountHealth_RadioButton)
    AccountEntertainment_RadioButton=view.findViewById(R.id.AccountEntertainment_RadioButton)
    AccountCredit_RadioButton=view.findViewById(R.id.AccountCredit_RadioButton)
    AccountFood_RadioButton=view.findViewById(R.id.AccountHealth_RadioButton)


    Log.d("<<NewTransactionFragment>>", "onCreateView: ")
    _binding = FragmentNewTransactionBinding.inflate(inflater, container, false)
    Log.d("<<NewTransactionFragment>>", "inflate ")

    return binding.root

    }

//
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  transactionsViewModel.transactionLiveData.observe(
      //      viewLifecycleOwner,
      //      Observer { transactiondata ->
      //          transactiondata?.let {
      //              this.transactiondata = transactiondata
      //              updateUI()
      //          }
      //      })

        Log.d("<<NewTransActionFrag>>", "OnViewCreated() ")
        transactionsViewModel = (activity as MainActivity).transactionViewModel
        mView = view

    }
//*Save a transaction to the data base.Check account_name to verify populated or not
//*If populated then ui indicates the user enterd a value.

         override fun onStart() {
             super.onStart()

         Log.d("<<NewTransFragment>>", "<<onStart>>")


             // Get the transaction name
             val nameWatcher = object : TextWatcher {
                 override fun beforeTextChanged(
                     sequence: CharSequence?,
                     start: Int,
                     count: Int,
                     after: Int
                 ) {
                     // This space intentionally left blank
                 }
                 override fun onTextChanged(sequence: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int) {
                     transactiondata.acct_name  = sequence.toString()

                     Log.d("<<NewTransFragment>>", "<<onTextChanged>>" + transactiondata.acct_name)
                }
                 override fun afterTextChanged(sequence: Editable?) {
                     Log.d("<<NewTransFrag>>", "afterTextChanged: ")
                 }
             }

           //Get the transaction $ amount paid
             val amountWatcher = object : TextWatcher {
                 // Amount Paid
                 override fun beforeTextChanged(
                     sequence: CharSequence?, start: Int, count: Int, After: Int
                 ) {
                     // This space intentionally left blank
                 }
                 override fun onTextChanged(
                     sequence: CharSequence?, start: Int, before: Int, count: Int
                 ) {
                     transactiondata.account_amount_paid = sequence.toString()
                     Log.d(
                         "<<NewTransFragment>>",
                         "<<onTextChanged+account_amount_paid = " + transactiondata.account_amount_paid
                     )

                 }
                 override fun afterTextChanged(sequence: Editable?) {
                     // This one too
                 }
             }

             account_name_field.addTextChangedListener(nameWatcher)
             amount_paid.addTextChangedListener(amountWatcher)

             acct_date_paid.setOnClickListener{
                 Log.d("<<NewTransactionFragment>>", "acct_date_paid.setOnClickListener ")
                 val Cal = Calendar.getInstance()

                 mMonth = Cal[Calendar.MONTH]
                 mDate = Cal[Calendar.DATE]
                 mYear = Cal[Calendar.YEAR]

                 DatePickerFragment.newInstance(transactiondata.account_date_Paid).apply {
                     setTargetFragment(this@NewTransactionFragment, REQUEST_DATE)
                     //   show(this@AccountFragment.requireFragmentManager(), DIALOG_DATE)
                     Log.d("<<AccountFragment>>", "<<REQUEST_DATE>>" + REQUEST_DATE)
                     Log.d("<<AccountFragment>>", "<<DIALOG_DATE>> " + DIALOG_DATE)
                     Log.d(
                         "<<NewTransactionFragment>>", "<transactions.account_date_paid>>= " + transactiondata.account_date_Paid
                     )
                     Log.d("<<AccountFragment>>", "<<acct_date_paid::BEEP:: >>" + acct_date_paid)
                 }

             }

             //12/20/23 //////////////////////////////////////////////////////////////////////////
             //Start Radio Button Logic here ... Remove savetransaction fun and encapsule all
             //screen ui to database in onStart. Just like in the model Mymoney
             ///////////////////////////////////////////////////////////////////////////////////

             AccountUtility_RadioButton.apply {
                 setOnCheckedChangeListener{_,isChecked->
                     transactiondata.isUtility = isChecked.toString()

                     val transactionsData = TransactionsData(id)
                     transactionsViewModel.addTransaction(transactionsData)
                 }
             }
             //Health
             AccountHealth_RadioButton.apply {
                 setOnCheckedChangeListener {_,isChecked->
                     transactiondata.isHealth = isChecked.toString()
                     val transactionsData = TransactionsData(id)
                     transactionsViewModel.addTransaction(transactionsData)
                 }
             }
            //Entertainmaint
             AccountEntertainment_RadioButton.apply {
                 setOnCheckedChangeListener{_,isChecked->
                     transactiondata.isEntertainment = isChecked.toString()
                     val transactionsData = TransactionsData(id)
                     transactionsViewModel.addTransaction(transactionsData)

                 }
             }

            //Credit
             AccountCredit_RadioButton.apply {
                 setOnCheckedChangeListener{_,isChecked->
                     transactiondata.isCredit = isChecked.toString()
                     val transactionsData = TransactionsData(id)
                     transactionsViewModel.addTransaction(transactionsData)

                 }

             }

            //Food
            AccountFood_RadioButton.apply {
                setOnCheckedChangeListener{_,isChecked->
                transactiondata.isFood = isChecked.toString()
                val transactionsData = TransactionsData(id)
                transactionsViewModel.addTransaction(transactionsData)

                    }

                }


         }


        // fun savetransaction(view: View)


         //val uiaccount_name              = binding.accountName.text.toString().trim()
//         val uiaccount_amount_paid       = binding.amountPaid.text.toString().trim()
//
  //       val uiaccount_cat_credit        = binding.AccountCreditRadioButton.text.toString().trim()
    //     val uiaccount_cat_health        = binding.AccountHealthRadioButton.toString()
      //   val uiaccount_cat_food          = binding.firstAccountFoodRadioButton.toString()
        // val uiaccount_cat_entertainment = binding.AccountEntertainmentRadioButton.toString()
       //  val uiaccount_cat_utlity        = binding.confirmButton.toString()

       //  Log.d("<<NewTransactionFrag>>", "account name is =  " + uiaccount_name)

// If uiaccount_name is not blank or null then we are adding a transaction
         //transactiondata = TransactionsData(id)

        // amount_paid.setText(transactiondata.account_amount_paid)
       //  account_name_field.setText(transactiondata.acct_name)

        // Log.d("<<NewTransFragment>>", "<<Model--------------------------------->>")
       //  Log.d("<<NewTransFragment>>", "<<Model>>acct.name        " + transactiondata.acct_name)
       //  Log.d("<<NewTransFragment>>", "<<Model>>acct.date        " + transactiondata.account_date_Paid)
       //  Log.d("<<NewTransFragment>>", "<<Model>>acct.amount_paid " + transactiondata.account_amount_paid)


      //   if (uiaccount_name.isNotEmpty()){


           //  val transactiondata = TransactionsData(0, acct_name,uiaccount_amount_paid, uiaccount_date_paid,
           //      uiaccount_cat_credit,uiaccount_cat_health,uiaccount_cat_food,uiaccount_cat_entertainment,uiaccount_cat_utlity)



        //    Log.d("<<NewTransactionFrag>>", "transactionsViewModel.addTransaction(transactiondata) ")

        //    CoroutineScope(Dispatchers.IO).launch{
        //        Log.d("<<NewTransFrag>>", "savetransaction: ")
       //         transactionsViewModel.addTransaction(transactiondata)
       //     }


         //   Toast.makeText(mView.context,
         //       "Note Saved Successfully",
         //       Toast.LENGTH_LONG).show()


           //  view.findNavController().navigate(R.id.action_newTransctionFragment_to_homeFragment)

        //}
       // else{
       //     Toast.makeText(
       //         mView.context,
       //         "Please enter transaction name Title",
      //          Toast.LENGTH_LONG).show()

       //  }


//Menu Driven//////

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d("<<NewTransFrag>>", "onCreateOptionsMenu ")
        menu.clear()
        inflater.inflate(R.menu.menu_new_transaction, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onDestroy() {
        Log.d("<<NewTransFrag>>", "onDestroy() ")
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("<<NewTransFrag>>", "onOptionsItemSelected ")
        when(item.itemId){

            R.id.menu_save -> {
                Log.d("<<NewTransFrag>>", "menu_saved ")
                //savetransaction(mView)
                transactiondata=TransactionsData(id)
                transactionsViewModel.addTransaction(transactiondata)
            }
        }
        return super.onOptionsItemSelected(item)
    }


// Date Selection
    override fun onDateSelected(date: Date) {

        Log.d("<<NewTransactionFrag>> ", "<<onDateSelected:>>= " + date)

        transactiondata.account_date_Paid = date

        updateUI()

    }

    private fun updateUI(){
        Log.d("<<NewTransactionFragment>>", "updateUI()")
        acct_date_paid.setText(transactiondata.account_date_Paid.toString())






    }

}