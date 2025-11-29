package com.cemsofwave.gunsimulator.data.database

import androidx.room.TypeConverter
import com.cemsofwave.gunsimulator.utils.SimulatorType

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 27/10/2023
 */
class Converters {
    @TypeConverter
    fun toSimulatorType(value: String) = enumValueOf<SimulatorType>(value)

    @TypeConverter
    fun fromSimulatorType(value: SimulatorType) = value.name
}