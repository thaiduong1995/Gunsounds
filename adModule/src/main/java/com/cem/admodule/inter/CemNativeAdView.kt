package com.cem.admodule.inter

import android.content.Context
import com.cem.admodule.manager.CustomNativeView

interface CemNativeAdView {
    fun load(context : Context, callback : NativeAdCallback?) : CemNativeAdView
    fun show(view : CustomNativeView)

    val isLoaded : Boolean
}