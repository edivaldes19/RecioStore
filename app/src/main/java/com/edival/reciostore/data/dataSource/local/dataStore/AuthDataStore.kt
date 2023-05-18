package com.edival.reciostore.data.dataSource.local.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.edival.reciostore.core.Config
import com.edival.reciostore.domain.model.AuthResponse
import com.edival.reciostore.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class AuthDataStore(private val dataStore: DataStore<Preferences>) {
    suspend fun saveUser(authResponse: AuthResponse) {
        val dataStoreKey = stringPreferencesKey(Config.AUTH_KEY)
        dataStore.edit { pref -> pref[dataStoreKey] = authResponse.toJson() }
    }

    suspend fun saveRoleName(name: String) {
        val dataStoreKey = stringPreferencesKey(Config.ROLE_KEY)
        dataStore.edit { pref -> pref[dataStoreKey] = name }
    }

    suspend fun updateUser(newUser: User) {
        val dataStoreKey = stringPreferencesKey(Config.AUTH_KEY)
        val authResponse = runBlocking { getUser().first() }
        authResponse.user?.let { oldUser ->
            oldUser.name = newUser.name
            oldUser.surname = newUser.surname
            oldUser.phone = newUser.phone
            oldUser.address = newUser.address
            if (!newUser.img.isNullOrBlank()) oldUser.img = newUser.img
        }
        dataStore.edit { pref -> pref[dataStoreKey] = authResponse.toJson() }
    }

    suspend fun logOut() {
        dataStore.edit { pref -> pref.clear() }
    }

    fun getUser(): Flow<AuthResponse> {
        val dataStoreKey = stringPreferencesKey(Config.AUTH_KEY)
        return dataStore.data.catch { emptyPreferences() }.map { pref ->
            if (pref[dataStoreKey] != null) AuthResponse.fromJson(pref[dataStoreKey]!!)
            else AuthResponse()
        }
    }

    fun getRoleName(): Flow<String?> {
        val dataStoreKey = stringPreferencesKey(Config.ROLE_KEY)
        return dataStore.data.catch { emptyPreferences() }.map { pref -> pref[dataStoreKey] }
    }
}