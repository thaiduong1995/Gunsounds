package com.trinhbx.base.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

/**
 * Created by Trinh BX on 01/02/2023.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 */

open class BaseViewModel:ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var showMessageLiveData = MutableLiveData<String>()

    protected fun showLoading(isShow: Boolean) {
        isLoading.postValue(isShow)
    }

    protected fun showMessage(message: String) {
        showMessageLiveData.postValue(message)
    }
}