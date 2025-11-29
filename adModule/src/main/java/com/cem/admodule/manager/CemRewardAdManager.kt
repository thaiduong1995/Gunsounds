package com.cem.admodule.manager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import com.cem.admodule.ads.admob.AdmobRewardAdManager
import com.cem.admodule.ads.adx.AdxRewardAdManager
import com.cem.admodule.ads.applovin.ApplovinRewardAdManager
import com.cem.admodule.data.AdUnitItem
import com.cem.admodule.data.PlacementItem
import com.cem.admodule.data.RewardAdItem
import com.cem.admodule.enums.AdNetwork
import com.cem.admodule.ext.getAdCollection
import com.cem.admodule.ext.getAdUnit
import com.cem.admodule.ext.getAdUnitsCollection
import com.cem.admodule.inter.CemRewardAd
import com.cem.admodule.inter.CemRewardListener
import com.cem.admodule.inter.RewardLoadCallback
import com.google.gson.Gson
import javax.inject.Inject

class CemRewardAdManager @Inject constructor(
    private val context: Context
) {
    private val configManager: ConfigManager by lazy {
        ConfigManager.getInstance(context)
    }

    private val adsListRewardAd: MutableMap<String, CemRewardAd?> = HashMap()
    var isShowingAd: Boolean = false
        private set

    private fun createRewardManager(adUnitItem: AdUnitItem?): CemRewardAd? {
        if (adUnitItem == null) return null
        return when (AdNetwork.getNetwork(adUnitItem.adNetwork)) {
            AdNetwork.ADMOB -> AdmobRewardAdManager.newInstance(adUnitItem.adUnit)
            AdNetwork.APPLOVIN -> ApplovinRewardAdManager.newInstance(adUnitItem.adUnit)
            AdNetwork.ADX -> AdxRewardAdManager.newInstance(adUnitItem.adUnit)
            else -> null
        }
    }

    private fun loadAdsRewardManager(
        activity: Activity,
        configKey: String,
        units: MutableList<AdUnitItem>,
        callback: RewardLoadCallback? = null
    ) {
        val adUnit = getAdUnit(units)
        if (adUnit == null) {
            callback?.onLoadedFailed("ad unit null")
            return
        }

        val adManager = createRewardManager(adUnit)
        if (adManager == null) {
            callback?.onLoadedFailed("ad manager null")
            return
        }

        adManager.load(activity, object : RewardLoadCallback {
            override fun onLoaded(rewardAds: CemRewardAd?) {
                Log.d(TAG, "loadAdsReward $configKey: ${Gson().toJson(adUnit)}")
                callback?.onLoaded(rewardAds)
            }

            override fun onLoadedFailed(error: String?) {
                Log.d(TAG, "onLoadedFailed $configKey $error")
                units.remove(adUnit)
                loadAdsRewardManager(activity, configKey, units, callback)
            }
        })
    }

    fun loadRewardByPlacementInterface(
        activity: Activity, configKey: String, callback: RewardLoadCallback? = null
    ) {
        val adManager = configManager.adManagement
        if (adManager == null || !configManager.isEnable()) {
            callback?.onLoadedFailed("load ad error or disable")
            return
        }

        val adCollection = getAdCollection(adManager, configKey)
        if (adCollection == null) {
            callback?.onLoadedFailed("ad list null")
            return
        }
        if (!adCollection.enable) {
            callback?.onLoadedFailed("adCollection disable")
            return
        }

        val unitsItem: MutableList<AdUnitItem>? =
            getAdUnitsCollection(adManager, configKey)?.toMutableList()
        if (unitsItem.isNullOrEmpty()) {
            callback?.onLoadedFailed("ad list null")
            return
        }
        if (adsListRewardAd.containsKey(adCollection.adsUnit ?: configKey)) {
            callback?.onLoadedFailed("load add error, Ads already loaded")
            return
        }

        loadAdsRewardManager(activity, configKey, unitsItem, object : RewardLoadCallback {
            override fun onLoaded(rewardAds: CemRewardAd?) {
                adsListRewardAd[adCollection.adsUnit ?: configKey] = rewardAds
                callback?.onLoaded(rewardAds)
            }

            override fun onLoadedFailed(error: String?) {
                callback?.onLoadedFailed(error)
            }
        })
    }

    fun loadRewardByUnitsInterface(
        activity: Activity, configKey: String, callback: RewardLoadCallback? = null
    ) {
        val adManager = configManager.adManagement
        if (adManager == null || !configManager.isEnable()) {
            callback?.onLoadedFailed("load ad error or disable")
            return
        }

        val unitsItem: MutableList<AdUnitItem>? =
            getAdUnitsCollection(adManager, configKey)?.toMutableList()
        if (unitsItem.isNullOrEmpty()) {
            callback?.onLoadedFailed("ad list null")
            return
        }

        if (adsListRewardAd.containsKey(configKey)) {
            callback?.onLoadedFailed("load add error, Ads already loaded")
            return
        }

        loadAdsRewardManager(activity, configKey, unitsItem, object : RewardLoadCallback {
            override fun onLoaded(rewardAds: CemRewardAd?) {
                adsListRewardAd[configKey] = rewardAds
                callback?.onLoaded(rewardAds)
            }

            override fun onLoadedFailed(error: String?) {
                callback?.onLoadedFailed(error)
            }
        })
    }

    @JvmOverloads
    fun loadRewardByPlacementCallback(
        activity: Activity,
        configKey: String,
        callback: (() -> Unit)? = null
    ) {
        loadRewardByPlacementInterface(activity, configKey, object : RewardLoadCallback {
            override fun onLoaded(rewardAds: CemRewardAd?) {
                callback?.invoke()
            }

            override fun onLoadedFailed(error: String?) {
                callback?.invoke()
            }
        })
    }

    @JvmOverloads
    fun loadRewardByUnitsCallback(
        activity: Activity,
        configKey: String,
        callback: (() -> Unit)? = null
    ) {
        loadRewardByUnitsInterface(activity, configKey, object : RewardLoadCallback {
            override fun onLoaded(rewardAds: CemRewardAd?) {
                callback?.invoke()
            }

            override fun onLoadedFailed(error: String?) {
                callback?.invoke()
            }
        })
    }

    private fun showRewardByPlacement(
        activity: Activity, configKey: String, reload: Boolean, callback: CemRewardListener? = null
    ) {
        if (!configManager.isEnable()) {
            callback?.onRewardFail("show ads failed")
            return
        }

        val placementItem = getPlacementItem(configKey)
        if (placementItem?.enable == false) {
            callback?.onRewardFail("ads disable")
            return
        }

        val rewardAds = adsListRewardAd[placementItem?.adsUnit ?: configKey]
        if (rewardAds == null) {
            callback?.onRewardFail("not ads when show")
            return
        }
        val rewardListener = object : CemRewardListener() {
            override fun onRewardAdded(rewardAdItem: RewardAdItem?) {
                isShowingAd = false
                callback?.onRewardAdded(rewardAdItem)
            }

            override fun onRewardFail(error: String?) {
                isShowingAd = false
                callback?.onRewardFail(error)
            }

            override fun onAdShowedFullScreenContent() {
                isShowingAd = true
            }

            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                adsListRewardAd.remove(configKey)
                isShowingAd = false
                if (reload) loadRewardByPlacementCallback(activity, configKey)
            }
        }
        rewardAds.show(activity, rewardListener, rewardListener)
    }

    fun showByPlacementNotReload(
        activity: Activity, configKey: String, callback: CemRewardListener? = null
    ) {
        showRewardByPlacement(activity, configKey, false, callback)
    }

    fun showByPlacementReload(
        activity: Activity, configKey: String, callback: CemRewardListener? = null
    ) {
        showRewardByPlacement(activity, configKey, true, callback)
    }

    fun isRewardLoaded(configKey: String): Boolean {
        val placementItem = getPlacementItem(configKey)
        if (!adsListRewardAd.containsKey(placementItem?.adsUnit ?: configKey)) return false
        val cemRewardAd = adsListRewardAd[placementItem?.adsUnit ?: configKey]
        return cemRewardAd != null && cemRewardAd.isLoaded
    }

    private fun getPlacementItem(configKey: String): PlacementItem? {
        return ConfigManager.getInstance(context).adManagement?.placementList?.get(configKey)
    }


    companion object {
        val TAG = "CemRewardAdManager"

        @SuppressLint("StaticFieldLeak")
        private var mInstance: CemRewardAdManager? = null

        fun getInstance(activity: Context): CemRewardAdManager {
            return mInstance ?: synchronized(this) {
                val instance = CemRewardAdManager(activity)
                mInstance = instance
                instance
            }
        }
    }
}