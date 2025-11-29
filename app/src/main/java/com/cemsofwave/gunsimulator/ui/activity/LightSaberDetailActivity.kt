package com.cemsofwave.gunsimulator.ui.activity

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ColorFilter
import android.hardware.Sensor
import android.hardware.SensorManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
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
import com.cemsofwave.gunsimulator.base.utils.Timer
import com.cemsofwave.gunsimulator.data.model.LightSaberModel
import com.cemsofwave.gunsimulator.data.model.ShockTaserModel
import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.databinding.ActivityLightSaberDetailBinding
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
import com.cemsofwave.gunsimulator.utils.CLICK_SABER
import com.cemsofwave.gunsimulator.utils.RotateTransformation
import com.cemsofwave.gunsimulator.utils.SABER_DETAIL
import com.cemsofwave.gunsimulator.utils.SABER_DETAIL_SHOW
import com.cemsofwave.gunsimulator.utils.SABER_NAME
import com.cemsofwave.gunsimulator.utils.SELECT_BACKGROUND
import com.cemsofwave.gunsimulator.utils.SHAKE_SABER
import com.cemsofwave.gunsimulator.utils.ShakeDetector
import com.cemsofwave.gunsimulator.utils.TASER_DETAIL
import com.cemsofwave.gunsimulator.viewmodel.ImageViewModel
import com.trinhbx.base.extension.getDataParcelable
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.model.StateData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LightSaberDetailActivity : BasePreviewActivity<ActivityLightSaberDetailBinding>(),
    AudioManager.OnAudioFocusChangeListener, ShakeDetector.OnShakeListener, MyFragmentCallback {

    private val imageViewModel by viewModels<ImageViewModel>()
    private val keyPath by lazy { KeyPath("**") }
    private val backgroundAdapter by lazy { BackgroundAdapter() }
    private val colorAdapter by lazy { ColorAdapter() }
    private var mediaPlayerStart: MediaPlayer? = null
    private var mediaPlayer: MediaPlayer? = null
    private var mediaPlayerEnd: MediaPlayer? = null
    private var lightSaberModel: LightSaberModel? = null
    private val openTime = System.currentTimeMillis()
    private var batteryProcess = 0f
    private var isVibrate = true
    private var isFlash = true
    private var isMute = false

    private lateinit var sensorManager: SensorManager
    private lateinit var shakeDetector: ShakeDetector

    private val listBg by lazy {
        listOf(
            R.drawable.bg_light_01,
            R.drawable.bg_light_02,
            R.drawable.bg_light_03,
            R.drawable.bg_light_04,
            R.drawable.bg_light_05
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
        lightSaberModel = intent?.getDataParcelable(EXTRA_LIGHT_SABER_MODEL)
        this.lifecycleScope.launch {
            this@LightSaberDetailActivity.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
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
                        observeLightSaberBitmap(it)
                    }
                }
            }
        }
        lightSaberModel?.let { initDataSource(it) }
    }

    private fun initDataSource(lightSaberModel: LightSaberModel){
        initSound(lightSaberModel)
        imageViewModel.resizeListBg(listBg)
        colorAdapter.setData(listColor)
        simulatorViewModel.checkSimulatorLike(lightSaberModel)
    }

    private fun observeLightSaberBitmap(bitmap: Bitmap?) {
        bitmap?.let {
            binding.imgHilt.setImageBitmap(it)
        }
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
        lightSaberModel?.let { initDataView(it) }
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (accelerometer != null) {
            shakeDetector = ShakeDetector()
            shakeDetector.setOnShakeListener(this)

            sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_FASTEST)
        } else {
            Toast.makeText(this, getText(R.string.device_does_not_sp), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initDataView(lightSaberModel: LightSaberModel){
        initLightSaber(lightSaberModel)
        initBackgroundAdapter()
        initColorAdapter()
        binding.colorPickerView.progress = simulatorViewModel.colorProgress
    }

    private fun initLightSaber(lightSaberModel: LightSaberModel) {
        lightSaberModel?.let { lightSaber ->
            imageViewModel.loadThumbBitmapFromAsset(path = lightSaber.location)
            (binding.lightSaberView.layoutParams as? ConstraintLayout.LayoutParams)?.let {
                it.matchConstraintPercentWidth = 0.21f
            }
            binding.lightSaberView.setAnimation(
                   lightSaberModel?.listEffect?.getOrNull(0)
            )
            binding.lightSaberEffect.setAnimation(lightSaberModel?.listEffect?.getOrNull(2))
        }
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
                CemAnalytics.logEventClickView(this@LightSaberDetailActivity, screenName, CLICK_SETTING)
                val settingDetailFragment = SettingDetailFragment
                settingDetailFragment.show(this@LightSaberDetailActivity,
                    simulatorViewModel.isVibrate.value?.getData() ?: IS_VIBRATE_DEF,
                    simulatorViewModel.isFlash.value?.getData() ?: IS_FLASH_DEF,
                    simulatorViewModel.isSound.value?.getData() ?: IS_SOUND_DEF,
                    SABER_DETAIL)
            }

            btnFav.setOnClickListener {
                eventClickFav(btnFav.isChecked)
                val params = HashMap<String, String>()
                params[SABER_NAME] = lightSaberModel?.name.toString()
                CemAnalytics.logEventAndParams(this@LightSaberDetailActivity, screenName = SABER_DETAIL, eventName = CLICK_FAVOURITE, params = params)
                btnFav.isEnabled = false
                simulatorViewModel.likeSimulator(lightSaberModel, btnFav.isChecked) {
                    btnFav.isEnabled = true
                }
            }

            btnFullscreen.setOnCheckedChangeListener { _, b ->
                val params = HashMap<String, String>()
                params[SABER_NAME] = lightSaberModel?.name.toString()
                CemAnalytics.logEventAndParams(this@LightSaberDetailActivity, screenName = SABER_DETAIL, eventName = CLICK_FULLSCREEN, params = params)
                scalePreviewOffset = 0.1f
                binding.apply {
                    setFullScreen(
                        b,
                        listOf(previewLayout),
                        listOf(imgBackground, layoutBackground, colorPickerView),
                        listOf(btnBack, btnSetting, btnFav),
                        null,
                        null
                    )
                }
            }

            imgBackground.setOnClickListener{
//                layoutBackground.isGone = !layoutBackground.isGone
                val backgroundDialog = BackgroundDialog
                backgroundDialog.show(this@LightSaberDetailActivity, screenName,
                    onPosListener = {pos ->
                        if (pos != -1) {
                            CemAnalytics.logEventClickView(this@LightSaberDetailActivity, screenName, CLICK_RELOAD)
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

            colorAdapter.onItemClickListener = { color, position ->
                changeLightSaberEffect(color)
                eventClickColorBar(SABER_DETAIL)
            }

            root.setOnTouchListener { _, event ->
                val touchTime = System.currentTimeMillis()
                if (touchTime - openTime <= 1500L) false
                else {
                    observeGestureScreen(event)
                    true
                }
            }
            batteryEffect.addAnimatorUpdateListener {
                if(it.animatedFraction >= 1f){
                    openChargeBatteryDialog()
                }
            }
            colorPickerView.colorChangeListener = (object : ColorPickerView.OnColorChangeListener {
                override fun onColorChangeListener(color: Int, progress: Int) {
                    changeLightSaberEffect(color)
                    simulatorViewModel.colorProgress = progress
                }

                override fun onStartTrackingTouch() {
                    eventClickColorBar(SABER_DETAIL)
                }
            })
        }
    }
    private fun handleActionUp(){
        binding.apply {
            when {
                !isMute && !isPlayingEnd() -> mediaPlayerEnd?.start()

                isMute && isPlayingEnd() -> mediaPlayerEnd?.pause()
            }
            lightSaberView.apply {
                speed = -1f
                playAnimation()
            }
            lightSaberEffect.apply {
                isGone = true
                pauseAnimation()
            }
            batteryEffect.pauseAnimation()
            batteryProcess = batteryEffect.progress
            cancelVibrator()
            simulatorViewModel.toggleFlashLight(on = false)
            if (isPlaying()) mediaPlayer?.pause()
        }
    }
    private fun observeGestureScreen(event: MotionEvent) {
        binding.apply {
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    onLightSaber(CLICK_SABER)
                }

                MotionEvent.ACTION_MOVE -> {
                    when {
                        !isMute && !isPlaying() -> {
                            mediaPlayer?.start()
                        }

                        isMute && isPlaying() -> {
                            mediaPlayer?.pause()
                        }
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

    private fun changeLightSaberEffect(color: Int) {
        val filter = SimpleColorFilter(color)
        val callback: LottieValueCallback<ColorFilter> = LottieValueCallback(filter)
        binding.lightSaberView.addValueCallback(
            keyPath,
            LottieProperty.COLOR_FILTER,
            callback
        )
        binding.lightSaberEffect.addValueCallback(
            keyPath,
            LottieProperty.COLOR_FILTER,
            callback
        )
    }

    private fun openChargeBatteryDialog() {
        if (binding.batteryEffect.progress >= 0.99f) {
//            val reloadBulletDialog = ChargeBatteryDialog.newInstance()
//            reloadBulletDialog.setOnButtonClickListener(this)
//            this@LightSaberDetailActivity.addFragment(
//                binding.fragmentHost,
//                reloadBulletDialog
//            )
            AddDetailActivity.start(this@LightSaberDetailActivity, "CHARGE", screenName, this)
//            ChargeBatteryDialog.show(this,screenName,
//                onChargeBatteryListener = {
//                    isCharge ->
//                if (isCharge) {
//                    batteryProcess = 0f
//                    binding.batteryEffect.setMinAndMaxProgress(0f, 1f)
//                    binding.batteryEffect.progress = 0f
//                }
//                loadAds()
//            },
//                onItemRequestListener = {
//                    val lightSaberModel = it as LightSaberModel
//                    initDataSource(lightSaberModel)
//                    initDataView(lightSaberModel)
//                    loadAds()
//                })
            binding.lightSaberView.isGone = true
            binding.lightSaberEffect.isGone = true
            cancelVibrator()
            simulatorViewModel.toggleFlashLight(on = false)
            if (isPlaying()) {
                mediaPlayer?.pause()
                mediaPlayer?.seekTo(0)
            }
        }
    }

    private fun initSound(lightSaberModel: LightSaberModel) {
        mediaPlayerStart = MediaPlayer().apply {
            lightSaberModel.soundStart.let { soundPath ->
                val descriptor = assets.openFd(soundPath)
                setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
                descriptor.close()
                prepare()
                isLooping = false
            }
        }
        mediaPlayer = MediaPlayer().apply {
            lightSaberModel.sound?.let { soundPath ->
                val descriptor = assets.openFd(soundPath)
                setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
                descriptor.close()
                prepare()
                isLooping = true
            }
        }
        mediaPlayerEnd = MediaPlayer().apply {
            lightSaberModel.soundEnd.let { soundPath ->
                val descriptor = assets.openFd(soundPath)
                setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
                descriptor.close()
                prepare()
                isLooping = false
            }
        }
    }

    private fun isPlayingStart(): Boolean {
        return try {
            if (mediaPlayerStart == null) {
                false
            } else mediaPlayerStart!!.isPlaying
        } catch (e: IllegalStateException) {
            false
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

    private fun isPlayingEnd(): Boolean {
        return try {
            if (mediaPlayerEnd == null) {
                false
            } else mediaPlayerEnd!!.isPlaying
        } catch (e: IllegalStateException) {
            false
        }
    }

    override fun onDestroy() {
        mediaPlayerRelease()
        super.onDestroy()
        screenName?.let { cemAdManager.removeBannerLoaded(it) }

        if (shakeDetector != null) {
            sensorManager.unregisterListener(shakeDetector)
        }
    }

    private fun mediaPlayerRelease() {

        try {
            if (isPlayingStart()) {
                mediaPlayerStart?.stop()
                mediaPlayerStart?.reset()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            mediaPlayerStart?.release()
            mediaPlayerStart = null
        } catch (e: Exception) {
            e.printStackTrace()
        }

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

        try {
            if (isPlayingEnd()) {
                mediaPlayerEnd?.stop()
                mediaPlayerEnd?.reset()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            mediaPlayerEnd?.release()
            mediaPlayerEnd = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onAudioFocusChange(p0: Int) {
    }

    override fun binding(): ActivityLightSaberDetailBinding {
        return ActivityLightSaberDetailBinding.inflate(layoutInflater)
    }

    override fun provideScreenName(): String {
        return SABER_DETAIL_SHOW
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
        binding.lightSaberView.apply {
            isGone = true
            pauseAnimation()
        }
        binding.lightSaberEffect.pauseAnimation()
    }

    override fun onShake(count: Int) {
        this.lifecycleScope.launch {
            onLightSaber(SHAKE_SABER)
            delay(2000)
            handleActionUp()
        }
    }

    private fun onLightSaber(type: String){
        binding.apply {
            val params = HashMap<String, String>()
            params[SABER_NAME] = lightSaberModel?.name.toString()
            CemAnalytics.logEventAndParams(this@LightSaberDetailActivity, screenName = SABER_DETAIL, eventName = type, params = params)
            if (batteryEffect.progress < 0.99f) {
                lightSaberView.apply {
                    isVisible = true
                    speed = 1f
                    playAnimation()
                }
                lightSaberEffect.apply {
                    isVisible = true
                    repeatCount = ValueAnimator.INFINITE
                    playAnimation()
                }
                batteryEffect.apply {
                    progress = batteryProcess
                    speed = if(BuildConfig.DEBUG){
                        1.9f
                    }else{
                        1.9f
                    }
                    setMinAndMaxProgress(batteryProcess, 1f)
                    playAnimation()
                }
                initVibrator(isVibrate)
                simulatorViewModel.toggleFlashLight(on = isFlash)
                when {
                    !isMute && !isPlayingStart() -> mediaPlayerStart?.start()

                    isMute && isPlayingStart() -> mediaPlayerStart?.pause()
                }
            } else {
                openChargeBatteryDialog()
            }
        }
    }

    companion object {

        private const val EXTRA_LIGHT_SABER_MODEL = "EXTRA_LIGHT_SABER_MODEL"

        @JvmStatic
        fun start(
            activity: FragmentActivity?,
            lightSaberModel: LightSaberModel,
            fromScreenName: String?
        ) {
            if (activity == null || activity.supportFragmentManager.isStateSaved) return
            CemAdManager.getInstance(activity).showInterstitialReloadCallback(activity = activity, configKey = FULL_KEY_DETAIL, callback = {
                val intent = Intent(activity, LightSaberDetailActivity::class.java).apply {
                    putExtra(EXTRA_LIGHT_SABER_MODEL, lightSaberModel)
                }
                activity.startActivity(intent)
            })
        }
    }

    override fun onBulletsRequestListener(isReload: Boolean) {
        if (isReload) {
                    batteryProcess = 0f
                    binding.batteryEffect.setMinAndMaxProgress(0f, 1f)
                    binding.batteryEffect.progress = 0f
                }
                loadAds()
    }

    override fun onGunRequestListener(simulatorModel: SimulatorModel) {

    }
}