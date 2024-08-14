package com.example.calendar_app.utils

import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat

fun Context.colorList(id: Int): ColorStateList {
    return ColorStateList.valueOf(ContextCompat.getColor(this, id))
}