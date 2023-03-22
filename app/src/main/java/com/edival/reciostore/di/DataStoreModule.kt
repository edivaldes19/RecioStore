package com.edival.reciostore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.edival.reciostore.core.Config
import com.edival.reciostore.data.dataStore.AuthDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext ctx: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(produceFile = { ctx.preferencesDataStoreFile(Config.AUTH_PREFERENCE) })
    }

    @Provides
    @Singleton
    fun provideAuthDataStore(dataStore: DataStore<Preferences>) = AuthDataStore(dataStore)
}