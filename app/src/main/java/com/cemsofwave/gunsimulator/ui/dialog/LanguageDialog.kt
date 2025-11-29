package com.cemsofwave.gunsimulator.ui.dialog

import android.content.Intent
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.cem.admodule.ext.ConstAd
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.base.model.Language
import com.cemsofwave.gunsimulator.base.utils.SettingsSharedPref
import com.cemsofwave.gunsimulator.databinding.DialogLanguageBinding
import com.cemsofwave.gunsimulator.extension.addItemDecoration
import com.cemsofwave.gunsimulator.extension.configLanguage
import com.cemsofwave.gunsimulator.extension.start
import com.cemsofwave.gunsimulator.ui.activity.FlashActivity
import com.cemsofwave.gunsimulator.ui.adapter.LanguageAdapter
import com.cemsofwave.gunsimulator.utils.CLICK_SETTING
import com.cemsofwave.gunsimulator.utils.DONE
import com.cemsofwave.gunsimulator.utils.LANGUAGE_DIALOG
import com.cemsofwave.gunsimulator.viewmodel.PreviewViewModel
import com.trinhbx.base.ui.BaseDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LanguageDialog: BaseDialogFragment<DialogLanguageBinding>(){

    private val previewViewModel by viewModels<PreviewViewModel>()
    private val listLanguage = mutableListOf<Language>()
    private val languageAdapter = LanguageAdapter()
    private var config: SettingsSharedPref? = null
    private var isInstallFirst = true

    override fun provideScreenName(): String {
        return LANGUAGE_DIALOG
    }

    override fun binding(viewGroup: ViewGroup?, attachToParent: Boolean): DialogLanguageBinding {
        return DialogLanguageBinding.inflate(layoutInflater, viewGroup, attachToParent)
    }

    override fun getData() {
        super.getData()
        activity?.let { config = SettingsSharedPref.getInstance(it) }
        this.lifecycleScope.launch {
            requireActivity().lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    previewViewModel.installFirst.collect {
                        isInstallFirst = it
                    }
                }
            }
        }
        listLanguage.apply {
            clear()
            addAll(
                listOf(
                    Language(name = getString(R.string.english), code = "en", R.drawable.ic_english),
                    Language(name = getString(R.string.french), code = "fr", R.drawable.ic_french),
                    Language(name = getString(R.string.german), code = "de", R.drawable.ic_german),
                    Language(name = getString(R.string.spanish), code = "es", R.drawable.ic_spainish),
                    Language(name = getString(R.string.italian), code = "pt", R.drawable.ic_italian),
                )
            )
        }
    }

    override fun initView() {
        for (i in listLanguage.indices) {
            if (listLanguage[i].code == config?.language?.code) {
                initAdapter(data = listLanguage, selectedPosition = i)
                break
            }
        }
    }

    override fun initOnClickListener() {
        binding.btnSetting.setOnClickListener {
            activity?.let { act ->
                if(isInstallFirst){
                    previewViewModel.setInstallFirst(false)
                }
                CemAnalytics.logEventClickView(requireActivity(), screenName, DONE)
                act.configLanguage()
                val intent = Intent(act, FlashActivity::class.java)
                act.overridePendingTransition(0, 0)
                act.start(intent)
            }
        }
    }

    private fun initAdapter(data: List<Language>, selectedPosition: Int) {
        languageAdapter.apply {
            submitData(data)
            setSelectedPosition(position = selectedPosition)
            this@apply.onClickItemListener = { itemChecked, position ->
                config?.let {
                    CemAnalytics.logEventClickView(requireActivity(), screenName, itemChecked.name)
                    it.language = Language(name = itemChecked.name, code = itemChecked.code)
                }
            }
        }
        binding.rcvChecked.apply {
            adapter = languageAdapter
            addItemDecoration(marginHorizontal = 20, marginVertical = 10)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.nativeView.apply {
            setBackground(com.cem.admodule.R.drawable.bg_native_language_ads)
            screenName?.let {
                CemAdManager.getInstance(requireContext()).loadAndShowNativeByPlacement(context, this,
                    ConstAd.NATIVE_INTRO,
                    it
                )
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LanguageDialog().apply {
            arguments = bundleOf()
        }
        @JvmStatic
        fun show(activity: FragmentActivity?, fromScreenName:String?
        ){
            if(activity==null || activity.supportFragmentManager.isDestroyed || activity.supportFragmentManager.isStateSaved) return
            newInstance().apply {

            }.show(
                activity.supportFragmentManager,
                null
            )
        }
    }
}