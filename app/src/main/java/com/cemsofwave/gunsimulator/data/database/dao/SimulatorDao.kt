package com.cemsofwave.gunsimulator.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.utils.SimulatorType
import kotlinx.coroutines.flow.Flow

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 27/10/2023
 */
@Dao
interface SimulatorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSimulator(simulatorModel: SimulatorModel)

//    @Query("DELETE FROM SimulatorModel WHERE id=:id")
//    fun deleteSimulator(id: Long)

    @Delete
    fun deleteSimulator(simulatorModel: SimulatorModel)

    @Query("SELECT * FROM SimulatorModel WHERE type =:simulatorType AND timestamp> 0 ORDER BY timestamp DESC")
    fun getSimulatorsByType(simulatorType: SimulatorType): Flow<List<SimulatorModel>>

    @Query("SELECT * FROM SimulatorModel WHERE name=:name AND type=:type")
    fun getSimulator(name: String,type: SimulatorType): SimulatorModel?

    @Query("DELETE FROM SimulatorModel")
    fun reset()

    @Update
    fun updateSimulator(simulatorModel: SimulatorModel)
}