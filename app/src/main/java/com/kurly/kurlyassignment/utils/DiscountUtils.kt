package com.kurly.kurlyassignment.utils

import java.util.Locale

object DiscountUtils {
    fun calculateDiscount(originalPrice: Int, discountedPrice: Int?): String {
        if (discountedPrice == null) return ""
        val discountPercentage = (originalPrice - discountedPrice) * 100 / originalPrice
        return String.format(Locale.US, "%d%%", discountPercentage)
    }
}