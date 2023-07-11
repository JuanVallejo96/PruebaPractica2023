package com.example.pruebapractica

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DealService {
    @Query("SELECT * FROM Deal ")
    fun listDeals(@Path("id") id: String?, @Path("price") price: String?): Call<List<OfferModel>>
}