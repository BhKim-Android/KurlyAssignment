package com.kurly.kurlyassignment.data.model

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("originalPrice")
    val originalPrice: Int,

    @SerializedName("discountedPrice")
    val discountedPrice: Int?,

    @SerializedName("isSoldOut")
    val isSoldOut: Boolean
)
