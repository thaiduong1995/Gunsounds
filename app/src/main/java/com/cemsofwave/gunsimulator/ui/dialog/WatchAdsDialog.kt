package com.cemsofwave.gunsimulator.ui.dialog

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.data.model.GunModel
import com.cemsofwave.gunsimulator.databinding.DiaglogWatchAdsBinding
import com.cemsofwave.gunsimulator.databinding.FragmentGunInfoBinding
import com.cemsofwave.gunsimulator.utils.GUN_INFO
import com.trinhbx.base.extension.getArgumentParcelable
import com.trinhbx.base.ui.BaseDialogFragment


class WatchAdsDialog : BaseDialogFragment<DiaglogWatchAdsBinding>() {

    private var onAdsRequestListener: ((Boolean) -> Unit)? = null
    companion object {
        private const val EXTRA_GUN_MODEL = "EXTRA_GUN_MODEL"
        private const val EXTRA_GUN_SKIN_POS = "EXTRA_GUN_SKIN_POS"

        @JvmStatic
        fun newInstance() = WatchAdsDialog().apply {
            arguments = bundleOf()
        }
        @JvmStatic
        fun show(activity:FragmentActivity?, gunModel: GunModel?, skinPos:Int, fromScreenName:String?,  onAdsRequestListener: ((Boolean) -> Unit)?){
            if(activity == null || gunModel == null || activity.supportFragmentManager.isStateSaved) return
            newInstance().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_GUN_MODEL, gunModel)
                    putInt(EXTRA_GUN_SKIN_POS, skinPos)
                }
                this.onAdsRequestListener = onAdsRequestListener
            }.show(activity.supportFragmentManager,null)
        }
    }

    override fun provideScreenName(): String {
        return GUN_INFO
    }

    override fun binding(viewGroup: ViewGroup?, attachToParent: Boolean): DiaglogWatchAdsBinding {
        return DiaglogWatchAdsBinding.inflate(layoutInflater,viewGroup,attachToParent)
    }

    override fun initView() {
        binding.apply {
            arguments?.getArgumentParcelable<GunModel>(EXTRA_GUN_MODEL)?.let { gunModel->
                val pos= arguments?.getInt(EXTRA_GUN_SKIN_POS)?:0
                tvName.text = gunModel.name
                gunView.setAnimation(gunModel.listSkin.getOrNull(pos))
            }
        }
    }

    override fun initOnClickListener() {
        binding.apply {
            btnClose.setOnClickListener {
                onAdsRequestListener?.invoke(false)
                backPress()
            }
            btnWatchAds.setOnClickListener {
                onAdsRequestListener?.invoke(true)
                dismiss()
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog_Transparent
    }

}