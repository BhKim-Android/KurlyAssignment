package com.kurly.kurlyassignment.data.model

import com.google.gson.annotations.SerializedName

data class PagingDto(
    @SerializedName("next_page")
    val nextPage: Int
)
