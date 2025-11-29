package com.cemsofwave.gunsimulator.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.cemsofwave.gunsimulator.base.utils.PreferenceHelper
import com.cemsofwave.gunsimulator.data.database.AppDatabase
import com.cemsofwave.gunsimulator.data.model.SkinManager
import com.cemsofwave.gunsimulator.data.model.TimeManager
import com.cemsofwave.gunsimulator.extension.dataStore
import com.cemsofwave.gunsimulator.utils.JsonHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 16/10/2023
 */
@Singleton
class Repository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: AppDatabase,
    private val jsonHelper: JsonHelper,
    private val preferenceHelper: PreferenceHelper,
) {
    fun insertSkin(skinManager: SkinManager): Long{
        return database.skinDao().insertSkin(skinManager)
    }
    fun getSkin(): Flow<List<SkinManager>>{
       return database.skinDao().getAllSkin()
    }

    fun editSkin(skinManager: SkinManager): Int{
        return database.skinDao().updateSkin(skinManager)
    }

    fun getIsShowRate(): Boolean{
        return preferenceHelper.isShowRate
    }

    fun setIsShowRate(isShowRate: Boolean){
        preferenceHelper.isShowRate = isShowRate
    }

    fun getNumberShowRate(): Int{
        return preferenceHelper.numberShowRate
    }

    fun setNumberShowRate(numberShowRate: Int){
        preferenceHelper.numberShowRate = numberShowRate
    }

    suspend fun setInstallFirst(isInstallFirst: Boolean) {
        context.dataStore.edit {
            it[IS_INSTALL_FIRST] = isInstallFirst
        }
    }

    val installFirst: Flow<Boolean> = context.dataStore.data.catch { exception ->
        if (exception is IOException) {
            Timber.tag("ERROR")
                .e("check install first failed: ${exception.message}")
            emit(emptyPreferences())
        } else {
            emit(emptyPreferences())
            throw exception
        }
    }.map { preference ->
        preference[IS_INSTALL_FIRST] ?: true
    }

    suspend fun setTimeReloadNative(timeManager: TimeManager) {
        val json = TimeManager.convertTipManagerToJson(timeManager)
        context.dataStore.edit {
            it[IS_RELOAD_NATIVE] = json
        }
    }

    val timeReloadNative: Flow<TimeManager?> = context.dataStore.data
        .catch { ex ->
            if (ex is IOException) {
                emit(emptyPreferences())
            } else {
                throw ex
            }
        }
        .map { preferences ->
            val json = preferences[IS_RELOAD_NATIVE] ?: ""
            TimeManager.fromJson(json)
        }

    suspend fun setTimeShowRate(number: Int) {
        context.dataStore.edit {
            it[TIME_SHOW_RATE] = number
        }
    }

    val timeShowRate: Flow<Int?> = context.dataStore.data
        .catch { ex ->
            if (ex is IOException) {
                emit(emptyPreferences())
            } else {
                throw ex
            }
        }
        .map { preferences ->
            preferences[TIME_SHOW_RATE] ?: 0
        }

    companion object{
        val IS_INSTALL_FIRST = booleanPreferencesKey("IS_INSTALL_FIRST")
        val IS_RELOAD_NATIVE = stringPreferencesKey("IS_RELOAD_NATIVE")
        val SKIN_GUN_ADS = stringPreferencesKey("SKIN_GUN_ADS")
        val TIME_SHOW_RATE = intPreferencesKey("TIME_SHOW_RATE")
    }
}