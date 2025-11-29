package com.cem.admodule.manager

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.cem.admodule.R
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd

class CustomNativeView : FrameLayout {
    private var templateType = 0
    var primaryView: TextView? = null
    var secondaryView: TextView? = null
    var ratingBar: RatingBar? = null
    var tertiaryView: TextView? = null
    var iconView: ImageView? = null
    var callToActionView: Button? = null
    var mediaView: MediaView? = null
    var background: ViewGroup? = null
    var textAd: TextView? = null

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ){
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.CustomNativeView, 0, 0)
        templateType = getTemplateType(attributes)
        attributes.recycle()
        initLayout(context, templateType)
        initIds()
    }

    private fun initLayout(context: Context, layoutRes: Int) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(layoutRes, this)
    }

    private fun getTemplateType(attributes: TypedArray): Int {
        return attributes.getResourceId(
            R.styleable.CustomNativeView_ad_view_layout, R.layout.admob_native_ad_view
        )
    }

    private fun initIds() {
        primaryView = findViewById<TextView>(R.id.primary)
        tertiaryView = findViewById<TextView>(R.id.body)
        if (ratingBar != null) ratingBar!!.isEnabled = false
        callToActionView = findViewById<Button>(R.id.cta)
        iconView = findViewById<ImageView>(R.id.icon)
        background = findViewById<ViewGroup>(R.id.background)
        mediaView = findViewById<MediaView>(R.id.media_view)
        textAd = findViewById<TextView>(R.id.ad_notification_view)
    }

    fun adHasOnlyStore(nativeAd: NativeAd?): Boolean {
        val store = nativeAd?.store
        val advertiser = nativeAd?.advertiser
        return !TextUtils.isEmpty(store) && TextUtils.isEmpty(advertiser)
    }

    fun setTemplateType(int: Int) {
        templateType = int
        removeAllViews()
        initLayout(this.context, templateType)
        initIds()
    }

    fun getTemplateType(): Int {
        return templateType
    }

    fun setBackgroundNative(backgroundRes: Int) {
        background?.setBackgroundResource(backgroundRes)
    }
    fun setBackgroundTint(colorRes: Int) {
        background?.let {
            ViewCompat.setBackgroundTintList(
                it,
                ColorStateList.valueOf(colorRes)
            )
        }
    }

    fun setPrimaryColor(textColor: Int) {
        primaryView?.setTextColor(textColor)
    }

    fun setPrimarySize(textSize: Float) {
        primaryView?.textSize = textSize
    }

    fun setSecondaryColor(textColor: Int) {
        secondaryView?.setTextColor(textColor)
    }

    fun setSecondarySize(textSize: Float) {
        secondaryView?.textSize = textSize
    }

    fun setBodyColor(textColor: Int) {
        tertiaryView?.setTextColor(textColor)
    }

    fun setBodySize(textSize: Float) {
        tertiaryView?.textSize = textSize
    }

    fun setCallTextColor(textColor: Int) {
        callToActionView?.setTextColor(textColor)
    }

    fun setCallTextSize(textSize: Float) {
        callToActionView?.textSize = textSize
    }

    fun setCallBackground(bgRes: Int) {
        callToActionView?.setBackgroundResource(bgRes)
    }

    fun setTextADColor(textColor: Int) {
        textAd?.setTextColor(textColor)
    }

    fun setTextADBackground(bgRes: Int) {
        textAd?.setBackgroundResource(bgRes)
    }

    fun setBackground(int: Int) {
        background?.setBackgroundResource(int)
        invalidate()
    }
}