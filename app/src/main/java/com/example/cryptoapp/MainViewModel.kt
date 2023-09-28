package com.example.cryptoapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.DataStorePreferences
import com.example.cryptoapp.util.Constants
import kotlinx.coroutines.launch

class MainViewModel(private val settings: DataStorePreferences) : ViewModel() {

    var isDarkMode by mutableStateOf(false)

    init {
        viewModelScope.launch {
            settings.addListener().collect { preferences ->
                val key = booleanPreferencesKey(Constants.DARK_THEME_KEY)
                preferences[key]?.let {
                    isDarkMode = it
                }
            }
        }
    }
}