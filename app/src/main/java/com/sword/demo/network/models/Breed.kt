package com.sword.demo.network.models

import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("id") val id: Int,
    @SerializedName("bred_for") val category: String,
    @SerializedName("breed_group") val group: String,
    @SerializedName("image") val image: Image,
    @SerializedName("name") val name: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("temperament") val temperament: String
)