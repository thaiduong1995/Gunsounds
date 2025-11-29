package com.trinhbx.base.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Created by Trinh BX on 01/02/2023.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 */
open class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initUi()
        initListener()
    }

    open fun initData() {}
    open fun initUi() {}
    open fun initListener() {}
}