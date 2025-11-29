package com.cemsofwave.gunsimulator.ui.fragment

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.cem.admodule.ext.ConstAd.NATIVE_RELOAD
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.BuildConfig
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.data.model.TimeManager
import com.cemsofwave.gunsimulator.databinding.FragmentSettingBinding
import com.cemsofwave.gunsimulator.ui.dialog.LanguageDialog
import com.cemsofwave.gunsimulator.ui.dialog.VoteDialog
import com.cemsofwave.gunsimulator.utils.SETTING_CLICK_FEEDBACK
import com.cemsofwave.gunsimulator.utils.SETTING_CLICK_LANGUAGE
import com.cemsofwave.gunsimulator.utils.SETTING_CLICK_POLICY
import com.cemsofwave.gunsimulator.utils.SETTING_CLICK_RATE
import com.cemsofwave.gunsimulator.utils.SETTING_CLICK_SHARE
import com.cemsofwave.gunsimulator.utils.VIEW_SETTING
import com.cemsofwave.gunsimulator.viewmodel.HomeViewModel
import com.trinhbx.base.extension.rateMyApp
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.extension.shareMyApp
import com.trinhbx.base.extension.support
import com.trinhbx.base.ui.BaseDialogFragment
import kotlinx.coroutines.launch

class SettingFragment : BaseDialogFragment<FragmentSettingBinding>() {

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private var lastTimeShowNative = 0L

    companion object {
        @JvmStatic
        fun show(activity: FragmentActivity?, fromScreenName: String?) {
            if (activity == null || activity.supportFragmentManager.isStateSaved) return
            SettingFragment().show(activity.supportFragmentManager, null)
        }
    }


    override fun provideScreenName(): String {
        return VIEW_SETTING
    }

    override fun binding(viewGroup: ViewGroup?, attachToParent: Boolean): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(layoutInflater, viewGroup, attachToParent)
    }

    override fun initView() {}

    override fun initOnClickListener() {
        binding.apply {
            btnClose.setOnClickListener { backPress() }
            btnLanguage.setOnSingleClickListener {
                CemAnalytics.logEventClickView(context, screenName, SETTING_CLICK_LANGUAGE)
                val languageDialog = LanguageDialog
                languageDialog.show(
                    requireActivity(),
                    screenName)
            }
            btnShare.setOnSingleClickListener {
                CemAnalytics.logEventClickView(context, screenName, SETTING_CLICK_SHARE)
                context?.shareMyApp(BuildConfig.APPLICATION_ID, getString(R.string.app_name))
            }
            btnRate.setOnSingleClickListener {
                CemAnalytics.logEventClickView(context, screenName, SETTING_CLICK_RATE)
                val voteDialog = VoteDialog
                voteDialog.show(
                    requireActivity(),
                    screenName,
                    onRequestListener = { isVote, numberStar ->
                        if (isVote) {
                            context?.rateMyApp(BuildConfig.APPLICATION_ID)
                        }
                    })
            }
            btnFeedback.setOnSingleClickListener {
                CemAnalytics.logEventClickView(context, screenName, SETTING_CLICK_FEEDBACK)
                context?.support()
            }
            btnPrivacyPolicy.setOnSingleClickListener {
                CemAnalytics.logEventClickView(context, screenName, SETTING_CLICK_POLICY)
                CompanyInfoFragment.show(
                    activity,
                    CompanyInfoFragment.Flag.PRIVACY_POLICY,
                    screenName
                )
            }
        }
    }

    //    override fun getTheme(): Int {
//        return R.style.FullScreenDialog_Transparent
//    }
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