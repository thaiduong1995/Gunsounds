package com.cemsofwave.gunsimulator.interfaces

import com.cemsofwave.gunsimulator.data.model.SimulatorModel

interface MyFragmentCallback {
    fun onBulletsRequestListener(isReload : Boolean)
    fun onGunRequestListener(simulatorModel: SimulatorModel)
}