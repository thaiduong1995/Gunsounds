package com.cemsofwave.gunsimulator.ui.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.cem.admodule.data.ErrorCode
import com.cem.admodule.ext.AdmobConfig
import com.cem.admodule.ext.ConstAd
import com.cem.admodule.ext.ConstAd.FULL_KEY_SPLASH
import com.cem.admodule.ext.gone
import com.cem.admodule.inter.CemInterstitialAd
import com.cem.admodule.inter.InterstitialLoadCallback
import com.cem.admodule.manager.GoogleConsentManager
import com.cem.admodule.manager.PurchaseManager
import com.cemsofwave.gunsimulator.BuildConfig
import com.cemsofwave.gunsimulator.databinding.ActivityFlashBinding
import com.cemsofwave.gunsimulator.event.OnPreviewListener
import com.cemsofwave.gunsimulator.ui.fragment.PreviewOneFragment
import com.cemsofwave.gunsimulator.ui.fragment.PreviewTwoFragment
import com.cemsofwave.gunsimulator.utils.VIEW_SPLASH_ACTIVITY
import com.cemsofwave.gunsimulator.viewmodel.PreviewViewModel
import com.google.android.ump.ConsentDebugSettings
import com.cemsofwave.gunsimulator.base.BaseActivity
import com.cemsofwave.gunsimulator.ui.dialog.LanguageDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean

@AndroidEntryPoint
class FlashActivity : BaseActivity<ActivityFlashBinding>(), OnPreviewListener {

    var TAG: String = FlashActivity::class.java.simpleName

    private lateinit var googleConsentManager: GoogleConsentManager
    private val isMobileAdsInitializeCalled = AtomicBoolean(false)
    private val previewViewModel by viewModels<PreviewViewModel>()

    private var needShowAdsFull = false
    private var isInstallFirst = true
    override fun provideScreenName(): String {
        return VIEW_SPLASH_ACTIVITY
    }

    override fun binding(): ActivityFlashBinding {
        return  ActivityFlashBinding.inflate(layoutInflater)
    }

    override fun getData() {
        super.getData()
        this.lifecycleScope.launch {
            this@FlashActivity.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    previewViewModel.installFirst.collect {
                        isInstallFirst = it
                    }
                }
            }
        }
    }

    override fun initView() {
        cemAdManager.initMMKV()
        this.lifecycleScope.launch(Dispatchers.Main) {
            val activity = this@FlashActivity
            PurchaseManager.instance?.isRemovedAds(activity)
        }
        binding.apply {
            val paint = tvTitle.paint
            val width = paint.measureText(tvTitle.text.toString())
            val textShader: Shader = LinearGradient(0f, 0f, width, tvTitle.textSize, intArrayOf(
                Color.parseColor("#F9DF7B"),
                Color.parseColor("#BA872E"),
                Color.parseColor("#BD8B31"),
                Color.parseColor("#C5963C"),
                Color.parseColor("#D3AA4D"),
                Color.parseColor("#E6C564"),
                Color.parseColor("#F9DF7B"),
                Color.parseColor("#FFEA85"),
                Color.parseColor("#F9DF7B"),
                Color.parseColor("#B57E10"),
                Color.parseColor("#E5C35C"),
                Color.parseColor("#F9DF7B")
            ), null, Shader.TileMode.REPEAT)

            tvTitle.paint.shader = textShader
            tvTitle.gone()
        }
        if(checkNetwork()){
            startApp()
        }else{
            fetchConfigFirebase()
        }
    }

    private fun fetchConfigFirebase() {
        lifecycleScope.launch {
            cemAdManager.fetchConfig(
                configKey = ConstAd.ADS_REMOTE, fileLocal = ConstAd.ADS_CONFIG
            ).collect { response ->
                if (response) initGDPR()
            }
        }
    }

    private fun initGDPR() {
        googleConsentManager = GoogleConsentManager.getInstance(this)
        if (BuildConfig.DEBUG) {
            googleConsentManager.resetConsent()
        }
        googleConsentManager.gatherConsentDebugWithGeography(activity = this,
            geography = ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA,
            hashedId = "9E6EFCDACDC588A12ECBBC0B32275D62",
            appId = cemAdManager.getAdManager?.adConfig?.appId,
            onConsentGatheringCompleteListener = { consentError ->
                if (consentError != null) {
                    initAdmobManager()
                }
                if (googleConsentManager.isRequestAds) {
                    initAdmobManager()
                }
            })
        if (googleConsentManager.isRequestAds) {
            initAdmobManager()
        }
    }

    private fun initAdmobManager() {
        lifecycleScope.launch(Dispatchers.Main) {
            if (isMobileAdsInitializeCalled.getAndSet(true)) {
                return@launch
            }
            AdmobConfig.initialize(
                this@FlashActivity, testDeviceIds = listOf(
                    "59B28BE4BF69F8F6DF58FD314FBC858B", "8CC819096052C744497915C174693FBB"
                )
            ).let {
                withContext(Dispatchers.Main){
                    loadingAdManager()
                }
            }
        }
    }

    private fun loadingAdManager(){
        cemAdManager
            .loadInterstitialByPlacements(
                activity = this@FlashActivity,
                configKey = FULL_KEY_SPLASH,
                object : InterstitialLoadCallback{
                    override fun onAdLoaded(cemInterstitialAd: CemInterstitialAd?) {
                        if(supportFragmentManager.isStateSaved || supportFragmentManager.isDestroyed) {
                            needShowAdsFull = true
                            return
                        }
                        startApp()
                    }

                    override fun onAdFailedToLoaded(error: ErrorCode) {
                        startApp()
                    }

                }
            ).preloadManagerAdUnits(this@FlashActivity)
    }

    private fun startApp(){
//        if(cemAdManager.isEnableIntroAds){
//            when {
//                isInstallFirst -> {
//                    val previewOneFragment = LanguageDialog.newInstance()
//                    addFragment(binding.fragmentPreview, previewOneFragment)
//                }
//
//                !isInstallFirst -> {
//                    goToMain()
//                }
//            }
//        }else{
//            goToMain()
//        }
        when {
            isInstallFirst -> {
                val previewOneFragment = LanguageDialog.newInstance()
                addFragment(binding.fragmentPreview, previewOneFragment)
            }

            !isInstallFirst -> {
                goToMain()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(needShowAdsFull){
            goToMain()
        }
    }

    override fun onClick(preview: String) {
        when (preview) {
            OnPreviewListener.PREVIEW_ONE -> {
                val previewTwoFragment = PreviewTwoFragment.newInstance()
                addFragment(binding.fragmentPreview, previewTwoFragment)
            }

            OnPreviewListener.PREVIEW_TWO -> {
                goToMain()
            }
        }
    }

    private fun goToMain(){
        previewViewModel.setInstallFirst(false)
//        if(cemAdManager.isEnableIntroAds){
//            previewViewModel.setInstallFirst(false)
//        }
        cemAdManager.showInterstitialReloadCallback(activity = this@FlashActivity,
            configKey = FULL_KEY_SPLASH,
            callback = {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            })
    }
}