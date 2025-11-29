package com.cemsofwave.gunsimulator.base.utils

import android.content.Context
import android.content.SharedPreferences
import com.cemsofwave.gunsimulator.R
import com.cemsofwave.gunsimulator.base.model.Language
import java.util.Locale

class SettingsSharedPref {
    companion object {
        val listLanguage = arrayListOf(
            Language(name = "English", code = "en", R.drawable.ic_english),
            Language(name = "French", code = "fr", R.drawable.ic_french),
            Language(name = "German", code = "de", R.drawable.ic_german),
            Language(name = "Spanish", code = "es", R.drawable.ic_spainish),
            Language(name = "Italy", code = "pt", R.drawable.ic_italian)
        )
        private val sharePref = SettingsSharedPref()
        private lateinit var sharedPreferences: SharedPreferences
        private const val SHARE_PREF_NAME = "Setting Shared Preferences"

        private const val LANGUAGE = "LANGUAGE"
        private val LANGUAGE_DEFAULT = listLanguage[0]

        @JvmStatic
        fun getInstance(context: Context): SettingsSharedPref {
            if (!Companion::sharedPreferences.isInitialized) {
                synchronized(SettingsSharedPref::class.java) {
                    if (!Companion::sharedPreferences.isInitialized) {
                        sharedPreferences = context.getSharedPreferences(
                            SHARE_PREF_NAME,
                            Context.MODE_PRIVATE
                        )
                    }
                }
            }
            return sharePref
        }
    }

    var language: Language
        get() {
            val value = sharedPreferences.getString(LANGUAGE, Locale.getDefault().language)
            return try {
                listLanguage.first {
                    it.code == value
                }
            } catch (e: Exception) {
                sharedPreferences.edit().putString(LANGUAGE, LANGUAGE_DEFAULT.code).apply()
                LANGUAGE_DEFAULT
            }
        }
        set(language) {
            sharedPreferences.edit().putString(LANGUAGE, language.code).apply()
        }
}
