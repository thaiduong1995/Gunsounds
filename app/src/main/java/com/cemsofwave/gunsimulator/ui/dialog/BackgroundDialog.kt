package com.cemsofwave.gunsimulator.ui.dialog

import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.cem.admodule.ext.ConstAd.FULL_KEY_BACK
import com.cem.admodule.ext.ConstAd.FULL_KEY_DETAIL
import com.cem.admodule.ext.ConstAd.NATIVE_RELOAD
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.data.model.TimeManager
import com.cemsofwave.gunsimulator.databinding.DialogChangeBackgroundBinding
import com.cemsofwave.gunsimulator.ui.adapter.BackgroundAdapter
import com.cemsofwave.gunsimulator.utils.BACKGROUND_NAME
import com.cemsofwave.gunsimulator.utils.BOMB_GRENADE_SHOW
import com.cemsofwave.gunsimulator.utils.BOMB_TIMER_SHOW
import com.cemsofwave.gunsimulator.utils.BULLETS_REQUEST_DIALOG
import com.cemsofwave.gunsimulator.utils.CLICK_RELOAD
import com.cemsofwave.gunsimulator.utils.GUN_DETAIL_SHOW
import com.cemsofwave.gunsimulator.utils.RotateTransformation
import com.cemsofwave.gunsimulator.utils.SABER_DETAIL_SHOW
import com.cemsofwave.gunsimulator.utils.TASER_DETAIL_SHOW
import com.cemsofwave.gunsimulator.viewmodel.HomeViewModel
import com.cemsofwave.gunsimulator.viewmodel.ImageViewModel
import com.cemsofwave.gunsimulator.viewmodel.SimulatorViewModel
import com.trinhbx.base.extension.dpToPx
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.ui.BaseDialogFragment
import com.trinhbx.base.utils.SpacesItemDecoration
import kotlinx.coroutines.launch


class BackgroundDialog : BaseDialogFragment<DialogChangeBackgroundBinding>() {

    private var onPosListener: ((Int) -> Unit)? = null
    private var pos: Int = -1
    private var type: String = ""
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private val simulatorViewModel by activityViewModels<SimulatorViewModel>()
    private val imageViewModel by activityViewModels<ImageViewModel>()
    private val backgroundAdapter by lazy { BackgroundAdapter() }
    private var lastTimeShowNative = 0L

    override fun provideScreenName(): String {
        return "${type}_select_bg"
    }

    override fun binding(
        viewGroup: ViewGroup?,
        attachToParent: Boolean
    ): DialogChangeBackgroundBinding {
        return DialogChangeBackgroundBinding.inflate(layoutInflater, viewGroup, attachToParent)
    }

    override fun initView() {
        binding.apply {
            backgroundAdapter.onItemClickListener = { bitmap, position ->
                Glide.with(requireContext()).clearOnStop().asBitmap().load(bitmap)
                    .transform(RotateTransformation(binding.root.context, -90f))
                    .into(binding.imvBackground)
                pos = position
            }
            rvItem.apply{
                layoutManager = GridLayoutManager(requireActivity(), 2, GridLayoutManager.HORIZONTAL, false)
                adapter = backgroundAdapter
                val hor = dpToPx(12)
                val ver = dpToPx(10)
                addItemDecoration(SpacesItemDecoration(0, 0, hor, ver))
            }
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
                    homeViewModel.timeReload.collect {
                        val timeReload = it
                        if (timeReload != null) {
                            if(timeReload.configKey.contains("NATIVE_BG")){
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
                CemAdManager.getInstance(requireContext()).loadAndShowNativeByPlacement(context,
                    this,
                    NATIVE_RELOAD,
                    it
                )
            }
        }
    }

    override fun registerObserve() {
        this.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    simulatorViewModel.listBgBitmapFlow.collect {
                        if(it.isNotEmpty()){
                            val list = mutableListOf<Bitmap>()
                            list.addAll(it)
                            when(type){
                                GUN_DETAIL_SHOW -> {
                                    try {
                                        Glide.with(requireContext()).clearOnStop().asBitmap().load(R.drawable.bg_gun)
                                            .into(object : CustomTarget<Bitmap>() {
                                                override fun onResourceReady(
                                                    resource: Bitmap,
                                                    transition: Transition<in Bitmap>?
                                                ) {
                                                    list.removeAt(0)
                                                    list.add(0, resource)
                                                }
                                                override fun onLoadCleared(placeholder: Drawable?) {}

                                            })
                                    }catch (e: Exception){
                                        e.printStackTrace()
                                    }
                                    backgroundAdapter.setData(list)
                                }
                            }
                        }
                    }
                }

                launch {
                    imageViewModel.listBgBitmapFlow.collect {
                        if (it.isNotEmpty()) {
                            val list = mutableListOf<Bitmap>()
                            list.addAll(it)
                            when(type){
                                SABER_DETAIL_SHOW -> {
                                    val width = requireContext().resources.displayMetrics.widthPixels
                                    val height = requireContext().resources.displayMetrics.heightPixels
                                    try {
                                        Glide.with(requireContext()).clearOnStop().asBitmap().load(R.drawable.bg_shock)
                                            .override(width, height)
                                            .centerCrop()
                                            .into(object : CustomTarget<Bitmap>() {
                                                override fun onResourceReady(
                                                    resource: Bitmap,
                                                    transition: Transition<in Bitmap>?
                                                ) {
                                                    list.removeAt(0)
                                                    list.add(0, resource)
                                                }
                                                override fun onLoadCleared(placeholder: Drawable?) {}

                                            })
                                    }catch (e: Exception){
                                        e.printStackTrace()
                                    }
                                    backgroundAdapter.setData(list)
                                }
                                TASER_DETAIL_SHOW -> {
                                    val width = requireContext().resources.displayMetrics.widthPixels
                                    val height = requireContext().resources.displayMetrics.heightPixels
                                    try {
                                        Glide.with(requireContext()).clearOnStop().asBitmap().load(R.drawable.bg_light)
                                            .override(width, height)
                                            .centerCrop()
                                            .into(object : CustomTarget<Bitmap>() {
                                                override fun onResourceReady(
                                                    resource: Bitmap,
                                                    transition: Transition<in Bitmap>?
                                                ) {
                                                    list.removeAt(0)
                                                    list.add(0, resource)
                                                }
                                                override fun onLoadCleared(placeholder: Drawable?) {}

                                            })
                                    }catch (e: Exception){
                                        e.printStackTrace()
                                    }
                                    backgroundAdapter.setData(list)
                                }
                                BOMB_GRENADE_SHOW, BOMB_TIMER_SHOW  -> {
                                    val width = requireContext().resources.displayMetrics.widthPixels
                                    val height = requireContext().resources.displayMetrics.heightPixels
                                    try {
                                        Glide.with(requireContext()).clearOnStop().asBitmap().load(R.drawable.bg_light)
                                            .override(width, height)
                                            .centerCrop()
                                            .into(object : CustomTarget<Bitmap>() {
                                                override fun onResourceReady(
                                                    resource: Bitmap,
                                                    transition: Transition<in Bitmap>?
                                                ) {
                                                    list.removeAt(0)
                                                    list.add(0, resource)
                                                }
                                                override fun onLoadCleared(placeholder: Drawable?) {}

                                            })
                                    }catch (e: Exception){
                                        e.printStackTrace()
                                    }
                                    backgroundAdapter.setData(list)
                                }
                            }
                        }
                    }
                }

                launch {
                    simulatorViewModel.backgroundBitmapFlow.collect {
                        if(it!=null){
                            Glide.with(requireContext()).clearOnStop().asBitmap().load(it)
                                .transform(RotateTransformation(binding.root.context, -90f))
                                .into(binding.imvBackground)
                        }
                    }

                }
                launch {
                    imageViewModel.backgroundBitmapFlow.collect {
                        if(it!=null){
                            Glide.with(requireContext()).clearOnStop().asBitmap().load(it)
                                .transform(RotateTransformation(binding.root.context, -90f))
                                .into(binding.imvBackground)
                        }
                    }
                }
            }
        }
    }

    override fun initOnClickListener() {
        binding.apply {
            setOnBackPressedListener {
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback(activity = it, configKey =  FULL_KEY_BACK, callback = {
                            dismiss()
                        })
                }
            }
            imgClose.setOnSingleClickListener {
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback(activity = it, configKey = FULL_KEY_BACK, callback =  {
                            dismiss()
                        })
                }
            }
            btnCharge.setOnSingleClickListener {
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback( activity = it, configKey = FULL_KEY_DETAIL, callback =  {
                            if(pos != -1){
                                when(type){
                                    GUN_DETAIL_SHOW -> {
                                        simulatorViewModel.changeBackground(pos)
                                    }
                                    SABER_DETAIL_SHOW -> {
                                        imageViewModel.loadBackgroundBitmap(pos)
                                    }
                                    TASER_DETAIL_SHOW -> {
                                        imageViewModel.loadBackgroundBitmap(pos)
                                    }
                                    BOMB_GRENADE_SHOW -> {
                                        imageViewModel.loadBackgroundBitmap(pos)
                                    }
                                }
                            }
                            dismiss()
                        })
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onPosListener?.invoke(pos)
    }
    companion object {
        @JvmStatic
        fun newInstance() = BackgroundDialog().apply {
            arguments = bundleOf()
        }
        @JvmStatic
        fun show(activity:FragmentActivity?, fromScreenName:String?,
                 onPosListener: ((Int) -> Unit)?
        ){
            if(activity==null || activity.supportFragmentManager.isDestroyed || activity.supportFragmentManager.isStateSaved) return
            newInstance().apply {
                this.onPosListener = onPosListener
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