package com.kurly.kurlyassignment.common

import java.text.DecimalFormat

fun Int.toWon(): String = "${DecimalFormat("#,###").format(this)}원"