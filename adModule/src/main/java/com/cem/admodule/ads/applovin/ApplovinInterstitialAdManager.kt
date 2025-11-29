package com.cem.admodule.ads.applovin

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxAdRevenueListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import com.cem.admodule.data.ErrorCode
import com.cem.admodule.enums.AdNetwork
import com.cem.admodule.inter.CemInterstitialAd
import com.cem.admodule.inter.InterstitialLoadCallback
import com.cem.admodule.inter.InterstitialShowCallback
import com.cem.admodule.manager.CemInterstitialManager
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ApplovinInterstitialAdManager @Inject constructor(
    private val unitId: String?
) : CemInterstitialAd, MaxAdListener, MaxAdRevenueListener {

    private var interstitialAd: MaxInterstitialAd? = null
    private var callbackLoadAd: InterstitialLoadCallback? = null
    private var callbackShowAd: InterstitialShowCallback? = null

    override fun load(activity: Activity, callback: InterstitialLoadCallback?): CemInterstitialAd {
        callbackLoadAd = callback
        (activity as AppCompatActivity).lifecycleScope.launch(start = CoroutineStart.DEFAULT) {
            if (unitId != null && interstitialAd == null) {
                interstitialAd = MaxInterstitialAd(unitId, activity)
                interstitialAd?.setListener(this@ApplovinInterstitialAdManager)
                interstitialAd?.setRevenueListener(this@ApplovinInterstitialAdManager)
                interstitialAd?.loadAd()
            } else {
                callbackLoadAd?.onAdFailedToLoaded(
                    ErrorCode(
                        message = "unitId null",
                        code = -1
                    )
                )
            }
        }
        return this
    }


    override val isLoaded: Boolean
        get() = interstitialAd != null && unitId != null

    override fun show(activity: Activity, callback: InterstitialShowCallback?) {
        callbackShowAd = callback
        if (isLoaded) {
            if (interstitialAd?.isReady == true) {
                interstitialAd?.showAd(activity)
            } else {
                callback?.onDismissCallback(AdNetwork.APPLOVIN)
            }
        } else {
            callback?.onDismissCallback(AdNetwork.APPLOVIN)
        }
    }

    override fun onAdLoaded(p0: MaxAd) {
        Log.d(TAG, "onAdLoaded applovin: ${p0.adUnitId}")
        callbackLoadAd?.onAdLoaded(this@ApplovinInterstitialAdManager)
    }

    override fun onAdDisplayed(p0: MaxAd) {
        Log.d(TAG, "onAdDisplayed: ")
        callbackShowAd?.onAdShowedCallback(AdNetwork.APPLOVIN)
    }

    override fun onAdHidden(p0: MaxAd) {
        Log.d(TAG, "onAdHidden: ")
        callbackShowAd?.onDismissCallback(AdNetwork.APPLOVIN)
    }

    override fun onAdClicked(p0: MaxAd) {
        Log.d(TAG, "onAdClicked: ")
        callbackShowAd?.onAdClicked()
    }

    override fun onAdLoadFailed(p0: String, p1: MaxError) {
        Log.d(TAG, "onAdLoadFailed: ")
        callbackLoadAd?.onAdFailedToLoaded(
            ErrorCode(
                message = p1.message,
                code = p1.code
            )
        )
    }

    override fun onAdDisplayFailed(p0: MaxAd, p1: MaxError) {
        Log.d(TAG, "onAdDisplayFailed: ")
        callbackShowAd?.onAdFailedToShowCallback(p1.code.toString())
    }

    override fun onAdRevenuePaid(ad: MaxAd) {
        Log.d(TAG, "onAdRevenuePaid: ${ad.revenue}")
    }

    companion object {
        var TAG: String? = CemInterstitialManager.TAG

        @JvmStatic
        fun newInstance(adUnit: String?): ApplovinInterstitialAdManager {
            return ApplovinInterstitialAdManager(adUnit)
        }
    }
}