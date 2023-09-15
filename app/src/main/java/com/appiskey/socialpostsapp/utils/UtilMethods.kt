package com.appiskey.socialpostsapp.utils

import android.view.*
import java.text.SimpleDateFormat
import java.util.*


fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun getCurrentTime(format: String): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(Date())
}

fun getFormattedTagsString(tags: String): String{
    var tagsFormatted = ""
    tags.split(",\\s+".toRegex()).forEach { tag ->
        tagsFormatted += "#$tag "
    }
    return tagsFormatted
}
