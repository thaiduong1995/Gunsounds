package com.cemsofwave.gunsimulator.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.net.Uri
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
import com.bumptech.glide.Glide
import com.cem.admodule.ext.ConstAd
import com.cem.admodule.ext.ConstAd.BANNER_KEY_PREVIEW
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.base.BasePreviewActivity
import com.cemsofwave.gunsimulator.data.model.ExplosionModel
import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.databinding.ActivityBombDetailBinding
import com.cemsofwave.gunsimulator.interfaces.MyFragmentCallback
import com.cemsofwave.gunsimulator.ui.dialog.BackgroundDialog
import com.cemsofwave.gunsimulator.ui.dialog.SettingDetailFragment
import com.cemsofwave.gunsimulator.utils.BACKGROUND_NAME
import com.cemsofwave.gunsimulator.utils.BOMB_GRENADE
import com.cemsofwave.gunsimulator.utils.BOMB_GRENADE_SHOW
import com.cemsofwave.gunsimulator.utils.BOMB_NAME
import com.cemsofwave.gunsimulator.utils.BOMB_TIMER
import com.cemsofwave.gunsimulator.utils.BOMB_TIMER_SHOW
import com.cemsofwave.gunsimulator.utils.BROKEN
import com.cemsofwave.gunsimulator.utils.CLICK_BACKGROUND
import com.cemsofwave.gunsimulator.utils.CLICK_GRENADE
import com.cemsofwave.gunsimulator.utils.CLICK_FULLSCREEN
import com.cemsofwave.gunsimulator.utils.CLICK_SETTING
import com.cemsofwave.gunsimulator.utils.CLICK_TIMER
import com.cemsofwave.gunsimulator.utils.IS_FLASH_DEF
import com.cemsofwave.gunsimulator.utils.IS_SOUND_DEF
import com.cemsofwave.gunsimulator.utils.IS_VIBRATE_DEF
import com.cemsofwave.gunsimulator.utils.MyAnimationDrawable
import com.cemsofwave.gunsimulator.utils.SELECT_BACKGROUND
import com.cemsofwave.gunsimulator.utils.SHAKE_BOMB
import com.cemsofwave.gunsimulator.utils.ShakeDetector
import com.cemsofwave.gunsimulator.viewmodel.ImageViewModel
import com.trinhbx.base.extension.getDataParcelable
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.model.StateData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BombDetailActivity : BasePreviewActivity<ActivityBombDetailBinding>(), ShakeDetector.OnShakeListener,
    MyFragmentCallback {
    private val imageViewModel by viewModels<ImageViewModel>()
    private lateinit var mFrames: List<MyAnimationDrawable.Frame>
    private var explosionModel: ExplosionModel? = null
    private val myAnimationDrawable = MyAnimationDrawable()

    private var mediaPlayer: MediaPlayer? = null
    private var isVibrate = true
    private var isFlash = true
    private var isMute = false
    private var isShake = false
    private var countBomb = 0
    private var countBombMax = 0
    private var typeBomb = "click"
    private var timeBomb = 0L

    private lateinit var sensorManager: SensorManager
    private lateinit var shakeDetector: ShakeDetector
    override fun provideScreenName(): String {
        return when(typeBomb){
            "click" -> BOMB_GRENADE_SHOW
            else -> BOMB_TIMER_SHOW
        }
    }

    override fun binding(): ActivityBombDetailBinding {
        return ActivityBombDetailBinding.inflate(layoutInflater)
    }

    private val listBg by lazy {
        listOf(
            R.drawable.bg_bomb_click,
            R.drawable.bg_bomb_time,
            R.drawable.bg_first,
            R.drawable.bg_second,
            R.drawable.bg_third,
            R.drawable.bg_four,
            R.drawable.bg_five,
            R.drawable.bg_six
        )
    }

    override fun getData() {
        super.getData()
        explosionModel = intent?.getDataParcelable(EXTRA_EXPLOSION_MODEL)
        this.lifecycleScope.launch {
            this@BombDetailActivity.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {

                launch {
                    imageViewModel.backgroundBitmapFlow.collect {
                        observeBackgroundBitmap(it)
                    }
                }
            }
        }
        explosionModel?.let { initDataSource(it) }
    }

    private fun observeBackgroundBitmap(bitmap: Bitmap?) {
        bitmap?.let {
            binding.imvBackground.setImageBitmap(it)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
       binding.apply {
           Glide.with(this@BombDetailActivity).asBitmap()
               .load(Uri.parse("file:///android_asset/${explosionModel?.image}"))
               .into(imvItem)
           tvCountBomb.text = "${countBomb}/${countBombMax}"
           when(typeBomb){
               "click" -> {
                   tvTime.isGone = true
                   layoutTime.isGone = true
                   imgCountBomb.isGone = false
                   tvCountBomb.isGone = false
                   imageViewModel.loadBackgroundBitmap(listBg[0])
               }
               else -> {
                   tvTime.isGone = false
                   layoutTime.isGone = false
                   imgCountBomb.isGone = true
                   tvCountBomb.isGone = true
                   val layoutParamsTimer = tvTime.layoutParams
                   val mLayoutParamsTimer = layoutParamsTimer as ConstraintLayout.LayoutParams
                   mLayoutParamsTimer.marginStart = explosionModel!!.start
                   mLayoutParamsTimer.marginEnd = explosionModel!!.end
                   mLayoutParamsTimer.topMargin = explosionModel!!.top
                   mLayoutParamsTimer.bottomMargin = explosionModel!!.bottom
                   when(explosionModel?.id){
                       "bomb10" -> {
                           tvTime.textSize = 20f
                       }
                       "bomb11" -> {
                           tvTime.textSize = 41f
                       }
                       "bomb12" -> {
                           tvTime.setTextColor(ContextCompat.getColor(this@BombDetailActivity, R.color.black))
                           tvTime.textSize = 15f
                           tvTime.rotation = -11f
                       }
                   }
                   setTime(5)
                   tvTime.layoutParams = mLayoutParamsTimer
                   imageViewModel.loadBackgroundBitmap(listBg[1])
               }
           }
           val animationDrawableId = R.drawable.anim_bomb
           val layoutParams = imvItem.layoutParams as ConstraintLayout.LayoutParams
           val sb = StringBuilder()

           layoutParams.dimensionRatio = sb.toString()
           imvItem.layoutParams = layoutParams

           myAnimationDrawable.loadAnimation(animationDrawableId, this@BombDetailActivity, object : MyAnimationDrawable.OnAnimationLoadedListener{
               override fun onLoaded(frames: List<MyAnimationDrawable.Frame>) {
                   mFrames = frames
               }
           })

           sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
           val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
           if (accelerometer != null) {
               shakeDetector = ShakeDetector()
               shakeDetector.setOnShakeListener(this@BombDetailActivity)

               sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_FASTEST)
           } else {
               Toast.makeText(this@BombDetailActivity, getText(R.string.device_does_not_sp), Toast.LENGTH_SHORT).show()
           }
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

    private fun initDataSource(explosionModel: ExplosionModel){
        countBomb = explosionModel.count
        countBombMax = explosionModel.count
        typeBomb = explosionModel.typeBomb
        timeBomb = explosionModel.timer
        imageViewModel.resizeListBg(listBg)
        simulatorViewModel.checkSimulatorLike(explosionModel)
    }

    private fun initSound(explosionModel: ExplosionModel) {
        mediaPlayer = MediaPlayer().apply {
            explosionModel.sound?.let { soundPath ->
                val descriptor = assets.openFd(soundPath)
                setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
                descriptor.close()
                prepare()
                isLooping = true
            }
        }
    }

    override fun initOnClickListener() {
        super.initOnClickListener()
        binding.apply {

            imvBackground.setOnClickListener {
                when(typeBomb){
                    "click" -> onBombStart(CLICK_GRENADE)
                    else -> onBombStart(CLICK_TIMER)
                }
            }

            imvFinish.setOnClickListener {
                imvFinish.isGone = true
                CemAnalytics.logEventClickView(this@BombDetailActivity, screenName, BROKEN)
            }

            btnBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            btnSetting.setOnSingleClickListener {
                CemAnalytics.logEventClickView(this@BombDetailActivity, screenName, CLICK_SETTING)
                val settingDetailFragment = SettingDetailFragment
                settingDetailFragment.show(this@BombDetailActivity,
                    simulatorViewModel.isVibrate.value?.getData() ?: IS_VIBRATE_DEF,
                    simulatorViewModel.isFlash.value?.getData() ?: IS_FLASH_DEF,
                    simulatorViewModel.isSound.value?.getData() ?: IS_SOUND_DEF,
                    screenName
                )
            }

            btnFav.setOnClickListener {
                eventClickFav(btnFav.isChecked)
                btnFav.isEnabled = false
                simulatorViewModel.likeSimulator(explosionModel, btnFav.isChecked) {
                    btnFav.isEnabled = true
                }
            }

            btnFullscreen.setOnCheckedChangeListener { _, b ->
                val params = HashMap<String, String>()
                params[BOMB_NAME] = explosionModel?.name.toString()
                CemAnalytics.logEventAndParams(this@BombDetailActivity, screenName = if(typeBomb == "click")  BOMB_GRENADE else BOMB_TIMER, eventName = CLICK_FULLSCREEN, params = params)
                scalePreviewOffset = 0.1f
                binding.apply {
                    setFullScreen(
                        b,
                        null,
                        listOf(imgBackground),
                        listOf(btnBack, btnSetting, btnFav),
                        null,
                        null
                    )
                }
            }

            imgBackground.setOnClickListener{
                CemAnalytics.logEventClickView(this@BombDetailActivity, screenName, CLICK_BACKGROUND)
                val backgroundDialog = BackgroundDialog
                backgroundDialog.show(this@BombDetailActivity, screenName,
                    onPosListener = {pos ->
                        if (pos != -1) {
                            CemAnalytics.logEventClickView(this@BombDetailActivity, screenName, SELECT_BACKGROUND)
                            val params = HashMap<String, String>()
                            params[BACKGROUND_NAME] = listBg[pos].toString()
                            imageViewModel.loadBackgroundBitmap(listBg[pos])
                        }
                        loadAds()
                    })
            }

            time1.setOnClickListener {
                CemAnalytics.logEventClickView(this@BombDetailActivity, screenName, "5s")
                setTime(5)
            }

            time2.setOnClickListener {
                CemAnalytics.logEventClickView(this@BombDetailActivity, screenName, "10s")
                setTime(10)
            }

            time3.setOnClickListener {
                CemAnalytics.logEventClickView(this@BombDetailActivity, screenName, "15s")
                setTime(15)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onBombStart(type: String){
        binding.apply {
            val params = HashMap<String, String>()
            params[BOMB_NAME] = explosionModel?.name.toString()
            CemAnalytics.logEventAndParams(this@BombDetailActivity, screenName = if(typeBomb == "click")  BOMB_GRENADE else BOMB_TIMER, eventName = type, params = params)
            explosionModel?.let { initSound(it) }
            if (countBomb > 0 || typeBomb != "click") {
                delayAndExecuteWithCountdown(timeBomb) {
                    countBomb -= 1
                    tvCountBomb.text = "${countBomb}/${countBombMax}"
                    imvItem.isGone = true
                    imvFinish.isGone = false
                    imvBackground.isEnabled = false
                    myAnimationDrawable.playAnimation(mFrames, imvAnimation, onEndRunnable)
                    initVibrator(isVibrate)
                    simulatorViewModel.toggleFlashLight(on = isFlash)
                }
            } else {
                openChargeBombDialog()
            }
        }
    }

    private fun delayAndExecuteWithCountdown(delayMillis: Long, runnable: () -> Unit) {
        val handler = CoroutineScope(Dispatchers.Main).launch {
            var countdownValue = delayMillis / 1000 // Convert milliseconds to seconds
            if (delayMillis.toInt() == 0){
                when {
                    !isMute && !isPlaying() -> mediaPlayer?.start()

                    isMute && isPlaying() -> mediaPlayer?.pause()
                }
            }
            while (countdownValue > 0) {
                setTime(countdownValue.toInt())
                delay(1000)
                countdownValue--
                if (countdownValue < 3){
                    when {
                        !isMute && !isPlaying() -> mediaPlayer?.start()

                        isMute && isPlaying() -> mediaPlayer?.pause()
                    }
                }
            }
            runnable()
        }
    }

    private val onEndRunnable = Runnable {
        isShake = false
        binding.imvItem.isGone = false
        binding.imvBackground.isEnabled = true
        cancelVibrator()
        if(typeBomb != "click"){
            setTime(5)
        }

        CemAnalytics.logEventScreenView(this, "${screenName}_${BROKEN}")
        simulatorViewModel.toggleFlashLight(on = false)
        mediaPlayerRelease()
    }

    override fun onPause() {
        super.onPause()
        initVibrator(isVibrate = false)
        simulatorViewModel.toggleFlashLight(on = false)
        mediaPlayerRelease()
    }

    private fun openChargeBombDialog() {
        AddDetailActivity.start(this@BombDetailActivity, "RELOAD_GRENADES", screenName, this)
        cancelVibrator()
        simulatorViewModel.toggleFlashLight(on = false)
        mediaPlayerRelease()
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
        super.onDestroy()
        screenName?.let { cemAdManager.removeBannerLoaded(it) }
        mediaPlayerRelease()
        if (shakeDetector != null) {
            sensorManager.unregisterListener(shakeDetector)
        }
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

    override fun onShake(count: Int) {
        if(!isShake){
            isShake = true
            if(!binding.imvFinish.isVisible){
                onBombStart(SHAKE_BOMB)
            }else{
                binding.imvFinish.isVisible = false
            }
        }
    }

    private fun setTime(time: Int){
        timeBomb = (time*1000).toLong()
        binding.apply {
            when(explosionModel?.id){
                "bomb10" ->{
                    tvTime.text = "00:${if(time < 10) "0${time}" else "$time"}"
                }
                "bomb12" ->{
                    tvTime.text = "0:${if(time < 10) "0${time}" else "$time"}"
                }
                else -> {
                    tvTime.text = "00 00 ${if(time < 10) "0${time}" else "$time"}"
                }
            }
        }
    }

    override fun onBulletsRequestListener(isReload: Boolean) {
        if (isReload) {
            explosionModel?.let {
                countBomb = it.count
                isShake = false
                binding.imvFinish.isGone = true
                binding.tvCountBomb.text = "${countBomb}/${countBombMax}"
            }
        }
        loadAds()
    }

    override fun onGunRequestListener(simulatorModel: SimulatorModel) {

    }

    companion object {

        private const val EXTRA_EXPLOSION_MODEL = "EXTRA_EXPLOSION_MODEL"

        @JvmStatic
        fun start(
            activity: FragmentActivity?,
            explosionModel: ExplosionModel,
            fromScreenName: String?
        ) {
            if (activity == null || activity.supportFragmentManager.isStateSaved) return
            CemAdManager.getInstance(activity).showInterstitialReloadCallback(activity = activity, configKey = ConstAd.FULL_KEY_DETAIL, callback = {
                val intent = Intent(activity, BombDetailActivity::class.java).apply {
                    putExtra(EXTRA_EXPLOSION_MODEL, explosionModel)
                }
                activity.startActivity(intent)
            })
        }
    }

}