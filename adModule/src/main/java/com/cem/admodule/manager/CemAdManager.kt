package com.cem.admodule.manager

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import com.cem.admodule.BuildConfig
import com.cem.admodule.data.AdUnitItem
import com.cem.admodule.data.AdsType
import com.cem.admodule.inter.BannerAdListener
import com.cem.admodule.inter.Callback
import com.cem.admodule.inter.CemNativeAdView
import com.cem.admodule.inter.CemRewardListener
import com.cem.admodule.inter.InterstitialLoadCallback
import com.cem.admodule.inter.InterstitialShowCallback
import com.cem.admodule.inter.OpenLoadCallback
import com.cem.admodule.inter.RewardLoadCallback

class CemAdManager private constructor(activity: Context) {

    private val cemInterstitialManager by lazy {
        CemInterstitialManager.getInstance(activity)
    }

    private val cemBannerManager by lazy {
        CemBannerManager.getInstance()
    }

    private val cemBannerReloadManager by lazy {
        CemBannerReloadManager.getInstance()
    }

    private val cemNativeManager by lazy {
        CemNativeManager.getInstance(activity)
    }

    private val cemRewardAdManager by lazy {
        CemRewardAdManager.getInstance(activity)
    }

    private val configManager by lazy {
        ConfigManager.getInstance(activity)
    }

    val getAdManager
        get() = configManager.adManagement

    val isShowingInterstitialAd: Boolean
        get() = cemInterstitialManager.isShowingAd || cemRewardAdManager.isShowingAd

    val isEnableRewardAds
        get() = configManager.isEnableRewardAds

    //fetch config remote
    suspend fun fetchConfig(configKey: String, fileLocal: String) =
        configManager.fetchConfig(configKey, fileLocal)

    fun initMMKV() = apply {
        configManager.initMMKV()
    }

    fun setIsVip(value: Boolean) = apply {
        configManager.setIsVip(value)
    }

    fun isVip() = configManager.isVip()


    fun removeCacheAdmobManager() {
        removeCacheInterstitial()
        removeNativeForAll()
        removeBannerForAll()
    }

    fun preloadManagerAdUnits(activity: Activity) {
        if (configManager.adManagement?.adUnitList.isNullOrEmpty()) return
        for (units in configManager.adManagement?.adUnitList!!) {
            preLoadAdsUnit(activity = activity, units = units)
        }
    }

    private fun preLoadAdsUnit(activity: Activity, units: Map.Entry<String, List<AdUnitItem>?>) {
        val unit = units.value?.firstOrNull() ?: return
        if (!unit.allowPreLoad) return
        Log.d(TAG, "preLoadAdsUnit: $unit")
        when (unit.adsType) {
            AdsType.INTERSTITIAL.nameType -> {
                loadInterstitialByUnits(activity = activity, configKey = units.key)
            }

            AdsType.OPEN.nameType -> {
                fetchOpenAdsByUnits(adConfigKey = units.key)
            }

            AdsType.NATIVE.nameType -> {
                loadNativeByUnitsCallback(activity = activity, configKey = units.key)
            }

            AdsType.REWARDED.nameType -> {
                loadRewardByUnitsCallback(activity = activity, configKey = units.key)
            }
        }
    }

    //remove inter full
    private fun removeCacheInterstitial() = apply {
        cemInterstitialManager.removeCache()
    }

    //load inter full
    fun loadInterstitialByPlacements(
        activity: Activity, configKey: String, callback: InterstitialLoadCallback? = null
    ) = apply {
        cemInterstitialManager.loadInterstitialByPlacemnt(activity, configKey, callback)
    }

    fun loadInterstitialByUnits(
        activity: Activity, configKey: String, callback: InterstitialLoadCallback? = null
    ) = apply {
        cemInterstitialManager.loadInterstitialByUnits(activity, configKey, callback)
    }


    //show inter full
    fun showInterstitialNotReloadInterface(
        activity: Activity, configKey: String, callback: InterstitialShowCallback? = null
    ) = apply {
        cemInterstitialManager.showInterstitialNotReloadInterface(
            activity = activity, configKey = configKey, callback = callback
        )
    }

    fun showInterstitialReloadInterface(
        activity: Activity, configKey: String, callback: InterstitialShowCallback? = null
    ) = apply {
        cemInterstitialManager.showInterstitialReloadInterface(
            activity = activity, configKey = configKey, callback = callback
        )
    }

    fun showInterstitialNotReloadCallback(
        activity: Activity, configKey: String, callback: (() -> Unit)? = null
    ) = apply {
        cemInterstitialManager.showInterstitialNotReloadCallback(
            activity = activity, configKey = configKey, callback = callback
        )
    }

    fun showInterstitialReloadCallback(
        activity: Activity, configKey: String, callback: (() -> Unit)? = null
    ) = apply {
        cemInterstitialManager.showInterstitialReloadCallback(
            activity = activity, configKey = configKey, callback = callback
        )
    }

    // Native

    fun removeNativeLoaded(nameScreen: String): CemAdManager = apply {
        cemNativeManager.removeNativeLoaded(nameScreen)
    }

    private fun removeNativeForAll(): CemAdManager = apply {
        cemNativeManager.removeNativeForAll()
    }

    //load and show native
    fun loadAndShowNativeByPlacement(
        context: Context, nativeAdView: CustomNativeView, configKey: String, nameScreen: String
    ) = apply {
        cemNativeManager.loadAndShowNativeByPlacement(
            context = context,
            nativeAdView = nativeAdView,
            configKey = configKey,
            nameScreen = nameScreen
        )
    }

    //load native by cache
    fun loadNativeByPlacementCallback(
        context: Context, configKey: String, callback: Callback<CemNativeAdView>? = null
    ) = apply {
        cemNativeManager.loadNativeManagerByPlacement(context, configKey, callback)
    }

    //load with callback
    fun loadNativeByPlacementCallback(
        activity: Context, configKey: String, callback: ((Boolean) -> Unit)? = null
    ) = apply {
        cemNativeManager.loadNativeByPlacementCallback(activity, configKey, callback)
    }

    fun loadNativeByUnitsInterface(
        activity: Context, configKey: String, callback: Callback<CemNativeAdView>? = null
    ) = apply {
        cemNativeManager.loadNativeManagerByUnits(activity, configKey, callback)
    }

    fun loadNativeByUnitsCallback(
        activity: Context, configKey: String, callback: ((Boolean) -> Unit)? = null
    ) = apply {
        cemNativeManager.loadNativeManagerByUnits(activity, configKey, callback)
    }

    //get native by cache
    fun getNativeByPlacement(
        activity: Context, configKey: String, reload: Boolean
    ) = apply {
        cemNativeManager.getNativeByPlacement(activity, configKey, reload)
    }

    //get native by cache config key
    fun getNativeByListPlacement(
        activity: Context, configKey: String, reload: Boolean
    ) = apply {
        cemNativeManager.getNativeByListPlacement(activity, configKey, reload)
    }

    //show native by cache
    fun showNativeByPlacement(
        configKey: String, view: CustomNativeView
    ) = apply {
        cemNativeManager.showNativeByPlacement(configKey, view)
    }

    //show native by cache config key
    fun showNativeByListPlacement(
        configKey: String, view: CustomNativeView
    ) = apply {
        cemNativeManager.showNativeByListPlacement(configKey, view)
    }

    //check native đã có chưa
    fun isNativeLoadedByPlacement(
        configKey: String
    ): Boolean {
        return cemNativeManager.isNativeLoadedByPlacement(configKey)
    }

    //reward
    //load reward with callback
    fun loadRewardByPlacementCallback(
        activity: Activity, configKey: String, callbackLoaded: (() -> Unit)? = null
    ) = apply {
        cemRewardAdManager.loadRewardByPlacementCallback(activity, configKey, callbackLoaded)
    }

    fun loadRewardByPlacementInterface(
        activity: Activity, configKey: String, callback: RewardLoadCallback? = null
    ) = apply {
        cemRewardAdManager.loadRewardByPlacementInterface(activity, configKey, callback)
    }

    fun loadRewardByUnitsCallback(
        activity: Activity, configKey: String, callbackLoaded: (() -> Unit)? = null
    ) = apply {
        cemRewardAdManager.loadRewardByUnitsCallback(activity, configKey, callbackLoaded)
    }

    //load reward with interface
    fun loadRewardByUnitsInterface(
        activity: Activity, configKey: String, callback: RewardLoadCallback? = null
    ) = apply {
        cemRewardAdManager.loadRewardByUnitsInterface(activity, configKey, callback)
    }

    //show reward not reload ads
    fun showRewardByPlacementReload(
        activity: Activity, configKey: String, listener: CemRewardListener? = null
    ) = apply {
        cemRewardAdManager.showByPlacementReload(activity, configKey, listener)
    }

    //show reward reload ads
    fun showRewardByPlacementNotReload(
        activity: Activity, configKey: String, listener: CemRewardListener? = null
    ) = apply {
        cemRewardAdManager.showByPlacementNotReload(activity, configKey, listener)
    }

    fun isRewardLoaded(
        configKey: String
    ): Boolean = cemRewardAdManager.isRewardLoaded(configKey)

    //Banner
    //load banner by context
    fun loadBannerAndShowByContext(
        context: Context,
        viewGroup: ViewGroup,
        configKey: String,
        position: String? = null,
        callback: BannerAdListener? = null,
        nameScreen: String? = null
    ): CemAdManager = apply {
        cemBannerManager.loadAndShowBannerByContext(
            context = context,
            viewGroup = viewGroup,
            configKey = configKey,
            nameScreen = nameScreen,
            position = position,
            callback = callback
        )
    }

    //load banner by activity
    fun loadBannerAndShowByActivity(
        activity: Activity,
        viewGroup: ViewGroup,
        configKey: String,
        position: String? = null,
        callback: BannerAdListener? = null,
        nameScreen: String? = null,
    ): CemAdManager = apply {
        cemBannerManager.loadBannerAndShowByActivity(
            activity = activity,
            viewGroup = viewGroup,
            configKey = configKey,
            nameScreen = nameScreen,
            position = position,
            callback = callback
        )
    }

    fun loadBannerShowNoCollapsible(
        context: Context,
        viewGroup: ViewGroup,
        configKey: String,
        callback: BannerAdListener? = null,
        nameScreen: String? = null
    ): CemAdManager = apply {
        cemBannerManager.loadBannerShowNoCollapsible(
            context = context,
            viewGroup = viewGroup,
            configKey = configKey,
            nameScreen = nameScreen,
            callback = callback
        )
    }

    //load banner by context reload
    fun loadBannerAndShowByContextReload(
        context: Context,
        viewGroup: ViewGroup,
        configKey: String,
        position: String? = null,
        callback: BannerAdListener? = null,
        refreshTimeBanner: Int = 10
    ): CemAdManager = apply {
        cemBannerReloadManager.loadAndShowBannerByContextReload(
            context, viewGroup, configKey, position, callback, refreshTimeBanner
        )
    }

    //load banner by activity
    fun loadBannerAndShowByActivityReload(
        activity: Activity,
        viewGroup: ViewGroup,
        configKey: String,
        position: String? = null,
        callback: BannerAdListener? = null,
        refreshTimeBanner: Int = 10
    ): CemAdManager = apply {
        cemBannerReloadManager.loadBannerAndShowByActivityReload(
            activity, viewGroup, configKey, position, callback, refreshTimeBanner
        )
    }

    fun loadBannerShowNoCollapsibleReload(
        context: Context,
        viewGroup: ViewGroup,
        configKey: String,
        callback: BannerAdListener? = null,
        refreshTimeBanner: Int = 10
    ): CemAdManager = apply {
        cemBannerReloadManager.loadBannerShowNoCollapsibleReload(
            context, viewGroup, configKey, callback, refreshTimeBanner
        )
    }

    fun removeCallbackAndMessages(configKey: String, messages: String? = null): CemAdManager =
        apply {
            cemBannerReloadManager.removeRunnableAndCallback(configKey = configKey, messages)
        }

    fun removeBannerLoaded(nameScreen: String): CemAdManager =
        apply {
            cemBannerManager.removeBannerLoaded(nameScreen)
        }

    private fun removeBannerForAll(): CemAdManager =
        apply {
            cemBannerManager.removeBannerForAll()
        }

    //get position activity
    fun getPosition(
        context: Context, configKey: String
    ): String? = cemBannerManager.getPosition(context, configKey)


    //OPEN
    fun fetchOpenAdsByUnits(adConfigKey: String, callback: OpenLoadCallback? = null) = apply {
        CemAppOpenManager.instance.fetchOpenAdsByUnits(adConfigKey, callback)
    }
    fun fetchOpenAdsByPlacements(adConfigKey: String, callback: OpenLoadCallback? = null) = apply {
        CemAppOpenManager.instance.fetchOpenAdsByPlacements(adConfigKey, callback)
    }

    fun fetchOpenAdsAndShow(adConfigKey: String, callback: OpenLoadCallback? = null) = apply {
        CemAppOpenManager.instance.fetchOpenAdsAndShow(adConfigKey, callback)
    }

    fun enableOpenAds() {
        CemAppOpenManager.instance.enableOpenAds()
    }

    fun blockOpenAds() {
        CemAppOpenManager.instance.blockOpenAds()
    }

    fun registerProcessLifecycle() = apply {
        CemAppOpenManager.instance.registerProcessLifecycle()
    }

    fun unregisterProcessLifecycle() = apply {
        CemAppOpenManager.instance.unregisterProcessLifecycle()
    }

    fun registerCallback(fullScreenContentCallback: InterstitialShowCallback?) = apply {
        CemAppOpenManager.instance.registerCallback(fullScreenContentCallback)
    }

    fun unregisterCallback() = apply {
        CemAppOpenManager.instance.unregisterCallback()
    }

    fun setIgnoreActivities(data: List<String>) = apply {
        CemAppOpenManager.instance.setIgnoreActivities(data)
    }

    fun showOpenAdsByPlacements(
        configKey: String,
        isShowDirect: Boolean = false,
        callback: (() -> Unit)? = null
    ) = apply {
        CemAppOpenManager.instance.showAdByKeyIfAvailable(configKey, isShowDirect, callback)
    }

    companion object {
        val TAG: String = CemAdManager::class.java.simpleName
        private var mInstance: CemAdManager? = null

        fun getInstance(activity: Context): CemAdManager {
            return mInstance ?: synchronized(this) {
                val instance = CemAdManager(activity)
                mInstance = instance
                instance
            }
        }
    }
}