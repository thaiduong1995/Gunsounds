package com.cemsofwave.gunsimulator.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.cem.admodule.ext.ConstAd.NATIVE_INTRO
import com.cem.admodule.manager.CemAdManager
import com.cemsofwave.gunsimulator.databinding.FragmentPreviewTwoBinding
import com.cemsofwave.gunsimulator.event.OnPreviewListener
import com.cemsofwave.gunsimulator.ui.activity.FlashActivity
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.ui.BaseFragment

class PreviewTwoFragment : BaseFragment() {

    private lateinit var binding: FragmentPreviewTwoBinding
    private var onPreviewListener: OnPreviewListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onPreviewListener = activity as? FlashActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPreviewTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUi() {}

    override fun initListener() {
        binding.btnContinue.setOnSingleClickListener {
            onPreviewListener?.onClick(OnPreviewListener.PREVIEW_TWO)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.nativeView.apply {
            CemAdManager.getInstance(requireContext()).loadAndShowNativeByPlacement(context, this, NATIVE_INTRO,"PreviewTwoFragment")
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = PreviewTwoFragment().apply {
            arguments = bundleOf()
        }
    }
}