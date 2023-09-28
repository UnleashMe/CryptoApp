package com.example.cryptoapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptoapp.presentation.asset_info.AssetInfoScreen
import com.example.cryptoapp.presentation.assets_list.AssetListScreen
import com.example.cryptoapp.presentation.settings.SettingsScreen
import com.example.cryptoapp.util.Constants

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(navController = navController, startDestination = Screen.AssetList.route) {
        composable(route = Screen.AssetList.route) {
            AssetListScreen(paddingValues = paddingValues, navController = navController)
        }
        composable(route = Screen.AssetInfo.route) {
            val id = it.arguments?.getString(Constants.ASSET_ID)
            AssetInfoScreen(paddingValues = paddingValues, id = id ?: "blank")
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(paddingValues)
        }
    }
}