package com.sword.demo.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breed(
    @SerializedName("id") val id: Int,
    @SerializedName("bred_for") val category: String,
    @SerializedName("breed_group") val group: String,
    @SerializedName("image") val image: Image? = null,
    @SerializedName("name") val name: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("temperament") val temperament: String
) : Parcelable