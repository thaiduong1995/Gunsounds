package com.cemsofwave.gunsimulator.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cemsofwave.gunsimulator.data.database.dao.SimulatorDao
import com.cemsofwave.gunsimulator.data.database.dao.SkinDao
import com.cemsofwave.gunsimulator.data.model.SimulatorModel
import com.cemsofwave.gunsimulator.data.model.SkinManager

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 27/10/2023
 */

@Database(
    entities = [SimulatorModel::class, SkinManager::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase :RoomDatabase(){
    companion object {
        private const val DATABASE_NAME = "GUN_SIMULATOR"
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
    abstract fun simulatorDao(): SimulatorDao
    abstract fun skinDao(): SkinDao
}