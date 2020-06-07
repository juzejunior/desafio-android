package com.picpay.desafio.android.utils

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.visible() {
    this.visibility = View.VISIBLE
}

fun ProgressBar.invisible() {
    this.visibility = View.INVISIBLE
}