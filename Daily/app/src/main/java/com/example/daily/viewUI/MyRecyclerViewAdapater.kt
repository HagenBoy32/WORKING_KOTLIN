package com.example.daily.viewUI

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.daily.R
import com.example.daily.databinding.CardItemBinding
import com.example.daily.room.Logs

class MyRecyclerViewAdapater
    (private val logsList:List<Logs>,
     private val clickListener: (Logs)->Unit
) : RecyclerView.Adapter<MyViewHolder>()

{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d("<<MyRclyclAdaptr>>", "onCreateViewHolder: ")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : CardItemBinding = DataBindingUtil.
        inflate(layoutInflater, R.layout.card_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.d("<<MyRclyclAdaptr>>", "Records # in Log database " + logsList.size)
        return logsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("<<MyRclyclAdaptr>>", "onBindViewHolder ")
        holder.bind(logsList[position],clickListener)
    }

}

class MyViewHolder(val binding: CardItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(logs: Logs, clickListener: (Logs) -> Unit){

        Log.d("<<MyRclyclrVAdaptr>>", "bind: ")

        binding.weightTextView.text    = logs.weight.toString()
        binding.exerciseTextView.text  = logs.exercise.toString()
        binding.dateTextView.text      = logs.selected_Date.toString()

        Log.d("<<MyRclyclrVAdaptr>>", "in fun bind: ")

        binding.listItemLayout.setOnClickListener{
            clickListener(logs)
        }

    }

}
