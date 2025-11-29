package com.cem.admodule.ext

import android.app.Application
import android.content.Context
import com.appsflyer.adrevenue.AppsFlyerAdRevenue
import com.cem.admodule.data.Configuration
import com.cem.firebase_module.AnalyticsConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object CemConfigManager {
    @JvmStatic
    suspend fun initializeAds(
        app: Application,
        configuration: Configuration
    ) {
        AppLovinConfig.initialize(app = app, configuration = configuration)
        withContext(Dispatchers.Main) {
            MintegralConfig.initialize(
                app = app, configuration = configuration
            )
        }
    }

    @JvmStatic
    fun initQonVersion(context: Context, keySdk: String) {
        AnalyticsConfig.initQonVersion(context, keySdk)
    }

    @JvmStatic
    fun initAppFlyer(application: Application, flurryKey: String) {
        AnalyticsConfig.initAppFlyer(application, flurryKey)
        val afRevenueBuilder: AppsFlyerAdRevenue.Builder = AppsFlyerAdRevenue.Builder(application)
        AppsFlyerAdRevenue.initialize(afRevenueBuilder.build())
    }
}