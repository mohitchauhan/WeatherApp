package com.android.xu.core.extensions

import java.text.SimpleDateFormat
import java.util.*

fun SimpleDateFormat.getDay(stringDate: String, format: String): String {
    val df = SimpleDateFormat(format, Locale.getDefault())
    val date = df.parse(stringDate)
    return format(date)
}