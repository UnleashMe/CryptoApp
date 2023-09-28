package com.example.cryptoapp.presentation.navigation

import com.example.cryptoapp.util.Constants

sealed class Screen(val route: String, val title: String, val text: String) {
    object AssetList : Screen("assetList", "NameApp", "ver.01")
    object AssetInfo : Screen("assetInfo/{${Constants.ASSET_ID}}", "asset", "abbr") {
        fun passId(id: String): String {
            return "assetInfo/$id"
        }
    }

    object Settings : Screen("settings", "Settings", "")
}
