package com.cemsofwave.gunsimulator.ui.dialog

import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.cem.admodule.ext.ConstAd.NATIVE_RELOAD
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.data.model.TimeManager
import com.cemsofwave.gunsimulator.databinding.FragmentSettingDetailBinding
import com.cemsofwave.gunsimulator.utils.SETTING_TOGGLE_FLASH
import com.cemsofwave.gunsimulator.utils.SETTING_TOGGLE_SOUND
import com.cemsofwave.gunsimulator.utils.SETTING_TOGGLE_VIBRATE
import com.cemsofwave.gunsimulator.utils.VIEW_SETTING
import com.cemsofwave.gunsimulator.viewmodel.HomeViewModel
import com.cemsofwave.gunsimulator.viewmodel.SimulatorViewModel
import com.trinhbx.base.ui.BaseDialogFragment
import kotlinx.coroutines.launch

class SettingDetailFragment : BaseDialogFragment<FragmentSettingDetailBinding>() {

    private var isVibrate: Boolean? = false
    private var isFlash: Boolean? = false
    private var isSound: Boolean? = false
    private var fromScreenName: String? = ""
    private val simulatorViewModel by activityViewModels<SimulatorViewModel>()
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private var lastTimeShowNative = 0L
    companion object {

        @JvmStatic
        fun show(activity:FragmentActivity?,
                 isVibrate: Boolean?,
                 isFlash: Boolean?,
                 isSound: Boolean?,
                 fromScreenName:String?){
            if(activity==null || activity.supportFragmentManager.isStateSaved) return
            newInstance().apply {
                this.isVibrate = isVibrate
                this.isFlash = isFlash
                this.isSound = isSound
                this.fromScreenName = fromScreenName
            }.show(activity.supportFragmentManager,null)
        }

        @JvmStatic
        fun newInstance() = SettingDetailFragment().apply {
            arguments = bundleOf()
        }
    }


    override fun provideScreenName(): String {
        return fromScreenName.toString()
    }

    override fun binding(viewGroup: ViewGroup?, attachToParent: Boolean): FragmentSettingDetailBinding {
        return FragmentSettingDetailBinding.inflate(layoutInflater,viewGroup,attachToParent)
    }

    override fun initView() {
        binding.apply {
            switchVibrate.isChecked = isVibrate!!
            switchFlash.isChecked = isFlash!!
            switchSound.isChecked = isSound!!
        }
    }

    override fun getData() {
        super.getData()
        this.lifecycleScope.launch {
            requireActivity().lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.timeReload.collect {
                        val timeReload = it
                        if (timeReload != null) {
                            if (timeReload.configKey.contains("NATIVE_SETTING")) {
                                lastTimeShowNative = timeReload.time
                            }
                        }
                    }
                }
            }
        }
    }

    override fun initOnClickListener() {
        binding.apply {
            btnClose.setOnClickListener {
                simulatorViewModel.postBoolean(
                    switchVibrate.isChecked,
                    switchFlash.isChecked,
                    switchSound.isChecked
                )
                dismiss()
            }
            switchVibrate.setOnCheckedChangeListener { buttonView, isChecked ->
                switchVibrate.isChecked = isChecked
                CemAnalytics.logEventClickView(requireContext(), provideScreenName(), SETTING_TOGGLE_VIBRATE)
            }
            switchFlash.setOnCheckedChangeListener { buttonView, isChecked ->
                switchFlash.isChecked = isChecked
                CemAnalytics.logEventClickView(requireContext(), provideScreenName(), SETTING_TOGGLE_FLASH)
            }
            switchSound.setOnCheckedChangeListener { buttonView, isChecked ->
                switchSound.isChecked = isChecked
                CemAnalytics.logEventClickView(requireContext(), provideScreenName(), SETTING_TOGGLE_SOUND)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        binding.nativeView.apply {
            screenName?.let {
                CemAdManager.getInstance(requireContext()).loadAndShowNativeByPlacement(
                    context,
                    this,
                    NATIVE_RELOAD,
                    it
                )
            }
        }
    }
}