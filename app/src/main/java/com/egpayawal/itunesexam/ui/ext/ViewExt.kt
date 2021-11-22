package com.egpayawal.itunesexam.ui.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View.makeVisibleOrGone(condition: Boolean) = when {
    condition -> this.visibility = View.VISIBLE
    else -> this.visibility = View.GONE
}

val ViewGroup.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)