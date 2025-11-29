package com.cem.admodule.manager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import com.cem.admodule.ads.admob.AdmobNativeAdManager
import com.cem.admodule.ads.adx.AdxNativeAdManager
import com.cem.admodule.ads.applovin.ApplovinNativeAdManager
import com.cem.admodule.data.AdUnitItem
import com.cem.admodule.data.BannerLoaded
import com.cem.admodule.data.PlacementItem
import com.cem.admodule.enums.AdNetwork
import com.cem.admodule.ext.getAdCollection
import com.cem.admodule.ext.getAdUnit
import com.cem.admodule.ext.getAdUnitsCollection
import com.cem.admodule.ext.gone
import com.cem.admodule.ext.visible
import com.cem.admodule.inter.Callback
import com.cem.admodule.inter.CemNativeAdView
import com.cem.admodule.inter.NativeAdCallback
import com.google.gson.Gson

class CemNativeManager private constructor(
    private val context: Context
) {
    private val configManager: ConfigManager by lazy {
        ConfigManager.getInstance(context)
    }

    private val adsDataNative: MutableMap<String, CemNativeAdView> = HashMap()

    private val adsListNative: MutableMap<String, List<CemNativeAdView>> = HashMap()

    private var adsNativeManager: MutableMap<String, BannerLoaded> = HashMap()

    private fun getOrPutHandler(configKey: String): BannerLoaded {
        val bannerLoaded: BannerLoaded = adsNativeManager.getOrPut(configKey) {
            BannerLoaded(timeLoaded = 0, isLoaded = true, isClosed = true)
        }
        return bannerLoaded
    }

    private fun isLoadedSuccess(configKey: String, timeInterval: Long): Boolean {
        val nativeLoaded = getOrPutHandler(configKey)
        return nativeLoaded.isLoaded && System.currentTimeMillis() - nativeLoaded.timeLoaded >= timeInterval
    }

    private fun createNative(adUnitItem: AdUnitItem?): CemNativeAdView? {
        if (adUnitItem == null) return null
        val adNetwork = adUnitItem.adNetwork
        return when (AdNetwork.getNetwork(adNetwork)) {
            AdNetwork.ADMOB -> AdmobNativeAdManager.newInstance(adUnitItem.adUnit)
            AdNetwork.APPLOVIN -> ApplovinNativeAdManager.newInstance(adUnitItem.adUnit)
            AdNetwork.ADX -> AdxNativeAdManager.newInstance(adUnitItem.adUnit)
            else -> null
        }
    }

    private fun loadAndShowNative(
        context: Context,
        configKey: String,
        units: MutableList<AdUnitItem>,
        nameScreen: String,
        nativeAdView: CustomNativeView
    ) {
        val adUnit = getAdUnit(units)
        if (adUnit == null) {
            getOrPutHandler(nameScreen).let {
                it.isLoaded = true
                it.timeLoaded = 0
            }
            showNativeByPlacement(configKey = configKey, view = nativeAdView)
            return
        }

        val adManager = createNative(adUnit)
        if (adManager == null) {
            Log.d(TAG, "loadAndShow: ad manager null")
            units.remove(adUnit)
            loadAndShowNative(
                context = context,
                configKey = configKey,
                units = units,
                nameScreen = nameScreen,
                nativeAdView = nativeAdView
            )
            return
        }

        adManager.load(context, object : NativeAdCallback {
            override fun onNativeLoaded(view: CemNativeAdView) {
                Log.d(TAG, "onNativeLoaded $configKey: ${Gson().toJson(adUnit)}")
                adsDataNative[configKey] = view
                addNativeWithList(configKey, view)
                view.show(nativeAdView)
                getOrPutHandler(nameScreen).let {
                    it.isLoaded = true
                    it.timeLoaded = System.currentTimeMillis()
                }
            }

            override fun onNativeFailed(errorCode: String?) {
                Log.d(TAG, "onNativeFailed $configKey $errorCode")
                units.remove(adUnit)
                loadAndShowNative(
                    context = context,
                    configKey = configKey,
                    units = units,
                    nameScreen = nameScreen,
                    nativeAdView = nativeAdView
                )
            }
        })
    }


    //load native mà hiển thị ngay
    fun loadAndShowNativeByPlacement(
        context: Context,
        nativeAdView: CustomNativeView,
        configKey: String,
        nameScreen: String
    ) {
        val adManagement = configManager.adManagement
        if (adManagement == null || !configManager.isEnable()) {
            nativeAdView.gone()
            return
        }
        val adCollection = getAdCollection(adManagement, configKey)
        if (adCollection == null) {
            nativeAdView.gone()
            return
        }
        if (!adCollection.enable) {
            nativeAdView.gone()
            return
        }
        if (!isLoadedSuccess(nameScreen, timeInterval = adManagement.nativeInterval)) {
            showNativeByPlacement(configKey, view = nativeAdView)
            return
        }
        val unitsId = getAdUnitsCollection(adManagement, adCollection.adsUnit)
        if (unitsId.isNullOrEmpty()) {
            nativeAdView.gone()
            return
        }
        val units: MutableList<AdUnitItem> = ArrayList(unitsId)
        nativeAdView.visibility = View.VISIBLE
        loadAndShowNative(
            context = context,
            configKey = adCollection.adsUnit ?: configKey,
            units = units,
            nameScreen = nameScreen,
            nativeAdView = nativeAdView
        )
    }

    private fun loadNativeInternal(
        context: Context,
        configKey: String,
        units: MutableList<AdUnitItem>,
        callback: Callback<CemNativeAdView>?
    ) {
        val adUnit = getAdUnit(units)
        if (adUnit == null) {
            Log.d(TAG, "loadNativeInternal: ad unit null")
            callback?.onFailure(NullPointerException("adUnit null"))
            return
        }

        val adManager = createNative(adUnit)
        if (adManager == null) {
            Log.d(TAG, "loadNativeInternal: adManager null")
            callback?.onFailure(NullPointerException("adManager null"))
            return
        }

        adManager.load(context, object : NativeAdCallback {
            override fun onNativeLoaded(view: CemNativeAdView) {
                Log.d(TAG, "onNativeLoaded $configKey: ${Gson().toJson(adUnit)}")
                callback?.onSuccess(view)
            }

            override fun onNativeFailed(errorCode: String?) {
                units.remove(adUnit)
                Log.d(TAG, "onNativeFailed $configKey $errorCode")
                loadNativeInternal(context, configKey, units, callback)
            }
        })
    }

    //load native và lưu cache
    fun loadNativeManagerByPlacement(
        context: Context, configKey: String, callback: Callback<CemNativeAdView>? = null
    ) {
        val adManagement = configManager.adManagement
        if (adManagement == null || !configManager.isEnable()) {
            callback?.onFailure(NullPointerException("Load native on ad disabled"))
            return
        }
        val adCollection = getAdCollection(adManagement, configKey)
        if (adCollection == null) {
            callback?.onFailure(NullPointerException("Load native on no ad config"))
            return
        }
        if (!adCollection.enable) {
            callback?.onFailure(NullPointerException("Load native on no ad config"))
            return
        }
        val unitsId = getAdUnitsCollection(adManagement, adCollection.adsUnit)
        if (unitsId.isNullOrEmpty()) {
            callback?.onFailure(NullPointerException("Load native on no ad config"))
            return
        }
        val units: MutableList<AdUnitItem> = ArrayList(unitsId)
        loadNativeInternal(context, configKey, units, object : Callback<CemNativeAdView> {
            override fun onSuccess(data: CemNativeAdView) {
                adsDataNative[adCollection.adsUnit ?: configKey] = data
                addNativeWithList(adCollection.adsUnit ?: configKey, data)
                callback?.onSuccess(data)
            }

            override fun onFailure(e: Exception?) {
                callback?.onFailure(e)
            }
        })
    }

    //load native và lưu cache
    fun loadNativeManagerByUnits(
        context: Context, configKey: String, callback: Callback<CemNativeAdView>? = null
    ) {
        val adManagement = configManager.adManagement
        if (adManagement == null || !configManager.isEnable()) {
            callback?.onFailure(NullPointerException("Load native on ad disabled"))
            return
        }
        val unitsId = getAdUnitsCollection(adManagement, configKey)
        if (unitsId.isNullOrEmpty()) {
            callback?.onFailure(NullPointerException("Load native on no ad config"))
            return
        }
        val units: MutableList<AdUnitItem> = ArrayList(unitsId)
        loadNativeInternal(context, configKey, units, object : Callback<CemNativeAdView> {
            override fun onSuccess(data: CemNativeAdView) {
                adsDataNative[configKey] = data
                addNativeWithList(configKey, data)
                callback?.onSuccess(data)
            }

            override fun onFailure(e: Exception?) {
                callback?.onFailure(e)
            }
        })
    }

    //show native với key config
    fun showNativeByPlacement(configKey: String, view: CustomNativeView) {
        if (!configManager.isEnable()) {
            Log.d(TAG, "showNative: ${configManager.isEnable()}")
            view.gone()
            return
        }
        val placementList = getPlacement(configKey)
        val nativeManager = adsDataNative[placementList?.adsUnit ?: configKey]
        if (nativeManager != null) {
            Log.d(TAG, "showNative: ads exits")
            view.visibility = View.VISIBLE
            nativeManager.show(view)
        } else {
            Log.d(TAG, "showNative: ads null")
            view.gone()
        }
    }

    //show native với key config dạng list
    fun showNativeByListPlacement(configKey: String, view: CustomNativeView) {
        if (!configManager.isEnable()) {
            Log.d(TAG, "showNative: ${configManager.isEnable()}")
            view.gone()
            return
        }
        val adUnits = getPlacement(configKey)
        val nativeManager = adsListNative[adUnits?.adsUnit ?: configKey]
        if (nativeManager != null) {
            Log.d(TAG, "showNative: ads exits")
            nativeManager.shuffled().lastOrNull()?.let {
                view.visible()
                it.show(view)
            }
        } else {
            Log.d(TAG, "showNative: ads null")
            view.gone()
        }
    }

    //load native  callback
    fun loadNativeByPlacementCallback(
        activity: Context,
        configKey: String,
        callback: ((Boolean) -> Unit)? = null
    ) {
        loadNativeManagerByPlacement(activity, configKey, object : Callback<CemNativeAdView> {
            override fun onSuccess(data: CemNativeAdView) {
                callback?.invoke(true)
            }

            override fun onFailure(e: Exception?) {
                callback?.invoke(false)
            }
        })
    }

    fun loadNativeManagerByUnits(
        activity: Context,
        configKey: String,
        callback: ((Boolean) -> Unit)? = null
    ) {
        loadNativeManagerByUnits(activity, configKey, object : Callback<CemNativeAdView> {
            override fun onSuccess(data: CemNativeAdView) {
                callback?.invoke(true)
            }

            override fun onFailure(e: Exception?) {
                callback?.invoke(false)
            }
        })
    }

    //check xem native đã có chưa
    fun isNativeLoadedByPlacement(configKey: String): Boolean {
        return adsDataNative.containsKey(configKey) || adsListNative.containsKey(configKey)
    }

    //get native cache
    fun getNativeByPlacement(
        activity: Context, configKey: String, reload: Boolean
    ): CemNativeAdView? {
        var nativeAds: CemNativeAdView? = null
        val placementList = getPlacement(configKey)
        if (adsDataNative.containsKey(placementList?.adsUnit ?: configKey)) nativeAds =
            adsDataNative[placementList?.adsUnit ?: configKey]
        if (reload) loadNativeByPlacementCallback(activity, configKey, callback = {

        })
        return nativeAds
    }

    //get native cache list
    fun getNativeByListPlacement(
        activity: Context, configKey: String, reload: Boolean
    ): CemNativeAdView? {
        var nativeAds: CemNativeAdView? = null
        val placementList = getPlacement(configKey)
        if (adsListNative.containsKey(placementList?.adsUnit ?: configKey)) nativeAds =
            adsListNative[placementList?.adsUnit ?: configKey]?.lastOrNull()
        if (reload) loadNativeByPlacementCallback(activity, configKey, callback = {

        })
        return nativeAds
    }

    private fun addNativeWithList(
        configKey: String, view: CemNativeAdView
    ) {
        val newList = mutableListOf<CemNativeAdView>()
        val listData = adsListNative[configKey]
        if (listData != null) {
            newList.addAll(listData)
        }
        newList.add(view)
        newList.let {
            adsListNative[configKey] = newList
        }
    }

    private fun getPlacement(configKey: String): PlacementItem? {
        return ConfigManager.getInstance(context = context).adManagement?.placementList?.get(
            configKey
        )
    }

    fun removeNativeLoaded(configKey: String) {
        adsDataNative.remove(configKey)
    }

    fun removeNativeForAll() {
        adsNativeManager.clear()
        adsListNative.clear()
        adsDataNative.clear()
    }

    companion object {
        val TAG: String = CemNativeManager::class.java.simpleName

        @SuppressLint("StaticFieldLeak")
        private var mInstance: CemNativeManager? = null

        fun getInstance(context: Context): CemNativeManager {
            return mInstance ?: synchronized(this) {
                val instance = CemNativeManager(context)
                mInstance = instance
                instance
            }
        }
    }
}