package com.cem.admodule.manager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import com.cem.admodule.ads.admob.AdmobInterstitialAdManager
import com.cem.admodule.ads.adx.AdxInterstitialAdManager
import com.cem.admodule.ads.applovin.ApplovinInterstitialAdManager
import com.cem.admodule.ads.mintegral.MintegralInterstitialAdManager
import com.cem.admodule.data.AdUnitItem
import com.cem.admodule.data.ErrorCode
import com.cem.admodule.data.PlacementItem
import com.cem.admodule.enums.AdNetwork
import com.cem.admodule.ext.getAdCollection
import com.cem.admodule.ext.getAdUnit
import com.cem.admodule.ext.getAdUnitsCollection
import com.cem.admodule.inter.CemInterstitialAd
import com.cem.admodule.inter.InterstitialLoadCallback
import com.cem.admodule.inter.InterstitialShowCallback
import com.google.gson.Gson
import javax.inject.Inject

class CemInterstitialManager @Inject constructor(
    private val context: Context
) {
    private val configManager: ConfigManager by lazy {
        ConfigManager.getInstance(context)
    }

    private val adsInterstitialManager: MutableMap<String, CemInterstitialAd?> = HashMap()

    private var lastShowAdsTime: Long = System.currentTimeMillis()

    var isShowingAd: Boolean = false
        private set

    private fun createInterstitial(adUnitItem: AdUnitItem?): CemInterstitialAd? {
        if (adUnitItem == null) return null
        return when (AdNetwork.getNetwork(adUnitItem.adNetwork)) {
            AdNetwork.ADMOB -> AdmobInterstitialAdManager.newInstance(adUnitItem.adUnit)

            AdNetwork.ADX -> AdxInterstitialAdManager.newInstance(
                adUnitItem.adUnit
            )

            AdNetwork.APPLOVIN -> ApplovinInterstitialAdManager.newInstance(adUnitItem.adUnit)

            AdNetwork.MINTEGRAL -> MintegralInterstitialAdManager.newInstance(
                adUnit = adUnitItem.adUnit,
                placementId = adUnitItem.placementId
            )

            else -> null
        }
    }

    private fun loadAdsByUnitsId(
        activity: Activity,
        configKey: String,
        unitsId: MutableList<AdUnitItem>,
        onAdListener: InterstitialLoadCallback?
    ) {
        val adUnit = getAdUnit(unitsId)

        if (adUnit == null) {
            onAdListener?.onAdFailedToLoaded(ErrorCode(message = "not config ad unit"))
            return
        }

        val adInterstitialAd = createInterstitial(adUnit)
        if (adInterstitialAd == null) {
            onAdListener?.onAdFailedToLoaded(ErrorCode("not ad network"))
            return
        }

        adInterstitialAd.load(activity, object : InterstitialLoadCallback {
            override fun onAdLoaded(cemInterstitialAd: CemInterstitialAd?) {
                Log.d(TAG, "onAdLoaded $configKey: ${Gson().toJson(adUnit)}")
                onAdListener?.onAdLoaded(cemInterstitialAd)
            }

            override fun onAdFailedToLoaded(error: ErrorCode) {
                Log.d(TAG, "onAdFailedToLoaded: $configKey : ${error.message}")
                unitsId.remove(adUnit)
                loadAdsByUnitsId(
                    activity = activity,
                    configKey = configKey,
                    unitsId = unitsId,
                    onAdListener = onAdListener
                )
            }
        })
    }

    fun loadInterstitialByUnits(
        activity: Activity,
        configKey: String,
        onAdListener: InterstitialLoadCallback? = null
    ) {
        val adManager = configManager.adManagement
        if (adManager == null) {
            Log.d(TAG, "loading ad manager null")
            onAdListener?.onAdFailedToLoaded(ErrorCode(message = "loading null"))
            return
        }

        if (!configManager.isEnable()) {
            Log.d(TAG, "loading isEnable false")
            onAdListener?.onAdFailedToLoaded(ErrorCode(message = "loading isEnable false"))
            return
        }

        val units: MutableList<AdUnitItem>? =
            getAdUnitsCollection(adManager, configKey)?.toMutableList()

        if (units.isNullOrEmpty()) {
            Log.d(TAG, "loadAds: list units null")
            onAdListener?.onAdFailedToLoaded(ErrorCode(message = "list data null"))
            return
        }

        if (adsInterstitialManager.containsKey(configKey)) {
            Log.d(TAG, "loadAds: ads exits")
            onAdListener?.onAdLoaded(adsInterstitialManager[configKey])
            return
        }

        loadAdsByUnitsId(
            activity,
            configKey = configKey,
            unitsId = units,
            object : InterstitialLoadCallback {
                override fun onAdLoaded(cemInterstitialAd: CemInterstitialAd?) {
                    adsInterstitialManager[configKey] = cemInterstitialAd
                    onAdListener?.onAdLoaded(cemInterstitialAd)
                }

                override fun onAdFailedToLoaded(error: ErrorCode) {
                    Log.d(TAG, "onAdFailedToLoaded: ${error.message}")
                    onAdListener?.onAdFailedToLoaded(error)
                }
            })
    }

    fun loadInterstitialByPlacemnt(
        activity: Activity,
        configKey: String,
        onAdListener: InterstitialLoadCallback? = null
    ) {
        val adManager = configManager.adManagement
        if (adManager == null) {
            Log.d(TAG, "$configKey loading ad manager null")
            onAdListener?.onAdFailedToLoaded(ErrorCode(message = "loading null"))
            return
        }

        if (!configManager.isEnable()) {
            Log.d(TAG, "$configKey loading isEnable false")
            onAdListener?.onAdFailedToLoaded(ErrorCode(message = "loading isEnable false"))
            return
        }

        val adsCollection = getAdCollection(adManager, configKey)
        if (adsCollection == null) {
            Log.d(TAG, "$configKey: addCollection data null")
            onAdListener?.onAdFailedToLoaded(ErrorCode(message = "list data null"))
            return
        }

        if (!adsCollection.enable) {
            Log.d(TAG, "$configKey: addCollection disable")
            onAdListener?.onAdFailedToLoaded(ErrorCode(message = "addCollection disable"))
            return
        }

        val units: MutableList<AdUnitItem>? =
            getAdUnitsCollection(adManager, adsCollection.adsUnit)?.toMutableList()

        if (units.isNullOrEmpty()) {
            Log.d(TAG, "$configKey: list units null")
            onAdListener?.onAdFailedToLoaded(ErrorCode(message = "list data null"))
            return
        }

        if (adsInterstitialManager.containsKey(adsCollection.adsUnit)) {
            Log.d(TAG, "$configKey: ads exits")
            onAdListener?.onAdLoaded(adsInterstitialManager[configKey])
            return
        }

        loadAdsByUnitsId(
            activity,
            configKey = configKey,
            unitsId = units,
            object : InterstitialLoadCallback {
                override fun onAdLoaded(cemInterstitialAd: CemInterstitialAd?) {
                    adsInterstitialManager[adsCollection.adsUnit ?: configKey] = cemInterstitialAd
                    onAdListener?.onAdLoaded(cemInterstitialAd)
                }

                override fun onAdFailedToLoaded(error: ErrorCode) {
                    Log.d(TAG, "onAdFailedToLoaded: ${error.message}")
                    onAdListener?.onAdFailedToLoaded(error)
                }
            })
    }

    private fun loadInterstitialReload(
        activity: Activity, configKey: String, callback: (() -> Unit)? = null
    ) {
        loadInterstitialByPlacemnt(activity, configKey, object : InterstitialLoadCallback {
            override fun onAdLoaded(cemInterstitialAd: CemInterstitialAd?) {
                callback?.invoke()
            }

            override fun onAdFailedToLoaded(error: ErrorCode) {
                callback?.invoke()
            }
        })
    }

    private fun showInterstitialManager(
        activity: Activity,
        configKey: String,
        reload: Boolean,
        callback: InterstitialShowCallback? = null
    ) {
        if (!configManager.isEnable()) {
            Log.d(TAG, "showAds enable: ${configManager.isEnable()}")
            callback?.onDismissCallback(AdNetwork.ADMOB)
            return
        }

        val placementItem = getPlacementItem(configKey)
        if (placementItem == null || !placementItem.enable) {
            Log.d(TAG, "showAds disable")
            callback?.onDismissCallback(AdNetwork.ADMOB)
            return
        }

        val adManager = configManager.adManagement
        val interval = adManager?.adInterval ?: (30 * 1000L)
        val currentTime = System.currentTimeMillis()

        val isNeedShow = if (placementItem.showDirect) {
            true
        } else {
            currentTime - lastShowAdsTime >= interval
        }

        if (!isNeedShow) {
            Log.d(TAG, "not time show")
            callback?.onAdFailedToShowCallback("not time show")
            return
        }
        val cemInterstitialAd = adsInterstitialManager[placementItem.adsUnit]
        if (cemInterstitialAd == null) {
            loadInterstitialByPlacemnt(activity, configKey)
            Log.d(TAG, "showAds: cemInterstitialAd null")
            callback?.onDismissCallback(AdNetwork.ADMOB)
            return
        }

        cemInterstitialAd.show(activity, object : InterstitialShowCallback {
            override fun onAdFailedToShowCallback(error: String) {
                callback?.onAdFailedToShowCallback(error)
                isShowingAd = false
            }

            override fun onAdShowedCallback(network: AdNetwork) {
                callback?.onAdShowedCallback(network)
                lastShowAdsTime = System.currentTimeMillis()
                isShowingAd = true
            }

            override fun onDismissCallback(network: AdNetwork) {
                callback?.onDismissCallback(network)
                isShowingAd = false
                lastShowAdsTime = System.currentTimeMillis()
                adsInterstitialManager.remove(placementItem.adsUnit)
                if (reload) loadInterstitialReload(activity, configKey)
            }

            override fun onAdClicked() {
            }
        })

    }

    fun showInterstitialNotReloadCallback(
        activity: Activity,
        configKey: String,
        callback: (() -> Unit)? = null
    ) {
        showInterstitialManager(
            activity = activity,
            configKey = configKey,
            reload = false,
            callback = object : InterstitialShowCallback {
                override fun onAdFailedToShowCallback(error: String) {
                    callback?.invoke()
                }

                override fun onAdShowedCallback(network: AdNetwork) {
                }

                override fun onDismissCallback(network: AdNetwork) {
                    callback?.invoke()
                }

                override fun onAdClicked() {

                }
            })
    }

    fun showInterstitialReloadCallback(
        activity: Activity,
        configKey: String,
        callback: (() -> Unit)? = null
    ) {
        showInterstitialManager(
            activity = activity,
            configKey = configKey,
            reload = true,
            callback = object : InterstitialShowCallback {
                override fun onAdFailedToShowCallback(error: String) {
                    callback?.invoke()
                }

                override fun onAdShowedCallback(network: AdNetwork) {
                }

                override fun onDismissCallback(network: AdNetwork) {
                    callback?.invoke()
                }

                override fun onAdClicked() {
                }
            })
    }

    fun showInterstitialNotReloadInterface(
        activity: Activity,
        configKey: String,
        callback: InterstitialShowCallback? = null
    ) {
        showInterstitialManager(
            activity = activity,
            configKey = configKey,
            reload = false,
            callback = callback
        )
    }

    fun showInterstitialReloadInterface(
        activity: Activity,
        configKey: String,
        callback: InterstitialShowCallback? = null
    ) {
        showInterstitialManager(
            activity = activity,
            configKey = configKey,
            reload = true,
            callback = callback
        )
    }

    private fun getPlacementItem(configKey: String): PlacementItem? {
        return ConfigManager.getInstance(context).adManagement?.placementList?.get(configKey)
    }

    private fun hashMapCountry(
        configKey: String
    ): HashMap<String, String> {
        val hashMap = HashMap<String, String>()
        hashMap["country"] = configManager.getCountry() ?: ""
        hashMap["config_key"] = configKey
        return hashMap
    }

    fun removeCache() {
        adsInterstitialManager.clear()
    }

    companion object {
        val TAG: String = CemInterstitialManager::class.java.simpleName

        @SuppressLint("StaticFieldLeak")
        private var mInstance: CemInterstitialManager? = null

        fun getInstance(activity: Context): CemInterstitialManager {
            return mInstance ?: synchronized(this) {
                val instance = CemInterstitialManager(activity)
                mInstance = instance
                instance
            }
        }
    }
}