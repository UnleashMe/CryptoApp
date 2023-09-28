package com.example.cryptoapp.presentation.settings

sealed class SettingsEvent {
    class OnSwitchClick(val b: Boolean): SettingsEvent()
}
