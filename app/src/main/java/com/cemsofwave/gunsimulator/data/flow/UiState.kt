/*
 * *
 *  * Created by duonglkh on 11/10/23, 11:26 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 11/10/23, 11:26 AM
 *
 */

sealed class UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>()

    data class Error(val exception: Throwable? = null)
        : UiState<Nothing>()

    object Loading : UiState<Nothing>()
}