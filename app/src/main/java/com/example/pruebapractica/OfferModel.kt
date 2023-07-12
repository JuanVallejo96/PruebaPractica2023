package com.example.pruebapractica

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OfferModel (
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("dealID")
    @Expose
    val dealID: String,
    @SerializedName("storeID")
    @Expose
    val storeID: String,
    @SerializedName("gameID")
    @Expose
    val gameID: String,
    @SerializedName("salePrice")
    @Expose
    val salePrice: String,
    @SerializedName("normalPrice")
    @Expose
    val normalPrice: String
)
