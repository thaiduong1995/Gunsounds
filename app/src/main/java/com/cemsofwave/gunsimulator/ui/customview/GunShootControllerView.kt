package com.cemsofwave.gunsimulator.ui.customview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.cemsofwave.gunsimulator.base.utils.Timer
import com.cemsofwave.gunsimulator.data.model.GunModel
import com.cemsofwave.gunsimulator.utils.GunShootMode
import com.cemsofwave.gunsimulator.utils.IS_FLASH_DEF
import com.cemsofwave.gunsimulator.utils.IS_SOUND_DEF
import com.cemsofwave.gunsimulator.utils.IS_VIBRATE_DEF
import com.trinhbx.base.utils.RequestAudioFocusManager
import kotlin.math.abs


/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 12/10/2023
 */
class GunShootControllerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle), AudioManager.OnAudioFocusChangeListener {
    companion object {
        private const val SPEED_SINGLE_SHOOT = 1f
        private const val SPEED_AUTO_SHOOT = 3.5f
        private const val SPEED_BRUSH_SHOOT = 2.5f
        private const val TIME_CLICK_INTERVAL = 150L
        private const val BRUSH_BULLET_OFFSET = 5
        private const val FLASH_DURATION = 100L
        private const val FLASH_ALPHA_MAX = 200
        private const val BROKEN_THRESHOLD = 500
    }
    interface Listener {
        fun onShoot(shootMode:GunShootMode,durShoot:Long,speedShoot:Float)
        fun onShot(remainBullet:Int)
        fun onBulletReloaded(bulletCount:Int)
        fun onRequestBullets()
        fun onShooting(isShooting:Boolean,shootMode:GunShootMode)
    }
    private var limitShot = 0
    private var shotCount = 0
    private var shotCountBrush = 0
    var listener:Listener? = null
    var canShoot = true
    private var singleSoundItem:MediaItem? = null
    private var autoSoundItem:MediaItem? = null
    private var emptyItem :MediaItem? = null
    private var reloadSoundItem :MediaItem? = null
    var gunModel:GunModel? = null
        set(value) {
            if(field==null){
                listener?.onBulletReloaded(value?.bulletCount?:0)
            }
            initCameraManager()
            field = value
            value?.let {
                limitShot = it.bulletCount
                timeShootOne = when (gunShootMode) {
                    GunShootMode.SINGLE -> {
                        it.timeOneShot.toLong()
                    }
                    GunShootMode.AUTO -> {
                        it.speedAutoShot.toLong()
                    }
                    GunShootMode.BRUSH -> {
                        it.speedBrushShot.toLong()
                    }
                    else -> {
                        it.timeOneShot.toLong()
                    }
                }
                singleSoundItem = MediaItem.fromUri(Uri.parse("asset:///${it.oneShotSound}"))
                it.autoShotSound?.let { autoSound->
                    autoSoundItem = MediaItem.fromUri(Uri.parse("asset:///$autoSound"))
                }
                emptyItem = MediaItem.fromUri(Uri.parse("asset:///${it.emptySound}"))
                reloadSoundItem = MediaItem.fromUri(Uri.parse("asset:///${it.reloadSound}"))
                if(exoPlayer==null){
                    exoPlayer =  ExoPlayer.Builder(context).build().apply {
                        repeatMode = ExoPlayer.REPEAT_MODE_OFF
                        playbackParameters = PlaybackParameters(1f)
                        prepare()
                    }
                }
                if(exoPlayer2 == null){
                    exoPlayer2 = ExoPlayer.Builder(context).build().apply {
                        repeatMode = ExoPlayer.REPEAT_MODE_OFF
                        playbackParameters = PlaybackParameters(1f)
                        addMediaItem(singleSoundItem!!)
                        prepare()
                    }
                }
                if(exoPlayer3==null){
                    exoPlayer3 = ExoPlayer.Builder(context).build().apply {
                        repeatMode = ExoPlayer.REPEAT_MODE_OFF
                        playbackParameters = PlaybackParameters(1f)
                        addMediaItem(singleSoundItem!!)
                        prepare()
                    }
                }
            }
            val timeShoot = if(value?.autoShotSound!=null){
                (timeShootOne*0.6).toLong()
            } else{timeShootOne}
            initTimerAuto(timeShoot)
            initTimerShootCount(timeShoot)
//            loadBullet()
        }

    private var timeShootOne = 200L

    var gunShootMode = GunShootMode.AUTO
        set(value) {
            field = value
            timerCountShoot?.stop()
            timerCountShoot = null
            timerAuto?.stop()
            timerAuto = null
            sensorManager?.unregisterListener(sensorListener)
            sensorManager = null
            remoteSensor = null
            isShootingBrush = false
            shotCountBrush = 0
            val timeShoot = if(gunModel?.autoShotSound!=null){
                (timeShootOne*0.6).toLong()
            } else{timeShootOne}
            if(value == GunShootMode.AUTO){
                initTimerAuto(timeShoot)
            } else if(value==GunShootMode.BRUSH || value==GunShootMode.SHAKE){
                initSensorManager()
                initTimerShootCount(timeShoot)
            }

        }
    private var timerAuto:Timer? = null

    private var timerCountShoot:Timer? = null

    var isVibrate = IS_VIBRATE_DEF
    var isFlash = IS_FLASH_DEF
    var isSound = IS_SOUND_DEF

    private var cameraId: String? = null
    private var cameraManager: CameraManager? = null
    private var isShotFirst = true
    private fun initTimerAuto(timeShootOne: Long){
        timerAuto?.stop()
        timerAuto = null
        timerAuto = Timer(object :Timer.OnTimerTickListener{
            override fun onTimerTick(duration: Long) {
                if (canShoot && gunShootMode==GunShootMode.AUTO){
                    incrementShot()
                }
            }
        }, timeShootOne)
    }

    private fun initTimerShootCount(timeShootOne:Long){
        timerCountShoot?.stop()
        timerCountShoot = null
        shotCountBrush = 0
        timerCountShoot = Timer(object :Timer.OnTimerTickListener{
            override fun onTimerTick(duration: Long) {
                if(gunShootMode==GunShootMode.BRUSH || gunShootMode==GunShootMode.SHAKE){
                    if(canShoot && shotCountBrush<BRUSH_BULLET_OFFSET ){
                        incrementShot()
                    } else{
                        timerCountShoot?.stop()
                        shotCountBrush = 0
                        isShootingBrush = false
                        postDelayed({
                            listener?.onShooting(false,gunShootMode)
                            exoPlayer?.playWhenReady = false
                        },100)
                    }
                }
            }
        },timeShootOne)
    }
    private val gestureDetector = GestureDetector(context,object :OnSwipeListener(){
        override fun onSwipe(direction: Direction?): Boolean {
//            loadBullet()
            return true
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            loadBullet()
            playSoundEmptyBullet()
            return super.onSingleTapUp(e)
        }
    })

    private var timestamp = 0L
    private var isShootingBrush = false
    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        Log.e("EVENT","${event?.action} - ${event?.eventTime} - ${event?.actionButton} - ${event?.downTime}")
        if(!canShoot){
            if (event != null) {
                gestureDetector.onTouchEvent(event)
            }
            return true
        }
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                listener?.onShooting(true, gunShootMode)
                timestamp = System.currentTimeMillis()
                if(canShoot && gunShootMode==GunShootMode.AUTO){
                    playSoundAuto()
                    timerAuto?.start()
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                exoPlayer?.repeatMode = Player.REPEAT_MODE_OFF
                timerAuto?.stop()
                if(canShoot){
                    if(gunShootMode==GunShootMode.SINGLE){
                        incrementShot()
                    } else if(gunShootMode==GunShootMode.BRUSH){
                        startBrush()
                    } else if(gunShootMode==GunShootMode.AUTO){
                        val con = (System.currentTimeMillis() - timestamp) <= TIME_CLICK_INTERVAL
                        if(con){
                            exoPlayer?.playWhenReady = false
                            playSoundSingle()
                            incrementShot()
                        } else{
                            postDelayed({
                                exoPlayer?.playWhenReady = false
                            },100)
                        }
                    }
                }
                listener?.onShooting(gunShootMode==GunShootMode.BRUSH,gunShootMode)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                return true
            }
            MotionEvent.ACTION_CANCEL -> {
                exoPlayer?.playWhenReady = false
                exoPlayer?.repeatMode = Player.REPEAT_MODE_OFF
                timerAuto?.stop()
                listener?.onShooting(gunShootMode==GunShootMode.BRUSH,gunShootMode)
                return true
            }
        }
        return false
    }

    private fun startBrush(){
        if(shotCountBrush == 0 &&  !isShootingBrush){
            isShootingBrush = true
            if(timerCountShoot==null) {
                val timeShoot = if (gunModel?.autoShotSound != null) {
                    (timeShootOne * 0.6).toLong()
                } else {
                    timeShootOne
                }
                initTimerShootCount(timeShoot)
            }
            timerCountShoot?.start()
        } else if(!canShoot){
            playSoundEmptyBullet()
        }
    }

    override fun onDetachedFromWindow() {
        pause()
        super.onDetachedFromWindow()
    }

    private fun incrementShot(){
        if(shotCount<limitShot){
            shotCount +=1
            shotCountBrush +=1
            val timeShoot = if(gunShootMode==GunShootMode.SINGLE){
                200L
            } else{
                120L
            }
            if(gunShootMode!=GunShootMode.AUTO){
                playSoundSingle()
            } else if(gunModel?.autoShotSound == null){
                playSoundSingle()
            }

            flashScreen()
            vibrator()
            turnOnFlash()

            val speedShoot = if(gunShootMode==GunShootMode.AUTO){
                SPEED_AUTO_SHOOT
            } else{
                SPEED_BRUSH_SHOOT
            }
            listener?.onShoot(gunShootMode,timeShoot, speedShoot)
            if(shotCount>=limitShot){
                postDelayed({
                    canShoot = false
                    timerAuto?.stop()
                    timerCountShoot?.stop()
//                    listener?.onRequestBullets()
                    listener?.onShooting(false,gunShootMode)
                    playSoundEmptyBullet()
                },500L)
            }
            if((limitShot-shotCount) > 0){
                listener?.onShot(limitShot-shotCount)
            }else{
                loadBullet()
            }
        }
    }
    private fun loadBullet(){
        shotCount = 0
        shotCountBrush = 0
        isShootingBrush = false
        isShotFirst = true
        listener?.onBulletReloaded(gunModel?.bulletCount?:0)
//        listener?.onShot(limitShot-shotCount)
    }

    private var flashScreenAnim: ValueAnimator? = null
    private var flashAlphaColor = 0
    private fun flashScreen(isRevert:Boolean=false) {
        flashScreenAnim?.pause()
        val startValue = if(isRevert) FLASH_ALPHA_MAX else 0
        val endValue = if(isRevert) 0 else FLASH_ALPHA_MAX
        flashScreenAnim = ValueAnimator.ofInt(startValue, endValue).apply {
            duration = FLASH_DURATION
            addUpdateListener {
                val value = it.animatedValue as Int
                flashAlphaColor = value
                invalidate()
                if(value==FLASH_ALPHA_MAX && !isRevert){
                    flashScreen(true)
                }
            }
        }
        flashScreenAnim?.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawARGB(flashAlphaColor,255,255,255)
    }

    private var sensorManager: SensorManager? = null
    private var remoteSensor: Sensor? = null
    private var lastX = 0f
    private var lastY = 0f
    private var lastZ = 0f
    private var lastUpdate = 0L

    private val sensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                val currentTime = System.currentTimeMillis()
                if ((currentTime - lastUpdate) > 100) {
                    val timeDifference = currentTime - lastUpdate
                    lastUpdate = currentTime
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]

                    val acceleration =
                        abs(x + y + z - lastX - lastY - lastZ) / timeDifference * 10000

                    if (acceleration > BROKEN_THRESHOLD && gunShootMode==GunShootMode.SHAKE) {
                       startBrush()
                    }
                    lastX = x
                    lastY = y
                    lastZ = z
                }
            }
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

        }
    }
    private fun initSensorManager() {
        if(gunShootMode==GunShootMode.SHAKE) {
            sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
            remoteSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
            sensorManager?.registerListener(
                sensorListener, remoteSensor, SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    private fun releaseSensorManager(){
        sensorManager?.unregisterListener(sensorListener)
        sensorManager = null
        remoteSensor = null
    }

    private fun playSoundSingle(){
        if(!isSound) return
        exoPlayer?.repeatMode = Player.REPEAT_MODE_OFF
        if (RequestAudioFocusManager.requestAudioFocus(context, this)) {
            singleSoundItem?.let {
                if(shotCount%3 == 0){
                    exoPlayer2?.seekTo(0)
                    exoPlayer2?.playWhenReady = true
                } else if(shotCount%3 == 1){
                    val cur = exoPlayer?.currentMediaItem?.localConfiguration?.uri
                    val single = it.localConfiguration?.uri
                    if(cur!=single){
                        exoPlayer?.playWhenReady = false
                        exoPlayer?.setMediaItem(it,true)
                    } else{
                        exoPlayer?.seekTo(0)
                    }
                    exoPlayer?.playWhenReady = true
                } else{
                    exoPlayer3?.seekTo(0)
                    exoPlayer3?.playWhenReady = true
                }
            }
        }
    }

    private fun playSoundEmptyBullet(){
        if(!isSound) return
        exoPlayer?.repeatMode = Player.REPEAT_MODE_OFF
        if (RequestAudioFocusManager.requestAudioFocus(context, this)) {
            emptyItem?.let {
                exoPlayer?.setMediaItem(it,true)
                exoPlayer?.playWhenReady = true
            }
        }
    }

    fun playSoundReload(){
        if(!isSound) return
        exoPlayer?.repeatMode = Player.REPEAT_MODE_OFF
        if (RequestAudioFocusManager.requestAudioFocus(context, this)) {
            reloadSoundItem?.let {
                    exoPlayer?.playWhenReady = false
                    exoPlayer?.setMediaItem(it,true)
                    exoPlayer?.playWhenReady = true
            }
        }
    }
    private fun playSoundAuto(){
        if(!isSound) return
        if (RequestAudioFocusManager.requestAudioFocus(context, this)) {
            autoSoundItem?.let {
                exoPlayer?.repeatMode = Player.REPEAT_MODE_ONE
                exoPlayer?.playWhenReady = false
                exoPlayer?.setMediaItem(it,true)
                exoPlayer?.playWhenReady = true
            }
        }
    }

    private var exoPlayer:ExoPlayer? =  null
    private var exoPlayer2:ExoPlayer? =  null
    private var exoPlayer3:ExoPlayer? =  null

    fun resume(){
        gunModel = gunModel
        gunShootMode = gunShootMode
    }

    fun stopMedia(){
        exoPlayer = null
        exoPlayer2 = null
        exoPlayer3 = null
    }

    fun pause(){
        RequestAudioFocusManager.abandonAudioFocus(this)
        listener?.onShooting(false,gunShootMode)
        cameraId?.let {
            try {
                cameraManager?.setTorchMode(it, false)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        exoPlayer?.playWhenReady = false
        exoPlayer?.release()
        exoPlayer = null
        exoPlayer2?.playWhenReady = false
        exoPlayer2?.release()
        exoPlayer2 = null
        exoPlayer3?.playWhenReady = false
        exoPlayer3?.release()
        exoPlayer3 = null
        listener = null
        timerAuto?.stop()
        timerAuto = null
        timerCountShoot?.stop()
        timerCountShoot = null
        releaseSensorManager()
        cameraManager = null
        cameraId = null
    }

    override fun onAudioFocusChange(focusChange: Int) {
//        when (focusChange) {
//            AudioManager.AUDIOFOCUS_GAIN -> {
////                val isPlay = true
////                exoPlayer?.playWhenReady = isPlay
////                exoPlayer2?.playWhenReady = isPlay
////                exoPlayer3?.playWhenReady = isPlay
//                val volume = 1f
//                exoPlayer?.volume = volume
//                exoPlayer2?.volume = volume
//                exoPlayer3?.volume = volume
////                mediaPlayer?.playWhenReady = true
////                mediaPlayer.start()
//                Timber.e("AUDIOFOCUS_GAIN")
////                mediaPlayer.setVolume(1.0f,1.0f)
//            }
//            AudioManager.AUDIOFOCUS_LOSS -> {
//                //App khac yeu cau quyen phat -> pause/stop de nhuong quyen
////                mediaPlayer?.playWhenReady = false
//                Timber.e("AUDIOFOCUS_LOSS")
//                RequestAudioFocusManager.abandonAudioFocus(this)
//                val isPlay = false
//                exoPlayer?.playWhenReady = isPlay
//                exoPlayer2?.playWhenReady = isPlay
//                exoPlayer3?.playWhenReady = isPlay
//            }
//            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
//                //app bị mất quyền phát một lúc. -> pause ->đợi nhận AudioManager.AUDIOFOCUS_GAIN.
//                Timber.e("AUDIOFOCUS_LOSS")
//                RequestAudioFocusManager.abandonAudioFocus(this)
//                val isPlay = false
//                exoPlayer?.playWhenReady = isPlay
//                exoPlayer2?.playWhenReady = isPlay
//                exoPlayer3?.playWhenReady = isPlay
////                mediaPlayer.pause()
//            }
//            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
//                //App bi mat quyen mot luc. Khong can thiet phai pause, co the decrement volume
////                mediaPlayer.pause()
//                val volume = 0.3f
//                exoPlayer?.volume = volume
//                exoPlayer2?.volume = volume
//                exoPlayer3?.volume = volume
//            }
//            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE -> {
//                // het AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> decrement volume
////                mediaPlayer.start()
////                mediaPlayer.setVolume(1f,1f)
//                val volume = 1f
//                exoPlayer?.volume = volume
//                exoPlayer2?.volume = volume
//                exoPlayer3?.volume = volume
//            }
//        }
    }

    private fun vibrator() {
        if(!isVibrate) return
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        200, VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator.vibrate(200)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun getCameraId(): String? {
        for (id in cameraManager!!.cameraIdList) {
            val characteristics = cameraManager!!.getCameraCharacteristics(id)
            val isFlashAvailable = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
            val lensFacing = characteristics.get(CameraCharacteristics.LENS_FACING)

            if (isFlashAvailable != null && isFlashAvailable && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                return id
            }
        }
        return null
    }

    private fun initCameraManager() {
        (context.getSystemService(Context.CAMERA_SERVICE) as? CameraManager)?.let {
            cameraManager = it
            cameraId = getCameraId()
        }
    }

    private fun turnOnFlash() {
        if(!isFlash) return
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraId?.let {
                    cameraManager?.setTorchMode(it, true)
                    postDelayed({
                        cameraManager?.setTorchMode(it, false)
                    },200)
                }
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
}