package com.cem.admodule.inter

interface CemOpenAd {
    fun onLoaded(adUnit : String,callback: OpenLoadCallback ) : CemOpenAd
    val  isLoaded : Boolean
    fun onShowed(isShowDirect: Boolean = false,callback: InterstitialShowCallback?)
}