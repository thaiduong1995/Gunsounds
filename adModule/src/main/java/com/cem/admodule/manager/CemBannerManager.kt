package com.cem.admodule.manager


import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.cem.admodule.ads.admob.AdmobBannerAdManager
import com.cem.admodule.ads.adx.AdxBannerAdManager
import com.cem.admodule.ads.applovin.ApplovinBannerAdManager
import com.cem.admodule.ads.mintegral.MintegralBannerAdManager
import com.cem.admodule.data.AdUnitItem
import com.cem.admodule.data.BannerLoaded
import com.cem.admodule.enums.AdNetwork
import com.cem.admodule.ext.getAdCollection
import com.cem.admodule.ext.getAdUnit
import com.cem.admodule.ext.getAdUnitsCollection
import com.cem.admodule.ext.gone
import com.cem.admodule.ext.visible
import com.cem.admodule.inter.BannerAdListener
import com.cem.admodule.inter.BannerAdView
import com.google.gson.Gson

class CemBannerManager private constructor() {

    private var adsBannerManager: MutableMap<String, BannerLoaded> = HashMap()
    private fun getOrPutHandler(configKey: String): BannerLoaded {
        val bannerLoaded: BannerLoaded = adsBannerManager.getOrPut(configKey) {
            BannerLoaded(timeLoaded = 0, isLoaded = true, isClosed = true)
        }
        return bannerLoaded
    }

    private fun isLoadedSuccess(configKey: String, timeInterval: Long): Boolean {
        val bannerLoaded = getOrPutHandler(configKey)
        return bannerLoaded.isLoaded && System.currentTimeMillis() - bannerLoaded.timeLoaded >= timeInterval
    }

    fun removeBannerLoaded(configKey: String) {
        adsBannerManager.remove(configKey)
    }

    fun removeBannerForAll(){
        adsBannerManager.clear()
    }

    private fun createBanner(adUnitItem: AdUnitItem?): BannerAdView? {
        if (adUnitItem == null) return null
        return when (AdNetwork.getNetwork(adUnitItem.adNetwork)) {
            AdNetwork.ADMOB -> AdmobBannerAdManager.newInstance(
                AdUnitItem.getAdSize(adUnitItem.adSize), adUnitItem.adUnit
            )

            AdNetwork.APPLOVIN -> ApplovinBannerAdManager.newInstance(
                adUnitItem.adUnit
            )

            AdNetwork.ADX -> AdxBannerAdManager.newInstance(
                AdUnitItem.getAdSize(adUnitItem.adSize), adUnitItem.adUnit
            )

            AdNetwork.MINTEGRAL -> MintegralBannerAdManager.newInstance(
                adSize = AdUnitItem.getAdSize(adUnitItem.adSize),
                adUnit = adUnitItem.adUnit,
                placementId = adUnitItem.placementId
            )

            else -> null
        }
    }

    private fun loadBannerShowByActivity(
        activity: Activity,
        configKey: String,
        units: MutableList<AdUnitItem>,
        viewGroup: ViewGroup,
        collapsible: Boolean,
        nameScreen: String? = null,
        position: String? = null,
        callback: BannerAdListener? = null
    ) {
        val adUnit = getAdUnit(units)
        if (adUnit == null) {
            Log.d(TAG, "loadBannerShowByActivity: adUnit null")
            viewGroup.gone()
            callback?.onBannerFailed("No adUnit")
            return
        }

        val bannerAdView = createBanner(adUnit)
        if (bannerAdView == null) {
            Log.d(TAG, "loadBannerShowByActivity: bannerAdView null")
            viewGroup.gone()
            callback?.onBannerFailed("No bannerAdView")
            return
        }
        bannerAdView.createByActivity(activity = activity,
            position = if (collapsible) position else null,
            listener = object : BannerAdListener {
                override fun onBannerLoaded(banner: BannerAdView, view: View) {
                    Log.d(TAG, "onBannerLoaded $configKey: ${Gson().toJson(adUnit)}")
                    callback?.onBannerLoaded(banner, view)
                    viewGroup.removeAllViews()
                    viewGroup.visible()
                    viewGroup.addView(view)
                }

                override fun onBannerFailed(error: String?) {
                    Log.d(TAG, "onBannerFailed $configKey: $error")
                    units.remove(adUnit)
                    loadBannerShowByActivity(
                        activity = activity,
                        configKey = configKey,
                        units = units,
                        viewGroup = viewGroup,
                        collapsible = collapsible,
                        nameScreen = nameScreen,
                        position = position,
                        callback = callback
                    )
                }

                override fun onBannerClicked() {
                    callback?.onBannerClicked()
                }

                override fun onBannerOpen() {
                    callback?.onBannerOpen()
                }

                override fun onBannerClose() {
                    callback?.onBannerClose()
                }
            })
    }

    private fun loadBannerShowByContext(
        context: Context,
        configKey: String,
        units: MutableList<AdUnitItem>,
        viewGroup: ViewGroup,
        collapsible: Boolean,
        nameScreen: String? = null,
        position: String? = null,
        callback: BannerAdListener? = null
    ) {
        val adUnit = getAdUnit(units)
        if (adUnit == null) {
            Log.d(TAG, "loadBannerShowByActivity: adUnit null")
            viewGroup.gone()
            callback?.onBannerFailed("No adUnit")
            return
        }

        val bannerAdView = createBanner(adUnit)
        if (bannerAdView == null) {
            Log.d(TAG, "loadBannerShowByActivity: bannerAdView null")
            viewGroup.gone()
            callback?.onBannerFailed("No bannerAdView")
            return
        }
        bannerAdView.createByContext(context,
            position = if (collapsible) position else null,
            listener = object : BannerAdListener {
                override fun onBannerLoaded(banner: BannerAdView, view: View) {
                    Log.d(TAG, "onBannerLoaded $configKey:: ${Gson().toJson(adUnit)}")
                    callback?.onBannerLoaded(banner, view)
                    viewGroup.removeAllViews()
                    viewGroup.visible()
                    viewGroup.addView(view)
                }

                override fun onBannerFailed(error: String?) {
                    Log.d(TAG, "onBannerFailed $configKey: $error")
                    units.remove(adUnit)
                    loadBannerShowByContext(
                        context = context,
                        configKey = configKey,
                        units = units,
                        viewGroup = viewGroup,
                        collapsible = collapsible,
                        nameScreen = nameScreen,
                        position = position,
                        callback = callback
                    )
                }

                override fun onBannerClicked() {
                    callback?.onBannerClicked()
                }

                override fun onBannerOpen() {
                    callback?.onBannerOpen()
                }

                override fun onBannerClose() {
                    callback?.onBannerClose()
                }
            })
    }

    fun loadBannerShowNoCollapsible(
        context: Context,
        viewGroup: ViewGroup,
        configKey: String,
        nameScreen: String? = null,
        callback: BannerAdListener? = null
    ) {
        val configManager = ConfigManager.getInstance(context)
        val adManager = configManager.adManagement
        if (adManager == null || !configManager.isEnable()) {
            Log.d(TAG, "loadAndShowBannerByContext: ads null or disable ")
            viewGroup.gone()
            callback?.onBannerFailed("Ads is null or disable")
            return
        }
        if (!isLoadedSuccess(nameScreen ?: configKey, timeInterval = adManager.bannerInterval)) {
            return
        }
        val adCollection = getAdCollection(adManager, configKey)
        if (adCollection == null) {
            Log.d(TAG, "loadAndShowBannerByContext: adCollection null")
            viewGroup.gone()
            callback?.onBannerFailed("adCollection null")
            return
        }
        if (!adCollection.enable) {
            Log.d(TAG, "loadAndShowBannerByContext: adCollection disable")
            viewGroup.gone()
            callback?.onBannerFailed("adCollection disable")
            return
        }
        Log.d(TAG, "loadBannerShowNoCollapsible: ${Gson().toJson(adCollection)}")
        val unitsId = getAdUnitsCollection(adManager, adCollection.adsUnit)
        if (unitsId.isNullOrEmpty()) {
            Log.d(TAG, "loadAndShowBannerByContext: unitsId isNullOrEmpty")
            viewGroup.gone()
            callback?.onBannerFailed("unitsId isNullOrEmpty")
            return
        }
        getOrPutHandler(nameScreen ?: configKey).isLoaded = false
        loadBannerShowByContext(context = context,
            configKey = configKey,
            units = unitsId.toMutableList(),
            viewGroup = viewGroup,
            collapsible = false,
            nameScreen = nameScreen,
            callback = object : BannerAdListener {
                override fun onBannerLoaded(banner: BannerAdView, view: View) {
                    callback?.onBannerLoaded(banner, view)
                    getOrPutHandler(nameScreen ?: configKey).let {
                        it.isLoaded = true
                        it.timeLoaded = System.currentTimeMillis()
                    }
                }

                override fun onBannerFailed(error: String?) {
                    callback?.onBannerFailed(error)
                    getOrPutHandler(nameScreen ?: configKey).let {
                        it.isLoaded = true
                        it.timeLoaded = 0
                    }
                }

                override fun onBannerClicked() {
                    callback?.onBannerClicked()
                }

                override fun onBannerOpen() {
                    getOrPutHandler(nameScreen ?: configKey).isClosed = false
                }

                override fun onBannerClose() {
                    getOrPutHandler(nameScreen ?: configKey).isClosed = true
                }
            })
    }


    fun loadAndShowBannerByContext(
        context: Context,
        viewGroup: ViewGroup,
        configKey: String,
        nameScreen: String? = null,
        position: String? = null,
        callback: BannerAdListener? = null
    ) {
        val configManager = ConfigManager.getInstance(context)
        val adManager = configManager.adManagement
        if (adManager == null || !configManager.isEnable()) {
            Log.d(TAG, "loadAndShowBannerByContext: ads null or disable ")
            viewGroup.gone()
            callback?.onBannerFailed("Ads is null or disable")
            return
        }
        if (!isLoadedSuccess(nameScreen ?: configKey, timeInterval = adManager.bannerInterval)) {
            return
        }
        val adCollection = getAdCollection(adManager, configKey)
        if (adCollection == null) {
            Log.d(TAG, "loadAndShowBannerByContext: adCollection null")
            viewGroup.gone()
            callback?.onBannerFailed("adCollection null")
            return
        }
        if (!adCollection.enable) {
            Log.d(TAG, "loadAndShowBannerByContext: adCollection disable")
            viewGroup.gone()
            callback?.onBannerFailed("adCollection disable")
            return
        }

        val unitsId = getAdUnitsCollection(adManager, adCollection.adsUnit)
        if (unitsId.isNullOrEmpty()) {
            Log.d(TAG, "loadAndShowBannerByContext: unitsId isNullOrEmpty")
            viewGroup.gone()
            callback?.onBannerFailed("unitsId isNullOrEmpty")
            return
        }
        getOrPutHandler(nameScreen ?: configKey).isLoaded = false
        loadBannerShowByContext(context = context,
            configKey = configKey,
            units = unitsId.toMutableList(),
            viewGroup = viewGroup,
            collapsible = adCollection.collapsible,
            nameScreen = nameScreen,
            position = adCollection.position ?: position,
            callback = object : BannerAdListener {
                override fun onBannerLoaded(banner: BannerAdView, view: View) {
                    callback?.onBannerLoaded(banner, view)
                    getOrPutHandler(nameScreen ?: configKey).let {
                        it.timeLoaded = System.currentTimeMillis()
                        it.isLoaded = true
                    }
                }

                override fun onBannerFailed(error: String?) {
                    callback?.onBannerFailed(error)
                    getOrPutHandler(nameScreen ?: configKey).let {
                        it.timeLoaded = 0
                        it.isLoaded = true
                    }
                }

                override fun onBannerClicked() {
                    callback?.onBannerClicked()
                }

                override fun onBannerOpen() {
                    getOrPutHandler(nameScreen ?: configKey).isClosed = false
                }

                override fun onBannerClose() {
                    getOrPutHandler(nameScreen ?: configKey).isClosed = true
                }

            })
    }

    fun loadBannerAndShowByActivity(
        activity: Activity,
        viewGroup: ViewGroup,
        configKey: String,
        nameScreen: String? = null,
        position: String? = null,
        callback: BannerAdListener? = null
    ) {
        loadAndShowBannerByContext(
            context = activity,
            viewGroup = viewGroup,
            configKey = configKey,
            nameScreen = nameScreen,
            position = position,
            callback = callback
        )
    }

    fun getPosition(context: Context, configKey: String): String? {
        return ConfigManager.getInstance(context).adManagement?.placementList?.getOrElse(configKey) { null }
            ?.position
    }

    companion object {
        val TAG: String = CemBannerManager::class.java.simpleName
        private var mInstance: CemBannerManager? = null
        fun getInstance(): CemBannerManager {
            return mInstance ?: synchronized(this) {
                val instance = CemBannerManager()
                mInstance = instance
                instance
            }
        }
    }
}