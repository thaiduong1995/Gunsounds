package com.cemsofwave.gunsimulator.ui.dialog

import android.content.DialogInterface
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.cem.admodule.data.RewardAdItem
import com.cem.admodule.ext.ConstAd.FULL_KEY_BACK
import com.cem.admodule.ext.ConstAd.FULL_KEY_DETAIL
import com.cem.admodule.ext.ConstAd.NATIVE_RELOAD
import com.cem.admodule.ext.ConstAd.REWARD_ADS
import com.cem.admodule.inter.CemRewardListener
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.adapters.SkinAdapter2
import com.cemsofwave.gunsimulator.data.model.GunModel
import com.cemsofwave.gunsimulator.data.model.SkinManager
import com.cemsofwave.gunsimulator.data.model.TimeManager
import com.cemsofwave.gunsimulator.databinding.DialogChangeSkinBinding
import com.cemsofwave.gunsimulator.utils.SKIN_DIALOG
import com.cemsofwave.gunsimulator.utils.CLICK_RELOAD
import com.cemsofwave.gunsimulator.utils.SELECT_SKIN
import com.cemsofwave.gunsimulator.utils.SKIN_ID
import com.cemsofwave.gunsimulator.viewmodel.HomeViewModel
import com.trinhbx.base.extension.dpToPx
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.ui.BaseDialogFragment
import com.trinhbx.base.utils.SpacesItemDecoration
import kotlinx.coroutines.launch


class SkinDialog(val gunModel: GunModel?, var postLast: Int) : BaseDialogFragment<DialogChangeSkinBinding>() {

    private var onSkinListener: ((Int, SkinManager) -> Unit)? = null
    private var skinSelect: SkinManager? = null
    private var type: String = ""
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private var lastTimeShowNative = 0L
    private var isSelect = false

    override fun provideScreenName(): String {
        return SKIN_DIALOG
    }

    override fun binding(
        viewGroup: ViewGroup?,
        attachToParent: Boolean
    ): DialogChangeSkinBinding {
        return DialogChangeSkinBinding.inflate(layoutInflater, viewGroup, attachToParent)
    }

    private val skinAdapter = SkinAdapter2 { pos, skin ->
        val params = HashMap<String, String>()
        params[SKIN_ID] = skin.nameSkin
        CemAnalytics.logEventAndParams(
            requireContext(),
            screenName = screenName,
            eventName = SELECT_SKIN,
            params = params
        )
        this.skinSelect = skin
        this.postLast = pos
        if (skin.lock) {
            if (CemAdManager.getInstance(requireContext()).isEnableRewardAds) {
                val watchAdsDialog = WatchAdsDialog
                watchAdsDialog.show(
                    requireActivity(),
                    gunModel,
                    pos,
                    screenName,
                    onAdsRequestListener = {
                        if (it) {
                            CemAdManager.getInstance(requireContext()).showRewardByPlacementReload(
                                activity = requireActivity(),
                                configKey = REWARD_ADS,
                                listener = object : CemRewardListener() {
                                    override fun onRewardAdded(rewardAdItem: RewardAdItem?) {
                                        val skinManager =
                                            gunModel?.let { it1 ->
                                                SkinManager(it1.name, it1.listSkin[pos], pos, false)
                                            }
                                        skinManager?.let { it1 -> homeViewModel.insertSkin(it1) }
                                        skinSelect = skin
                                        postLast = pos
                                        binding.preview.cancelAnimation()
                                        binding.preview.setAnimation(skin.nameSkin)
                                    }

                                    override fun onRewardFail(error: String?) {
                                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                                    }
                                })
                        }
                    })
            } else {
                this.skinSelect = (skin)
                this.postLast = pos
                binding.preview.cancelAnimation()
                binding.preview.setAnimation(skin.nameSkin)
            }
        } else {
            binding.preview.cancelAnimation()
            binding.preview.setAnimation(skin.nameSkin)
        }
    }

    override fun initView() {
        homeViewModel.getAllSkin()
        binding.apply {
            rvItem.apply{
                layoutManager = GridLayoutManager(requireActivity(), 2, GridLayoutManager.HORIZONTAL, false)
                adapter = skinAdapter
                val hor = dpToPx(12)
                val ver = dpToPx(10)
                addItemDecoration(SpacesItemDecoration(0, 0, hor, ver))
            }
            preview.cancelAnimation()
            preview.setAnimation(gunModel?.listSkin?.get(postLast))
            title.setStrokeColor(ContextCompat.getColor(title.context, R.color.secondary_color))
            title.setGradientText(
                ContextCompat.getColor(title.context, R.color.black),
                ContextCompat.getColor(title.context, R.color.black),
            )
        }
    }

    override fun getData() {
        super.getData()
        this.lifecycleScope.launch {
            requireActivity().lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.recordAllSkinFlow.collect {
                        when (it) {
                            is UiState.Loading -> {}

                            is UiState.Success -> {
                                val list = mutableListOf<SkinManager?>()
                                val skinList = arrayListOf<SkinManager>()
                                list.addAll(it.data)
                                gunModel?.apply {
                                    listSkin.forEachIndexed { index, item ->
                                        if (CemAdManager.getInstance(requireContext()).isEnableRewardAds) {
                                            if (index >= 2) {
                                                val isDuplicate = list.any { it?.nameSkin == item }
                                                skinList.add(
                                                    SkinManager(
                                                        this.name,
                                                        item,
                                                        index,
                                                        !isDuplicate
                                                    )
                                                )
                                            } else {
                                                skinList.add(
                                                    SkinManager(
                                                        this.name,
                                                        item,
                                                        index,
                                                        false
                                                    )
                                                )
                                            }
                                        } else {
                                            skinList.add(SkinManager(this.name, item, index, false))
                                        }
                                    }
                                    skinAdapter.updateAll(skinList, postLast)
                                }
                            }

                            else -> {
                                println("Fail to get record")
                            }
                        }
                    }
                }
                launch {
                    homeViewModel.timeReload.collect {
                        val timeReload = it
                        if (timeReload != null) {
                            if(timeReload.configKey.contains("NATIVE_SKIN")){
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
        binding.nativeView.apply {
            screenName?.let {
                CemAdManager.getInstance(requireContext()).loadAndShowNativeByPlacement(context, this, NATIVE_RELOAD,
                    it
                )
            }
        }
    }

    override fun initOnClickListener() {
        binding.apply {
            setOnBackPressedListener {
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback( activity = it, configKey = FULL_KEY_BACK, callback =  {
                            dismiss()
                        })
                }
            }
            imgClose.setOnSingleClickListener {
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback( activity = it, configKey =  FULL_KEY_BACK, callback =  {
                            dismiss()
                        })
                }
            }
            btnCharge.setOnSingleClickListener {
                CemAnalytics.logEventClickView(context, screenName, CLICK_RELOAD)
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback( activity = it, configKey = FULL_KEY_DETAIL, callback =  {
                            isSelect = true
                            dismiss()
                        })
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if(isSelect){
            skinSelect?.let { onSkinListener?.invoke(postLast, it) }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(gunModel: GunModel?, postLast: Int) = SkinDialog(gunModel, postLast).apply {
            arguments = bundleOf()
        }
        @JvmStatic
        fun show(activity:FragmentActivity?, fromScreenName:String?, gunModel: GunModel?, postLast: Int,
                 onSkinListener: ((Int, SkinManager) -> Unit)?
        ){
            if(activity==null || activity.supportFragmentManager.isDestroyed || activity.supportFragmentManager.isStateSaved) return
            newInstance(gunModel, postLast).apply {
                this.onSkinListener = onSkinListener
                if (fromScreenName != null) {
                    this.type = fromScreenName
                }
            }.show(
                activity.supportFragmentManager,
                null
            )
        }
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog_Transparent
    }
}