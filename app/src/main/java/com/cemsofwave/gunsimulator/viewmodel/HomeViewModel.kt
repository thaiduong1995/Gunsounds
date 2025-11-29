package com.cemsofwave.gunsimulator.viewmodel

import UiState
import androidx.lifecycle.viewModelScope
import com.cemsofwave.gunsimulator.data.database.DatabaseHelper
import com.cemsofwave.gunsimulator.data.model.ExplosionModel
import com.cemsofwave.gunsimulator.data.model.GunModel
import com.cemsofwave.gunsimulator.data.model.LightSaberModel
import com.cemsofwave.gunsimulator.data.model.ShockTaserModel
import com.cemsofwave.gunsimulator.data.model.SkinManager
import com.cemsofwave.gunsimulator.data.model.TimeManager
import com.cemsofwave.gunsimulator.data.repository.Repository
import com.cemsofwave.gunsimulator.utils.JsonHelper
import com.trinhbx.base.model.MutableStateLiveData
import com.trinhbx.base.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 18/10/2023
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jsonHelper: JsonHelper,
    private val databaseHelper: DatabaseHelper,
    private val repository: Repository
) : BaseViewModel() {

    val timeReload = repository.timeReloadNative
    val timeShowRate = repository.timeShowRate
    val isShowRate = repository.getIsShowRate()
    val numberShowRate = repository.getNumberShowRate()

    val gunCollection = MutableStateLiveData<List<GunModel>>()
    val gunCollectionFav = MutableStateLiveData<List<GunModel>>()
    val shockTaserCollection = MutableStateLiveData<List<ShockTaserModel>>()
    val shockTaserCollectionFav = MutableStateLiveData<List<ShockTaserModel>>()
    val lightSaberCollection = MutableStateLiveData<List<LightSaberModel>>()
    val lightSaberCollectionFav = MutableStateLiveData<List<LightSaberModel>>()
    val explosionCollection = MutableStateLiveData<List<ExplosionModel>>()
    val explosionCollectionFav = MutableStateLiveData<List<ExplosionModel>>()

    private var _saveSkinFlow = MutableStateFlow<UiState<SkinManager>>(UiState.Loading)
    val saveSkinFlow: StateFlow<UiState<SkinManager>> = _saveSkinFlow

    private var _recordAllSkinFlow = MutableStateFlow<UiState<List<SkinManager>>>(UiState.Loading)
    val recordAllSkinFlow: StateFlow<UiState<List<SkinManager>>> = _recordAllSkinFlow

    init {
        viewModelScope.launch(Dispatchers.IO){
            jsonHelper.getGunCollection().collect{listOrigin->
                gunCollection.postSuccess(listOrigin)
                launch {
                    databaseHelper.getListGun().collect {list->
                        val listFav = mutableListOf<GunModel>()
                        list.forEach {model->
                            listOrigin.find { model.name==it.name}?.let {
                                listFav.add(it)
                            }
                        }
                        gunCollectionFav.postSuccess(listFav)
                    }
                }
            }
            jsonHelper.getShockTaserCollection().collect {listOrigin->
                shockTaserCollection.postSuccess(listOrigin)
                launch {
                    databaseHelper.getListShockTaser().collect {list->
                        val listFav = mutableListOf<ShockTaserModel>()
                        list.forEach {model->
                            listOrigin.find { model.name==it.name}?.let {
                                listFav.add(it)
                            }
                        }
                        shockTaserCollectionFav.postSuccess(listFav)
                    }
                }
            }
            jsonHelper.getLightSaberCollection().collect {listOrigin->
                lightSaberCollection.postSuccess(listOrigin)
                launch {
                    databaseHelper.getListLightSaber().collect {list->
                        val listFav = mutableListOf<LightSaberModel>()
                        list.forEach {model->
                            listOrigin.find { model.name==it.name}?.let {
                                listFav.add(it)
                            }
                        }
                        lightSaberCollectionFav.postSuccess(listFav)
                    }
                }
            }
            jsonHelper.getExplosionCollection().collect {listOrigin->
                explosionCollection.postSuccess(listOrigin)
                launch {
                    databaseHelper.getListLightSaber().collect {list->
                        val listFav = mutableListOf<ExplosionModel>()
                        list.forEach {model->
                            listOrigin.find { model.name==it.name}?.let {
                                listFav.add(it)
                            }
                        }
                        explosionCollectionFav.postSuccess(listFav)
                    }
                }
            }
        }
    }

    fun insertSkin(skinManager: SkinManager){
        viewModelScope.launch(Dispatchers.IO) {
            _saveSkinFlow.emit(UiState.Loading)
            val insert = repository.insertSkin(skinManager)
            if (insert != -1L){
                _saveSkinFlow.emit(UiState.Success(skinManager))
            }else{
                _saveSkinFlow.emit(UiState.Error())
            }
        }
    }

    fun getAllSkin(){
        viewModelScope.launch(Dispatchers.IO) {
            _recordAllSkinFlow.emit(UiState.Loading)
            repository.getSkin().collect{
                _recordAllSkinFlow.emit(UiState.Success(it))
            }
        }
    }

    fun updateSkin(skinManager: SkinManager){
        viewModelScope.launch(Dispatchers.IO) {
            _saveSkinFlow.emit(UiState.Loading)
            val insert = repository.editSkin(skinManager)
            if (insert != -1){
                _saveSkinFlow.emit(UiState.Success(skinManager))
            }else{
                _saveSkinFlow.emit(UiState.Error())
            }
        }
    }

    fun setTimeReload(timeManager: TimeManager){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setTimeReloadNative(timeManager)
        }
    }

    fun setTimeShowRate(timeShowRate: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setTimeShowRate(timeShowRate)
        }
    }

    fun setIsShowRate(isShowRate: Boolean){
        repository.setIsShowRate(isShowRate)
    }

    fun setNumberShowRate(numberShowRate: Int){
        repository.setNumberShowRate(numberShowRate)
    }
}