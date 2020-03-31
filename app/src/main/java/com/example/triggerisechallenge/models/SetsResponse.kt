package com.example.triggerisechallenge.models

import com.google.gson.annotations.SerializedName

class SetsResponse {

    @SerializedName("sets")
    private var sets: List<Set>? = null

    fun getSets(): List<Set>? {
        return sets
    }

}