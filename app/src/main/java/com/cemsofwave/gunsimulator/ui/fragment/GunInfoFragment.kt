package com.cemsofwave.gunsimulator.ui.fragment

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.data.model.GunModel
import com.cemsofwave.gunsimulator.databinding.FragmentGunInfoBinding
import com.cemsofwave.gunsimulator.utils.GUN_INFO
import com.cemsofwave.gunsimulator.utils.SimulatorType
import com.trinhbx.base.extension.getArgumentParcelable
import com.trinhbx.base.ui.BaseDialogFragment


class GunInfoFragment : BaseDialogFragment<FragmentGunInfoBinding>() {
    companion object {
        private const val EXTRA_GUN_MODEL = "EXTRA_GUN_MODEL"
        private const val EXTRA_GUN_SKIN_POS = "EXTRA_GUN_SKIN_POS"

        @JvmStatic
        fun newInstance(gunModel: GunModel?, skinPos:Int, fromScreenName:String?) = GunInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_GUN_MODEL, gunModel)
                putInt(EXTRA_GUN_SKIN_POS, skinPos)
            }
        }

        @JvmStatic
        fun show(activity:FragmentActivity?, gunModel: GunModel?, skinPos:Int, fromScreenName:String?){
            if(activity == null || gunModel == null || activity.supportFragmentManager.isStateSaved) return
            newInstance(gunModel, skinPos, fromScreenName).apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_GUN_MODEL, gunModel)
                    putInt(EXTRA_GUN_SKIN_POS, skinPos)
                }
            }.show(activity.supportFragmentManager,null)
        }
    }

    override fun provideScreenName(): String {
        return GUN_INFO
    }

    override fun binding(viewGroup: ViewGroup?, attachToParent: Boolean): FragmentGunInfoBinding {
        return FragmentGunInfoBinding.inflate(layoutInflater,viewGroup,attachToParent)
    }

    override fun initView() {
        binding.apply {
            arguments?.getArgumentParcelable<GunModel>(EXTRA_GUN_MODEL)?.let { gunModel->
                val pos= arguments?.getInt(EXTRA_GUN_SKIN_POS)?:0
                gunView.setAnimation(gunModel.listSkin.getOrNull(pos))
                tvName.text = gunModel.name
                tvOriginValue.text = if(gunModel.placeOfOrigin.length > 16) gunModel.placeOfOrigin.substring(0, 16).plus("...") else gunModel.placeOfOrigin
                tvServiceValue.text = if(gunModel.inService.length > 16) gunModel.inService.substring(0, 16).plus("...") else gunModel.inService
                tvDesignedValue.text = if(gunModel.designed.length > 16) gunModel.designed.substring(0, 16).plus("...") else gunModel.designed
                tvLengthValue.text = if(gunModel.length.length > 26) gunModel.length.substring(0, 26).plus("...") else gunModel.length
            }
        }
    }

    override fun initOnClickListener() {
        binding.apply {
            btnClose.setOnClickListener { backPress() }
        }
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog_Transparent
    }

}