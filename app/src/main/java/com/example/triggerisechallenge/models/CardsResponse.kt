package com.example.triggerisechallenge.models

import com.google.gson.annotations.SerializedName

class CardsResponse {


    @SerializedName("cards")
    private var cards: List<Card>? = null

    fun getCards(): List<Card>? {
        return cards
    }

}