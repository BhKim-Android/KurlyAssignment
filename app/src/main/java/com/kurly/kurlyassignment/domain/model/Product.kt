package com.kurly.kurlyassignment.domain.model

data class Product(
    val id: Int,
    val name: String,
    val image: String,
    val discount: String,
    val originalPrice: String,
    val discountedPrice: String?,
    val isSoldOut: Boolean
)