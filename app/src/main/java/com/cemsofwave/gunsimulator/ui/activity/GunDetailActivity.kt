package com.cemsofwave.gunsimulator.ui.activity

import UiState
import android.animation.Animator
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.cem.admodule.data.RewardAdItem
import com.cem.admodule.ext.ConstAd.BANNER_KEY_PREVIEW
import com.cem.admodule.ext.ConstAd.FULL_KEY_DETAIL
import com.cem.admodule.ext.ConstAd.REWARD_ADS
import com.cem.admodule.inter.CemRewardListener
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.base.BasePreviewActivity
import com.cemsofwave.gunsimulator.data.model.GunModel
import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.data.model.SkinManager
import com.cemsofwave.gunsimulator.databinding.ActivityGunDetailBinding
import com.cemsofwave.gunsimulator.databinding.ItemBulletBinding
import com.cemsofwave.gunsimulator.databinding.ItemGunSkinBinding
import com.cemsofwave.gunsimulator.interfaces.MyFragmentCallback
import com.cemsofwave.gunsimulator.rcv.addItemDecoration
import com.cemsofwave.gunsimulator.ui.adapter.BackgroundAdapter
import com.cemsofwave.gunsimulator.ui.customview.GunShootControllerView
import com.cemsofwave.gunsimulator.ui.customview.SeekbarVertical
import com.cemsofwave.gunsimulator.ui.dialog.BackgroundDialog
import com.cemsofwave.gunsimulator.ui.dialog.SettingDetailFragment
import com.cemsofwave.gunsimulator.ui.dialog.SkinDialog
import com.cemsofwave.gunsimulator.ui.dialog.WatchAdsDialog
import com.cemsofwave.gunsimulator.ui.fragment.GunInfoFragment
import com.cemsofwave.gunsimulator.utils.CLICK_AUTO
import com.cemsofwave.gunsimulator.utils.CLICK_BRUSH
import com.cemsofwave.gunsimulator.utils.CLICK_PREVIOUS
import com.cemsofwave.gunsimulator.utils.CLICK_RELOAD
import com.cemsofwave.gunsimulator.utils.CLICK_SETTING
import com.cemsofwave.gunsimulator.utils.CLICK_SHAKE
import com.cemsofwave.gunsimulator.utils.CLICK_SINGLE
import com.cemsofwave.gunsimulator.utils.CLICK_ZOOM
import com.cemsofwave.gunsimulator.utils.CLICK_GUN
import com.cemsofwave.gunsimulator.utils.GUN_DETAIL
import com.cemsofwave.gunsimulator.utils.GUN_NAME
import com.cemsofwave.gunsimulator.utils.GunShootMode
import com.cemsofwave.gunsimulator.utils.GUN_DETAIL_SHOW
import com.cemsofwave.gunsimulator.utils.GUN_INFO
import com.cemsofwave.gunsimulator.utils.MODE
import com.cemsofwave.gunsimulator.utils.SELECT_MODE
import com.cemsofwave.gunsimulator.utils.SELECT_SKIN
import com.cemsofwave.gunsimulator.utils.SKIN_ID
import com.cemsofwave.gunsimulator.viewmodel.HomeViewModel
import com.trinhbx.base.extension.dpToPx
import com.trinhbx.base.extension.getDataParcelable
import com.trinhbx.base.extension.gone
import com.trinhbx.base.extension.invisible
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.extension.visible
import com.trinhbx.base.model.StateData
import com.trinhbx.base.ui.BaseAdapter
import com.trinhbx.base.utils.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val ITEM_SKIN_OFFSET = 2
private const val WIDTH_PREVIEW = 1920f
private const val HEIGHT_PREVIEW = 1080f
private const val BULLET_MAX_OFFSET = 20
private const val GUN_6_BARREL = "MINIGUN"

@AndroidEntryPoint
class GunDetailActivity : BasePreviewActivity<ActivityGunDetailBinding>(), MyFragmentCallback {
    companion object {
        private const val EXTRA_GUN_MODEL = "EXTRA_GUN_MODEL"

        @JvmStatic
        fun start(activity: FragmentActivity?, gunModel: GunModel, fromScreenName: String?) {
            if (activity == null || activity.supportFragmentManager.isStateSaved) return
            CemAdManager.getInstance(activity)
                .showInterstitialReloadCallback(activity = activity, configKey =  FULL_KEY_DETAIL, callback = {
                    val intent = Intent(activity, GunDetailActivity::class.java).apply {
                        putExtra(EXTRA_GUN_MODEL, gunModel)
                    }
                    activity.startActivity(intent)
                })
        }
    }

    private val homeViewModel by viewModels<HomeViewModel>()
    private var gunModel: GunModel? = null

    private val bulletAdapter = BulletAdapter()

    private var pos = 0
    private var firstPosSkin = 0
    private var lastPosSkin = 0
    private var bulletNumber = 0

    private val listBg by lazy {
        listOf(
            R.drawable.bg_gun,
            R.drawable.bg_first,
            R.drawable.bg_second,
            R.drawable.bg_third,
            R.drawable.bg_four,
            R.drawable.bg_five,
            R.drawable.bg_six
        )
    }

    private val skinAdapter = SkinAdapter { pos, skinName ->
        val params = HashMap<String, String>()
        params[SKIN_ID] = skinName.nameSkin
        CemAnalytics.logEventAndParams(
            this@GunDetailActivity,
            screenName = screenName,
            eventName = SELECT_SKIN,
            params = params
        )
        this.pos = pos
        if (skinName.lock) {
            if (cemAdManager.isEnableRewardAds) {
                val watchAdsDialog = WatchAdsDialog
                watchAdsDialog.show(
                    this@GunDetailActivity,
                    gunModel,
                    pos,
                    screenName,
                    onAdsRequestListener = {
                        if (it) {
                            CemAdManager.getInstance(this@GunDetailActivity).showRewardByPlacementReload(
                                activity = this@GunDetailActivity,
                                configKey = REWARD_ADS,
                                listener = object : CemRewardListener() {
                                    override fun onRewardAdded(rewardAdItem: RewardAdItem?) {
                                        val skinManager =
                                            gunModel?.let { it1 ->
                                                SkinManager(it1.name, it1.listSkin[pos], pos, false)
                                            }
                                        skinManager?.let { it1 -> homeViewModel.insertSkin(it1) }
                                        binding.gunViewStatic.setAnimation(skinName.nameSkin)
                                    }

                                    override fun onRewardFail(error: String?) {
                                        Toast.makeText(this@GunDetailActivity, error, Toast.LENGTH_SHORT).show()
                                    }
                                })
                        }
                    })
            } else {
                binding.gunViewStatic.setAnimation(skinName.nameSkin)
            }
        } else {
            binding.gunViewStatic.setAnimation(skinName.nameSkin)
        }
    }

    private val backgroundAdapter by lazy { BackgroundAdapter() }

    override fun getData() {
        super.getData()
        homeViewModel.getAllSkin()
        gunModel = intent?.getDataParcelable(EXTRA_GUN_MODEL)
        gunModel?.let {
            binding.bulletsRequestView.setAnimation(it.reloadAnimFile)
            loadDataItem(it)
        }

        simulatorViewModel.resizeListBg(listBg)

        this.lifecycleScope.launch {
            this@GunDetailActivity.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.recordAllSkinFlow.collect {
                        when (it) {
                            is UiState.Loading -> {

                            }

                            is UiState.Success -> {
                                val list = mutableListOf<SkinManager?>()
                                val skinList = arrayListOf<SkinManager>()
                                list.addAll(it.data)
                                gunModel?.apply {
                                    listSkin.forEachIndexed { index, item ->
                                        if (cemAdManager.isEnableRewardAds) {
                                            if(index >= 2){
                                                val isDuplicate = list.any { it?.nameSkin == item }
                                                skinList.add(SkinManager(this.name, item, index, !isDuplicate))
                                            }else{
                                                skinList.add(SkinManager(this.name, item, index, false))
                                            }
                                        } else {
                                            skinList.add(SkinManager(this.name, item, index, false))
                                        }
                                    }
                                    skinAdapter.updateAll(skinList.subList(0, 2))
                                }
                            }

                            else -> {
                                println("Fail to get record")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateGunModel(gunModel: GunModel) {
        this.gunModel = gunModel
        loadDataItem(gunModel)
    }

    private fun loadDataItem(gunModel: GunModel) {
        binding.coverView.gunModel = gunModel
        gunModel.let {
            val w = it.headX / WIDTH_PREVIEW
            val h = it.headY / HEIGHT_PREVIEW
            binding.lineFireHor.setGuidelinePercent(w)
            binding.lineFireVer.setGuidelinePercent(h)
            binding.fireView.setAnimation(it.fireFile)
            (binding.fireView.layoutParams as? ConstraintLayout.LayoutParams)?.marginEnd =
                -dpToPx(16)
            binding.bulletShellView.setAnimation(it.bulletShell)
            simulatorViewModel.checkSimulatorLike(it)
            if (it.bulletCount <= BULLET_MAX_OFFSET) {
                binding.rvBullet.visible()
                bulletAdapter.reload(it.bulletCount, it.bulletType)
                binding.tvBulletCount.gone()
                binding.imvBullet.gone()
            } else {
                binding.rvBullet.gone()
                binding.tvBulletCount.visible()
                binding.imvBullet.visible()
                Glide.with(binding.root.context)
                    .asBitmap()
                    .load(Uri.parse(it.bulletType))
                    .into(binding.imvBullet)
                handleBulletCountText(it.bulletCount)
            }
        }
    }

    override fun provideScreenName(): String {
        return GUN_DETAIL_SHOW
    }

    override fun binding(): ActivityGunDetailBinding {
        return ActivityGunDetailBinding.inflate(layoutInflater)
    }

    override fun loadAds() {
        super.loadAds()
        cemAdManager.loadBannerAndShowByActivity(
            activity = this,
            viewGroup = binding.bannerBottom.bannerLayout,
            configKey = BANNER_KEY_PREVIEW,
            nameScreen = screenName
        )
    }

    override fun initView() {
        gunModel?.let { initViewGeneral(it) }
    }

    private fun initViewGeneral(gunModel: GunModel) {
        binding.apply {
            previewLayout.scaleX = 1.5f
            previewLayout.scaleY = 1.5f
//            btnPrevSkin.setEnable(false)
//            btnNextSkin.setEnable(false)
            rvBullet.adapter = bulletAdapter
            rvBullet.addItemDecoration(SpacesItemDecoration(dpToPx(1), 0))
            rvSkin.adapter = skinAdapter
            rvSkin.addItemDecoration(0, 0)
            rvSkin.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val manager = recyclerView.layoutManager as? LinearLayoutManager
                    if (manager != null) {
                        firstPosSkin = manager.findFirstVisibleItemPosition()
                        lastPosSkin = manager.findLastVisibleItemPosition()
//                        btnPrevSkin.setEnable(firstPosSkin >= 1)
//                        btnNextSkin.setEnable(lastPosSkin <= skinAdapter.itemCount - ITEM_SKIN_OFFSET)
                    }
                }
            })
            initBackgroundAdapter()

            gunModel.listSkin.let {
                binding.gunViewStatic.setAnimation(it.getOrNull(0))
            }
        }
    }

    override fun initOnClickListener() {
        binding.apply {

            btnBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            btnSetting.setOnSingleClickListener {
                CemAnalytics.logEventClickView(this@GunDetailActivity, screenName, CLICK_SETTING)
                val settingDetailFragment = SettingDetailFragment
                settingDetailFragment.show(
                    this@GunDetailActivity,
                    coverView.isVibrate,
                    coverView.isFlash,
                    coverView.isSound,
                    screenName
                )
            }

            btnShowInfo.setOnSingleClickListener {
                CemAnalytics.logEventClickView(this@GunDetailActivity, screenName, GUN_INFO)
                GunInfoFragment.show(this@GunDetailActivity, gunModel, pos, screenName)
            }

            btnFav.setOnClickListener {
                eventClickFav(btnFav.isChecked)
                btnFav.isEnabled = false
                simulatorViewModel.likeSimulator(gunModel, btnFav.isChecked) {
                    btnFav.isEnabled = true
                }
            }

            btnFullscreen.setOnCheckedChangeListener { _, b ->
                setFullScreen(
                    b,
                    listOf(previewLayout),
                    listOf(imvBgLabel, rcvBackground),
                    listOf(btnBack, btnShowInfo, btnSetting, btnFav),
                    listOf(listSkinLayout),
                    listOf(bulletLayout, modeSelectorLayout)
                )
                if (gunModel?.isPistol == false) {
                    (binding.previewLayout.layoutParams as? ConstraintLayout.LayoutParams)?.let {
                        var bias = if (b) {
                            0.9f
                        } else {
                            0.6f
                        }
                        it.verticalBias = bias
                        binding.previewLayout.requestLayout()
                    }
                }
            }

            seekbar.listener = object : SeekbarVertical.Listener {
                override fun onProgressChanged(value: Int) {
                    val offset = if (btnFullscreen.isChecked) {
                        1.5f + scalePreviewOffset
                    } else {
                        1.5f
                    }
                    val scale = offset + (value / 100f) / 2
                    previewLayout.scaleX = scale
                    previewLayout.scaleY = scale
                }

                override fun onStopTrackingTouch() {
                    CemAnalytics.logEventClickView(this@GunDetailActivity, screenName, CLICK_ZOOM)
                }
            }

            btnPrevSkin.setOnClickListener {
                CemAnalytics.logEventClickView(this@GunDetailActivity, screenName, CLICK_PREVIOUS)
                var pos = firstPosSkin - ITEM_SKIN_OFFSET
                if (pos <= 0) pos = 0
                rvSkin.smoothScrollToPosition(pos)
            }
            btnNextSkin.setOnClickListener {
//                CemAnalytics.logEventClickView(this@GunDetailActivity, screenName, CLICK_PREVIOUS)
//                rvSkin.adapter?.itemCount?.let {
//                    var pos = lastPosSkin + ITEM_SKIN_OFFSET
//                    if (pos >= it) pos = it
//                    rvSkin.smoothScrollToPosition(pos)
//                }
                val skinDialog = SkinDialog
                skinDialog.show(this@GunDetailActivity, screenName, gunModel, postLast = pos,
                    onSkinListener = {position, skin ->
                        pos = position
                        skinAdapter.setItemSelect(skin, position)
                        binding.gunViewStatic.setAnimation(skin.nameSkin)
                    })
            }

            imvBgLabel.setOnClickListener {
                val backgroundDialog = BackgroundDialog
                backgroundDialog.show(this@GunDetailActivity, screenName,
                    onPosListener = { pos ->
                        if (pos != -1) {
                            CemAnalytics.logEventClickView(
                                this@GunDetailActivity,
                                screenName,
                                CLICK_RELOAD
                            )

                        }
                        loadAds()
                    })
//                rcvBackground.isGone = !rcvBackground.isGone
            }

            btnSingleShoot.setOnClickListener {
                val params = HashMap<String, String>()
                params[MODE] = CLICK_SINGLE
                CemAnalytics.logEventAndParams(
                    this@GunDetailActivity,
                    screenName = screenName,
                    eventName = SELECT_MODE,
                    params = params
                )
                btnBrushMode.isChecked = false
                btnAutoShoot.isChecked = false
                btnShake.isChecked = false
                coverView.gunShootMode = GunShootMode.SINGLE
            }

            btnBrushMode.setOnClickListener {
                val params = HashMap<String, String>()
                params[MODE] = CLICK_BRUSH
                CemAnalytics.logEventAndParams(
                    this@GunDetailActivity,
                    screenName = screenName,
                    eventName = SELECT_MODE,
                    params = params
                )
                btnSingleShoot.isChecked = false
                btnAutoShoot.isChecked = false
                btnShake.isChecked = false
                coverView.gunShootMode = GunShootMode.BRUSH
            }

            btnAutoShoot.setOnClickListener {
                val params = HashMap<String, String>()
                params[MODE] = CLICK_AUTO
                CemAnalytics.logEventAndParams(
                    this@GunDetailActivity,
                    screenName = screenName,
                    eventName = SELECT_MODE,
                    params = params
                )
                btnSingleShoot.isChecked = false
                btnBrushMode.isChecked = false
                btnShake.isChecked = false
                coverView.gunShootMode = GunShootMode.AUTO
            }

            btnShake.setOnClickListener {
                val params = HashMap<String, String>()
                params[MODE] = CLICK_SHAKE
                CemAnalytics.logEventAndParams(
                    this@GunDetailActivity,
                    screenName = screenName,
                    eventName = SELECT_MODE,
                    params = params
                )
                btnSingleShoot.isChecked = false
                btnBrushMode.isChecked = false
                btnAutoShoot.isChecked = false
                coverView.gunShootMode = GunShootMode.SHAKE
            }
            viewCoverTop.setOnClickListener { }
        }
    }

    override fun onResume() {
        super.onResume()
        gunModel?.let { initializeGunView(it) }
        binding.coverView.resume()
        listenStatusButton()
    }

    override fun onPause() {
        super.onPause()
        binding.coverView.pause()
        binding.coverView.listener = null
    }

    private fun initializeGunView(gunModel: GunModel) {
        binding.fireView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                binding.fireView.progress = 0f
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })
        (binding.previewLayout.layoutParams as? ConstraintLayout.LayoutParams)?.let {
            if (gunModel.isPistol) {
                it.topMargin = 650
            }
        }
        binding.coverView.listener = object : GunShootControllerView.Listener {
            override fun onShoot(shootMode: GunShootMode, durShoot: Long, speedShoot: Float) {
                val isGun6Barrel = gunModel.name == GUN_6_BARREL
                val params = HashMap<String, String>()
                params[GUN_NAME] = gunModel.name.toString()
                CemAnalytics.logEventAndParams(
                    this@GunDetailActivity,
                    screenName = GUN_DETAIL,
                    eventName = CLICK_GUN,
                    params = params
                )
                binding.gunViewStatic.shakeGun(durShoot)
                binding.fireView.apply {
                    this@apply.speed = speedShoot
                    this@apply.cancelAnimation()
                    this@apply.playAnimation()
                    this@apply.shakeGun(durShoot)
                    this@apply.progress = 0.4f
                    if (isGun6Barrel) {
                        this@apply.speed = 5f
                    } else {
                        this@apply.speed = speedShoot
                    }
                }
                binding.smokeView.apply {
                    this@apply.pauseAnimation()
                    this@apply.playAnimation()
                    if (isGun6Barrel && shootMode == GunShootMode.AUTO) {
                        this@apply.speed = 5f
                        this@apply.progress = 0.4f
                    } else {
                        this@apply.speed = speedShoot
                    }
                }
                binding.bulletShellView.apply {
                    this@apply.pauseAnimation()
                    this@apply.playAnimation()
                    if (isGun6Barrel) {
                        this@apply.speed = 5f
                        this@apply.progress = 0.4f
                    } else {
                        this@apply.speed = speedShoot
                    }
                }
                bulletAdapter.shoot()
            }

            override fun onShot(remainBullet: Int) {
                if (remainBullet <= BULLET_MAX_OFFSET) {
                    if (remainBullet == BULLET_MAX_OFFSET) {
                        bulletAdapter.reload(BULLET_MAX_OFFSET, gunModel.bulletType)
                    }
                    binding.rvBullet.visible()
                    binding.tvBulletCount.gone()
                    binding.imvBullet.gone()
                } else {
                    binding.rvBullet.gone()
                    binding.tvBulletCount.visible()
                    binding.imvBullet.visible()
                    handleBulletCountText(remainBullet)
                }
            }

            override fun onBulletReloaded(bulletCount: Int) {
                binding.coverView.canShoot = false
                openReloadBulletsDialog(gunModel.bulletCount)
            }

            override fun onRequestBullets() {
                binding.bulletsRequestView.visible()
            }

            override fun onShooting(isShooting: Boolean, shootMode: GunShootMode) {
                binding.viewCoverTop.isVisible = isShooting
            }
        }
    }

    override fun registerObserve() {
        listenStatusButton()
        this.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    simulatorViewModel.listBgBitmapFlow.collect {
                        if (it.isNotEmpty()) {
                            backgroundAdapter.setData(it)
                            binding.viewCoverTop.gone()
                            binding.viewCoverTop.setBackgroundColor(Color.TRANSPARENT)
                            binding.loading.gone()
                        }

                    }
                }
                launch {
                    simulatorViewModel.backgroundBitmapFlow.collect {
                        if (it != null) {
                            binding.imvBackground.setImageBitmap(it)
                        }
                    }
                }
            }
        }
    }

    private fun listenStatusButton() {
        simulatorViewModel.isVibrate.observe(this) {
            if (it.getStatus() == StateData.DataStatus.SUCCESS) {
                binding.coverView.isVibrate = it.getData() ?: binding.coverView.isVibrate
            }
        }
        simulatorViewModel.isFlash.observe(this) {
            if (it.getStatus() == StateData.DataStatus.SUCCESS) {
                binding.coverView.isFlash = it.getData() ?: binding.coverView.isFlash
            }
        }
        simulatorViewModel.isSound.observe(this) {
            if (it.getStatus() == StateData.DataStatus.SUCCESS) {
                binding.coverView.isSound = it.getData() ?: binding.coverView.isSound
            }
        }
        simulatorViewModel.isSimulatorLiked.observe(this) {
            if (it.getStatus() == StateData.DataStatus.SUCCESS) {
                it.getData()?.let { isFav ->
                    binding.btnFav.isChecked = isFav
                }
            }
        }
    }

    private fun initBackgroundAdapter() {
        backgroundAdapter.onItemClickListener = { _, position ->
            eventClickItemBg(GUN_DETAIL)
            simulatorViewModel.changeBackground(position)
        }
        simulatorViewModel.changeBackground(0)
        binding.rcvBackground.apply {
            adapter = backgroundAdapter
            addItemDecoration(marginHorizontal = 5, marginVertical = 0)
        }
    }

    private fun handleBulletCountText(bulletCount: Int) {
        val text = "${bulletCount}X "
        binding.tvBulletCount.text = text
    }

    private fun openReloadBulletsDialog(bulletCount: Int) {
//        val reloadBulletDialog = ReloadBulletDialog.newInstance()
//        reloadBulletDialog.setOnButtonClickListener(this)
//        this@GunDetailActivity.addFragment(
//            binding.fragmentHost,
//            reloadBulletDialog
//        )

        AddDetailActivity.start(this@GunDetailActivity, "RELOAD_BULLET", screenName, this)
        bulletNumber = bulletCount

//        val reloadBulletDialog = ReloadBulletDialog
//
//        reloadBulletDialog.show(this, screenName,
//            onBulletsRequestListener = { isReload ->
//                if (isReload) {
//                    CemAnalytics.logEventClickView(this@GunDetailActivity, screenName, CLICK_RELOAD)
//                    binding.coverView.playSoundReload()
//                    binding.coverView.canShoot = true
//                    binding.bulletsRequestView.invisible()
//                    binding.rvBullet.gone()
//                    binding.imvBullet.visible()
//                    binding.tvBulletCount.visible()
//                    handleBulletCountText(bulletCount)
//                    bulletAdapter.reload(bulletCount, null)
//                }
//                loadAds()
//            }, onGunRequestListener = {
//                binding.coverView.stopMedia()
//                val gunModel = it as GunModel
//                updateGunModel(gunModel)
//                initViewGeneral(gunModel)
//                initializeGunView(gunModel)
//                binding.coverView.canShoot = true
//                binding.bulletsRequestView.invisible()
//                bulletAdapter.reload(gunModel.bulletCount, gunModel.bulletType)
//                binding.coverView.resume()
//                loadAds()
//            })
        cancelVibrator()
        simulatorViewModel.toggleFlashLight(on = false)
    }

    override fun onBulletsRequestListener(isReload: Boolean) {
        if (isReload) {
                    CemAnalytics.logEventClickView(this@GunDetailActivity, screenName, CLICK_RELOAD)
                    binding.coverView.playSoundReload()
                    binding.coverView.canShoot = true
                    binding.bulletsRequestView.invisible()
                    binding.rvBullet.gone()
                    binding.imvBullet.visible()
                    binding.tvBulletCount.visible()
                    handleBulletCountText(bulletNumber)
                    bulletAdapter.reload(bulletNumber, null)
                }
                loadAds()
    }

    override fun onGunRequestListener(simulatorModel: SimulatorModel) {

    }

    override fun onDestroy() {
        super.onDestroy()
        screenName?.let { cemAdManager.removeBannerLoaded(it) }
    }
}

private class BulletAdapter : BaseAdapter<ItemBulletBinding, Boolean>() {
    private var lastShoot = 0
    private var pathBullet: String? = null
    private var bulletBitmap: Bitmap? = null
    override fun binding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean,
        viewType: Int
    ): ItemBulletBinding {
        return ItemBulletBinding.inflate(inflater, parent, attachToParent)
    }

    override fun onBindData(binding: ItemBulletBinding, position: Int) {
        data.getOrNull(position)?.let { itemData ->
            if (bulletBitmap == null) {
                Glide.with(binding.root.context)
                    .asBitmap()
                    .load(Uri.parse(pathBullet))
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            bulletBitmap = resource
                            binding.root.setImageBitmap(bulletBitmap)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
            } else {
                binding.root.setImageBitmap(bulletBitmap)
            }

            if (itemData) {
                binding.root.invisible()
            } else {
                binding.root.visible()
            }
        }
    }

    fun reload(bulletCount: Int?, pathBullet: String?) {
        println("Load bullets: $bulletCount $pathBullet")
        if (bulletCount == null || bulletCount <= 0) return
        this.pathBullet = pathBullet ?: this.pathBullet
        val newList = mutableListOf<Boolean>()
        for (i in 1..bulletCount) {
            newList.add(false)
        }
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
        lastShoot = bulletCount
    }

    fun shoot() {
        if (lastShoot <= 0) return
        val pos = lastShoot - 1
        data.getOrNull(pos)?.let {
            data[pos] = true
            notifyItemChanged(pos)
            lastShoot -= 1
        }
    }
}

private fun View.shakeGun(dur: Long, onEndAnimate: ((View) -> Unit)? = null) {
    val set = AnimationSet(true)
    val animRotate: Animation =
        RotateAnimation(
            0f,
            1.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
    val fromX = 0f
    val toX = dpToPx(12f)
    val animTrans: Animation = TranslateAnimation(fromX, toX, 0f, 0f)
    set.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationRepeat(animation: Animation?) {}
        override fun onAnimationEnd(animation: Animation?) {
            clearAnimation()
            onEndAnimate?.invoke(this@shakeGun)
        }
    })
    set.addAnimation(animRotate)
    set.addAnimation(animTrans)
    set.duration = dur
    startAnimation(set)
}

private class SkinAdapter(private val onItemClicked: (Int, SkinManager) -> Unit) :
    BaseAdapter<ItemGunSkinBinding, SkinManager>() {
    private var itemSelected: SkinManager? = null
    override fun binding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean,
        viewType: Int
    ): ItemGunSkinBinding {
        return ItemGunSkinBinding.inflate(inflater, parent, attachToParent)
    }
    fun setItemSelect(skinManager: SkinManager, position: Int){
        val posLast = data.indexOfFirst { it == itemSelected }
        notifyItemChanged(posLast)
        itemSelected = skinManager
        notifyItemChanged(position)
    }

    override fun onBindData(binding: ItemGunSkinBinding, position: Int) {
        data.getOrNull(position)?.let { itemData ->
            binding.apply {
                preview.cancelAnimation()
                preview.setAnimation(itemData.nameSkin)
                if (itemData == itemSelected) {
                    imvSelected.visible()
                    if (itemData.lock) {
                        imvSelected.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context,
                                R.drawable.ic_video_ads
                            )
                        )
                    } else {
                        imvSelected.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context,
                                R.drawable.ic_tick
                            )
                        )
                    }
                } else {
                    if (itemData.lock) {
                        imvSelected.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context,
                                R.drawable.ic_video_ads
                            )
                        )
                    } else {
                        imvSelected.gone()
                    }
                }
                root.setOnClickListener {
                    onItemClicked.invoke(position, itemData)
                    if (itemData != itemSelected) {
                        val posLast = data.indexOfFirst { it == itemSelected }
                        notifyItemChanged(posLast)
                        itemSelected = itemData
                        notifyItemChanged(position)
                    }
                }
            }
        }
    }

    private var recyclerView: RecyclerView? = null
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.itemAnimator = null
        this.recyclerView = recyclerView
    }

    fun updateAll(newList: List<SkinManager>?) {
        if (newList == null) return
        itemSelected = newList.getOrNull(0)
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
        recyclerView?.setItemViewCacheSize(itemCount)
    }
}