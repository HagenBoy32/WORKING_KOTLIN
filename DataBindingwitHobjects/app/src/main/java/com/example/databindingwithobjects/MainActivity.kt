package com.example.databindingwithobjects

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindingwithobjects.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        var u1 = User(1,"Jeff","123")
        binding.user = u1

       // binding.apply {
       //     textView.setText(""+u1.id)
       //     nametxt.setText(u1.name)
       //     passwordTxt.setText(u1.pass)

        //}


    }
}