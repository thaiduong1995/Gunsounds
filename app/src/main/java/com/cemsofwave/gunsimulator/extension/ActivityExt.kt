package com.cemsofwave.gunsimulator.extension

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import com.cemsofwave.gunsimulator.R

fun Activity.start(intent: Intent) {
    try {
        this.startActivity(intent)
    } catch (ex: ActivityNotFoundException) {
        this.toast(this.getString(R.string.common_error))
    }
}