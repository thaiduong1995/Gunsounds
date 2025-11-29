package com.cemsofwave.gunsimulator.ui.dialog

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.cem.admodule.ext.ConstAd
import com.cem.admodule.ext.ConstAd.FULL_KEY_BACK
import com.cem.admodule.ext.ConstAd.FULL_KEY_DETAIL
import com.cem.admodule.ext.ConstAd.NATIVE_RELOAD
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.data.model.TimeManager
import com.cemsofwave.gunsimulator.databinding.DialogChargeBatteryBinding
import com.cemsofwave.gunsimulator.interfaces.MyFragmentCallback
import com.cemsofwave.gunsimulator.ui.activity.AddDetailActivity
import com.cemsofwave.gunsimulator.utils.BULLETS_REQUEST_DIALOG
import com.cemsofwave.gunsimulator.utils.CLICK_RELOAD
import com.cemsofwave.gunsimulator.utils.GRENADE_REQUEST_DIALOG
import com.cemsofwave.gunsimulator.utils.RotateTransformation
import com.cemsofwave.gunsimulator.viewmodel.HomeViewModel
import com.trinhbx.base.extension.invisible
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.ui.BaseDialogFragment
import kotlinx.coroutines.launch

class ChargeBulletDialog : BaseDialogFragment<DialogChargeBatteryBinding>() {

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var myFragmentCallback: MyFragmentCallback
    private var isReload = false
    private var timeShow = 0
    private var type = "gun"
    private var lastTimeShowNative = 0L

    override fun provideScreenName(): String {
        return when(type) {
            "gun" -> {
                BULLETS_REQUEST_DIALOG
            }

            else -> {
                GRENADE_REQUEST_DIALOG
            }
        }
    }

    override fun binding(
        viewGroup: ViewGroup?,
        attachToParent: Boolean
    ): DialogChargeBatteryBinding {
        return DialogChargeBatteryBinding.inflate(layoutInflater, viewGroup, attachToParent)
    }

    fun setOnButtonClickListener(listener: MyFragmentCallback) {
        myFragmentCallback = listener
    }

    override fun getData() {
        super.getData()
        type = arguments?.getString(EXTRA_MODEL, "gun").toString()
        this.lifecycleScope.launch {
            requireActivity().lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.timeShowRate.collect {
                        if (it != null) {
                            timeShow = it
                        }
                    }
                }
                launch {
                    homeViewModel.timeReload.collect {
                        val timeReload = it
                        if (timeReload != null) {
                            if(timeReload.configKey.contains("NATIVE_RELOAD")){
                                lastTimeShowNative = timeReload.time
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun initView() {
        binding.apply {
            batteryEffect.invisible()
            imgFlash.invisible()
            bulletEffect.isGone = false
            when(type){
                "gun" -> {
                    Glide.with(requireContext()).load(R.drawable.your_bullets_are_out)
                        .into(tvNotify)
                    Glide.with(requireContext()).clearOnStop().asBitmap().load(R.drawable.img_item_bullet)
                        .transform(RotateTransformation(binding.root.context, 0f))
                        .into(binding.bulletEffect)
                }
                else -> {
                    Glide.with(requireContext()).load(R.drawable.your_grenades_are_out)
                        .into(tvNotify)
                    Glide.with(requireContext()).clearOnStop().asBitmap().load(R.drawable.img_item_grenades)
                        .transform(RotateTransformation(binding.root.context, 0f))
                        .into(binding.bulletEffect)
                }
            }
            tvNotify.textAlignment = View.TEXT_ALIGNMENT_CENTER
            btnCharge.text = getString(R.string.reload)
            title.setStrokeColor(ContextCompat.getColor(title.context, R.color.secondary_color))
            title.setGradientText(
                ContextCompat.getColor(title.context, R.color.black),
                ContextCompat.getColor(title.context, R.color.black),
            )
        }
    }

    override fun onResume() {
        super.onResume()
        binding.nativeView.apply {
            setBackground(com.cem.admodule.R.drawable.bg_native_reload_ads)
            screenName?.let {
                CemAdManager.getInstance(requireContext()).loadAndShowNativeByPlacement(
                    context,
                    this,
                    NATIVE_RELOAD, it
                )
            }
        }
    }

    override fun initOnClickListener() {
        binding.apply {
            view.setOnClickListener {
                println("Click")
            }
            setOnBackPressedListener {
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback(activity = it, configKey =  FULL_KEY_BACK, callback =  {
                            if(myFragmentCallback != null){
                                myFragmentCallback.onBulletsRequestListener(false)
                            }
                            it.finish()
                        })
                }
            }
            imgClose.setOnSingleClickListener {
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback(activity = it, configKey =  FULL_KEY_BACK, callback =  {
                            if(myFragmentCallback != null){
                                myFragmentCallback.onBulletsRequestListener(false)
                            }
                            it.finish()
                        })
                }
            }
            btnCharge.setOnSingleClickListener {
                CemAnalytics.logEventClickView(context, screenName, CLICK_RELOAD)
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback(activity = it, configKey =  FULL_KEY_DETAIL, callback =  {
                            isReload = true
                            if(myFragmentCallback != null){
                                myFragmentCallback.onBulletsRequestListener(true)
                            }
                            homeViewModel.setTimeShowRate(timeShow + 1)
                            it.finish()
                        })
                }
            }
        }
    }

    companion object {

        private const val EXTRA_MODEL = "EXTRA_MODEL"

        @JvmStatic
        fun newInstance(type: String) = ChargeBulletDialog().apply {
            arguments = bundleOf(EXTRA_MODEL to type)
        }
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog_Transparent
    }
}