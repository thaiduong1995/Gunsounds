package com.trinhbx.base.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentDialog
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.cem.firebase_module.analytics.CemAnalytics
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.utils.CLICK_BACK

/**
 * Created by Trinh BX on 02/02/2023.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 */
abstract class BaseDialogFragment<VB: ViewBinding>: DialogFragment(){
    var TAG = javaClass.simpleName
    lateinit var binding:VB
    protected var screenName:String? = null
    protected abstract fun provideScreenName():String
    abstract fun binding(viewGroup: ViewGroup?, attachToParent: Boolean):VB

    open fun getData(){}
    open fun registerObserve(){}
    abstract fun initView()
    open fun loadAds(){}
    abstract fun initOnClickListener()
    protected open fun registerEventBackPress(){

    }
    private var onBackPressed:(()->Unit)? = null
    protected fun backPress(){
        (dialog as? ComponentDialog)?.onBackPressedDispatcher?.onBackPressed()
    }
    protected fun setOnBackPressedListener(onBackPressed:(()->Unit)){
        this.onBackPressed = onBackPressed
    }
    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        screenName = provideScreenName()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        CemAnalytics.logEventShowScreen(context,screenName)
        registerObserve()
        initView()
        initOnClickListener()
        (dialog as? ComponentDialog)?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                CemAnalytics.logEventClickView(context,screenName, CLICK_BACK)
                if(onBackPressed==null){
                    dismiss()
                } else{
                    onBackPressed?.invoke()
                }
            }
        })
    }

    private val requestStoragePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        storagePermissionResult?.invoke(it)
    }
    private fun haveStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT in 23..32) {
            context?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
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
        if(activity?.supportFragmentManager?.isDestroyed==true || activity?.supportFragmentManager?.isStateSaved==true) return
        dialog?.window?.let {
            WindowCompat.setDecorFitsSystemWindows(it, false)
            WindowInsetsControllerCompat(it, binding.root).let { controller ->
                controller.hide(WindowInsetsCompat.Type.systemBars())
                controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        loadAds()
    }
}