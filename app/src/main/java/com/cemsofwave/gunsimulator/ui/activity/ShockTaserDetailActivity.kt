package com.cemsofwave.gunsimulator.ui.activity

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ColorFilter
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.bumptech.glide.Glide
import com.cem.admodule.ext.ConstAd.BANNER_KEY_PREVIEW
import com.cem.admodule.ext.ConstAd.FULL_KEY_DETAIL
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.BuildConfig
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.base.BasePreviewActivity
import com.cemsofwave.gunsimulator.data.model.ShockTaserModel
import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.databinding.ActivityShockTaserDetailBinding
import com.cemsofwave.gunsimulator.interfaces.MyFragmentCallback
import com.cemsofwave.gunsimulator.rcv.addItemDecoration
import com.cemsofwave.gunsimulator.ui.adapter.BackgroundAdapter
import com.cemsofwave.gunsimulator.ui.adapter.ColorAdapter
import com.cemsofwave.gunsimulator.ui.customview.ColorPickerView
import com.cemsofwave.gunsimulator.ui.dialog.BackgroundDialog
import com.cemsofwave.gunsimulator.ui.dialog.ChargeBatteryDialog
import com.cemsofwave.gunsimulator.ui.dialog.SettingDetailFragment
import com.cemsofwave.gunsimulator.utils.BACKGROUND_NAME
import com.cemsofwave.gunsimulator.utils.CLICK_FAVOURITE
import com.cemsofwave.gunsimulator.utils.CLICK_FULLSCREEN
import com.cemsofwave.gunsimulator.utils.CLICK_RELOAD
import com.cemsofwave.gunsimulator.utils.CLICK_SETTING
import com.cemsofwave.gunsimulator.utils.IS_FLASH_DEF
import com.cemsofwave.gunsimulator.utils.IS_SOUND_DEF
import com.cemsofwave.gunsimulator.utils.IS_VIBRATE_DEF
import com.cemsofwave.gunsimulator.utils.SELECT_BACKGROUND
import com.cemsofwave.gunsimulator.utils.CLICK_TASER
import com.cemsofwave.gunsimulator.utils.RotateTransformation
import com.cemsofwave.gunsimulator.utils.SABER_DETAIL
import com.cemsofwave.gunsimulator.utils.SimulatorType
import com.cemsofwave.gunsimulator.utils.TASER_DETAIL
import com.cemsofwave.gunsimulator.utils.TASER_DETAIL_SHOW
import com.cemsofwave.gunsimulator.utils.TASER_NAME
import com.cemsofwave.gunsimulator.viewmodel.ImageViewModel
import com.trinhbx.base.extension.getDataParcelable
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.model.StateData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShockTaserDetailActivity : BasePreviewActivity<ActivityShockTaserDetailBinding>(),
    AudioManager.OnAudioFocusChangeListener, MyFragmentCallback {

    private val imageViewModel by viewModels<ImageViewModel>()
    private val keyPath by lazy { KeyPath("**") }
    private val backgroundAdapter by lazy { BackgroundAdapter() }
    private val colorAdapter by lazy { ColorAdapter() }
    private val openTime = System.currentTimeMillis()
    private var mediaPlayer: MediaPlayer? = null
    private var shockTaserModel: ShockTaserModel? = null
    private var isVibrate = true
    private var isFlash = true
    private var isMute = false

    private val listBg by lazy {
        listOf(
            R.drawable.bg_taser_01,
            R.drawable.bg_taser_02,
            R.drawable.bg_taser_03,
            R.drawable.bg_taser_04,
            R.drawable.bg_taser_05
        )
    }

    private val listColor by lazy {
        listOf(
            ContextCompat.getColor(baseContext, R.color.yellowFCF600),
            ContextCompat.getColor(baseContext, R.color.purpleBD00FF),
            ContextCompat.getColor(baseContext, R.color.blue251DFF),
            ContextCompat.getColor(baseContext, R.color.green41FB33),
            ContextCompat.getColor(baseContext, R.color.orangeFD6A00),
            ContextCompat.getColor(baseContext, R.color.redE90D00)
        )
    }

    override fun getData() {
        shockTaserModel = intent?.getDataParcelable(EXTRA_SHOCK_TASER_MODEL)
        this.lifecycleScope.launch {
            this@ShockTaserDetailActivity.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    imageViewModel.listBgBitmapFlow.collect {
                        observeListBgBitmap(it)
                    }
                }
                launch {
                    imageViewModel.backgroundBitmapFlow.collect {
                        observeBackgroundBitmap(it)
                    }
                }
                launch {
                    imageViewModel.thumbBitmapFlow.collect {
                        observeShockTaserBitmap(it)
                    }
                }
            }
        }
        shockTaserModel?.let { initDataSource(it) }
    }
    private fun initDataSource(shockTaserModel: ShockTaserModel){
        initSound(shockTaserModel)
        imageViewModel.resizeListBg(listBg)
        colorAdapter.setData(listColor)
        simulatorViewModel.checkSimulatorLike(shockTaserModel)
    }

    private fun observeShockTaserBitmap(bitmap: Bitmap?) {
        bitmap?.let { binding.imgThumb.setImageBitmap(it) }
    }

    private fun observeListBgBitmap(listBitmap: List<Bitmap>) {
        if (listBitmap.isNotEmpty()) {
            backgroundAdapter.setData(listBitmap)
        }
    }

    private fun observeBackgroundBitmap(bitmap: Bitmap?) {
        bitmap?.let {
            binding.imvBackground.setImageBitmap(it)
        }
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
        shockTaserModel?.let { initDataView(it) }
    }

    private fun initDataView(shockTaserModel: ShockTaserModel){
        shockTaserModel.let { shockTaser ->
            imageViewModel.loadThumbBitmapFromAsset(path = shockTaser.location)
        }
        initBackgroundAdapter()
        initColorAdapter()
        binding.colorPickerView.progress = simulatorViewModel.colorProgress
    }

    private fun initBackgroundAdapter() {
        imageViewModel.loadBackgroundBitmap(listBg[0])
        binding.rcvBackground.apply {
            adapter = backgroundAdapter
            addItemDecoration(marginHorizontal = 5, marginVertical = 0)
        }
    }
    private fun initColorAdapter() {
        binding.rcvColor.apply {
            adapter = colorAdapter
        }
    }

    override fun initOnClickListener() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            btnSetting.setOnSingleClickListener {
                CemAnalytics.logEventClickView(this@ShockTaserDetailActivity, screenName, CLICK_SETTING)
                val settingDetailFragment = SettingDetailFragment
                settingDetailFragment.show(this@ShockTaserDetailActivity,
                    simulatorViewModel.isVibrate.value?.getData() ?: IS_VIBRATE_DEF,
                    simulatorViewModel.isFlash.value?.getData() ?: IS_FLASH_DEF,
                    simulatorViewModel.isSound.value?.getData() ?: IS_SOUND_DEF,
                    SABER_DETAIL)
            }

            btnFav.setOnClickListener {
                eventClickFav(btnFav.isChecked)
                btnFav.isEnabled = false
                val params = HashMap<String, String>()
                params[TASER_NAME] = shockTaserModel?.name.toString()
                CemAnalytics.logEventAndParams(this@ShockTaserDetailActivity, screenName = TASER_DETAIL, eventName = CLICK_FAVOURITE, params = params)
                simulatorViewModel.likeSimulator(shockTaserModel, btnFav.isChecked) {
                    btnFav.isEnabled = true
                }
            }

            imgBackground.setOnClickListener{
//                layoutBackground.isGone = !layoutBackground.isGone
                val backgroundDialog = BackgroundDialog
                backgroundDialog.show(this@ShockTaserDetailActivity, screenName,
                    onPosListener = {pos ->
                        if (pos != -1) {
                            CemAnalytics.logEventClickView(this@ShockTaserDetailActivity, screenName, CLICK_RELOAD)
                            eventClickItemBg(TASER_DETAIL)
                            val params = HashMap<String, String>()
                            params[BACKGROUND_NAME] = listBg[pos].toString()
                            imageViewModel.loadBackgroundBitmap(listBg[pos])
                        }
                        loadAds()
                    })
            }

            imgColor.setOnClickListener{
                layoutColor.isGone = !layoutColor.isGone
            }

            btnFullscreen.setOnCheckedChangeListener { _, b ->
                val params = HashMap<String, String>()
                params[TASER_NAME] = shockTaserModel?.name.toString()
                CemAnalytics.logEventAndParams(this@ShockTaserDetailActivity, screenName = TASER_DETAIL, eventName = CLICK_FULLSCREEN, params = params)
                scalePreviewOffset = 0.2f
                setFullScreen(
                    b,
                    listOf(previewLayout, effectView),
                    listOf(imgBackground, layoutBackground, colorPickerView),
                    listOf(btnBack, btnSetting, btnFav),
                    null,
                    null
                )
            }

            root.setOnTouchListener { _, event ->
                observeGestureScreen(event)
                true
            }

            colorPickerView.colorChangeListener = object : ColorPickerView.OnColorChangeListener {
                override fun onColorChangeListener(color: Int, progress: Int) {
                    changeShockTaserEffect(color)
                    simulatorViewModel.colorProgress = progress
                }

                override fun onStartTrackingTouch() {
                    eventClickColorBar(TASER_DETAIL)
                }
            }

            colorAdapter.onItemClickListener = { color, position ->
                changeShockTaserEffect(color)
                eventClickColorBar(TASER_DETAIL)
            }

            batteryEffect.addAnimatorUpdateListener {
                if(it.animatedFraction >= 1f){
                    openChargeBatteryDialog()
                }
            }
        }
    }
    private fun handleActionUp(){
        binding.apply {
            shockView.apply {
                isGone = true
                pauseAnimation()
            }
            batteryEffect.pauseAnimation()
            simulatorViewModel.batteryProcess = batteryEffect.progress
            effectView.apply {
                pauseAnimation()
                isGone = true
            }
            cancelVibrator()
            simulatorViewModel.toggleFlashLight(on = false)
            if (isPlaying()) mediaPlayer?.pause()
        }
    }
    private fun observeGestureScreen(event: MotionEvent) {
        binding.apply {
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    val params = HashMap<String, String>()
                    params[TASER_NAME] = shockTaserModel?.name.toString()
                    CemAnalytics.logEventAndParams(this@ShockTaserDetailActivity, screenName = TASER_DETAIL, eventName = CLICK_TASER, params = params)
                    if (batteryEffect.progress < 0.99f) {
                        shockView.apply {
                            repeatCount = ValueAnimator.INFINITE
                            playAnimation()
                            isVisible = true
                        }
                        batteryEffect.apply {
                            progress = simulatorViewModel.batteryProcess
                            speed = if(BuildConfig.DEBUG){
                                10f
                            }else{
                                1.9f
                            }
                            setMinAndMaxProgress(simulatorViewModel.batteryProcess, 1f)
                            playAnimation()
                        }

                        effectView.apply {
                            repeatCount = ValueAnimator.INFINITE
                            playAnimation()
                            isVisible = true
                        }
                        initVibrator(isVibrate)
                        simulatorViewModel.toggleFlashLight(on = isFlash)
                        when {
                            !isMute && !isPlaying() -> mediaPlayer?.start()
                            isMute && isPlaying() -> mediaPlayer?.pause()
                        }
                    } else {
                        shockView.isGone = true
                        effectView.isGone = true
                        openChargeBatteryDialog()
                    }
                }

                MotionEvent.ACTION_UP -> {
                    handleActionUp()
                }
                MotionEvent.ACTION_CANCEL->{
                    lifecycleScope.launch(Dispatchers.IO) {
                        delay(100)
                        runOnUiThread {
                            handleActionUp()
                        }
                    }
                }
            }
        }
    }

    private fun changeShockTaserEffect(color: Int) {
        val filter = SimpleColorFilter(color)
        val callback: LottieValueCallback<ColorFilter> = LottieValueCallback(filter)
        binding.effectView.addValueCallback(
            keyPath,
            LottieProperty.COLOR_FILTER,
            callback
        )
        binding.shockView.addValueCallback(
            keyPath,
            LottieProperty.COLOR_FILTER,
            callback
        )
    }

    private fun openChargeBatteryDialog() {
        if (binding.batteryEffect.progress >= 0.99f) {
//            ChargeBatteryDialog.show(this,screenName, SimulatorType.SHOCK_TASER,
//                onChargeBatteryListener = {
//                    isCharge ->
//                if (isCharge) {
//                    simulatorViewModel.batteryProcess = 0f
//                    binding.batteryEffect.setMinAndMaxProgress(0f, 1f)
//                    binding.batteryEffect.progress = 0f
//                }
//                loadAds()
//            }, onItemRequestListener = {
//                    val shockTaserModel = it as ShockTaserModel
//                    initDataSource(shockTaserModel)
//                    initDataView(shockTaserModel)
//                    loadAds()
//            })
//            val reloadBulletDialog = ChargeBatteryDialog.newInstance()
//            reloadBulletDialog.setOnButtonClickListener(this)
//            this@ShockTaserDetailActivity.addFragment(
//                binding.fragmentHost,
//                reloadBulletDialog
//            )
            AddDetailActivity.start(this@ShockTaserDetailActivity, "CHARGE", screenName, this)
            cancelVibrator()
            simulatorViewModel.toggleFlashLight(on = false)
            if (isPlaying()) {
                mediaPlayer?.pause()
                mediaPlayer?.seekTo(0)
            }
        }
    }

    private fun initSound(shockTaserModel: ShockTaserModel) {
        mediaPlayer = MediaPlayer().apply {
            shockTaserModel.sound?.let {
                val descriptor = assets.openFd(it)
                setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
                descriptor.close()
                prepare()
                isLooping = true
            }
        }
    }

    private fun isPlaying(): Boolean {
        return try {
            if (mediaPlayer == null) {
                false
            } else mediaPlayer!!.isPlaying
        } catch (e: IllegalStateException) {
            false
        }
    }

    override fun onDestroy() {
        mediaPlayerRelease()
        super.onDestroy()
        screenName?.let { cemAdManager.removeBannerLoaded(it) }
    }

    private fun mediaPlayerRelease() {
        try {
            if (isPlaying()) {
                mediaPlayer?.stop()
                mediaPlayer?.reset()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onAudioFocusChange(p0: Int) {
    }

    override fun binding(): ActivityShockTaserDetailBinding {
        return ActivityShockTaserDetailBinding.inflate(layoutInflater)
    }

    override fun provideScreenName(): String {
        return TASER_DETAIL_SHOW
    }

    override fun registerObserve() {
        simulatorViewModel.isVibrate.observe(this) {
            if (it.getStatus() == StateData.DataStatus.SUCCESS) {
                isVibrate = it.getData() == true
            }
        }
        simulatorViewModel.isFlash.observe(this) {
            if (it.getStatus() == StateData.DataStatus.SUCCESS) {
                isFlash = it.getData() == true
            }
        }
        simulatorViewModel.isSound.observe(this) {
            if (it.getStatus() == StateData.DataStatus.SUCCESS) {
                isMute = it.getData() != true
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

    override fun onPause() {
        super.onPause()
        initVibrator(isVibrate = false)
        simulatorViewModel.toggleFlashLight(on = false)
        if (isPlaying()) mediaPlayer?.pause()
        binding.shockView.pauseAnimation()
        binding.effectView.pauseAnimation()
    }

    companion object {

        private const val EXTRA_SHOCK_TASER_MODEL = "EXTRA_SHOCK_TASER_MODEL"

        @JvmStatic
        fun start(
            activity: FragmentActivity?,
            shockTaserModel: ShockTaserModel,
            fromScreenName: String?
        ) {
            if (activity == null || activity.supportFragmentManager.isStateSaved) return
            CemAdManager.getInstance(activity).showInterstitialReloadCallback(activity = activity, configKey =  FULL_KEY_DETAIL, callback = {
                val intent = Intent(activity, ShockTaserDetailActivity::class.java).apply {
                    putExtra(EXTRA_SHOCK_TASER_MODEL, shockTaserModel)
                }
                activity.startActivity(intent)
            })
        }
    }

    override fun onBulletsRequestListener(isReload: Boolean) {
        if (isReload) {
                    simulatorViewModel.batteryProcess = 0f
                    binding.batteryEffect.setMinAndMaxProgress(0f, 1f)
                    binding.batteryEffect.progress = 0f
                }
                loadAds()
    }

    override fun onGunRequestListener(simulatorModel: SimulatorModel) {

    }
}