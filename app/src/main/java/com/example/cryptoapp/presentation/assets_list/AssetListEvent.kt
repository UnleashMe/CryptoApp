package com.example.cryptoapp.presentation.assets_list

sealed class AssetListEvent {
    object LoadNextItems: AssetListEvent()
    class SetTitle(val t: String, val a: String): AssetListEvent()

}
