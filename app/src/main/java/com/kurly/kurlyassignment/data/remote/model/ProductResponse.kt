package com.kurly.kurlyassignment.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data")
    val data: List<ProductDto>
)
