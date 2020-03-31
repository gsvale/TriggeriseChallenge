package com.example.triggerisechallenge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.triggerisechallenge.R
import com.example.triggerisechallenge.databinding.ItemCardBinding
import com.example.triggerisechallenge.models.Card
import com.example.triggerisechallenge.viewModels.ItemCardViewModel

class CardsAdapter(private val context: Context, private val cards: ArrayList<Card>) :
    RecyclerView.Adapter<CardsAdapter.CardAdapterViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    // Create View Holders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapterViewHolder {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        // Set DataBinding instance
        val binding = DataBindingUtil.inflate<ItemCardBinding>(
            layoutInflater!!,
            R.layout.item_card,
            parent,
            false
        )

        return CardAdapterViewHolder(binding)
    }

    // OnBind method
    override fun onBindViewHolder(holder: CardAdapterViewHolder, position: Int) {
        holder.bindCard(cards[position])
    }

    // Get number/count of items of adapter
    override fun getItemCount(): Int {
        return cards.size
    }

    // Method to add items
    fun addItems(list: List<Card>?) {
        if (list != null) {
            cards.addAll(list)
        }
        notifyDataSetChanged()
    }

    class CardAdapterViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindCard(card: Card) {

            // Set ViewModel for itemView
            if(binding.itemCardViewModel == null) {
                binding.itemCardViewModel = ItemCardViewModel(itemView.context, card)
            }
            else{
                (binding.itemCardViewModel as ItemCardViewModel).setCurrentCard(card)
            }
        }


    }

}