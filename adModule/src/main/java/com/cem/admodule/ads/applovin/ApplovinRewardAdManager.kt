package com.cem.admodule.ads.applovin

import android.app.Activity
import android.util.Log
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import arrow.core.left
import arrow.core.right
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.MaxReward
import com.applovin.mediation.MaxRewardedAdListener
import com.applovin.mediation.ads.MaxRewardedAd
import com.cem.admodule.data.RewardAdItem
import com.cem.admodule.inter.CemRewardAd
import com.cem.admodule.inter.RewardItemCallback
import com.cem.admodule.inter.RewardLoadCallback
import com.cem.admodule.inter.RewardShowCallback
import com.cem.admodule.manager.CemRewardAdManager
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume


class ApplovinRewardAdManager @Inject constructor(
    private val adUnitId: String?
) : CemRewardAd, MaxRewardedAdListener {

    private var rewardAd: MaxRewardedAd? = null
    private var callBackLoad: RewardLoadCallback? = null
    private var callBackShow: RewardShowCallback? = null
    private var callbackItem: RewardItemCallback? = null
    override fun load(activity: Activity, callback: RewardLoadCallback?): CemRewardAd {
        this.callBackLoad = callback
        if (adUnitId != null) {
            rewardAd = MaxRewardedAd.getInstance(adUnitId, activity)
            rewardAd?.setListener(this)
            rewardAd?.loadAd()
        } else {
            callBackLoad?.onLoadedFailed("adUnit null")
        }
        return this
    }

    override val isLoaded: Boolean
        get() = adUnitId != null && rewardAd != null

    override fun show(
        activity: Activity, callback: RewardShowCallback?, callbackItem: RewardItemCallback?
    ) {
        this.callbackItem = callbackItem
        this.callBackShow = callback
        if (rewardAd != null && adUnitId != null) {
            Log.d(TAG, "show: adUnit and reward  != null")
            if (rewardAd?.isReady == true) {
                rewardAd?.setListener(this)
                rewardAd?.showAd(activity)
            } else {
                callBackShow?.onAdDismissedFullScreenContent()
            }
        } else {
            Log.d(TAG, "show: adUnit and reward  null")
            callBackShow?.onAdFailedToShowFullScreenContent("No ads loading success")
        }
    }

    override fun onAdLoaded(maxAd: MaxAd) {
        Log.d(TAG, "onAdLoaded: ${maxAd.revenue}")
        callBackLoad?.onLoaded(this@ApplovinRewardAdManager)
    }

    override fun onAdDisplayed(p0: MaxAd) {
        Log.d(TAG, "onAdDisplayed: ${p0.adUnitId}")
        callBackShow?.onAdShowedFullScreenContent()
    }

    override fun onAdHidden(p0: MaxAd) {
        Log.d(TAG, "onAdHidden: ${p0.adUnitId}")
        callBackShow?.onAdDismissedFullScreenContent()
    }

    override fun onAdClicked(p0: MaxAd) {
        Log.d(TAG, "onAdClicked: ")
    }

    override fun onAdLoadFailed(p0: String, p1: MaxError) {
        Log.d(TAG, "onAdLoadFailed: ")
        rewardAd = null
        callBackLoad?.onLoadedFailed(p0)
    }

    override fun onAdDisplayFailed(p0: MaxAd, p1: MaxError) {
        Log.d(TAG, "onAdDisplayFailed: ")
        callBackShow?.onAdFailedToShowFullScreenContent(p1.message)
    }

    override fun onUserRewarded(p0: MaxAd, p1: MaxReward) {
        Log.d(TAG, "onUserRewarded: ${p1.amount}")
        callbackItem?.onUserEarnedReward(
            RewardAdItem(type = p1.label, amount = p1.amount)
        )
    }

    companion object {
        val TAG = CemRewardAdManager.TAG

        @JvmStatic
        fun newInstance(adUnit: String?): ApplovinRewardAdManager {
            return ApplovinRewardAdManager(adUnit)
        }
    }

}