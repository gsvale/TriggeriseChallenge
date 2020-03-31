package com.example.triggerisechallenge.viewModels

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.View
import androidx.databinding.BaseObservable
import com.example.triggerisechallenge.R
import com.example.triggerisechallenge.activities.CardsActivity
import com.example.triggerisechallenge.models.Set
import com.example.triggerisechallenge.utils.Constants.CARDS_TAG

class ItemSetViewModel(private var context: Context?, private var mSet: Set) :
    BaseObservable() {


    // Method to set current Set item
    fun setCurrentSet(set: Set) {
        this.mSet = set
        notifyChange()
    }

    // Method to get SET name
    fun getSetName(): String? {
        return mSet.name
    }

    // Method to get SET type
    fun getSetType(): String? {
        return mSet.type
    }

    fun cardsOnClick(view: View) {
        val intent = Intent(context, CardsActivity::class.java)
        intent.putExtra(Set::TAG.name, mSet as Parcelable)
        intent.putExtra(CARDS_TAG, context!!.getString(R.string.cards))
        context!!.startActivity(intent)
    }

    fun boosterOnClick(view: View) {
        val intent = Intent(context, CardsActivity::class.java)
        intent.putExtra(Set::TAG.name, mSet as Parcelable)
        intent.putExtra(CARDS_TAG, context!!.getString(R.string.booster))
        context!!.startActivity(intent)
    }


}