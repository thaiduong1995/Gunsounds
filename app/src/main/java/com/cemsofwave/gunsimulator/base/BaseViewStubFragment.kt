package com.trinhbx.base.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.cemsofwave.gunsimulator.databinding.FragmentViewstubBinding
import com.trinhbx.base.model.AppInteractiveEvent
import com.trinhbx.base.utils.VersionUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.EventBusException

/**
 * Created by Trinh BX on 01/02/2023.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 */
abstract class BaseViewStubFragment<T : ViewDataBinding> : BaseFragment() {
    private lateinit var stubBinding: FragmentViewstubBinding
    private var mSavedInstanceState: Bundle? = null
    private var hasInflated = false
    private var mViewStub: ViewStub? = null
    private var visible = false
    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        stubBinding = FragmentViewstubBinding.inflate(inflater, container, false)
        mViewStub = stubBinding.fragmentViewStub.viewStub
        mViewStub?.layoutResource = getViewStubLayoutResource()
        mSavedInstanceState = savedInstanceState

        if (visible && !hasInflated) {
            val inflatedView = mViewStub?.inflate()
            inflatedView?.let {
                DataBindingUtil.bind<T>(inflatedView)?.let {
                    onCreateViewAfterViewStubInflated(it, inflatedView,
                        mSavedInstanceState)
                    hasInflated = true
                }
            }
        }
        return stubBinding.root
    }

    private fun onCreateViewAfterViewStubInflated(binding: T, inflatedView: View, savedInstanceState: Bundle?){
        this.binding = binding
        getData()
        registerObserve()
        initView()
        addOnClickListener()
    }
    protected open fun getData(){}
    protected open fun registerObserve(){}
    @LayoutRes
    protected abstract fun getViewStubLayoutResource(): Int
    protected abstract fun initView()
    protected abstract fun addOnClickListener()

    override fun onResume() {
        super.onResume()
        visible = true
        if (mViewStub != null && !hasInflated) {
            val inflatedView = mViewStub?.inflate()
            inflatedView?.let {
                 DataBindingUtil.bind<T>(inflatedView)?.let {
                    onCreateViewAfterViewStubInflated(it, inflatedView,
                        mSavedInstanceState)
                     hasInflated = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hasInflated = false
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
    }

    override fun onPause() {
        super.onPause()
        visible = false
    }

    override fun onDetach() {
        super.onDetach()
        hasInflated = false
    }

    private var storagePermissionResult: ((Boolean) -> Unit)? = null
    private val requestStoragePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            storagePermissionResult?.invoke(it)
        }

    fun requestStoragePermission(context: Context, storagePermissionResult: ((Boolean) -> Unit)) {
        fun haveStoragePermission(): Boolean {
            return if (VersionUtils.hasMarshmallow()) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
        }
        this.storagePermissionResult = storagePermissionResult
        if (haveStoragePermission()) {
            this.storagePermissionResult?.invoke(true)

        } else {
            requestStoragePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }
    override fun onStart() {
        super.onStart()
        try {
            if(!EventBus.getDefault().isRegistered(this)){
                EventBus.getDefault().register(this)
            }
        }catch (e: EventBusException){
            e.printStackTrace()
        }
    }

    abstract fun onEventsBusListener(event: AppInteractiveEvent)

//    protected fun btnInAppClicked(view: View){
//        if(DataLocal.isVip()){
//            view.invisible()
//        }
//        view.setOnSingleClickListener {
//            PurchaseManager.instance?.setCallback(object : PurchaseCallback {
//                override fun purchaseSuccess() {
//                    activity?.runOnUiThread {
//                        DataLocal.setIsVip(true)
//                        EventBus.getDefault().post(AppInteractiveEvent(AppInteractiveEvent.ACTION_HIDE_IN_APP_BTN))
//                        context?.toastMessageShortTime(getString(R.string.notification_purchase_success))
//                    }
//                }
//
//                override fun purchaseFail() {
//                    activity?.runOnUiThread {
//                        context?.toastMessageShortTime(getString(R.string.notification_purchase_failed))
//                    }
//                }
//            })
//            PurchaseManager.instance?.purchase(activity, PurchaseManager.PRODUCT_PURCHASE)
//        }
//    }
}