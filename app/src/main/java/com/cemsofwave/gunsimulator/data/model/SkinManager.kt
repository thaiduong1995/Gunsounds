package com.cemsofwave.gunsimulator.data.model

import androidx.annotation.Keep
import androidx.room.Entity
import com.cem.admodule.data.BaseResponseModel
import com.cemsofwave.gunsimulator.utils.SimulatorType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonDataException
import kotlinx.parcelize.Parcelize

@Entity(primaryKeys = ["nameSkin"])
@Keep
data class SkinManager(
    @Json(name = "name") var nameGun: String,
    @Json(name = "nameSkin") var nameSkin: String,
    @Json(name = "position") var position: Int,
    @Json(name = "lock") var lock: Boolean
) : BaseResponseModel(){
    companion object{

        private val adapter = moshi.adapter(SkinManager::class.java)
        @JvmStatic
        fun fromJson(json: String?): SkinManager? {
            return try {
                if (json.isNullOrBlank()) return null
                adapter.fromJson(json)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
        @JvmStatic
        fun convertSkinManagerToJson(skin: SkinManager?): String {
            return try {
                val adapter = moshi.adapter(SkinManager::class.java)
                adapter.toJson(skin)
            } catch (e: JsonDataException) {
                e.printStackTrace()
                ""
            }
        }
    }
}
