package com.cem.admodule.manager

import android.util.Log
import com.appsflyer.adrevenue.AppsFlyerAdRevenue
import com.appsflyer.adrevenue.adnetworks.generic.MediationNetwork
import com.appsflyer.adrevenue.adnetworks.generic.Scheme
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.gson.Gson
import java.util.Currency

/**
 * Created by Hưng Nguyễn on 10/04/2024
 * Phone: 0335236374
 * Email: nguyenhunghung2806@gmail.com
 */
object AppFlyersLogManager {
    var TAG : String  = "AppsflyerRevenue"
    fun logEventInterstitialAdRevenue(
        interstitialAd: InterstitialAd,
        adValue: AdValue
    ) {
        val customParams: MutableMap<String, String> = HashMap()
        val adUnitId = interstitialAd.adUnitId
        val mediation = interstitialAd.responseInfo.mediationAdapterClassName ?: "admob"
        val revenue = (adValue.valueMicros.toDouble() / 1_000_000)
        val currency = Currency.getInstance(adValue.currencyCode)
        customParams[Scheme.AD_UNIT] = adUnitId
        customParams[Scheme.AD_TYPE] = "InterstitialAd"
        responseMessageLog("InterstitialAd", adUnitId, revenue, currency.currencyCode)

        AppsFlyerAdRevenue.logAdRevenue(
            mediation,
            MediationNetwork.googleadmob,
            currency,
            revenue,
            customParams
        )
    }

    fun logEventNativeAdRevenue(
        nativeAd: NativeAd,
        adUnitId: String,
        adValue: AdValue
    ) {
        val customParams: MutableMap<String, String> = HashMap()
        val mediation = nativeAd.responseInfo?.mediationAdapterClassName ?: "admob"
        val revenue = (adValue.valueMicros.toDouble() / 1_000_000)
        val currency = Currency.getInstance(adValue.currencyCode)
        customParams[Scheme.AD_UNIT] = adUnitId
        customParams[Scheme.AD_TYPE] = "NativeAd"
        responseMessageLog("NativeAd", adUnitId, revenue, currency.currencyCode)
        AppsFlyerAdRevenue.logAdRevenue(
            mediation,
            MediationNetwork.googleadmob,
            currency,
            revenue,
            customParams
        )
    }

    fun logEventBannerAdRevenue(
        bannerAd: AdView,
        adValue: AdValue
    ) {
        val customParams: MutableMap<String, String> = HashMap()
        val adUnitId = bannerAd.adUnitId
        val mediation = bannerAd.responseInfo?.mediationAdapterClassName ?: "admob"
        val revenue = (adValue.valueMicros.toDouble() / 1_000_000)
        val currency = Currency.getInstance(adValue.currencyCode)
        customParams[Scheme.AD_UNIT] = adUnitId
        customParams[Scheme.AD_TYPE] = "BannerAd"

        responseMessageLog("BannerAd", adUnitId, revenue, currency.currencyCode)

        AppsFlyerAdRevenue.logAdRevenue(
            mediation,
            MediationNetwork.googleadmob,
            currency,
            revenue,
            customParams
        )
    }

    fun logEventRewardAdRevenue(
        rewardAd: RewardedAd,
        adValue: AdValue
    ) {
        val customParams: MutableMap<String, String> = HashMap()
        val adUnitId = rewardAd.adUnitId
        val mediation = rewardAd.responseInfo.mediationAdapterClassName ?: "admob"
        val revenue = (adValue.valueMicros.toDouble() / 1_000_000)
        val currency = Currency.getInstance(adValue.currencyCode)
        customParams[Scheme.AD_UNIT] = adUnitId
        customParams[Scheme.AD_TYPE] = "RewardAd"

        responseMessageLog("RewardAd", adUnitId, revenue, currency.currencyCode)

        AppsFlyerAdRevenue.logAdRevenue(
            mediation,
            MediationNetwork.googleadmob,
            currency,
            revenue,
            customParams
        )
    }

    fun logEventOpenAdsRevenue(
        openAds: AppOpenAd,
        adValue: AdValue
    ) {
        val customParams: MutableMap<String, String> = HashMap()
        val adUnitId = openAds.adUnitId
        val mediation = openAds.responseInfo.mediationAdapterClassName ?: "admob"
        val revenue = (adValue.valueMicros.toDouble() / 1_000_000)
        val currency = Currency.getInstance(adValue.currencyCode)
        customParams[Scheme.AD_UNIT] = adUnitId
        customParams[Scheme.AD_TYPE] = "OpenAd"

        responseMessageLog("OpenAd", adUnitId, revenue, currency.currencyCode)

        AppsFlyerAdRevenue.logAdRevenue(
            mediation,
            MediationNetwork.googleadmob,
            currency,
            revenue,
            customParams
        )
    }
    private fun responseMessageLog(
        format: String,
        unitId: String,
        revenue: Double,
        currencyCode: String
    ) {
        val message = StringBuilder()
            .append("format $format")
            .append(" - ")
            .append("unitId $unitId")
            .append(" - ")
            .append("revenue $revenue")
            .append(" - ")
            .append("currencyCode $currencyCode")
        Log.d("AppsflyerRevenue", message.toString())
    }

}