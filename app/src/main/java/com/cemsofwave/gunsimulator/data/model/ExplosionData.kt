package com.cemsofwave.gunsimulator.data.model

import androidx.annotation.Keep

@Keep
data class ExplosionData(
    var id: String,
    var name: String,
    var image: String,
    var preview: String,
    var bottom: Int,
    var count: Int,
    var end: Int,
    var height_image: Int,
    var sound: String,
    var start: Int,
    var timer: Long,
    var top: Int,
    var width_image: Int,
    var typeBomb: String,
    var timestamp: Long
)
