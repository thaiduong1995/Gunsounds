package com.cemsofwave.gunsimulator.base

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.cem.admodule.ext.ConstAd.FULL_KEY_BACK
import com.cem.admodule.manager.CemAdManager
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.extension.destroy
import com.cemsofwave.gunsimulator.ui.dialog.DialogNetwork
import com.cemsofwave.gunsimulator.utils.VIEW_SPLASH_ACTIVITY
import com.cemsofwave.gunsimulator.utils.isShowNetwork
import com.trinhbx.base.utils.VersionUtils


/**
 * Created by Trinh BX on 01/02/2023.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 */
abstract class BaseActivity<VB:ViewBinding>:AppCompatActivity() {
    protected lateinit var binding:VB
    protected val cemAdManager by lazy {
        CemAdManager.getInstance(this)
    }
    protected var screenName:String? = null
    private val mediaPlayerBg = MediaPlayer()

    protected abstract fun provideScreenName():String
    abstract fun binding(): VB
    open fun getData(){}
    abstract fun initView()
    open fun loadAds(){}
    open fun initOnClickListener(){}
    open fun registerObserve(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )
        binding = binding()
        setContentView(binding.root)
        getData()
        screenName = provideScreenName()
        CemAnalytics.logEventScreenView(this, screenName!!)
        registerObserve()
        initNativeAds()
        initView()
        initOnClickListener()
    }

    private fun initNativeAds(){
//        val count = 3 - AppAdmob.listNativeAd.size
//        if(count<=0) return
//        for (i in 0 until count){
//            lifecycleScope.launch(Dispatchers.IO) {
//                NativeManager.createNativesAds(this@BaseActivity, object : AdNativeListening() {
//                    override fun onNativeAdLoaded(p0: NativeAd) {
//                        if (isDestroyed || isFinishing || isChangingConfigurations) {
//                            p0.destroy()
//                            return
//                        }
//                        AppAdmob.listNativeAd.add(p0)
//                    }
//                })
//            }
//        }
    }



    private val requestStoragePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        storagePermissionResult?.invoke(it)
    }
    private fun haveStoragePermission(): Boolean {
        return if (VersionUtils.hasMarshmallow()) {
            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    fun requestStoragePermission(storagePermissionResult:((Boolean)->Unit)){
        this.storagePermissionResult = storagePermissionResult
        if (haveStoragePermission()){
            this.storagePermissionResult?.invoke(true)

        } else{
            requestStoragePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }
    private var storagePermissionResult:((Boolean)->Unit)? = null

    protected fun hideSystemUi() {
        if(supportFragmentManager.isDestroyed || supportFragmentManager.isStateSaved) return
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    override fun onResume() {
        hideSystemUi()
        cemAdManager.loadInterstitialByPlacements(this, FULL_KEY_BACK)
        super.onResume()
        loadAds()
        checkAndOpenNetworkSettingsIfNeeded()
    }

    private fun checkAndOpenNetworkSettingsIfNeeded() {
        if(screenName != VIEW_SPLASH_ACTIVITY){
            if(isShowNetwork){

                if (checkNetwork()) {
                    isShowNetwork = false
                    val dialogNetwork = DialogNetwork.newInstance()
                    dialogNetwork.onClickItemListener = {
                        if(it){
                            openNetworkSettings()
                        }
                    }
                    showDialogFragment(dialogNetwork)
                }
            }
        }
    }

    fun addFragment(frameContainer: FrameLayout, frag: Fragment) {
        try {
            val fragTransaction = supportFragmentManager.beginTransaction()
            fragTransaction.add(frameContainer.id, frag)
            fragTransaction.addToBackStack(null)
            fragTransaction.commitAllowingStateLoss()
        } catch (ex: RuntimeException) {
            Log.e("Error", "cannot init fragment: ${ex.message}")
        } catch (ex: NoSuchMethodException) {
            Log.e("Error", "cannot init fragment: ${ex.message}")
        }
    }

    fun checkNetwork(): Boolean{
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val isWiFiEnabled =
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)?.isConnectedOrConnecting ?: false
        val isMobileDataEnabled =
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)?.isConnectedOrConnecting ?: false

        return (!isWiFiEnabled && !isMobileDataEnabled)
    }

    private fun openNetworkSettings() {
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        if (intent.resolveActivity(this.packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun showDialogFragment(fragment: DialogFragment?) {
        if (fragment == null || fragment.isAdded) {
            return
        }
        if (supportFragmentManager.findFragmentByTag(fragment.tag) != null) {
            return
        }
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if(hasFocus){
            hideSystemUi()
        }
        super.onWindowFocusChanged(hasFocus)
    }

    fun initMedia(){
        val afd = assets.openFd("BgMusic.mp3")
        mediaPlayerBg.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        afd.close()
        mediaPlayerBg.prepare()
        mediaPlayerBg.isLooping = true
    }

    fun playMedia(){
        mediaPlayerBg.start()
    }

    fun pauseMedia(){
        mediaPlayerBg.pause()
    }

    fun destroyMedia(){
        mediaPlayerBg.destroy()
    }
}