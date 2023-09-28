package com.example.cryptoapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.cryptoapp.util.Constants
import kotlinx.coroutines.flow.Flow

class DataStorePreferencesImpl(private val context: Context) : DataStorePreferences {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore("data_store")

    override suspend fun addListener(): Flow<Preferences> {
        return context.datastore.data
    }

    override suspend fun setTitle(title: String) {
        val key = stringPreferencesKey(Constants.TITLE_KEY)
        context.datastore.edit {
            it[key] = title
        }
    }

    override suspend fun setAbbreviation(title: String) {
        val key = stringPreferencesKey(Constants.ABBR_KEY)
        context.datastore.edit {
            it[key] = title
        }
    }

    override suspend fun setDarkTheme(b: Boolean) {
        val key = booleanPreferencesKey(Constants.DARK_THEME_KEY)
        context.datastore.edit {
            it[key] = b
        }
    }
}