package com.droiddataplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.droiddataplace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)

        Log.d("<<MainActivity>>", "onCreate: ")


        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


        Log.d("<<MainActivity>>", "after binding<ActivityMainBinding> ")

    }
}