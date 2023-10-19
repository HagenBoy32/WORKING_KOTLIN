package com.example.navigationapp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.example.navigationapp.databinding.FragmentHomeBinding
import androidx.navigation.findNavController

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("<<HomeFragment>>", "onCreateView: ")
      //  return inflater.inflate(R.layout.fragment_home, container, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)


        // Handle the click Event on the button
        binding.btnSubmit.setOnClickListener{
            Log.d("<<HomeFragment>>", "setOnclickListener: ")
            if (!TextUtils.isEmpty(binding.editText.text.toString())){
                val bundle =  bundleOf("name" to binding.editText.text.toString())
                Log.d("<<HomeFragment>>", "<<1>> ")
                // The nav Controller says go do this action(Navigation COntrol
                it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment,bundle)
                Log.d("<<HomeFragment>>", "<<2>> ")
            }else{
                Toast.makeText(activity, "Enter your Name", Toast.LENGTH_LONG).show()
            }

        }

        return binding.root
    }


}