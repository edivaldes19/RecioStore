package com.edival.reciostore.data.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.AuthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class AuthDataStore constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveUser(authResponse: AuthResponse) {
        val dataStoreKey = stringPreferencesKey(Config.AUTH_KEY)
        dataStore.edit { pref -> pref[dataStoreKey] = authResponse.toJson() }
    }

    fun getUser(): Flow<AuthResponse> {
        val dataStoreKey = stringPreferencesKey(Config.AUTH_KEY)
        return dataStore.data.catch { emptyPreferences() }.map { pref ->
            if (pref[dataStoreKey] != null) AuthResponse.fromJson(pref[dataStoreKey]!!)
            else AuthResponse()
        }
    }
}