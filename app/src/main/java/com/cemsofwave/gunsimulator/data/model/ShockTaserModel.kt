package com.cemsofwave.gunsimulator.data.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.annotation.Keep
import com.cemsofwave.gunsimulator.utils.SimulatorType
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 18/10/2023
 */
@Keep
@Parcelize
data class ShockTaserModel(
    override var name: String,
    var folderName: String,
    var location: String,
    var sound: String,
    var effect: String,
    override var timestamp: Long
) : SimulatorModel(name,timestamp,SimulatorType.SHOCK_TASER), Parcelable
