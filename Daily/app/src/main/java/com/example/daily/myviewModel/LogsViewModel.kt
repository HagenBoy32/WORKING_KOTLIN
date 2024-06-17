package com.example.daily.myviewModel



import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daily.BMIService
import com.example.daily.BmiResponse
import com.example.daily.RetrofitInstance
import com.example.daily.room.Logs
import com.example.daily.room.LogsRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date


class LogsViewModel(private val repository: LogsRepository) : ViewModel(),Observable,
    LifecycleOwner {


    val logs                     = repository.logs
    private var isUpdateOrDelete = false

    private  lateinit var logsToUpdateOrDelete: Logs

    private val _selectedDate    = MutableLiveData<Date>()
    val selectedDate: MutableLiveData<Date> = MutableLiveData()

    private val _bmiResponse = MutableLiveData<BmiResponse>()
    val bmiResponse: LiveData<BmiResponse>get() = _bmiResponse
    private val BMIService = RetrofitInstance.create(BMIService::class.java)


    fun setDate(date: Date) {
        Log.d("<<LogsViewModel>>", "setDate:  = " + date)
        _selectedDate.value = date
        Log.d("<<LogsViewModel>>", "_selected = " + _selectedDate)
    }

    // logsToUpdateOrDelete
    @Bindable
    val inputDate     = MutableLiveData<String>()

    @Bindable
    val inputAge      = MutableLiveData<String?>()

    @Bindable
    val inputWeight   = MutableLiveData<String?>()

    @Bindable
    val inputHeight   = MutableLiveData<String?>()

    @Bindable
    val inputExercise = MutableLiveData<String?>()

    @Bindable
    val inputCardio   = MutableLiveData<String?>()

    @Bindable
    val inputSteps = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    @Bindable
    val getDateButtonText = MutableLiveData<String>()


    init {

        Log.d("<<LogsViewModel>>", "init ")

        saveOrUpdateButtonText.value     = "Update"
        clearAllOrDeleteButtonText.value = "Clear"
        getDateButtonText.value          = "Date"

    }

    fun processDate(date: String) {

//***************************************************************//
//* Incoming date from the Date Picker dialog in MainActivity.   //
//***************************************************************//

        Log.d("<<LogsViewModel>>", "in fun processDate: " + date)

        val dateselected = date

        if (isUpdateOrDelete) {
          //   Make Update:
            Log.d("<<LogsViewModel>>", "Update a record ")
            logsToUpdateOrDelete.age            = inputAge.value!!
            logsToUpdateOrDelete.weight        = inputWeight.value!!
            logsToUpdateOrDelete.height        = inputHeight.value!!
            logsToUpdateOrDelete.exercise      = inputExercise.value!!
            logsToUpdateOrDelete.cardio        = inputCardio.value!!
            logsToUpdateOrDelete.exercise      = inputSteps.value!!
            logsToUpdateOrDelete.selected_Date = inputDate.value.toString()!!
            update(logsToUpdateOrDelete)

        } else {
            // Insert Functionality

            Log.d("<<LogsViewModel>>", "Insert a record. ")

            //var activitydate = selectedDate

            if (inputDate != null) {
                Log.d("<<LogsViewModel>>", "inputDate: " + inputDate)
                val inputDate        = date
                val age              = inputAge.value!!
                val weight           = inputWeight.value!!
                val height           = inputHeight.value!!
                val exercise         = inputExercise.value!!
                val cardio           = inputCardio.value
                val steps            = inputSteps.value

                Log.d("<<*LogsViewModel*>>", "height" + height)
                Log.d("<<*LogsViewModel*>>", "weight" + weight)
                    // logsVIewModel.fetchBmi(weight, height)
                // Here we have a height in inches and weight in pounds
                // Start 1st step to RapidApi API call to get BMI using
                // by passing height and weight. Use the Retrofit Library
                // tp talk to the API.Here

             //   fun fetchBmi(weight: Int, height: Int) {
                    Log.d("<<LogsViewModel>>", "fetchBmi: ")
                    val call: Call<BmiResponse> = BMIService.getBmi(weight, height)
                    call.enqueue(object : Callback<BmiResponse> {
                      override fun onResponse(call: Call<BmiResponse>, response: Response<BmiResponse>) {
                            if (response.isSuccessful) {
                                Log.d("<<LogsViewModel>>", "onResponse: " + response.isSuccessful)
                                _bmiResponse.value = response.body()
                                Log.d("<<LogsViewModel>>", "onResponse: " + _bmiResponse.value)
                            } else {
                                // Handle the error
                            }
                        }

                     override fun onFailure(call: Call<BmiResponse>, t: Throwable) {
                            // Handle the failure
                            Log.d("<<LogsViewModel>>", "weight           " + weight)
                        }
                    })
             //   }

                Log.d("<<LogsViewModel>>", "age           " + age)
                Log.d("<<LogsViewModel>>", "date          " + date)
                Log.d("<<LogsViewModel>>", "inputDate:    " + inputDate)
                Log.d("<<LogsViewModel>>", "height        " + height)
                Log.d("<<LogsViewModel>>", "weight        " + weight)
                Log.d("<<LogsViewModel>>", "exercise      " + exercise)
                Log.d("<<LogsViewModel>>", "cardio        " + cardio)
                Log.d("<<LogsViewModel>>", "steps         " + steps)
                insert(Logs(0, dateselected,age, weight,height, exercise, cardio, steps))


            //continue processing
        } else {
            Log.d("<<LogsViewModel>>", "processDate: ")
        }
            inputWeight.value   = null
            inputExercise.value = null

        }

    }

    fun saveOrUpdate(){

        val x = toString()

        Log.d("<<LogsViewModel>>", "saveOrUpdate " )

        if (isUpdateOrDelete) {

            //   Make Update:
            Log.d("<<LogsViewModel>>", "Update a record ")
            logsToUpdateOrDelete.age          = inputAge.value!!
            logsToUpdateOrDelete.weight       = inputWeight.value!!
            logsToUpdateOrDelete.height       = inputHeight.value!!
            logsToUpdateOrDelete.exercise     = inputExercise.value!!
            logsToUpdateOrDelete.cardio       = inputCardio.value!!
            logsToUpdateOrDelete.exercise     = inputSteps.value!!
            logsToUpdateOrDelete.selected_Date = inputDate.value!!
            update(logsToUpdateOrDelete)

        } else {
            // Insert Functionality

            Log.d("<<LogsViewModel>>", "else Insert" )

         // var activitydate = selectedDate


          var activitydate     = inputDate
          val age              = inputAge.value!!
          val weight           = inputWeight.value!!
          val height           = inputHeight.value!!
          val exercise         = inputExercise.value!!
          val cardio           = inputCardio.value
          val steps            = inputSteps.value



         if (::logsToUpdateOrDelete.isInitialized) {
             logsToUpdateOrDelete.age          = inputAge.value!!
             logsToUpdateOrDelete.age          = 54.toString()
             logsToUpdateOrDelete.weight = inputWeight.value!!
             logsToUpdateOrDelete.height = inputHeight.value!!
             logsToUpdateOrDelete.exercise = inputExercise.value!!
             logsToUpdateOrDelete.cardio = inputCardio.value!!
             logsToUpdateOrDelete.exercise = inputSteps.value!!
             logsToUpdateOrDelete.selected_Date = inputDate.value!!
             logsToUpdateOrDelete.steps = inputSteps.value!!

         }    else  {
             Log.d("<<LogsViewModel>>", "else ")
             insert(Logs(0, activitydate.toString(),age, weight,height, exercise, cardio, steps))

         }

            inputWeight.value   = null
            inputExercise.value = null

        }

    }

    fun clearAllorDelete() {

        if (isUpdateOrDelete) {
            Log.d("<<LogsViewModel>>", "Delete ")
            delete(logsToUpdateOrDelete)
        } else {
            Log.d("<<LogsViewModel>>", "Clear ")
            clearAll()
        }

    }



   fun insert(logs: Logs) = viewModelScope.launch  {
        Log.d("<<LogsViewModel>>", "insert: ")
       repository.insert(logs)
   }

    fun clearAll() = viewModelScope.launch {
        Log.d("<<LogsViewModel>>", "clearAll: ")
        repository.deleteAll()
    }

    fun update(logs: Logs) = viewModelScope.launch {
        repository.update(logs)
        Log.d("<<LogsViewModel>>", "update: ")
        // Resetting the Buttons and Fields
        inputWeight.value   = null
        inputExercise.value = null
      //  inputDate.value     = null
        isUpdateOrDelete    = false
        saveOrUpdateButtonText.value     = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun delete(logs: Logs) = viewModelScope.launch {
        repository.delete(logs)
        Log.d("<<LogsVWModel>>", "initUpdateAndDelete: ")
        // Resetting the Buttons and Fields
        inputWeight.value   = null
        inputExercise.value = null
      //  inputDate.value     = null

        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }


    fun initUpdateAndDelete(logs: Logs) {
        Log.d("<<LogsVWModel>>", "initUpdateAndDelete: ")

        inputWeight.value = logs.weight
        inputExercise.value = logs.exercise
        inputDate.value = logs.selected_Date

        isUpdateOrDelete = true
        logsToUpdateOrDelete = logs
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {

    }

    fun fetchBmi(weight: Int, height: Int) {

    }

    override val lifecycle: Lifecycle
        get() = TODO("Not yet implemented")

    // override val lifecycle: Lifecycle

   // get() = viewModelScope.coroutineContext[LifecycleCoroutineScope.LifeCycle]?._lifecycle
  // override val lifecycle: Lifecycle
  //     get() = viewModelScope.coroutineContext[LifecycleCoroutineScope.Lifecycle]?.lifecycle
  //         ?: throw IllegalStateException("Lifecycle not found")

}