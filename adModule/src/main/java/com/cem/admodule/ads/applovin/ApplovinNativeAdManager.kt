package com.cem.admodule.ads.applovin

import android.content.Context
import android.view.LayoutInflater
import android.widget.RatingBar
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.nativeAds.MaxNativeAd
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder
import com.cem.admodule.R
import com.cem.admodule.databinding.NativeViewAdsBinding
import com.cem.admodule.ext.gone
import com.cem.admodule.inter.CemNativeAdView
import com.cem.admodule.inter.NativeAdCallback
import com.cem.admodule.manager.CemNativeManager
import com.cem.admodule.manager.CustomNativeView
import com.google.android.gms.ads.nativead.MediaView
import javax.inject.Inject


class ApplovinNativeAdManager @Inject constructor(
    private val adUnitId: String?
) : CemNativeAdView {
    private var nativeMaxAdView: MaxNativeAdView? = null
    private var nativeAdLoader: MaxNativeAdLoader? = null
    private var nativeAd: MaxAd? = null

    override fun load(context: Context, callback: NativeAdCallback?): CemNativeAdView {
        nativeAdLoader = MaxNativeAdLoader(adUnitId, context)
        nativeAdLoader?.setNativeAdListener(object : MaxNativeAdListener() {
            override fun onNativeAdLoaded(p0: MaxNativeAdView?, p1: MaxAd) {
                super.onNativeAdLoaded(p0, p1)
                nativeMaxAdView = p0
                nativeAd = p1
                callback?.onNativeLoaded(this@ApplovinNativeAdManager)
            }

            override fun onNativeAdLoadFailed(p0: String, p1: MaxError) {
                super.onNativeAdLoadFailed(p0, p1)
                callback?.onNativeFailed(p0)
            }
        })
        nativeAdLoader?.loadAd()
        return this
    }

    override fun show(view: CustomNativeView) {
        try {
            val nativeLayoutAd =
                NativeViewAdsBinding.inflate(LayoutInflater.from(view.context), view, true)
            val nativeView = createNativeAdView(view)
            if (isLoaded) {
                nativeAdLoader?.render(nativeView, nativeAd)
                nativeView.findViewById<MediaView>(R.id.media_view)?.gone()
                nativeLayoutAd.viewNative.let {
                    it.removeAllViews()
                    it.addView(nativeView)
                    view.background?.removeAllViews()
                }
            }
        } catch (e: Exception) {
            view.gone()
            e.printStackTrace()
        }
    }

    private fun createNativeAdView(view: CustomNativeView): MaxNativeAdView {
        val binder: MaxNativeAdViewBinder.Builder =
            MaxNativeAdViewBinder.Builder(view.getTemplateType())
        if (view.primaryView != null) binder.setTitleTextViewId(view.primaryView!!.id)
        if (view.tertiaryView != null) binder.setBodyTextViewId(view.tertiaryView!!.id)
        if (view.iconView != null) binder.setIconImageViewId(view.iconView!!.id)
        if (view.callToActionView != null) binder.setCallToActionButtonId(view.callToActionView!!.id)
        return MaxNativeAdView(binder.build(), view.context)
    }

    override val isLoaded: Boolean
        get() = nativeAd != null

    companion object {
        val TAG = CemNativeManager.TAG

        @JvmStatic
        fun newInstance(adUnit: String?): ApplovinNativeAdManager {
            return ApplovinNativeAdManager(adUnit)
        }
    }
}