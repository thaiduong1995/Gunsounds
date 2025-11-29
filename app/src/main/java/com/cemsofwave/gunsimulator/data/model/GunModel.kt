package com.cemsofwave.gunsimulator.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cemsofwave.gunsimulator.utils.SimulatorType
import kotlinx.parcelize.Parcelize

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 18/10/2023
 */

@Entity(primaryKeys = ["name", "type"])
open class SimulatorModel(open var name:String,open var timestamp: Long,val type:SimulatorType)
@Keep
@Parcelize
data class GunModel(
    override var name: String,
    var placeOfOrigin: String,
    var inService: String,
    var designed: String,
    var length: String,
    var timeOneShot: Int,
    var bulletCount: Int,
    var headX: Int,
    var headY: Int,
    var oneShotSound: String,
    var autoShotSound: String?,
    var emptySound: String,
    var reloadSound: String,
    var reloadAnimFile: String,
    var fireFile: String,
    var bulletShell: String,
    var bulletType: String,
    var isPistol: Boolean = false,
    var listSkin: List<String>,
    var speedAutoShot: Int,
    var speedBrushShot: Int,
    override var timestamp: Long,
):SimulatorModel(name, timestamp,SimulatorType.GUN),Parcelable