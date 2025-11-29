package com.cemsofwave.gunsimulator.base


import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.PopupWindow
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.viewbinding.ViewBinding
import com.cem.admodule.ext.ConstAd.FULL_KEY_BACK
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.databinding.PopupSettingLayoutBinding
import com.cemsofwave.gunsimulator.utils.CLICK_BACK
import com.cemsofwave.gunsimulator.utils.ADJUST_COLOR
import com.cemsofwave.gunsimulator.utils.CLICK_EXIT_FULLSCREEN
import com.cemsofwave.gunsimulator.utils.CLICK_FAVOURITE
import com.cemsofwave.gunsimulator.utils.CLICK_FULLSCREEN
import com.cemsofwave.gunsimulator.utils.GUN_DETAIL_CLICK_BG
import com.cemsofwave.gunsimulator.utils.MAIN_CLICK_SETTING
import com.cemsofwave.gunsimulator.utils.CLICK_UN_FAVOURITE
import com.cemsofwave.gunsimulator.utils.SETTING_TOGGLE_FLASH
import com.cemsofwave.gunsimulator.utils.SETTING_TOGGLE_SOUND
import com.cemsofwave.gunsimulator.utils.SETTING_TOGGLE_VIBRATE
import com.cemsofwave.gunsimulator.viewmodel.SimulatorViewModel
import com.trinhbx.base.extension.dpToPx
import com.trinhbx.base.extension.invisible
import com.trinhbx.base.extension.setRadius
import com.trinhbx.base.extension.visible
import com.cemsofwave.gunsimulator.base.BaseActivity
import timber.log.Timber

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 24/10/2023
 */

private const val ANIM_DURATION = 300L

abstract class BasePreviewActivity<VB : ViewBinding> : BaseActivity<VB>() {

    //    abstract fun onFullscreen(isFullscreen:Boolean)
    protected val simulatorViewModel by viewModels<SimulatorViewModel>()

    protected var scalePreviewOffset = 0.5f // total ratio scale = 1+scalePreviewOffset
    protected fun setFullScreen(
        isFullscreen: Boolean,
        scaleViews: List<View>?,
        listViewLeft: List<View>?,
        listViewTop: List<View>?,
        listViewRight: List<View>?,
        listViewBottom: List<View>?
    ) {
        if (isFullscreen) {
            CemAnalytics.logEventClickView(this, screenName, CLICK_FULLSCREEN)
            listViewLeft?.forEach { view ->
                if(!view.isGone){
                    view.slideLeft(true) {
                        it.invisible()
                    }
                }
            }
            listViewTop?.forEach { view ->
                view.slideUp(true) {
                    it.invisible()
                }
            }
            listViewRight?.forEach { view ->
                view.slideRight(false) {
                    it.invisible()
                }
            }
            listViewBottom?.forEach { view ->
                view.slideDown(false) {
                    it.invisible()
                }
            }
            scaleViews?.forEach { scaleView ->
                Timber.e("SCALE UP: ${scaleView.scaleX}")
                scaleView.scaleProgress(scaleView.scaleX, scaleView.scaleX + scalePreviewOffset)
            }
        } else {
            CemAnalytics.logEventClickView(this,screenName, CLICK_EXIT_FULLSCREEN)
            listViewLeft?.forEach { view ->
                if(!view.isGone) {
                    view.slideRight(true) {
                        it.visible()
                    }
                }
            }
            listViewTop?.forEach { view ->
                view.slideDown(true) {
                    it.visible()
                }
            }
            listViewRight?.forEach { view ->
                view.slideLeft(false) {
                    it.visible()
                }
            }
            listViewBottom?.forEach { view ->
                view.slideUp(false) {
                    it.visible()
                }
            }
            scaleViews?.forEach { scaleView ->
                Timber.e("SCALE: ${scaleView.scaleX}")
                scaleView.scaleProgress(scaleView.scaleX, scaleView.scaleX - scalePreviewOffset)
            }
        }
    }

    /**
     * Translate the view upwards
     * @param isFromTop is pos of view,
     * if is true -> translate from inside to outside screen,
     * otherwise -> translate from outside to inside screen
     */
    protected fun View.slideUp(isFromTop: Boolean, onEndAnimate: ((View) -> Unit)? = null) {
        val fromY = if (!isFromTop) height.toFloat() else 0f
        val toY = if (!isFromTop) 0f else -height.toFloat()
        val animate = TranslateAnimation(0f, 0f, fromY, toY)
        animate.duration = ANIM_DURATION
        animate.fillAfter = true
        animate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                clearAnimation()
                onEndAnimate?.invoke(this@slideUp)
            }
        })
        startAnimation(animate)
    }

    /**
     * Translate the view downward
     * @param isFromTop is pos of view,
     * if is true -> translate from outside to inside screen,
     * otherwise -> translate from inside to outside screen
     */
    protected fun View.slideDown(isFromTop: Boolean, onEndAnimate: ((View) -> Unit)? = null) {
        val fromY = if (!isFromTop) 0f else -height.toFloat()
        val toY = if (!isFromTop) height.toFloat() else 0f
        val animate = TranslateAnimation(0f, 0f, fromY, toY)
        animate.duration = ANIM_DURATION
        animate.fillAfter = true
        animate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                clearAnimation()
                onEndAnimate?.invoke(this@slideDown)
            }
        })
        startAnimation(animate)
    }

    /**
     * Translate the view to the left
     * @param isFromLeft is position of view,
     * if is true -> translate from inside to outside screen,
     * otherwise -> translate from outside to inside screen
     */
    protected fun View.slideLeft(isFromLeft: Boolean, onEndAnimate: ((View) -> Unit)? = null) {
        val fromX = if (isFromLeft) 0f else width.toFloat()
        val toX = if (isFromLeft) -width.toFloat() else 0f
        val animate = TranslateAnimation(fromX, toX, 0f, 0f)
        animate.duration = ANIM_DURATION
        animate.fillAfter = true
        animate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                clearAnimation()
                onEndAnimate?.invoke(this@slideLeft)
            }
        })
        startAnimation(animate)
    }

    protected fun View.slideRight(isFromLeft: Boolean, onEndAnimate: ((View) -> Unit)? = null) {
        val fromX = if (isFromLeft) -width.toFloat() else 0f
        val toX = if (isFromLeft) 0f else width * 2f
        val animate = TranslateAnimation(fromX, toX, 0f, 0f)
        animate.duration = ANIM_DURATION
        animate.fillAfter = true
        animate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                clearAnimation()
                onEndAnimate?.invoke(this@slideRight)
            }
        })
        startAnimation(animate)
    }

    private fun View.scaleProgress(startScale: Float, endScale: Float) {
        ValueAnimator.ofFloat(startScale, endScale).apply {
            duration = ANIM_DURATION
            addUpdateListener {
                scaleX = it.animatedValue as Float
                scaleY = it.animatedValue as Float
            }
        }?.start()
    }

    protected fun View.showPopupSetting(
        isVibrateDef: Boolean,
        isFlashDef: Boolean,
        isSoundDef: Boolean,
        mScreenName: String = ""
    ) {
        CemAnalytics.logEventClickView(this@BasePreviewActivity, mScreenName, MAIN_CLICK_SETTING)
        val popupBinding = PopupSettingLayoutBinding.inflate(LayoutInflater.from(context))
        val popupWindow = PopupWindow(
            popupBinding.root,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
        ).apply {
            isTouchable = true
            isFocusable = true
            isOutsideTouchable = true
            popupBinding.root.setRadius(12)
            elevation = dpToPx(4f)
            setOnDismissListener {
                simulatorViewModel.isVibrate.postSuccess(popupBinding.switchVibrate.isChecked)
                simulatorViewModel.isFlash.postSuccess(popupBinding.switchFlash.isChecked)
                simulatorViewModel.isSound.postSuccess(popupBinding.switchSound.isChecked)
            }
        }
        popupBinding.apply {
            switchVibrate.isChecked = isVibrateDef
            switchFlash.isChecked = isFlashDef
            switchSound.isChecked = isSoundDef
            vibrateLayout.setOnClickListener {
                switchVibrate.isChecked = !switchVibrate.isChecked
                CemAnalytics.logEventClickView(this@BasePreviewActivity, mScreenName, SETTING_TOGGLE_VIBRATE)
            }
            flashLayout.setOnClickListener {
                switchFlash.isChecked = !switchFlash.isChecked
                CemAnalytics.logEventClickView(this@BasePreviewActivity, mScreenName, SETTING_TOGGLE_FLASH)
            }
            soundLayout.setOnClickListener {
                switchSound.isChecked = !switchSound.isChecked
                CemAnalytics.logEventClickView(this@BasePreviewActivity, mScreenName, SETTING_TOGGLE_SOUND)
            }
        }
        popupBinding.root.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        val xOffset = -popupBinding.root.measuredWidth + dpToPx(4)
        popupWindow.showAsDropDown(this@showPopupSetting, xOffset, 0)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        hideSystemUi()
    }

    private val vibrate by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }

    fun initVibrator(isVibrate: Boolean) {
        if (vibrate.hasVibrator()) {
            if (isVibrate) {
                val pattern =
                    longArrayOf(0, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100)
                val amplitudes = intArrayOf(0, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrate.vibrate(VibrationEffect.createWaveform(pattern, amplitudes, 0))
                } else {
                    vibrate.vibrate(pattern, 0)
                }
            } else {
                vibrate.cancel()
            }
        }
    }

    fun cancelVibrator() {
        vibrate.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                CemAnalytics.logEventClickView(this@BasePreviewActivity,screenName, CLICK_BACK)
                CemAdManager.getInstance(this@BasePreviewActivity)
                    .showInterstitialReloadCallback(activity = this@BasePreviewActivity, configKey =  FULL_KEY_BACK, callback = {
                        finish()
                    })
            }
        })
    }

    protected fun eventClickFav(isFav:Boolean){
        if (isFav){
            CemAnalytics.logEventClickView(this,screenName, CLICK_FAVOURITE)
        } else{
            CemAnalytics.logEventClickView(this,screenName, CLICK_UN_FAVOURITE)
        }
    }
    protected fun eventClickColorBar(nameScreen: String){
        CemAnalytics.logEventClickView(this, nameScreen, ADJUST_COLOR)
    }

    protected fun eventClickItemBg(nameScreen: String){
        CemAnalytics.logEventClickView(this, nameScreen, GUN_DETAIL_CLICK_BG)
    }
}