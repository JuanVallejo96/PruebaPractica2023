package com.example.pruebapractica

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DealService {
    @GET("deals")
    fun listDeals(@Query("storeID") id: String?, @Query("upperPrice") price: String?): Call<List<OfferModel>>
}