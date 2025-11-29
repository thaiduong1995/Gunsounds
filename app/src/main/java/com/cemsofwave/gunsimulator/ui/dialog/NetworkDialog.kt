package com.cemsofwave.gunsimulator.ui.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.databinding.DialogDisconnectedBinding
import com.cemsofwave.gunsimulator.utils.NETWORK_DIALOG
import com.trinhbx.base.ui.BaseDialogFragment

class DialogNetwork : BaseDialogFragment<DialogDisconnectedBinding>() {

    var onClickItemListener: ((Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun provideScreenName(): String {
        return NETWORK_DIALOG
    }

    override fun binding(
        viewGroup: ViewGroup?,
        attachToParent: Boolean
    ): DialogDisconnectedBinding {
        return DialogDisconnectedBinding.inflate(layoutInflater, viewGroup, attachToParent)
    }

    override fun initView() {

    }

    override fun initOnClickListener() {
        binding.apply {
            btnCancel.setOnClickListener {
                onClickItemListener?.invoke(false)
                dismiss()
            }
            btnTurnOnNetwork.setOnClickListener {
                onClickItemListener?.invoke(true)
                dismiss()
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog_Transparent
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onClickItemListener?.invoke(false)
    }
    companion object {

        @JvmStatic
        fun newInstance() = DialogNetwork().apply {
            arguments = bundleOf()
        }
    }

}