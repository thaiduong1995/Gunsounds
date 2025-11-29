package com.cemsofwave.gunsimulator.ui.activity

import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.cem.admodule.ext.ConstAd
import com.cem.admodule.ext.ConstAd.BANNER_KEY_HOME
import com.cem.admodule.ext.ConstAd.NATIVE_ADS
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.data.model.TimeManager
import com.cemsofwave.gunsimulator.databinding.ActivityMainBinding
import com.cemsofwave.gunsimulator.ui.fragment.SettingFragment
import com.cemsofwave.gunsimulator.utils.MAIN_CLICK_GUN
import com.cemsofwave.gunsimulator.utils.MAIN_CLICK_SABER
import com.cemsofwave.gunsimulator.utils.MAIN_CLICK_TASER
import com.cemsofwave.gunsimulator.utils.MAIN_CLICK_SETTING
import com.cemsofwave.gunsimulator.utils.MAIN_SHOW
import com.cemsofwave.gunsimulator.viewmodel.HomeViewModel
import com.trinhbx.base.extension.setOnSingleClickListener
import com.cemsofwave.gunsimulator.base.BaseActivity
import com.cemsofwave.gunsimulator.utils.MAIN_CLICK_EXPLOSION
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private var isFirst = true
    private var lastTimeShow = 0L
    override fun provideScreenName(): String {
        return MAIN_SHOW
    }

    override fun binding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getData() {
        this.lifecycleScope.launch {

            this@MainActivity.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.timeReload.collect {
                        val timeReload = it
                        if (timeReload != null) {
                            if(timeReload.configKey.contains("Main")){
                                lastTimeShow = timeReload.time
                            }
                        }
                    }
                }
            }
        }
        initMedia()
    }

    override fun initView() {
    }

    override fun loadAds() {
        super.loadAds()
        playMedia()
        CemAdManager.getInstance(binding.root.context).loadBannerAndShowByActivity(
            activity = this,
            binding.bannerBottom.bannerLayout,
            configKey = BANNER_KEY_HOME,
            nameScreen = screenName
        )

        binding.nativeView.apply {
            screenName?.let {
                CemAdManager.getInstance(this@MainActivity).loadAndShowNativeByPlacement(context, this, NATIVE_ADS,
                    it
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        pauseMedia()
    }

    override fun initOnClickListener() {
        binding.apply {
            layoutMachineGun.setOnSingleClickListener {
                CemAnalytics.logEventClickView(this@MainActivity, screenName, MAIN_CLICK_GUN)
                CollectionActivity.start(this@MainActivity, "GUN", screenName)
            }
            layoutShockTaser.setOnSingleClickListener {
                CemAnalytics.logEventClickView(this@MainActivity,screenName, MAIN_CLICK_TASER)
                CollectionActivity.start(this@MainActivity, "SHOCK_TASER", screenName)
            }
            layoutLightSaber.setOnSingleClickListener {
                CemAnalytics.logEventClickView(this@MainActivity,screenName, MAIN_CLICK_SABER)
                CollectionActivity.start(this@MainActivity, "LIGHT_SABER", screenName)
            }

            layoutExplosion.setOnSingleClickListener {
                CemAnalytics.logEventClickView(this@MainActivity,screenName, MAIN_CLICK_EXPLOSION)
                CollectionActivity.start(this@MainActivity, "EXPLOSION", screenName)
            }

            btnSetting.setOnSingleClickListener {
                CemAnalytics.logEventClickView(this@MainActivity,screenName, MAIN_CLICK_SETTING)
                SettingFragment.show(this@MainActivity, screenName)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        screenName?.let { cemAdManager.removeBannerLoaded(it) }
        destroyMedia()
    }
}