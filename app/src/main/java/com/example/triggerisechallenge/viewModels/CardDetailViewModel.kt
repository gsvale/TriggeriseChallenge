package com.example.triggerisechallenge.viewModels

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.triggerisechallenge.R
import com.example.triggerisechallenge.models.Card
import com.squareup.picasso.Picasso
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

class CardDetailViewModel(private var context: Context?, private val mCard: Card) : Observable() {


    // Method to get CARD name
    fun getCardName(): String? {
        return mCard.name
    }

    // Method to get CARD type
    fun getCardType(): String? {
        return mCard.type
    }

    // Method to get CARD rarity
    fun getCardRarity(): String? {
        return mCard.rarity
    }

    // Method to get CARD text
    fun getCardText(): String? {
        return mCard.text
    }

    // Method to get CARD artist
    fun getCardArtist(): String? {
        return mCard.artist
    }

    // Method to get CARD number
    fun getCardNumber(): String? {
        return mCard.number
    }

    // Method to get CARD colors
    fun getCardColors(): String? {

        return if (mCard.colors.isNullOrEmpty()) {
            "-"
        } else {
            val colors = StringBuilder()
            val size: Int = mCard.colors!!.size - 1;
            for (i in 0..size) {
                colors.append(mCard.colors!![i])
                if (i != mCard.colors!!.size - 1) {
                    colors.append("\n")
                }
            }
            colors.toString()
        }
    }

    // Method to get Card image
    fun getCardImage(): String? {

        if (mCard.imageUrl.isNullOrEmpty()) {
            return "-"
        }
        return mCard.imageUrl
    }

    object DataBindingAdapter {

        @BindingAdapter("bind:cardImage")
        @JvmStatic
        fun loadImage(view: ImageView, cardImage: String?) {
            // Use Picasso to load avatar from url
            Picasso.get()
                .load(cardImage)
                .error(R.drawable.ic_no_card)
                .placeholder(R.drawable.ic_no_card)
                .into(view)
        }

    }


}