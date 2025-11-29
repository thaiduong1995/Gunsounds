package com.cemsofwave.gunsimulator.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.cemsofwave.gunsimulator.utils.SimulatorType
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ExplosionModel(
    var id: String,
    override var name: String,
    var image: String,
    var preview: String,
    var bottom: Int,
    var count: Int,
    var end: Int,
    var heightImage: Int,
    var sound: String,
    var start: Int,
    var timer: Long,
    var top: Int,
    var widthImage: Int,
    var typeBomb: String,
    override var timestamp: Long
) : SimulatorModel(name, timestamp, SimulatorType.EXPLOSION), Parcelable
