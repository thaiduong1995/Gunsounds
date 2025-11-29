package com.cemsofwave.gunsimulator.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cemsofwave.gunsimulator.data.model.SkinManager
import kotlinx.coroutines.flow.Flow

@Dao
interface SkinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSkin(skinManager: SkinManager): Long

    @Query("SELECT * FROM SkinManager WHERE nameGun=:name")
    fun getSkin(name: String): SkinManager?

    @Query("SELECT * FROM SkinManager ")
    fun getAllSkin(): Flow<List<SkinManager>>

    @Update
    fun updateSkin(skinManager: SkinManager): Int
}