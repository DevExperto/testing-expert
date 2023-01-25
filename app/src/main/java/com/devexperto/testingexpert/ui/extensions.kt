package com.devexperto.testingexpert.ui

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatToString(): String =
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).run {
        format(this@formatToString)
    }