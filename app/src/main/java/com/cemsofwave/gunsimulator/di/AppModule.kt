package com.cemsofwave.gunsimulator.di

import android.content.Context
import com.cemsofwave.gunsimulator.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author
 * Created by Trinh BX.
 * Mail: trinhbx196@gmail.com.
 * Phone: +084 988 324 622.
 * @since 27/10/2023
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        AppDatabase.getInstance(context)
}