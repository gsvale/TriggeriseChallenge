package com.example.triggerisechallenge.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Card() : Parcelable {

    val TAG = Card::class.java.name

    @SerializedName("name")
    var name: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("rarity")
    var rarity: String? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("artist")
    var artist: String? = null

    @SerializedName("number")
    var number: String? = null

    @SerializedName("colors")
    var colors: List<String>? = null

    @SerializedName("imageUrl")
    var imageUrl: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        type = parcel.readString()
        rarity = parcel.readString()
        text = parcel.readString()
        artist = parcel.readString()
        number = parcel.readString()
        colors = parcel.createStringArrayList()
        imageUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(rarity)
        parcel.writeString(text)
        parcel.writeString(artist)
        parcel.writeString(number)
        parcel.writeStringList(colors)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }

}