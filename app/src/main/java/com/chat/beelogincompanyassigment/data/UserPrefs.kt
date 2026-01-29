package com.chat.beelogincompanyassigment.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore("user_prefs")


class UserPrefs(private val context: Context) {


    companion object {
        val KEY_LOGGED_IN = booleanPreferencesKey("logged_in")
    }


    val isLoggedIn = context.dataStore.data.map {
        it[KEY_LOGGED_IN] ?: false
    }


    suspend fun setLoggedIn(value: Boolean) {
        context.dataStore.edit {
            it[KEY_LOGGED_IN] = value
        }
    }
}