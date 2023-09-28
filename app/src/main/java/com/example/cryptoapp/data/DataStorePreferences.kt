package com.example.cryptoapp.data

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStorePreferences {

    suspend fun addListener(): Flow<Preferences>

    suspend fun setTitle(title: String)

    suspend fun setAbbreviation(title: String)

    suspend fun setDarkTheme(b: Boolean)
}