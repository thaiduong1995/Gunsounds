package com.cemsofwave.gunsimulator.extension

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.util.TypedValue
import android.widget.Toast
import androidx.datastore.preferences.preferencesDataStore
import com.cemsofwave.gunsimulator.base.utils.PreferenceHelper
import com.cemsofwave.gunsimulator.base.utils.SettingsSharedPref
import java.util.Locale
import kotlin.math.roundToInt

/**
 * Created by duong_tt on 10/20/2023.
 * Email: tranthaiduong.kailoren@gmail.com
 * Github: https://github.com/thaiduong1995
 */
val Context.dataStore by preferencesDataStore(name = "DATA_STORE")

fun Context.dpToPx(value: Int): Int {
    val metrics = this.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), metrics)
        .roundToInt()
}
@SuppressLint("NewApi")
internal fun Drawable.toBitmap(): Bitmap {
    return when (this) {
        is BitmapDrawable -> bitmap
        is VectorDrawable -> toBitmap()
        else -> throw IllegalArgumentException("Unsupported drawable type")
    }
}
fun Context.configLanguage() {
    val config = resources.configuration
    val lang = SettingsSharedPref.getInstance(this).language.code
    if (lang.isNotEmpty()) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        config.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
fun Context?.toast(message: String) {
    this?.let {
        Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
    }
}