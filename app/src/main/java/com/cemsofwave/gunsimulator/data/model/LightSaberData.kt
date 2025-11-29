package com.cemsofwave.gunsimulator.data.model

import androidx.annotation.Keep

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 18/10/2023
 */
@Keep
data class LightSaberData(
    var name: String,
    var folderName: String,
    var location: String,
    var placeOfOrigin: String,
    var inService: String,
    var designed:String,
    var length: String,
    var timeOneShot: Int,
    var bulletCount: Int,
    var animateWidth: Int,
    var animateHeight: Int,
    var soundStart: String,
    var sound: String,
    var soundEnd: String,
    var listEffect: List<String>
)
