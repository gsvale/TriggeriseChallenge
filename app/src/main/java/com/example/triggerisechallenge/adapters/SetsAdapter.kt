package com.example.triggerisechallenge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.triggerisechallenge.R
import com.example.triggerisechallenge.databinding.ItemSetBinding
import com.example.triggerisechallenge.models.Set
import com.example.triggerisechallenge.viewModels.ItemSetViewModel
import java.util.ArrayList

class SetsAdapter(private val context: Context, private val sets: ArrayList<Set>) :
    RecyclerView.Adapter<SetsAdapter.SetAdapterViewHolder>() {


    private var layoutInflater: LayoutInflater? = null

    // Create View Holders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetAdapterViewHolder {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        // Set DataBinding instance
        val binding = DataBindingUtil.inflate<ItemSetBinding>(
            layoutInflater!!,
            R.layout.item_set,
            parent,
            false
        )

        return SetAdapterViewHolder(binding)
    }

    // OnBind method
    override fun onBindViewHolder(holder: SetAdapterViewHolder, position: Int) {
        holder.bindSet(sets[position])
    }

    // Get number/count of items of adapter
    override fun getItemCount(): Int {
        return sets.size
    }

    // Method to add items
    fun addItems(list: List<Set>?) {
        if (list != null) {
            sets.addAll(list)
        }
        notifyDataSetChanged()
    }

    class SetAdapterViewHolder(private val binding: ItemSetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSet(set: Set) {

            // Set ViewModel for itemView
            if(binding.itemSetViewModel == null) {
                binding.itemSetViewModel = ItemSetViewModel(itemView.context, set)
            }
            else{
                (binding.itemSetViewModel as ItemSetViewModel).setCurrentSet(set)
            }
        }


    }

}