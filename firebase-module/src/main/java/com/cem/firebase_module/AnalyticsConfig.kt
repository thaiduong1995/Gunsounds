package com.cem.firebase_module

import android.app.Application
import android.content.Context
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.cem.firebase_module.qonversion.QOnVersionConfig
import com.flurry.android.FlurryAgent
import com.flurry.android.FlurryPerformance

object AnalyticsConfig {
    const val TAG = "AnalyticsConfig"
    @JvmStatic
    fun initializeFlurry(application: Application, flurryKey : String){
        FlurryAgent.Builder()
            .withDataSaleOptOut(false)
            .withCaptureUncaughtExceptions(true)
            .withIncludeBackgroundSessionsInMetrics(true)
            .withLogLevel(Log.VERBOSE)
            .withPerformanceMetrics(FlurryPerformance.ALL)
            .build(application,flurryKey)
    }

    @JvmStatic
    fun initQonVersion(context : Context, keySdk : String) {
        QOnVersionConfig.initQonVersion(context,keySdk)
    }


    @JvmStatic
    fun initAppFlyer(app: Application, yourSdkKey: String) {
        AppsFlyerLib.getInstance().init(yourSdkKey, object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
                Log.d(TAG, "onConversionDataSuccess: ${p0?.values}")
            }

            override fun onConversionDataFail(p0: String?) {
                Log.d(TAG, "onConversionDataFail: $p0")
            }

            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
                Log.d(TAG, "onAppOpenAttribution: ${p0?.values}")
            }

            override fun onAttributionFailure(p0: String?) {
                Log.d(TAG, "onAttributionFailure: $p0")
            }
        }, app)
        AppsFlyerLib.getInstance().start(app, yourSdkKey, object : AppsFlyerRequestListener {
            override fun onSuccess() {
                Log.d(TAG, "Launch sent successfully")
            }

            override fun onError(errorCode: Int, errorDesc: String) {
                Log.d(
                    TAG, "Launch failed to be sent:\n" +
                            "Error code: " + errorCode + "\n"
                            + "Error description: " + errorDesc
                )
            }
        })
        if (BuildConfig.DEBUG) {
            AppsFlyerLib.getInstance().setDebugLog(true)
        }
    }

}