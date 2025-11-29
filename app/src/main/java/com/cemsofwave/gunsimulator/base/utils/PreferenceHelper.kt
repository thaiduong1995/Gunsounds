package com.cemsofwave.gunsimulator.base.utils

import android.content.Context
import android.content.SharedPreferences

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Created by TrinhBX.
 * Mail: trinhbx196@gmail.com
 * Phone: +08 988324622
 * @since Date: 9/3/23
 **/

@Singleton
class PreferenceHelper @Inject constructor(@ApplicationContext private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
    companion object {
        private const val SHARED_PREFERENCE = "SHARED_PREFERENCE"

        private const val IS_SNOWING = "IS_SNOWING"
        private const val IS_SNOWING_DEFAULT = true
        private const val TIME_SHOW_RATE = "TIME_SHOW_RATE"
        private const val IS_SHOW_RATE = "IS_SHOW_RATE"
        private const val NUMBER_SHOW_RATE = "NUMBER_SHOW_RATE"
    }
    fun save(key: String, value: Any) {
        val editor = sharedPreferences.edit()
        when (value) {
            is Boolean -> {
                editor.putBoolean(key, value)
            }

            is Int -> {
                editor.putInt(key, value)
            }

            is String -> {
                editor.putString(key, value)
            }

            is Float -> {
                editor.putFloat(key, value)
            }

            is Long -> {
                editor.putLong(key, value)
            }

            else -> {
                editor.putString(key, value.toString())
            }
        }
        editor.apply()
    }

    fun get(key: String, type: Any) : Any? {
        when(type){
            is Boolean -> {
                return sharedPreferences.getBoolean(key, false)
            }
            is Int -> {
                return sharedPreferences.getInt(key, -1)
            }

            is Float -> {
                return sharedPreferences.getFloat(key, -1f)
            }

            is Long -> {
                return sharedPreferences.getLong(key, -1)
            }

            is String -> {
                return sharedPreferences.getString(key, "")
            }
        }
        return null
    }

    var timeShowRate: Long
        get() {
            return sharedPreferences.getLong(TIME_SHOW_RATE, 0)
        }
        set(timeShowRate) {
            println("Đã vào đây: $timeShowRate haha")
            sharedPreferences.edit().putLong(TIME_SHOW_RATE, timeShowRate).apply()
        }

    var isShowRate: Boolean
        get() {
            return sharedPreferences.getBoolean(IS_SHOW_RATE, false)
        }
        set(isShowRate) {
            sharedPreferences.edit().putBoolean(IS_SHOW_RATE, isShowRate).apply()
        }

    var numberShowRate: Int
        get() {
            return sharedPreferences.getInt(NUMBER_SHOW_RATE, 0)
        }
        set(numberShowRate) {
            sharedPreferences.edit().putInt(NUMBER_SHOW_RATE, numberShowRate).apply()
        }
}