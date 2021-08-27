package com.sword.demo.network.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url") val url: String
)