package com.cemsofwave.gunsimulator.utils

import android.content.Context
import com.cemsofwave.gunsimulator.data.model.ExplosionData
import com.cemsofwave.gunsimulator.data.model.ExplosionModel
import com.cemsofwave.gunsimulator.data.model.GunData
import com.cemsofwave.gunsimulator.data.model.GunModel
import com.cemsofwave.gunsimulator.data.model.LightSaberData
import com.cemsofwave.gunsimulator.data.model.LightSaberModel
import com.cemsofwave.gunsimulator.data.model.ShockTaserData
import com.cemsofwave.gunsimulator.data.model.ShockTaserModel
import com.cemsofwave.gunsimulator.data.model.toModel
import com.google.gson.GsonBuilder
import com.trinhbx.base.extension.getAssetJsonData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class JsonHelper @Inject constructor(
    @ApplicationContext val context: Context,
) {
    fun getGunCollection(): Flow<List<GunModel>> {
        return flow {
            val jsonData = context.getAssetJsonData("gun/list_gun.json")
            val gson = GsonBuilder().create()
            val responseObject: Array<GunData> = gson.fromJson(jsonData, Array<GunData>::class.java)
            val listSong = responseObject.map {
                it.toModel()
            }
            emit(listSong)
        }.flowOn(Dispatchers.IO)
    }

    fun getShockTaserCollection(): Flow<List<ShockTaserModel>> {
        return flow {
            val jsonData = context.getAssetJsonData("shock_taser/list_shock_taser.json")
            val gson = GsonBuilder().create()
            val responseObject: Array<ShockTaserData> =
                gson.fromJson(jsonData, Array<ShockTaserData>::class.java)
            val listShockTaser = responseObject.map {
                it.toModel()
            }
            emit(listShockTaser)
        }.flowOn(Dispatchers.IO)
    }

    fun getLightSaberCollection(): Flow<List<LightSaberModel>> {
        return flow {
            val jsonData = context.getAssetJsonData("light_saber/list_light_saber.json")
            val gson = GsonBuilder().create()
            val responseObject: Array<LightSaberData> =
                gson.fromJson(jsonData, Array<LightSaberData>::class.java)
            val listLightSaber = responseObject.map {
                it.toModel()
            }
            emit(listLightSaber)
        }.flowOn(Dispatchers.IO)
    }
    fun getExplosionCollection(): Flow<List<ExplosionModel>> {
        return flow {
            val jsonData = context.getAssetJsonData("bomb/bomb.json")
            val gson = GsonBuilder().create()
            val responseObject: Array<ExplosionData> =
                gson.fromJson(jsonData, Array<ExplosionData>::class.java)
            val listExplosion = responseObject.map {
                it.toModel()
            }
            emit(listExplosion)
        }.flowOn(Dispatchers.IO)
    }
}