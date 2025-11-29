package com.cemsofwave.gunsimulator.ui.dialog

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.databinding.DialogVoteBinding
import com.cemsofwave.gunsimulator.utils.GUN_INFO
import com.trinhbx.base.extension.setOnSingleClickListener
import com.trinhbx.base.ui.BaseDialogFragment


class VoteDialog : BaseDialogFragment<DialogVoteBinding>() {

    private var onRequestListener: ((Boolean, Int) -> Unit)? = null
    companion object {

        @JvmStatic
        fun newInstance() = VoteDialog().apply {
            arguments = bundleOf()
        }
        @JvmStatic
        fun show(activity:FragmentActivity?, fromScreenName:String?,  onRequestListener: ((Boolean, Int) -> Unit)?){
            if(activity == null  || activity.supportFragmentManager.isStateSaved) return
            newInstance().apply {
                arguments = Bundle().apply {}
                this.onRequestListener = onRequestListener
            }.show(activity.supportFragmentManager,null)
        }
    }

    override fun provideScreenName(): String {
        return GUN_INFO
    }

    override fun binding(viewGroup: ViewGroup?, attachToParent: Boolean): DialogVoteBinding {
        return DialogVoteBinding.inflate(layoutInflater,viewGroup,attachToParent)
    }

    override fun initView() {
        binding.apply {
            btnCancel.setOnSingleClickListener {
                onRequestListener?.invoke(false, 0)
                dismiss()
            }
            btnVote.setOnSingleClickListener {
                if(ratingView.getReviewScore().toInt() >= 4){
                    onRequestListener?.invoke(true, ratingView.getReviewScore().toInt())
                }else{
                    onRequestListener?.invoke(false, ratingView.getReviewScore().toInt())
                }
                dismiss()
            }
        }
    }

    override fun initOnClickListener() {}

    override fun getTheme(): Int {
        return R.style.FullScreenDialog_Transparent
    }

}