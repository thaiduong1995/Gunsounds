package com.cem.admodule.ext

import android.app.Application
import android.util.Log
import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinPrivacySettings
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkInitializationConfiguration
import com.cem.admodule.BuildConfig
import com.cem.admodule.data.Configuration
import com.cem.admodule.manager.CemAdManager
import com.cem.admodule.manager.ConfigManager
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Collections
import java.util.Locale
import java.util.concurrent.Executors

object AppLovinConfig {
    var TAG: String = "AppLovinSdk"

    @JvmStatic
    fun initialize(
        app: Application,
        configuration: Configuration
    ) {

        //sự đồng ý của eu và gdpr . applovin sdk 12+ ko cần set
//        AppLovinPrivacySettings.setHasUserConsent(true, app)

        //độ tuoi giới hạn
//        AppLovinPrivacySettings.setIsAgeRestrictedUser(true, app)

        //luật về quyền riêng tư của tiểu bang Hoa Kỳ
        AppLovinPrivacySettings.setDoNotSell(true, app)

        if (configuration.sdkKeyAppLovin.isNullOrEmpty()) return
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val initConfigBuilder =
                AppLovinSdkInitializationConfiguration.builder(configuration.sdkKeyAppLovin, app)
            initConfigBuilder.mediationProvider = AppLovinMediationProvider.MAX

            if (BuildConfig.DEBUG) {
                // Enable test mode by default for the current device. Cannot be run on the main thread.
                val currentGaid = AdvertisingIdClient.getAdvertisingIdInfo(app).id
                if (currentGaid != null) {
                    initConfigBuilder.testDeviceAdvertisingIds =
                        Collections.singletonList(currentGaid)
                }
            }
            // Initialize the AppLovin SDK
            val sdk = AppLovinSdk.getInstance(app)
            sdk.initialize(initConfigBuilder.build()) {
                ConfigManager.getInstance(app).setCountry(it.countryCode.lowercase(Locale.US))
            }
            executor.shutdown()
        }
    }
}