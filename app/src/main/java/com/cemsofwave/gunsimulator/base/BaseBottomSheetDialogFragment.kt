package com.trinhbx.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber

abstract class BaseBottomSheetDialogFragment<VB: ViewBinding>: BottomSheetDialogFragment() {

    abstract fun binding(viewGroup: ViewGroup?, attachToParent: Boolean):VB
    open fun getData(data: Bundle?){}
    open fun registerObserve(){}
    abstract fun initView()
    abstract fun initOnClickListener()

    lateinit var binding:VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = binding(container, false)
        Timber.e("onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.e("onViewCreated")
        registerObserve()
        initView()
        initOnClickListener()
    }
}