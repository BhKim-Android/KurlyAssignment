package com.kurly.kurlyassignment.data.remote.model

import com.google.gson.annotations.SerializedName

data class SectionResponse(
    @SerializedName("data")
    val sections: List<SectionDto>,
    @SerializedName("paging")
    val paging: PagingDto?
)