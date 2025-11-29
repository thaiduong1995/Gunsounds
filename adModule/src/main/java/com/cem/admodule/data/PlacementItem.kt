package com.cem.admodule.data

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class PlacementItem(
    @Json(name = "ads_unit") var adsUnit: String? = null,
    @Json(name = "enable") var enable: Boolean = true,
    @Json(name = "position") val position: String? = null,
    @Json(name = "collapsible") val collapsible: Boolean = true,
    @Json(name = "show_direct") val showDirect: Boolean = true,
)
