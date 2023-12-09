package com.droiddataplace.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.droiddataplace.databinding.TransactionsLayoutBinding
//import com.droiddataplace.fragments.
import com.droiddataplace.fragments.HomeFragment
//import com.droiddataplace.fragments.HomeFragmentDirections
import com.droiddataplace.model.TransactionsData
import kotlinx.coroutines.NonDisposableHandle.parent
import java.util.Random

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(val itemBinding: TransactionsLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    private val differCallback = object : DiffUtil.ItemCallback<TransactionsData>() {
        override fun areItemsTheSame(
            oldItem: TransactionsData,
            newItem: TransactionsData
        ): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.acct_name == newItem.acct_name
            //  oldItem.account_category == newItem.noteTitle
        }

        override fun areContentsTheSame(
            oldItem: TransactionsData,
            newItem: TransactionsData
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            TransactionsLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val currentTransaction = differ.currentList[position]

        holder.itemBinding.tvaccountName.text = currentTransaction.acct_name
        holder.itemBinding.tvamountPaid.text = currentTransaction.account_amount_paid.toString()

        val random = Random()
        val color = Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )

        holder.itemBinding.ibColor.setBackgroundColor(color)

      //  holder.itemView.setOnClickListener {

          //  val direction =
          //      HomeFragmentDirections.action_HomeFragment_to_updateNoteFragment(currentTransaction)

            //it.findNavController().navigate(direction)


       // }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size

    }


}