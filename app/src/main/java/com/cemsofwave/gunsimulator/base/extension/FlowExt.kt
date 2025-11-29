package com.trinhbx.base.extension

import com.trinhbx.base.model.MutableStateLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

/**
 * Created by Trinh BX on 02/02/2023.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 */
suspend fun <T> Flow<T>.collectToSateLiveData(liveData: MutableStateLiveData<T>) {
    this.flowOn(Dispatchers.IO)
        .catch {
            Timber.d("Trinh BX", "catch: ${Thread.currentThread().name}")
            Timber.d("Trinh BX", "catch: ${it.message}")
            liveData.postError(it.message)
        }
        .onStart {
            liveData.postLoading()
        }.collect {
            Timber.d("Trinh BX", "postSuccess: ${Thread.currentThread().name}")
            liveData.postSuccess(it)
        }
}