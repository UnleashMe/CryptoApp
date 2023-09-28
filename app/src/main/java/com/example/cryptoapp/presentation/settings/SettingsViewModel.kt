package com.example.cryptoapp.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.DataStorePreferences
import com.example.cryptoapp.util.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(private val settings: DataStorePreferences) : ViewModel() {

    var darkThemeEnabled by mutableStateOf(false)

    init {
        viewModelScope.launch {
            settings.addListener().collectLatest { preferences ->
                val key = booleanPreferencesKey(Constants.DARK_THEME_KEY)
                preferences[key]?.let {
                    darkThemeEnabled = it
                }
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnSwitchClick -> onDarkThemeSwitchClick(event.b)
        }
    }

    private fun onDarkThemeSwitchClick(b: Boolean) = viewModelScope.launch {
        darkThemeEnabled = !darkThemeEnabled
        settings.setDarkTheme(b)
    }
}