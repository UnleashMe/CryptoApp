package com.example.cryptoapp.presentation.asset_info

sealed class AssetInfoEvent {
    class PassId(val id: String): AssetInfoEvent()
}