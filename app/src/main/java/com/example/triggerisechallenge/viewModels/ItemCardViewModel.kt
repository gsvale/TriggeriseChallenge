package com.example.triggerisechallenge.viewModels

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.View
import androidx.databinding.BaseObservable
import com.example.triggerisechallenge.activities.DetailsActivity
import com.example.triggerisechallenge.models.Card

class ItemCardViewModel(private var context: Context?, private var mCard: Card) :
    BaseObservable() {

    // Method to set current Card item
    fun setCurrentCard(card: Card) {
        this.mCard = card
        notifyChange()
    }

    // Method to get CARD name
    fun getCardName(): String? {
        return mCard.name
    }

    // Method to get CARD type
    fun getCardType(): String? {
        return mCard.type
    }

    fun onClickCardItem(view: View) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(Card::TAG.name, mCard as Parcelable)
        context!!.startActivity(intent)
    }

}