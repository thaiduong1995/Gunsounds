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
data class GunData(
    var name: String,
    var folderName: String,
    var placeOfOrigin: String,
    var inService: String,
    var designed:String,
    var length: String,
    var timeOneShot: Int,
    var bulletCount: Int,
    var headX: Int,
    var headY: Int,
    var oneShotSound: String,
    var autoShotSound: String?,
    var fireFile: String,
    var bulletShell: String,
    var bulletType: String,
    var listSkin: List<String>,
    var speedAutoShot: Int,
    var speedBrushShot: Int
)
