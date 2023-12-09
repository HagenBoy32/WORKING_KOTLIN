package com.droiddataplace.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.droiddataplace.R
import com.droiddataplace.databinding.FragmentUpdateTransactionBinding
import com.droiddataplace.model.TransactionsData
import com.droiddataplace.viewmodel.TransActionViewModel

class UpdateTransactionFragment : Fragment(R.layout.fragment_update_transaction) {
    private var _binding : FragmentUpdateTransactionBinding? = null
    private val binding get() = _binding!!

    private lateinit var transactionsViewModel : TransActionViewModel

    private lateinit var currentTransaction : TransactionsData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("<<UpdateTransFrag>>","onCreateView")

    return binding.root
    }


}