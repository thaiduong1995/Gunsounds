package com.cemsofwave.gunsimulator.ui.fragment

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.databinding.FragmentCompanyInfoBinding
import com.cemsofwave.gunsimulator.utils.VIEW_POLICY
import com.trinhbx.base.extension.getArgumentSerializable
import com.trinhbx.base.ui.BaseDialogFragment

/**
 * Created by Trinh BX on 09/02/2023.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 */
class CompanyInfoFragment: BaseDialogFragment<FragmentCompanyInfoBinding>() {
    enum class Flag{
        TERM_OF_USE,PRIVACY_POLICY
    }
    companion object{
        private const val EXTRA_FLAG = "EXTRA_FLAG"
        @JvmStatic
        fun show(activity: FragmentActivity?,flag: Flag,fromScreenName:String?){
            if(activity==null || activity.supportFragmentManager.isStateSaved) return
            CompanyInfoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(EXTRA_FLAG,flag)
                }
            }.show(activity.supportFragmentManager,null)
        }
    }
    private var flag = Flag.PRIVACY_POLICY
    override fun provideScreenName(): String {
        return VIEW_POLICY
    }

    override fun binding(
        viewGroup: ViewGroup?,
        attachToParent: Boolean
    ): FragmentCompanyInfoBinding {
        return FragmentCompanyInfoBinding.inflate(layoutInflater,viewGroup,attachToParent)
    }

    override fun getData() {
        arguments?.getArgumentSerializable<Flag>(EXTRA_FLAG)?.let {
            flag = it
        }
    }
    override fun initView() {
        binding.apply {
            context?.let {
                wvContent.setBackgroundColor(ContextCompat.getColor(it, R.color.window_color_2))
            }
            if(flag== Flag.TERM_OF_USE){
                tvTitle.text = getString(R.string.term_of_use)
                wvContent.loadUrl("file:///android_asset/term_of_use.html")
            } else{
                tvTitle.text = getString(R.string.policy_private)
                wvContent.loadUrl("file:///android_asset/privacy_policy.html")
            }
        }
    }

    override fun initOnClickListener() {
        binding.btnBack.setOnClickListener {
            backPress()
        }
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog_Transparent
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
    }
}