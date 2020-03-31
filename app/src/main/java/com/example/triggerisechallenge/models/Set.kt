package com.example.triggerisechallenge.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Set() : Parcelable {

    val TAG = Set::class.java.name

    @SerializedName("name")
    var name: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("code")
    var code: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        type = parcel.readString()
        code = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(code)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Set> {
        override fun createFromParcel(parcel: Parcel): Set {
            return Set(parcel)
        }

        override fun newArray(size: Int): Array<Set?> {
            return arrayOfNulls(size)
        }
    }


}