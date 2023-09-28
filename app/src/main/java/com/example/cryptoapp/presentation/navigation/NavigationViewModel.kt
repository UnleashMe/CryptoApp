package com.example.cryptoapp.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.DataStorePreferences
import com.example.cryptoapp.util.Constants
import kotlinx.coroutines.launch

class NavigationViewModel(
    dataStore: DataStorePreferences
) : ViewModel() {

    var title by mutableStateOf("")
    var abbreviation by mutableStateOf("")

    init {
        viewModelScope.launch {
            dataStore.addListener().collect { preferences ->
                val titleKey = stringPreferencesKey(Constants.TITLE_KEY)
                val abbrKey = stringPreferencesKey(Constants.ABBR_KEY)
                preferences[titleKey]?.let {
                    title = it
                }
                preferences[abbrKey]?.let {
                    abbreviation = it
                }
            }
        }
    }
}