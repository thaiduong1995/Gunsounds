package com.cemsofwave.gunsimulator.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.data.model.LightSaberModel
import com.cemsofwave.gunsimulator.utils.ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by duong_tt on 10/24/2023.
 * Email: tranthaiduong.kailoren@gmail.com
 * Github: https://github.com/thaiduong1995
 */
@HiltViewModel
class ImageViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _listBgBitmapFlow: MutableStateFlow<List<Bitmap>> = MutableStateFlow(listOf())
    val listBgBitmapFlow: StateFlow<List<Bitmap>> = _listBgBitmapFlow

    private val _backgroundBitmapFlow: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val backgroundBitmapFlow: StateFlow<Bitmap?> = _backgroundBitmapFlow

    private val _thumbBitmapFlow: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val thumbBitmapFlow: StateFlow<Bitmap?> = _thumbBitmapFlow

    fun resizeListBg(list: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            val listBitmap = mutableListOf<Bitmap>()
            list.forEach { resourceId ->
                try{
                    ContextCompat.getDrawable(context, resourceId)?.toBitmap()?.let { bitmap ->
                        listBitmap.add(bitmap)
                    }
                }catch (e: OutOfMemoryError){
                    e.printStackTrace()
                }
            }
            _listBgBitmapFlow.value = listBitmap
        }
    }

    fun loadBackgroundBitmap(backgroundId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val width = context.resources.displayMetrics.widthPixels
            val height = context.resources.displayMetrics.heightPixels
            Glide.with(context).clearOnStop().asBitmap().load(backgroundId)
                .override(width, height)
                .centerCrop()
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        _backgroundBitmapFlow.value = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}

                })
        }
    }

    fun loadThumbBitmapFromAsset(path: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // get input stream
                val ims: InputStream = context.assets.open(path)
                // load image as Drawable
                Drawable.createFromStream(ims, null)?.let { drawable ->
                    _thumbBitmapFlow.value = drawable.toBitmap()
                }
                ims.close()

            } catch (ex: IOException) {
                Log.e(ERROR, "load image failed: ${ex.message}")
            }
        }
    }
}