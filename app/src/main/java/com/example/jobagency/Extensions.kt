package com.example.jobagency


import java.text.SimpleDateFormat
import java.util.*

fun  toSimpleString(updated_at: String) :String {
    val inputFormatter = SimpleDateFormat ("yyyy-MM-dd HH:mm:ss",
                                           Locale.ENGLISH)
    val outputFormatter = SimpleDateFormat("EEE dd MMM yyyy",
                                           Locale.getDefault())
    val date = inputFormatter.parse(updated_at)
    return outputFormatter.format(date)
}

//DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
//yyyy-MM-dd'T'HH:mm:ss'Z'
//EEE, dd MMM yyyy HH:mm