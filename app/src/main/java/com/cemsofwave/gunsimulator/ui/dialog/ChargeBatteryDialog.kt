package com.cemsofwave.gunsimulator.ui.dialog

import android.content.DialogInterface
import android.graphics.Color
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.FragmentActivity
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
import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.data.model.TimeManager
import com.cemsofwave.gunsimulator.databinding.DialogChargeBatteryBinding
import com.cemsofwave.gunsimulator.interfaces.MyFragmentCallback
import com.cemsofwave.gunsimulator.utils.CHARGE_BATTERY_DIALOG
import com.cemsofwave.gunsimulator.utils.CLICK_CHARGE
import com.cemsofwave.gunsimulator.utils.RotateTransformation
import com.cemsofwave.gunsimulator.utils.SimulatorType
import com.cemsofwave.gunsimulator.viewmodel.HomeViewModel
import com.trinhbx.base.extension.invisible
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.ui.BaseDialogFragment
import kotlinx.coroutines.launch

class ChargeBatteryDialog : BaseDialogFragment<DialogChargeBatteryBinding>() {

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var myFragmentCallback: MyFragmentCallback
    private var simulatorType = SimulatorType.LIGHT_SABER
    private var timeShow = 0
    private var lastTimeShowNative = 0L

    override fun provideScreenName(): String {
        return CHARGE_BATTERY_DIALOG
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

    override fun initView() {
        binding.apply {
            batteryEffect.invisible()
            imgFlash.invisible()
            bulletEffect.isGone = false
            Glide.with(requireContext()).load(R.drawable.ic_battery)
                .into(bulletEffect)
            title.setStrokeColor(ContextCompat.getColor(title.context, R.color.secondary_color))
            title.setGradientText(
                ContextCompat.getColor(title.context, R.color.black),
                ContextCompat.getColor(title.context, R.color.black),
            )

            Glide.with(requireContext()).load(R.drawable.the_power_has_run_out)
                .into(tvNotify)

        }
    }

    override fun getData() {
        super.getData()
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

    fun setType(simulatorType: SimulatorType){
        this.simulatorType = simulatorType
    }

//    private val collectionAdapter = Collection2Adapter(object :Collection2Adapter.Listener {
//        override fun onItemClicked(simulatorModel: SimulatorModel, position: Int) {
//            when (simulatorModel) {
//                is ShockTaserModel -> {
//                    val params = HashMap<String, String>()
//                    params[TASER_NAME] = simulatorModel.name
//                    context?.let { CemAnalytics.logEventAndParams(it, screenName = screenName, eventName = TASER_CLICK_TASER, params = params) }
//                    onItemRequestListener?.invoke(simulatorModel)
//                    binding.batteryEffect.progress = 0f
//                    dismiss()
//                }
//                is LightSaberModel -> {
//                    val params = HashMap<String, String>()
//                    params[SABER_NAME] = simulatorModel.name
//                    context?.let { CemAnalytics.logEventAndParams(it, screenName = screenName, eventName = SABER_CLICK_SABER, params = params) }
//                    onItemRequestListener?.invoke(simulatorModel)
//                    binding.batteryEffect.progress = 0f
//                    dismiss()
//                }
//            }
//        }
//
//        override fun onDataIsEmpty(isEmpty: Boolean) {
//        }
//    }, 1)

//    override fun registerObserve() {
//        fun supportUpdateDataRv(listData:List<SimulatorModel>?){
//            listData?.let { collectionAdapter.setData(it.reversed()) }
//        }
//        when (simulatorType) {
//
//            SimulatorType.SHOCK_TASER -> {
//                homeViewModel.shockTaserCollection.observe(
//                    viewLifecycleOwner
//                ){
//                    if (it.getStatus() == StateData.DataStatus.SUCCESS) {
//                        supportUpdateDataRv(it.getData())
//                    }
//                }
//            }
//
//            SimulatorType.LIGHT_SABER -> {
//                homeViewModel.lightSaberCollection.observe(
//                    viewLifecycleOwner
//                ){
//                    if (it.getStatus() == StateData.DataStatus.SUCCESS) {
//                        supportUpdateDataRv(it.getData())
//                    }
//                }
//            }
//
//            else -> {}
//        }
//    }
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
                        .showInterstitialReloadCallback( activity = it, configKey =  FULL_KEY_BACK, callback =  {
                            if(myFragmentCallback != null){
                                myFragmentCallback.onBulletsRequestListener(false)
                            }
                            it.finish()
                        })
                }
            }
            btnCharge.setOnSingleClickListener {
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback( activity = it, configKey = FULL_KEY_DETAIL, callback =  {
                            CemAnalytics.logEventClickView(context, screenName, CLICK_CHARGE)
                            batteryEffect.progress = 0f
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

    override fun getTheme(): Int {
        return R.style.FullScreenDialog_Transparent
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
    companion object {
        @JvmStatic
        fun newInstance() = ChargeBatteryDialog().apply {
            arguments = bundleOf()
        }
    }
}