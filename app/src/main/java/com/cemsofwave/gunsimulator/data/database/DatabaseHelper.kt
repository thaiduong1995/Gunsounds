package com.cemsofwave.gunsimulator.data.database

import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.utils.SimulatorType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 27/10/2023
 */

@Singleton
class DatabaseHelper @Inject constructor(database: AppDatabase) {
    private val simulatorDao = database.simulatorDao()

    fun likeSimulator(simulatorModel: SimulatorModel,isLike:Boolean){
        if(isLike){
            val entity = simulatorDao.getSimulator(simulatorModel.name,simulatorModel.type)
            if(entity==null){
                simulatorDao.insertSimulator(simulatorModel.apply {
                    timestamp = System.currentTimeMillis()
                })
            } else{
                simulatorDao.updateSimulator(simulatorModel.apply {
                    timestamp = System.currentTimeMillis()
                })
            }

        } else {
            simulatorDao.updateSimulator(simulatorModel.apply {
                timestamp = -1
            })
        }
    }

    fun isSimulatorLiked(simulatorModel: SimulatorModel):Boolean {
        val entity = simulatorDao.getSimulator(simulatorModel.name,simulatorModel.type)
        return if(entity==null) false else {
            entity.timestamp>0
        }
    }

    fun getListGun(): Flow<List<SimulatorModel>> {
        return simulatorDao.getSimulatorsByType(SimulatorType.GUN)
    }

    fun getListShockTaser(): Flow<List<SimulatorModel>> {
        return simulatorDao.getSimulatorsByType(SimulatorType.SHOCK_TASER)
    }

    fun getListLightSaber(): Flow<List<SimulatorModel>> {
        return simulatorDao.getSimulatorsByType(SimulatorType.LIGHT_SABER)
    }

    fun getListExplosion(): Flow<List<SimulatorModel>> {
        return simulatorDao.getSimulatorsByType(SimulatorType.EXPLOSION)
    }
}