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
data class ShockTaserData(
    var name: String,
    var folderName: String,
    var location: String,
    var sound: String,
    var effect: String
)
