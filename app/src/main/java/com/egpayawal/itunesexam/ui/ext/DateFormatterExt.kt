package com.egpayawal.itunesexam.ui.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
fun Date.toString(dateTimeFormat: String): String? {
    val simpleDateFormat = SimpleDateFormat(dateTimeFormat, Locale.getDefault())
    return simpleDateFormat.format(this)
}

fun String.toDate(dateTimeFormat: String): Date? = try {
    val simpleDateFormat = SimpleDateFormat(dateTimeFormat, Locale.getDefault())
    val date = this.replace("Z", "+00:00")
    simpleDateFormat.parse(date)
} catch (e: Throwable) {
    e.printStackTrace()
    null
}