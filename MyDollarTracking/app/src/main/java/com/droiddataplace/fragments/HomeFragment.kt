package com.droiddataplace.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.*
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.droiddataplace.MainActivity
import com.droiddataplace.R
import com.droiddataplace.adapter.TransactionAdapter
import com.droiddataplace.databinding.FragmentHomeBinding
import com.droiddataplace.model.TransactionsData
import com.droiddataplace.viewmodel.TransActionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment: Fragment(R.layout.fragment_home) , SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var transactionsViewModel: TransActionViewModel
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        Log.d("<<HomeFragment>>", "onCreate ")

    }
////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        Log.d("<<HomeFragment>>", "onCreateView: fragment_title ")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

////////////////
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionsViewModel = (activity as MainActivity).transactionViewModel

        setUpRecyclerView()

        binding.fabAddNote.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_homeFragment_to_newTransactionFragment
            )
        }
    }

////////////////
    private fun setUpRecyclerView() {

        Log.d("<<HomeFragment>>", "in setupRecyclerView() ")
        transactionAdapter = TransactionAdapter()

        binding.recyclerView.apply {
            Log.d("<<HomeFragment>>", "binding.recyclerview ")
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            Log.d("<<HomeFragment>>", "adapter = transactionAdapter ")
            adapter = transactionAdapter
        }

        activity?.let {
            Log.d("<<HomeFragment>>", "getAllTransactions().observe ")
            transactionsViewModel.getAllTransactions().observe(
                viewLifecycleOwner, { transaction ->
                    transactionAdapter.differ.submitList(transaction)
                    Log.d("<<HomeFragment>>", "transactionAdapter.differ.submitList(transaction) ")
                    updateUI(transaction)
                }
            )
        }

    }

    private fun updateUI(transaction: List<TransactionsData>?) {
        Log.d("<<HomeFragment>>", "updateUI ")
        if (transaction != null) {
            if (transaction.isNotEmpty()) {
                binding.cardView.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            } else {
                binding.cardView.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE

            }
        }

    }
//////////

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        Log.d("<<HomeFragment>>", "onCreateOptionsMenu: ")
        menu.clear()
        inflater.inflate(R.menu.home_menu, menu)

        val mMenuSearch = menu.findItem(R.id.menu_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled = false
        mMenuSearch.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        //searchTransaction
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("<<HomeFragment>>", "onQueryTextChange: ")
        if (newText != null) {
            searchTransaction(newText)
        }
        return true
    }
//////////

    private fun searchTransaction(query: String?) {
        val searchQuery = "%$query"
        CoroutineScope(Dispatchers.IO).launch {  }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}



