package com.example.triggerisechallenge.network

import com.example.triggerisechallenge.models.CardsResponse
import com.example.triggerisechallenge.models.Set
import com.example.triggerisechallenge.models.SetsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkInterface {

    @GET("{setsPath}")
    fun getSets(@Path(value = "setsPath", encoded = true) setsPath: String): Observable<SetsResponse>

    @GET("{cardsPath}")
    fun getCardsFromSet(@Path(value = "cardsPath", encoded = true) setsPath: String, @Query("set") code: String): Observable<CardsResponse>

    @GET("{setsPath}/{setCode}/{boosterPath}")
    fun getCardsFromBooster(@Path(value = "setsPath", encoded = true) setsPath: String,
                            @Path(value = "setCode", encoded = true) setCode: String,
                            @Path(value = "boosterPath", encoded = true) boosterPath: String): Observable<CardsResponse>

}