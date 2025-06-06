package com.kurly.kurlyassignment.data.remote.model

import com.google.gson.annotations.SerializedName

data class SectionDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)