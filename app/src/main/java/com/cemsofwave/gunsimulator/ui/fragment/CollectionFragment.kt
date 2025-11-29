package com.cemsofwave.gunsimulator.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cem.admodule.ext.ConstAd.BANNER_KEY_DETAIL
import com.cem.admodule.ext.ConstAd.FULL_KEY_BACK
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.BuildConfig
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.adapters.Collection2Adapter
import com.cemsofwave.gunsimulator.data.model.ExplosionData
import com.cemsofwave.gunsimulator.data.model.ExplosionModel
import com.cemsofwave.gunsimulator.data.model.GunModel
import com.cemsofwave.gunsimulator.data.model.LightSaberModel
import com.cemsofwave.gunsimulator.data.model.ShockTaserModel
import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.data.model.TimeManager
import com.cemsofwave.gunsimulator.databinding.FragmentCollectionBinding
import com.cemsofwave.gunsimulator.extension.addItemDecoration
import com.cemsofwave.gunsimulator.ui.activity.BombDetailActivity
import com.cemsofwave.gunsimulator.ui.activity.GunDetailActivity
import com.cemsofwave.gunsimulator.ui.activity.LightSaberDetailActivity
import com.cemsofwave.gunsimulator.ui.activity.ShockTaserDetailActivity
import com.cemsofwave.gunsimulator.ui.dialog.VoteDialog
import com.cemsofwave.gunsimulator.utils.BOMB_CLICK_GRENADE
import com.cemsofwave.gunsimulator.utils.BOMB_CLICK_TIMER
import com.cemsofwave.gunsimulator.utils.BOMB_FAVORITE_SHOW
import com.cemsofwave.gunsimulator.utils.BOMB_NAME
import com.cemsofwave.gunsimulator.utils.CLICK_FAVOURITE
import com.cemsofwave.gunsimulator.utils.GUN_CLICK_GUN
import com.cemsofwave.gunsimulator.utils.SABER_CLICK_SABER
import com.cemsofwave.gunsimulator.utils.TASER_CLICK_TASER
import com.cemsofwave.gunsimulator.utils.CLICK_NEXT
import com.cemsofwave.gunsimulator.utils.CLICK_PREVIOUS
import com.cemsofwave.gunsimulator.utils.SimulatorType
import com.cemsofwave.gunsimulator.utils.TAB_GUN_SHOW
import com.cemsofwave.gunsimulator.utils.GUN_FAVORITE_SHOW
import com.cemsofwave.gunsimulator.utils.GUN_NAME
import com.cemsofwave.gunsimulator.utils.TAB_SABER_SHOW
import com.cemsofwave.gunsimulator.utils.SABER_FAVORITE_SHOW
import com.cemsofwave.gunsimulator.utils.SABER_NAME
import com.cemsofwave.gunsimulator.utils.TAB_BOMB_SHOW
import com.cemsofwave.gunsimulator.utils.TAB_TASER_SHOW
import com.cemsofwave.gunsimulator.utils.TASER_FAVORITE_SHOW
import com.cemsofwave.gunsimulator.utils.TASER_NAME
import com.cemsofwave.gunsimulator.viewmodel.HomeViewModel
import com.trinhbx.base.extension.dpToPx
import com.trinhbx.base.extension.getArgumentSerializable
import com.trinhbx.base.extension.gone
import com.trinhbx.base.extension.invisible
import com.trinhbx.base.extension.rateMyApp
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.extension.visible
import com.trinhbx.base.model.StateData
import com.trinhbx.base.ui.BaseDialogFragment
import com.trinhbx.base.utils.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CollectionFragment : BaseDialogFragment<FragmentCollectionBinding>() {
    companion object {
        private const val EXTRA_IS_FAV = "EXTRA_IS_FAV"
        private const val EXTRA_SIMULATOR_TYPE = "EXTRA_SIMULATOR_TYPE"
        private const val SCREEN_NAME = "SCREEN_NAME"

        @JvmStatic
        fun newInstance(isFav: Boolean,
                        simulatorType: SimulatorType,
                        fromScreenName: String?) = CollectionFragment().apply {
            arguments = bundleOf(EXTRA_IS_FAV to isFav, EXTRA_SIMULATOR_TYPE to simulatorType, SCREEN_NAME to fromScreenName)
        }

        @JvmStatic
        fun show(
            activity: FragmentActivity?,
            isFav: Boolean,
            simulatorType: SimulatorType,
            fromScreenName: String?
        ) {
            if (activity == null || activity.supportFragmentManager.isStateSaved) return
            CemAdManager.getInstance(activity).showInterstitialReloadCallback(
                activity = activity,
                configKey = "",
                callback =
            {
                CollectionFragment().apply {
                    arguments = Bundle().apply {
                        putBoolean(EXTRA_IS_FAV, isFav)
                        putSerializable(EXTRA_SIMULATOR_TYPE, simulatorType)
                    }
                }.show(activity.supportFragmentManager, null)
            })
        }
    }

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private var isFirst = true

    private var simulatorType = SimulatorType.GUN

    private val collectionAdapter = Collection2Adapter(object :Collection2Adapter.Listener {
        override fun onItemClicked(simulatorModel: SimulatorModel, position: Int) {
            when (simulatorModel) {
                is GunModel -> {
                    val params = HashMap<String, String>()
                    params[GUN_NAME] = simulatorModel.name
                    context?.let { CemAnalytics.logEventAndParams(it, screenName = screenName, eventName = GUN_CLICK_GUN, params = params) }
                    GunDetailActivity.start(activity, simulatorModel, screenName)
                }
                is ShockTaserModel -> {
                    val params = HashMap<String, String>()
                    params[TASER_NAME] = simulatorModel.name
                    context?.let { CemAnalytics.logEventAndParams(it, screenName = screenName, eventName = TASER_CLICK_TASER, params = params) }
                    ShockTaserDetailActivity.start(activity, simulatorModel, screenName)
                }
                is LightSaberModel -> {
                    val params = HashMap<String, String>()
                    params[SABER_NAME] = simulatorModel.name
                    context?.let { CemAnalytics.logEventAndParams(it, screenName = screenName, eventName = SABER_CLICK_SABER, params = params) }
                    LightSaberDetailActivity.start(activity, simulatorModel, screenName)
                }

                is ExplosionModel -> {
                    val params = HashMap<String, String>()
                    params[BOMB_NAME] = simulatorModel.name
                    context?.let {
                        when(simulatorModel.typeBomb){
                            "click"-> {
                                CemAnalytics.logEventAndParams(it, screenName = "", eventName = BOMB_CLICK_GRENADE, params = params)
                            }
                            else -> {
                                CemAnalytics.logEventAndParams(it, screenName = "", eventName = BOMB_CLICK_TIMER, params = params)
                            }
                        }
                    }
                    BombDetailActivity.start(activity, simulatorModel, screenName)
                }
            }
        }

        override fun onDataIsEmpty(isEmpty: Boolean) {
            if(isEmpty){
                binding.tvDataNotify.visible()
            } else{
                binding.tvDataNotify.gone()
            }
        }
    })

    private var isFav = false
    private var isShowRate = false

    private var firstPos = 0
    private var lastPos = 0
    private var lastTimeShow = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        isFav = arguments?.getBoolean(EXTRA_IS_FAV) ?: isFav
        simulatorType = arguments?.getArgumentSerializable(EXTRA_SIMULATOR_TYPE) ?: simulatorType
        super.onCreate(savedInstanceState)
    }

    override fun provideScreenName(): String {
        return if (!isFav) {
            when (simulatorType) {
                SimulatorType.GUN -> TAB_GUN_SHOW
                SimulatorType.SHOCK_TASER -> TAB_TASER_SHOW
                SimulatorType.EXPLOSION -> TAB_BOMB_SHOW
                else -> TAB_SABER_SHOW
            }
        } else {
            when (simulatorType) {
                SimulatorType.GUN -> GUN_FAVORITE_SHOW
                SimulatorType.SHOCK_TASER -> TASER_FAVORITE_SHOW
                SimulatorType.EXPLOSION -> BOMB_FAVORITE_SHOW
                else -> SABER_FAVORITE_SHOW
            }
        }
    }

    override fun binding(
        viewGroup: ViewGroup?,
        attachToParent: Boolean
    ): FragmentCollectionBinding {
        return FragmentCollectionBinding.inflate(layoutInflater, viewGroup, attachToParent)
    }

    override fun initView() {
        binding.apply {
            isShowRate = homeViewModel.isShowRate
            btnPrev.setEnable(false)
            btnNext.setEnable(false)
            rvCollection.adapter = collectionAdapter
            val hor = dpToPx(12)
            val ver = dpToPx(12)
            rvCollection.addItemDecoration(hor, 0, ver, 0)
            if(isFav){
                btnFavList.invisible()
                tvTitle.text = getString(R.string.favorite)
            } else{
                btnFavList.visible()
                when (simulatorType) {
                    SimulatorType.GUN -> tvTitle.text = getString(R.string.txt_machine_gun)
                    SimulatorType.SHOCK_TASER -> tvTitle.text = getString(R.string.shock_taser)
                    SimulatorType.EXPLOSION -> tvTitle.text = getString(R.string.explosion)
                    else -> tvTitle.text = getString(R.string.light_saber)
                }
            }
            binding.loading.visible()
        }
    }

    override fun getData() {
        super.getData()
        this.lifecycleScope.launch {
            this@CollectionFragment.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.timeReload.collect {
                        val timeReload = it
                        if (timeReload != null) {
                            if(timeReload.configKey.contains("Collection")){
                                lastTimeShow = timeReload.time
                            }
                        }
                    }
                }
                launch {
                    homeViewModel.timeShowRate.collect {
                        if (it != null) {
                            if(it >= 1 && !homeViewModel.isShowRate && homeViewModel.numberShowRate < 1){
                                if(!homeViewModel.isShowRate && !isShowRate){
                                    val voteDialog = VoteDialog
                                    voteDialog.show(requireActivity(), screenName, onRequestListener = { isVote, numberStar ->
                                        if(isVote){
                                            isShowRate = true
                                            homeViewModel.setIsShowRate(true)
                                            context?.rateMyApp(BuildConfig.APPLICATION_ID)
                                        }
                                        homeViewModel.setNumberShowRate(homeViewModel.numberShowRate + 1)
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun loadAds() {
        super.loadAds()
        activity?.let {
            CemAdManager.getInstance(requireContext()).loadBannerAndShowByActivity(
                activity = it,
                binding.bannerBottom.bannerLayout,
                configKey = BANNER_KEY_DETAIL,
                nameScreen = screenName
            )
        }
    }

    override fun initOnClickListener() {
        binding.apply {
            setOnBackPressedListener {
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback(
                            activity = it,
                            configKey = FULL_KEY_BACK,
                            callback =
                        {
                            when(screenName){
                                GUN_FAVORITE_SHOW, TASER_FAVORITE_SHOW, SABER_FAVORITE_SHOW, BOMB_FAVORITE_SHOW -> {
                                    dismiss()
                                }
                                else -> {
                                    it.finish()
                                }
                            }
                        })
                }
            }
            btnBack.setOnClickListener {
                activity?.let {
                    CemAdManager.getInstance(it)
                        .showInterstitialReloadCallback(
                            activity = it,
                            configKey = FULL_KEY_BACK,
                            callback =
                        {
                            when(screenName){
                                GUN_FAVORITE_SHOW, TASER_FAVORITE_SHOW, SABER_FAVORITE_SHOW, BOMB_FAVORITE_SHOW -> {
                                    dismiss()
                                }
                                else -> {
                                    it.finish()
                                }
                            }
                        })
                }
            }
            btnPrev.setOnClickListener {
                CemAnalytics.logEventClickView(context,screenName, CLICK_PREVIOUS)
                var pos = firstPos - 6
                if (pos <= 0) pos = 0
                rvCollection.smoothScrollToPosition(pos)
            }
            btnNext.setOnClickListener {
                CemAnalytics.logEventClickView(context,screenName, CLICK_NEXT)
                rvCollection.adapter?.itemCount?.let {
                    var pos = lastPos + 6
                    if (pos >= it) pos = it
                    rvCollection.smoothScrollToPosition(pos)
                }
            }

            btnFavList.setOnSingleClickListener {
                CemAnalytics.logEventClickView(context, screenName, CLICK_FAVOURITE)
                show(
                    activity,
                    true,
                    simulatorType,
                    screenName
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        screenName?.let { CemAdManager.getInstance(requireActivity()).removeBannerLoaded(it) }
    }

    override fun registerObserve() {
        fun supportUpdateDataRv(listData:List<SimulatorModel>?){
            binding.loading.gone()
            listData?.let {
                var list = arrayListOf<SimulatorModel>()
                list.addAll(listData)
                if(listData.size >= 3 && simulatorType != SimulatorType.EXPLOSION){
                    list.add(3, SimulatorModel("Ads Native", 0, simulatorType))
                }
                collectionAdapter.setData(list)
            }
            binding.rvCollection.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val manager = LinearLayoutManager(
                        binding.root.context, LinearLayoutManager.VERTICAL, false
                    )
                    if (manager != null) {
                        firstPos = manager.findFirstVisibleItemPosition()
                        lastPos = manager.findLastVisibleItemPosition()
                        binding.btnPrev.setEnable(lastPos >= 6)
                        binding.btnNext.setEnable(firstPos <= collectionAdapter.itemCount - 7)
                    }
                }
            })
        }
        when (simulatorType) {
            SimulatorType.GUN -> {
                (if (!isFav) homeViewModel.gunCollection else homeViewModel.gunCollectionFav).observe(
                    viewLifecycleOwner
                ) {
                    if (it.getStatus() == StateData.DataStatus.SUCCESS) {
                        supportUpdateDataRv(it.getData())
                    }
                }
            }

            SimulatorType.SHOCK_TASER -> {
                (if (!isFav) homeViewModel.shockTaserCollection else homeViewModel.shockTaserCollectionFav).observe(
                    viewLifecycleOwner
                ){
                    if (it.getStatus() == StateData.DataStatus.SUCCESS) {
                        supportUpdateDataRv(it.getData())
                    }
                }
            }

            SimulatorType.LIGHT_SABER -> {
                (if (!isFav) homeViewModel.lightSaberCollection else homeViewModel.lightSaberCollectionFav).observe(
                    viewLifecycleOwner
                ){
                    if (it.getStatus() == StateData.DataStatus.SUCCESS) {
                        supportUpdateDataRv(it.getData())
                    }
                }
            }

            SimulatorType.EXPLOSION -> {
                (if (!isFav) homeViewModel.explosionCollection else homeViewModel.explosionCollectionFav).observe(
                    viewLifecycleOwner
                ){
                    if (it.getStatus() == StateData.DataStatus.SUCCESS) {
                        supportUpdateDataRv(it.getData())
                    }
                }
            }
        }
    }

    private fun ImageView.setEnable(isEnable: Boolean) {
        this.isEnabled = isEnable
        imageTintList = if (this.isEnabled) {
            null
        } else {
            ContextCompat.getColorStateList(context, R.color.text_hint_color)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        hideSystemUi()
    }
}
