package com.droiddataplace

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.droiddataplace.databinding.FragementTitleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class TitleFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragementTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragement_title, container, false)

        Log.d("<<TitleFragment>>", "onCreateView: fragment_title ")

        //The complete onClickListener with Navigation using createNavigateOnClickListener
        binding.addTransaction.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_MoneyFragment))

        Log.d("<<TitleFragment>>", "After setOnclickListener ")


        return binding.root
    }


}