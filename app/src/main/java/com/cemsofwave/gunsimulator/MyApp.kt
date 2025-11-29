package com.cemsofwave.gunsimulator

import android.app.Application
import com.cem.admodule.data.Configuration
import com.cem.admodule.ext.CemConfigManager
import com.cem.admodule.manager.CemAppOpenManager
import com.cem.admodule.manager.ConfigManager
import com.google.firebase.FirebaseApp
import com.trinhbx.base.utils.DebugTree
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by Trinh BX on 20/07/2023.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 */

@HiltAndroidApp
class MyApp :Application(){
    override fun onCreate() {
        super.onCreate()
        configTimber()
        CemConfigManager.initAppFlyer(
            application = this@MyApp,
            flurryKey = "k7fyBqQm2ys9iZowhKjhBn"
        )
        FirebaseApp.initializeApp(this)
        CoroutineScope(Dispatchers.IO).launch{
            CemConfigManager.initializeAds(
                app = this@MyApp,
                configuration = Configuration(testDeviceIds = listOf("8CC819096052C744497915C174693FBB")))
        }
        CemAppOpenManager.getInstance(this).registerProcessLifecycle()
    }

    private fun configTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}